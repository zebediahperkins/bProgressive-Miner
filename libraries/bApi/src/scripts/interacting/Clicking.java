package scripts.interacting;

import org.tribot.api.DynamicClicking;
import org.tribot.api.Timing;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Game;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSObject;

public class Clicking {
    private static boolean clickObject(RSObject object) {
        if (!object.isOnScreen() || !object.isClickable()) {
            if (Player.getPosition().distanceTo(object) > 2)
                Walking.getInstance().walkTo(object);
            else
                Camera.turnToTile(object);
        }
        DynamicClicking.clickRSObject(object, 1);
        return Timing.waitCondition(() -> Game.getCrosshairState() == 2, 5000);
    }

    public static void clickObjectUntilSuccessful(RSObject object) {
        if (object == null)
            return;
        while (!clickObject(object));
    }
}
