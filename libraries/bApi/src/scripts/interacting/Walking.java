package scripts.interacting;

import org.tribot.api.Timing;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Player;
import scripts.antiban.AntiBan;
import scripts.dax_api.api_lib.DaxWalker;
import scripts.dax_api.api_lib.models.DaxCredentials;

public class Walking {
    private static Walking instance = null;

    public boolean walkTo(Positionable positionable) {
        AntiBan.getInstance().tryActivatingRun();
        boolean success;
        if (Player.getPosition().distanceTo(positionable) < 10
                && positionable.getPosition().isClickable()
                && positionable.getPosition().isOnScreen()) {
            success = blindWalkTo(positionable);
        }
        else
            success = DaxWalker.walkTo(positionable);
        Timing.waitCondition(() -> !Player.isMoving(), 10000);
        if (!Player.getPosition().equals(positionable)) {
            success = Walking.getInstance().blindWalkTo(positionable);
            Timing.waitCondition(() -> !Player.isMoving(), 10000);
        }
        return success;
    }

    public boolean walkToBank() {
        AntiBan.getInstance().tryActivatingRun();
        boolean success = DaxWalker.walkToBank();
        Timing.waitCondition(() -> !Player.isMoving(), 10000);
        return success;
    }

    public boolean blindWalkTo(Positionable positionable) {
        if (!Player.getPosition().equals(positionable)) {
            AntiBan.getInstance().tryActivatingRun();
            boolean success = org.tribot.api2007.Walking.blindWalkTo(positionable);
            Timing.waitCondition(() -> !Player.isMoving(), 10000);
            return success;
        }
        return false;
    }

    public boolean isInBank() {
        return Banking.isInBank();
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
