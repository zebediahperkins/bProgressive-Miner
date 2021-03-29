package scripts.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tribot.api.General;
import org.tribot.api.Timing;

import javax.swing.*;
import java.io.ByteArrayInputStream;

public class GUI extends Application {
    private Controller controller;
    private Stage stage;
    private Scene scene;
    private boolean isOpen = false;
    private boolean forceClosed = false;
    private final String fxml;
    private final String title;
    private final GUI parentGui;
    private GUI childGui;

    public GUI(String fxml, String title, GUI parentGui) {
        this.fxml = fxml;
        this.title = title;
        this.parentGui = parentGui;
        this.childGui = null;
        SwingUtilities.invokeLater(() -> {
            new JFXPanel();
            Platform.runLater(() -> {
                try {
                    final Stage stage = new Stage();
                    start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        });
        waitForInit();
    }

    public GUI(String fxml, String title) {
        this(fxml, title, null);
    }

    public void setChildGui(GUI childGui) {
        this.childGui = childGui;
    }

    public GUI getChildGui() {
        return childGui;
    }

    public GUI getParentGui() {
        return parentGui;
    }

    public Scene getScene() {
        return this.scene;
    }

    public Stage getStage() {
        return this.stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle(title);
        stage.setAlwaysOnTop(false);
        Platform.setImplicitExit(false);
        FXMLLoader loader = new FXMLLoader();
        loader.setClassLoader(this.getClass().getClassLoader());
        Parent box = loader.load(new ByteArrayInputStream(fxml.getBytes()));
        controller = loader.getController();
        controller.setGui(this);
        controller.setParentGui(parentGui);
        scene = new Scene(box);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> controller.onClose());
        controller.delayed();
    }

    public Controller getController() {
        return this.controller;
    }

    public boolean show() {
        if (stage == null)
            return false;
        isOpen = true;
        Platform.runLater(() -> stage.show());
        while (isOpen) {
            if (forceClosed)
                return false;
            General.sleep(500);
        }
        return true;
    }

    public void close() {
        isOpen = false;
        if (stage == null)
            return;
        Platform.runLater(() -> stage.close());
    }

    public void forceClose() {
        forceClosed = true;
    }

    public boolean isOpen() {
        return isOpen;
    }

    private void waitForInit() {
        Timing.waitCondition(() -> stage != null, 50000);
    }
}