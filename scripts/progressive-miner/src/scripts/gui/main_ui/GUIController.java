package scripts.gui.main_ui;

import com.allatori.annotations.DoNotRename;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import scripts.data.UIData;
import scripts.gui.Controller;
import scripts.gui.GUI;
import scripts.gui.MinerGUI;
import scripts.gui.task_ui.TaskUIFXML;
import scripts.task.ProgressiveTask;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIController implements Initializable, Controller {
    private MinerGUI gui;

    /**
     * Mining Tab
     **/
    @DoNotRename
    @FXML
    public ListView<ProgressiveTask> taskListView;
    @DoNotRename
    @FXML
    public Button addTaskButton;
    @DoNotRename
    @FXML
    public Button removeTaskButton;
    @DoNotRename
    @FXML
    public Button startButton;
    @DoNotRename
    @FXML
    public CheckBox drawPaintBox;

    @DoNotRename
    @FXML
    public void addTaskButtonPressed(ActionEvent actionEvent) {
        changeButtonStatus(true);
        new Thread(() -> {
            MinerGUI child = new MinerGUI(TaskUIFXML.get, "Task Builder", gui);
            gui.setChildGui(child);
            child.show();
        }).start();
    }

    @DoNotRename
    @FXML
    public void removeTaskButtonPressed(ActionEvent actionEvent) {
        ProgressiveTask task = taskListView.getSelectionModel().getSelectedItem();
        if (task != null)
            taskListView.getItems().remove(task);
    }

    /**
     * Toolbar/Footer
     **/
    @DoNotRename
    @FXML
    public void startButtonPressed(ActionEvent actionEvent) {
        if (taskListView.getItems().size() > 0) {
            UIData.progressiveTasks = taskListView.getItems().toArray(ProgressiveTask[]::new);
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
        gui.forceClose();
    }

    @Override
    public void setGui(GUI gui) {
        this.gui = (MinerGUI) gui;
    }

    @Override
    public GUI getGui() {
        return gui;
    }

    @Override
    public void setParentGui(GUI parentGui) {
    }

    @Override
    public GUI getParentGui() {
        return null;
    }

    public void addTaskToListView(ProgressiveTask task) {
        taskListView.getItems().add(task);
    }

    public void changeButtonStatus(boolean disabled) {
        addTaskButton.setDisable(disabled);
        removeTaskButton.setDisable(disabled);
        startButton.setDisable(disabled);
    }
}
