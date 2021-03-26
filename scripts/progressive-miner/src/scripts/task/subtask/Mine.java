package scripts.task.subtask;

import org.tribot.api.General;
import scripts.data.UIData;
import scripts.task.Task;

public class Mine implements Task {
    @Override
    public boolean shouldCarryOutTask() {
        return UIData.lastRecordedTask != null;
    }

    @Override
    public boolean carryOutTask() {
        General.println("Mining");
        //TODO: Check if in proximity of any rocktiles
        //TODO: If not, walk to the closest one
        //TODO: Start mining until full inventory
        General.sleep(300); //TODO: Delete
        return true;
    }
}
