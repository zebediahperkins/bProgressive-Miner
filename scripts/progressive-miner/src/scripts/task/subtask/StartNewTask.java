package scripts.task.subtask;

import org.tribot.api.General;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.Inventory;
import scripts.data.UIData;
import scripts.dax_api.api_lib.DaxWalker;
import scripts.dax_api.shared.helpers.BankHelper;
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
        if (Inventory.getCount(currentTask.getPickaxe().id) == 0 && !Equipment.isEquipped(currentTask.getPickaxe().id)) { //TODO: Check if we need a new pickaxe
            if (!BankHelper.isInBank()) //TODO: If not in bank, walk to bank
                DaxWalker.walkToBank();
            else if (!Banking.isBankScreenOpen()) //TODO: If in bank but not in interface, talk to banker
                Banking.openBank();
            else if (Inventory.getAll().length > 0) //TODO: If in interface, deposit everything
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
