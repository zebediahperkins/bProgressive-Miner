package scripts.task.subtask;

import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import scripts.data.FullInventoryAction;
import scripts.data.UIData;
import scripts.dax_api.api_lib.DaxWalker;
import scripts.dax_api.shared.helpers.BankHelper;
import scripts.task.Task;

public class Bank implements Task {
    @Override
    public boolean shouldCarryOutTask() {
        return Inventory.isFull() && UIData.lastRecordedTask.getFullInventoryAction() == FullInventoryAction.BANK;
    }

    @Override
    public boolean carryOutTask() {
        if (!BankHelper.isInBank()) //TODO: If not in bank, walk to bank
            DaxWalker.walkToBank();
        else if (!Banking.isBankScreenOpen()) //TODO: If in bank but not in interface, talk to banker
            Banking.openBank();
        else { //TODO: If in interface, deposit everything but the pickaxe
            Banking.depositAllExcept(UIData.lastRecordedTask.getPickaxe().id);
            Banking.close();
        }
        return true;
    }
}
