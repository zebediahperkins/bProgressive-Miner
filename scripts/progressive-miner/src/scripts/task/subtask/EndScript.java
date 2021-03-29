package scripts.task.subtask;

import org.tribot.api.General;
import org.tribot.api2007.Inventory;
import scripts.data.FullInventoryAction;
import scripts.data.UIData;
import scripts.task.Task;

public class EndScript implements Task {
    @Override
    public boolean shouldCarryOutTask() {
        return Inventory.isFull() && UIData.lastRecordedTask.getFullInventoryAction() == FullInventoryAction.END_SCRIPT;
    }

    @Override
    public boolean carryOutTask() {
        General.println("Ending script: Inventory is full");
        return false;
    }
}
