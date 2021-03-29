package scripts.helpers;

import org.tribot.api2007.WorldHopper;
import org.tribot.api2007.types.RSServer;

public class WorldHelper {
    public static boolean hopWorlds(boolean useMembers, boolean usePvp) {
        int world;
        if (useMembers && usePvp)
            world = WorldHopper.getRandomWorld(
                    RSServer.WorldType.P2P,
                    RSServer.WorldType.PVP_P2P
            );
        else if (useMembers)
            world = WorldHopper.getRandomWorld(
                    RSServer.WorldType.P2P
            );
        else if (usePvp)
            world = WorldHopper.getRandomWorld(
                    RSServer.WorldType.F2P,
                    RSServer.WorldType.PVP_F2P
            );
        else
            world = WorldHopper.getRandomWorld(
                    RSServer.WorldType.F2P
            );
        return WorldHopper.changeWorld(world); //TODO: Sometimes this seems to get hung up?
    }
}
