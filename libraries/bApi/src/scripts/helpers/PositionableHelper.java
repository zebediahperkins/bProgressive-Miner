package scripts.helpers;

import org.tribot.api.interfaces.Positionable;
import org.tribot.api2007.Player;

public class PositionableHelper {
    public static Positionable getClosestPositionable(Positionable[] positionables) {
        Positionable closest = null;
        for (Positionable positionable : positionables) {
            if (closest == null
                    || Player.getPosition().distanceToDouble(positionable)
                    < Player.getPosition().distanceToDouble(closest))
                closest = positionable;
        }
        return closest;
    }
}
