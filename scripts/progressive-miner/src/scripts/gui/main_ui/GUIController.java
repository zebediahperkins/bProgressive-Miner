package scripts.gui.main_ui;

import com.allatori.annotations.DoNotRename;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import org.tribot.util.Util;
import scripts.data.UIData;
import scripts.gui.Controller;
import scripts.gui.GUI;
import scripts.gui.save_ui.SaveUIFXML;
import scripts.gui.task_ui.TaskUIFXML;
import scripts.task.ProgressiveTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

@DoNotRename
public class GUIController implements Initializable, Controller {
    private GUI gui;
    public final String PATH_TO_BBUU20_FOLDER = Util.getWorkingDirectory().getAbsolutePath() + "/bbuu20/miner";

    /* Mining Tab */

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
    public void addTaskButtonPressed() {
        changeButtonStatus(true);
        new Thread(() -> {
            GUI child = new GUI(TaskUIFXML.get, "Task Builder", gui);
            gui.setChildGui(child);
            child.show();
        }).start();
    }

    @DoNotRename
    @FXML
    public void removeTaskButtonPressed() {
        ProgressiveTask task = taskListView.getSelectionModel().getSelectedItem();
        if (task != null)
            taskListView.getItems().remove(task);
    }

    /* Profile Tab */

    @DoNotRename
    @FXML
    public ListView<File> profileListView;

    @DoNotRename
    @FXML
    public void loadProfileButtonPressed() {
        File profile = profileListView.getSelectionModel().getSelectedItem();
        if (profile != null) {
            try {
                ProgressiveTask[] tasks = new Gson()
                        .fromJson(
                                new FileReader(profile),
                                ProgressiveTask[].class
                        );
                taskListView.getItems().clear();
                taskListView.getItems().addAll(tasks);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @DoNotRename
    @FXML
    public void deleteProfileButtonPressed() {
        File profile = profileListView.getSelectionModel().getSelectedItem();
        if (profile != null) {
            profile.delete();
            profileListView.getItems().remove(profile);
        }
    }

    /* Toolbar/Footer */

    @DoNotRename
    @FXML
    public void saveProfileButtonPressed() {
        changeButtonStatus(true);
        new Thread(() -> {
            GUI child = new GUI(SaveUIFXML.get, "Save as", gui);
            gui.setChildGui(child);
            child.show();
        }).start();
    }

    @DoNotRename
    @FXML
    public void startButtonPressed() {
        if (taskListView.getItems().size() > 0) {
            UIData.shouldPaint = drawPaintBox.isSelected();
            UIData.progressiveTasks = taskListView.getItems().toArray(ProgressiveTask[]::new);
            gui.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        File pathToProfiles = new File(PATH_TO_BBUU20_FOLDER);
        pathToProfiles.mkdirs();
        profileListView.getItems().addAll(Arrays
                .stream(Objects.requireNonNull(pathToProfiles.listFiles()))
                .filter(file -> file.getName().contains(".json"))
                .toArray(File[]::new)
        );
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
        this.gui = gui;
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
