package scripts.gui.rock_ui;

import com.allatori.annotations.DoNotRename;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.tribot.api.General;
import org.tribot.api2007.types.RSTile;
import scripts.data.UIData;
import scripts.gui.Controller;
import scripts.gui.GUI;
import scripts.gui.task_ui.TaskUIController;

import java.net.URL;
import java.util.ResourceBundle;

public class RockUIController implements Initializable, Controller {
    private GUI gui;
    private GUI parentGui;
    private TaskUIController parentController;

    @DoNotRename
    @FXML
    public TextField tileXTextField;
    @DoNotRename
    @FXML
    public TextField tileYTextField;
    @DoNotRename
    @FXML
    public TextField tileZTextField;

    @DoNotRename
    @FXML
    public void addButtonClicked() {
        if (tileXTextField.getText().length() > 0
                && tileYTextField.getText().length() > 0
                && tileZTextField.getText().length() > 0) {
            parentController.rockListView.getItems().add(new RSTile(
                    Integer.parseInt(tileXTextField.getText()),
                    Integer.parseInt(tileYTextField.getText()),
                    Integer.parseInt(tileZTextField.getText())
            ));
            UIData.shouldPaint = false;
            parentController.changeButtonStatus(false);
            parentGui.setChildGui(null);
            gui.close();
        } else {
            General.println("Error: Make sure all fields contain a number");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UIData.shouldPaint = true;
    }

    @Override
    public void delayed() {
    }

    @Override
    public void onClose() {
        parentController.changeButtonStatus(false);
        UIData.shouldPaint = false;
        parentGui.setChildGui(null);
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
        parentController = (TaskUIController) parentGui.getController();
    }

    @Override
    public GUI getParentGui() {
        return parentGui;
    }
}
