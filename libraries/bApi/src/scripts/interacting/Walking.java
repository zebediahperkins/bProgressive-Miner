package scripts.interacting;

import org.tribot.api.interfaces.Positionable;
import scripts.antiban.AntiBan;
import scripts.dax_api.api_lib.DaxWalker;
import scripts.dax_api.api_lib.models.DaxCredentials;
import scripts.dax_api.shared.helpers.BankHelper;

public class Walking {
    private static Walking instance = null;

    public boolean walkTo(Positionable positionable) {
        AntiBan.getInstance().tryActivatingRun();
        return DaxWalker.walkTo(positionable);
    }

    public boolean walkToBank() {
        AntiBan.getInstance().tryActivatingRun();
        return DaxWalker.walkToBank();
    }

    public boolean isInBank() {
        return BankHelper.isInBank();
    }

    private Walking() {
        DaxWalker.setCredentials(() ->
                new DaxCredentials("sub_DPjXXzL5DeSiPf", "PUBLIC-KEY")
        );
    }

    public static Walking getInstance() {
        if (instance == null)
            instance = new Walking();
        return instance;
    }
}
