package scripts;

import org.tribot.api.Timing;
import org.tribot.api2007.Player;
import org.tribot.api2007.Projection;
import org.tribot.api2007.Skills;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.EventBlockingOverride;
import org.tribot.script.interfaces.Painting;
import scripts.data.UIData;
import scripts.gui.GUI;
import scripts.gui.main_ui.GUIFXML;
import scripts.gui.rock_ui.RockUIController;
import scripts.helpers.InventoryHelper;
import scripts.helpers.ProjectionHelper;
import scripts.task.Task;
import scripts.task.TaskHandler;
import scripts.task.subtask.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

@ScriptManifest(
        name = "bProgressive-Miner",
        authors = "bbuu20",
        category = "Mining",
        description = "New and improved free/open source customizable progressive mining script."
)

public class ProgressiveMiner extends Script implements TaskHandler, Painting, EventBlockingOverride {
    private final GUI mainUI = new GUI(GUIFXML.get, "bProgressive-Miner");
    public static RSObject[] availableRocks;
    public static RSObject target;
    public static RSObject nextTarget;
    private Image paintImage;
    private long startTime;
    private long startXp;
    private int gpMade = 0;
    private int inventoryValue = InventoryHelper.getInventoryValue();
    private final Font font = new Font("Courier", Font.BOLD, 16);
    private final Font loadingBar = new Font("Courier", Font.BOLD, 28);

    @Override
    public void run() {
        Task[] taskList = initTasks(
                new StartNewTask(),
                new Bank(),
                new Drop(),
                new EndScript(),
                new Mine()
        );
        if (mainUI.show()) {
            try {
                paintImage = ImageIO.read(new URL("https://i.imgur.com/A99NFsC.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            startXp = Skills.SKILLS.MINING.getXP();
            startTime = System.currentTimeMillis();
            while (handleTasks(taskList)) {
                int tempVal = InventoryHelper.getInventoryValue();
                if (tempVal > inventoryValue) {
                    gpMade += tempVal - inventoryValue;
                }
                inventoryValue = tempVal;
            }
        }
    }

    @Override
    public OVERRIDE_RETURN overrideKeyEvent(KeyEvent keyEvent) {
        return OVERRIDE_RETURN.DISMISS;
    }

    @Override
    public OVERRIDE_RETURN overrideMouseEvent(MouseEvent mouseEvent) {
        if (!mainUI.isOpen())
            return OVERRIDE_RETURN.DISMISS;
        else if (mouseEvent.getButton() == 1 && mouseEvent.paramString().contains("PRESSED")) {
            RSTile clickedTile = ProjectionHelper.screenToTile(mouseEvent.getPoint());
            if (mainUI.getChildGui() != null
                    && mainUI.getChildGui().getChildGui() != null
                    && clickedTile != null) {
                RockUIController rockUIController = (RockUIController) mainUI.getChildGui().getChildGui().getController();
                rockUIController.tileXTextField.setText(String.valueOf(clickedTile.getX()));
                rockUIController.tileYTextField.setText(String.valueOf(clickedTile.getY()));
                rockUIController.tileZTextField.setText("0");
            }
            return OVERRIDE_RETURN.DISMISS;
        }
        return OVERRIDE_RETURN.SEND;
    }

    @Override
    public void onPaint(Graphics graphics) {
        if (UIData.shouldPaint && mainUI.isOpen()) {
            graphics.setColor(Color.GREEN);
            RSArea nearbyArea = new RSArea(
                    new RSTile(Player.getPosition().getX() - 9, Player.getPosition().getY() - 9, 0),
                    new RSTile(Player.getPosition().getX() + 9, Player.getPosition().getY() + 9, 0)
            );
            for (RSTile tile : nearbyArea.getAllTiles()) {
                if (tile.isOnScreen()) {
                    graphics.drawPolygon(Projection.getTileBoundsPoly(tile, 0));
                }
            }
            if (mainUI.getChildGui() != null && mainUI.getChildGui().getChildGui() != null) {
                RockUIController rockUIController = (RockUIController) mainUI.getChildGui().getChildGui().getController();
                if (rockUIController.tileXTextField.getText().length() > 0
                        && rockUIController.tileYTextField.getText().length() > 0
                        && rockUIController.tileZTextField.getText().length() > 0) {
                    int tileX = Integer.parseInt(rockUIController.tileXTextField.getText());
                    int tileY = Integer.parseInt(rockUIController.tileYTextField.getText());
                    int tileZ = Integer.parseInt(rockUIController.tileZTextField.getText());
                    graphics.fillPolygon(Projection.getTileBoundsPoly(new RSTile(tileX, tileY, tileZ), 0));
                }
            }
        } else if (UIData.shouldPaint) {
            if (paintImage != null && Skills.SKILLS.MINING.getXP() - startXp > 0) {
                long timeRan = System.currentTimeMillis() - startTime;
                long xpToLvl = Skills.getXPToNextLevel(Skills.SKILLS.MINING);
                double xpHr = (double)Math.round(((Skills.SKILLS.MINING.getXP() - startXp) / (timeRan / 3600.0)) * 1000d) / 1000d;
                double gpHr = (double)Math.round((gpMade / (timeRan / 3600.0)) * 1000d) / 1000d;
                graphics.setFont(font);
                graphics.drawImage(paintImage, 25, 25, null);
                graphics.setColor(Color.BLACK);
                graphics.drawString(Timing.msToString(timeRan), 125, 77);
                graphics.drawString(String.valueOf(Skills.SKILLS.MINING.getActualLevel()), 125, 96);
                graphics.drawString(String.valueOf(Skills.SKILLS.MINING.getXP() - startXp), 125, 115);
                graphics.drawString(String.valueOf(gpMade), 125, 133);
                graphics.drawString(xpHr + "k", 125, 152);
                graphics.drawString(gpHr + "k", 125, 170);
                graphics.drawString(String.valueOf(xpToLvl), 125, 191);
                graphics.drawString(Timing.msToString((long)((xpToLvl / xpHr) * 3600)), 125, 210);
                graphics.setColor(Color.BLUE);
                graphics.setFont(loadingBar);
                graphics.drawString(getXpBar(), 29, 245);
            }
            if (availableRocks != null) {
                for (RSObject rock : availableRocks) {
                    if (rock.equals(target))
                        graphics.setColor(Color.GREEN);
                    else if (rock.equals(nextTarget))
                        graphics.setColor(Color.YELLOW);
                    else
                        graphics.setColor(Color.BLUE);
                    Polygon[] tris = rock.getModel().getTriangles();
                    for (Polygon tri : tris)
                        graphics.drawPolygon(tri);
                }
            }
        }
    }

    private String getXpBar() {
        String xpBar = "||||||||||||";
        int offset = Skills.getPercentToNextLevel(Skills.SKILLS.MINING);
        for (int i = 100; i > offset; i -= 9) {
            xpBar = xpBar.substring(0, xpBar.length() -1);
        }
        return xpBar;
    }
}