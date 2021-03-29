package scripts.gui.save_ui;

import com.allatori.annotations.DoNotRename;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import scripts.gui.Controller;
import scripts.gui.GUI;
import scripts.gui.main_ui.GUIController;
import scripts.task.ProgressiveTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SaveUIController implements Initializable, Controller {
    private GUI gui;
    private GUI parentGui;
    private GUIController parentController;

    @DoNotRename
    @FXML
    public TextField profileNameTextField;

    @DoNotRename
    @FXML
    public void saveButtonClicked() {
        if (profileNameTextField.getText().length() > 0) {
            File saveFile = new File(parentController.PATH_TO_BBUU20_FOLDER + "/" + profileNameTextField.getText() + ".json");
            if (saveFile.exists()) {
                //TODO: Inform the user that the save file already exists
                return;
            }
            try {
                saveFile.createNewFile();
                FileWriter saveFileWriter = new FileWriter(saveFile);
                new Gson().toJson(parentController.taskListView.getItems().toArray(ProgressiveTask[]::new), saveFileWriter);
                saveFileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            parentController.profileListView.getItems().add(saveFile);
            parentController.changeButtonStatus(false);
            gui.getParentGui().setChildGui(null);
            gui.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void delayed() {
    }

    @Override
    public void onClose() {
        parentController.changeButtonStatus(false);
        gui.getParentGui().setChildGui(null);
        gui.close();
    }

    @Override
    public void setGui(GUI gui) {
        this.gui = gui;
    }

    @Override
    public GUI getGui() {
        return gui;
    }

    @Override
    public void setParentGui(GUI parentGui) {
        this.parentGui = parentGui;
        parentController = (GUIController) parentGui.getController();
    }

    @Override
    public GUI getParentGui() {
        return parentGui;
    }
}
