package scripts.helpers;

import org.tribot.api2007.Player;
import org.tribot.api2007.Projection;
import org.tribot.api2007.types.RSTile;

import java.awt.*;

public class ProjectionHelper {
    /**
     * Converts a point on the screen to a RSTile.
     *
     * @author Encoded
     *
     * @param p Point on the screen.
     * @return RSTile that correlates to the specified point on the screen, or null if the point wasn't on screen.
     */
    public static RSTile screenToTile(Point p) {
        if (!Projection.isInViewport(p)) return null;
        RSTile pos = Player.getPosition();
        for (int x = pos.getX() - 12; x < pos.getX() + 12; x++) {
            for (int y = pos.getY() - 12; y < pos.getY() + 12; y++) {
                RSTile tile = new RSTile(x, y, pos.getPlane());
                Polygon bounds = Projection.getTileBoundsPoly(tile, 0);
                if (bounds != null && bounds.contains(p)) return tile;
            }
        }
        return null;
    }
}
