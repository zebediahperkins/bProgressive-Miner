package scripts.interacting;

import org.tribot.api.DynamicClicking;
import org.tribot.api.Timing;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Game;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSObject;
import scripts.antiban.AntiBan;

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

    public static boolean clickObjectAndWait(RSObject object, boolean startsAnimation) { //TODO: Clean this up
        if (object == null)
            return false;
        while (!clickObject(object));
        if (Player.getPosition().distanceToDouble(object) > 1)
            Timing.waitCondition(Player::isMoving, 2000);
        return Timing.waitCondition(() -> {
            AntiBan.getInstance().performTimedActions();
            if (startsAnimation) {
                Timing.waitCondition(() -> !Player.isMoving(), 20000);
                return Timing.waitCondition(() -> Player.getAnimation() != -1, 1000);
            }
            return !Player.isMoving();
        }, 5000);
    }
}
