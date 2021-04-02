package scripts.helpers;

import org.tribot.api.input.Mouse;
import org.tribot.api2007.Camera;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Player;

public class CameraHelper {
    /**
     * Gets the camera's current zoom
     *
     * @return Number from 0-96 representing the camera's current zoom
     */
    public static int getZoom() {
        return Interfaces.get(116, 60).getX();
    }

    /**
     * Changes the camera's zoom
     *
     * @param zoom Number from 0-96 representing the desired zoom value
     */
    public static void setZoom(int zoom) {
        if (zoom > 96)
            zoom = 96;
        else if (zoom < 0)
            zoom = 0;
        boolean up = zoom > getZoom();
        Player.getRSPlayer().hover();
        Mouse.scroll(up, Math.abs(zoom - getZoom()) / 3);
    }

    public static void setCamera(int degrees, int angle, int zoom) {
        setZoom(zoom);
        while (Math.abs(Camera.getCameraRotation() - degrees) > 8 || Math.abs(Camera.getCameraAngle() - angle) > 8) {
            Camera.setCamera(degrees, angle);
        }
    }
}