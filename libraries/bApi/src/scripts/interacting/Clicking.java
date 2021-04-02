package scripts.interacting;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Game;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSObject;

public class Clicking {
    public static boolean clickObject(RSObject object, boolean handleNotOnScreen) {
        if (handleNotOnScreen && (!object.isOnScreen() || !object.isClickable())) {
            if (Player.getPosition().distanceTo(object) > 2)
                Walking.getInstance().walkTo(object);
            else
                Camera.turnToTile(object);
        }
        DynamicClicking.clickRSObject(object, 1);
        boolean success = Timing.waitCondition(() -> Game.getCrosshairState() == 2, General.random(250, 900));
        if (success)
            return true;
        else {
            Player.getRSPlayer().hover();
            return false;
        }
    }

    public static void clickObjectUntilSuccessful(RSObject object, boolean handleNotOnScreen) {
        if (object == null)
            return;
        while (!clickObject(object, handleNotOnScreen));
    }
}
