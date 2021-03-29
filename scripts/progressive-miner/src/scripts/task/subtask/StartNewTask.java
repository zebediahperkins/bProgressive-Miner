package scripts.task.subtask;

import org.tribot.api.General;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Inventory;
import scripts.data.UIData;
import scripts.interacting.Walking;
import scripts.task.ProgressiveTask;
import scripts.task.Task;

public class StartNewTask implements Task {
    @Override
    public boolean shouldCarryOutTask() {
        return UIData.lastRecordedTask == null || !UIData.lastRecordedTask.equals(UIData.getCurrentProgressiveTask());
    }

    @Override
    public boolean carryOutTask() {
        ProgressiveTask currentTask = UIData.getCurrentProgressiveTask();
        if (currentTask == null)
            return false;
        if (Inventory.getCount(currentTask.getPickaxe().id) == 0 && !Equipment.isEquipped(currentTask.getPickaxe().id)) {
            if (!Walking.getInstance().isInBank())
                Walking.getInstance().walkToBank();
            else if (!Banking.isBankScreenOpen())
                Banking.openBank();
            else if (Inventory.getAll().length > 0)
                Banking.depositAll();
            else {
                if (!Banking.withdraw(1, UIData.getCurrentProgressiveTask().getPickaxe().id)) {
                    General.println("Ending script: " + UIData.getCurrentProgressiveTask().getPickaxe() + " pickaxe is not in bank");
                    return false;
                }
                Banking.close();
            }
        }
        else
            UIData.lastRecordedTask = currentTask;
        return true;
    }
}
