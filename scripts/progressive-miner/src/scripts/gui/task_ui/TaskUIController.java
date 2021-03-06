package scripts.gui.task_ui;

import com.allatori.annotations.DoNotRename;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.tribot.api2007.types.RSTile;
import scripts.data.FullInventoryAction;
import scripts.data.Pickaxe;
import scripts.gui.Controller;
import scripts.gui.GUI;
import scripts.gui.main_ui.GUIController;
import scripts.gui.rock_ui.RockUIFXML;
import scripts.task.ProgressiveTask;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@DoNotRename
public class TaskUIController implements Initializable, Controller {
    private GUI gui;
    private GUI parentGui;
    private GUIController mainUIController;

    @DoNotRename
    @FXML
    public ListView<RSTile> rockListView;
    @DoNotRename
    @FXML
    public Spinner<Integer> lowLevelSpinner;
    @DoNotRename
    @FXML
    public Spinner<Integer> highLevelSpinner;
    @DoNotRename
    @FXML
    public ChoiceBox<FullInventoryAction> fullInvBox;
    @DoNotRename
    @FXML
    public ChoiceBox<Pickaxe> prefPickBox;
    @DoNotRename
    @FXML
    public CheckBox hopWorldsCheck;
    @DoNotRename
    @FXML
    public CheckBox membersCheck;
    @DoNotRename
    @FXML
    public CheckBox pvpCheck;
    @DoNotRename
    @FXML
    public CheckBox antiPkCheck;
    @DoNotRename
    @FXML
    public Button addRockButton;
    @DoNotRename
    @FXML
    public Button removeRockButton;
    @DoNotRename
    @FXML
    public Button addButton;

    @DoNotRename
    @FXML
    public void addRockButtonPressed() {
        changeButtonStatus(true);
        new Thread(() -> {
            GUI child = new GUI(RockUIFXML.get, "Tile Selector", gui);
            gui.setChildGui(child);
            child.show();
        }).start();
    }

    @DoNotRename
    @FXML
    public void removeRockButtonPressed() {
        RSTile tile = rockListView.getSelectionModel().getSelectedItem();
        if (tile != null)
            rockListView.getItems().remove(tile);
    }

    @DoNotRename
    @FXML
    public void addButtonPressed() {
        if (highLevelSpinner.getValue() <= lowLevelSpinner.getValue())
            highLevelSpinner.getValueFactory().setValue(lowLevelSpinner.getValue() + 1);
        mainUIController.addTaskToListView(new ProgressiveTask(
                rockListView.getItems().toArray(RSTile[]::new),
                lowLevelSpinner.getValue(),
                highLevelSpinner.getValue(),
                fullInvBox.getValue(),
                prefPickBox.getValue(),
                hopWorldsCheck.isSelected(),
                pvpCheck.isSelected(),
                membersCheck.isSelected(),
                antiPkCheck.isSelected()
        ));
        mainUIController.changeButtonStatus(false);
        parentGui.setChildGui(null);
        gui.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lowLevelSpinner.setEditable(true);
        highLevelSpinner.setEditable(true);
        fullInvBox.getItems().addAll(FullInventoryAction.values());
        fullInvBox.getSelectionModel().select(0);
        prefPickBox.getItems().addAll(Pickaxe.values());
        prefPickBox.getSelectionModel().select(0);
    }

    @Override
    public void delayed() {
        List<ProgressiveTask> taskList = mainUIController.taskListView.getItems();
        if (taskList.size() > 0) {
            int lowVal = taskList.get(taskList.size() - 1).getHighLevel();
            lowLevelSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, lowVal));
            lowLevelSpinner.setDisable(true);
            highLevelSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(lowVal + 1, 99, lowVal + 1));
        } else {
            lowLevelSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 98, 1));
            highLevelSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 99, 2));
        }
        lowLevelSpinner.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                lowLevelSpinner.increment(0);
            }
        });
        highLevelSpinner.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                highLevelSpinner.increment(0);
            }
        });
    }

    @Override
    public void onClose() {
        mainUIController.changeButtonStatus(false);
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
        mainUIController = (GUIController) parentGui.getController();
    }

    @Override
    public GUI getParentGui() {
        return parentGui;
    }

    public void changeButtonStatus(boolean disabled) {
        addButton.setDisable(disabled);
        addRockButton.setDisable(disabled);
        removeRockButton.setDisable(disabled);
    }
}
