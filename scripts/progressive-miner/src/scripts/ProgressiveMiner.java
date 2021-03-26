package scripts;

import org.tribot.api2007.Player;
import org.tribot.api2007.Projection;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.EventBlockingOverride;
import org.tribot.script.interfaces.Painting;
import scripts.dax_api.api_lib.DaxWalker;
import scripts.dax_api.api_lib.models.DaxCredentials;
import scripts.gui.MinerGUI;
import scripts.gui.main_ui.GUIFXML;
import scripts.gui.rock_ui.RockUIController;
import scripts.helpers.ProjectionHelper;
import scripts.task.Task;
import scripts.task.TaskHandler;
import scripts.task.subtask.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

@ScriptManifest(
        name = "bProgressive-Miner",
        authors = "bbuu20",
        category = "Mining",
        description = "New and improved free/open source customizable progressive mining script."
)

public class ProgressiveMiner extends Script implements TaskHandler, Painting, EventBlockingOverride {
    private final MinerGUI mainUI = new MinerGUI(GUIFXML.get, "bProgressive-Miner");

    @Override
    public void run() {
        DaxWalker.setCredentials(() ->
                new DaxCredentials("sub_DPjXXzL5DeSiPf", "PUBLIC-KEY")
        );
        Task[] taskList = initTasks(
                new StartNewTask(),
                new Bank(),
                new Drop(),
                new EndScript(),
                new Mine()
        );
        if (mainUI.show()) {
            while (true) {
                if (!handleTasks(taskList))
                    break;
            }
        }
    }

    @Override
    public void onPaint(Graphics graphics) {
        if (MinerGUI.shouldPaint && mainUI.isOpen()) {
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
        } else if (MinerGUI.shouldPaint) {
            ; //TODO: Add Paint
        }
    }

    @Override
    public OVERRIDE_RETURN overrideKeyEvent(KeyEvent keyEvent) {
        return OVERRIDE_RETURN.DISMISS;
    }

    @Override
    public OVERRIDE_RETURN overrideMouseEvent(MouseEvent mouseEvent) {
        if (!mainUI.isOpen())
            return OVERRIDE_RETURN.SEND; //TODO: Change to delete
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
}