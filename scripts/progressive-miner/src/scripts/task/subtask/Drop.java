package scripts.task.subtask;

import org.tribot.api2007.Inventory;
import scripts.data.FullInventoryAction;
import scripts.data.UIData;
import scripts.task.Task;

public class Drop implements Task {
    @Override
    public boolean shouldCarryOutTask() {
        return Inventory.isFull() && UIData.lastRecordedTask.getFullInventoryAction() == FullInventoryAction.DROP;
    }

    @Override
    public boolean carryOutTask() {
        Inventory.dropAllExcept(UIData.lastRecordedTask.getPickaxe().id); //TODO: Drop all except the pickaxe
        return true;
    }
}
