package scripts.helpers;

import org.tribot.api2007.Objects;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

public class ObjectsHelper {
    public static RSObject getObjectAtTile(String name, RSTile tile) {
        RSObject[] objects = Objects.getAt(tile);
        for (RSObject object : objects) {
            if (object.getDefinition().getName().equals(name))
                return object;
        }
        return null;
    }

    public static RSObject getObjectAtTile(int id, RSTile tile) {
        RSObject[] objects = Objects.getAt(tile);
        for (RSObject object : objects) {
            if (object.getDefinition().getID() == id)
                return object;
        }
        return null;
    }
}
