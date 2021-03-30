package scripts.antiban;

import org.tribot.api.input.Mouse;
import org.tribot.api.interfaces.Positionable;
import org.tribot.api.util.abc.ABCProperties;
import org.tribot.api.util.abc.ABCUtil;
import org.tribot.api.util.abc.preferences.WalkingPreference;
import org.tribot.api2007.Combat;
import org.tribot.api2007.Game;
import org.tribot.api2007.Options;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSTile;

/**
 * ABC2 Implementation
 *
 * @author zebediahperkins
 * {@see https://community.tribot.org/topic/60720-guide-to-implementing-abc2/}
 */
public class AntiBan {
    private static AntiBan instance = null;
    private final ABCUtil abcUtil = new ABCUtil();

    private int energyToRunAt = abcUtil.generateRunActivation();
    private boolean isMenuOpen = false;
    private boolean isHovering = false;

    /**
     * Actions performed based on internal timers in ABCUtil
     */
    public void performTimedActions() {
        if (!isHovering) {
            if (abcUtil.shouldCheckTabs())
                abcUtil.checkTabs();
            if (abcUtil.shouldCheckXP())
                abcUtil.checkXP();
            if (abcUtil.shouldExamineEntity())
                abcUtil.examineEntity();
            if (abcUtil.shouldMoveMouse())
                abcUtil.moveMouse();
            if (abcUtil.shouldPickupMouse())
                abcUtil.pickupMouse();
            if (abcUtil.shouldRightClick())
                abcUtil.rightClick();
            if (abcUtil.shouldRotateCamera())
                abcUtil.rotateCamera();
            if (abcUtil.shouldLeaveGame())
                abcUtil.leaveGame();
        }
    }

    /**
     * Generate walking preference based on distance and ABCUtil profile
     *
     * @param destination The RSTile where the player is trying to walk
     * @return WalkingPreference correlating to whether we should click MINIMAP or SCREEN
     */
    public WalkingPreference getWalkingPreference(RSTile destination) {
        int distance = Player.getPosition().distanceTo(destination);
        return abcUtil.generateWalkingPreference(distance);
    }

    /**
     * Generate next target from an array of potential targets based on ABCUtil profile
     *
     * @param positionables An array of potential targets
     * @return Positionable correlating to the selected target
     */
    public Positionable getNextTarget(Positionable[] positionables) {
        return abcUtil.selectNextTarget(positionables);
    }

    /**
     * Try to activate run. Call before each step
     */
    public void tryActivatingRun() {
        if (!Game.isRunOn() && Game.getRunEnergy() >= energyToRunAt) {
            Options.setRunEnabled(true);
            energyToRunAt = abcUtil.generateRunActivation();
        }
    }

    /**
     * Check if we should hop worlds based on ABCUtil profile and how long it's been since we've won a resource
     * Only call this when we aren't winning many resources
     *
     * @param attemptsSinceSuccess The number of failed attempts since we last won a resource
     * @return Boolean indicating whether or not we should hop worlds
     */
    public boolean shouldHopHighCompetition(int attemptsSinceSuccess) {
        return abcUtil.shouldSwitchResources(attemptsSinceSuccess);
    }

    /**
     * Determine how, if at all, we should interact with our next target
     *
     * @return HoverOption indicating how we should interact with our next target
     */
    public HoverOption shouldHoverNextTarget() {
        if (!isHovering && abcUtil.shouldHover() && Mouse.isInBounds()) {
            isHovering = true;
            if (abcUtil.shouldOpenMenu()) {
                isMenuOpen = true;
                return HoverOption.OPEN_MENU;
            }
            return HoverOption.HOVER;
        }
        return HoverOption.NONE;
    }

    /**
     * Generate a reaction time, and feed it to ABCUtil#sleep. Also updates trackers
     *
     * @param averageWaitMS An approximate amount of time in milliseconds that this task should take to complete
     * @param actualWaitMS The actual amount of time in milliseconds that this task took to complete
     */
    public void generateReactionTimeAndSleep(int averageWaitMS, int actualWaitMS) {
        ABCProperties props = abcUtil.getProperties();
        props.setWaitingTime(averageWaitMS);
        props.setHovering(isHovering);
        props.setMenuOpen(isMenuOpen);
        props.setUnderAttack(Combat.isUnderAttack());
        props.setWaitingFixed(false);
        abcUtil.generateTrackers();
        long reactionTime = abcUtil.generateReactionTime(actualWaitMS) / 8;
        try {
            abcUtil.sleep(reactionTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the HoverOption, and reset isMenuOpen/isHovering to false
     *
     * @return HoverOption indicating the mouse/target hover state
     */
    public HoverOption getAndResetHover() {
        HoverOption hoverOption = HoverOption.NONE;
        if (isMenuOpen)
            hoverOption = HoverOption.OPEN_MENU;
        else if (isHovering)
            hoverOption = HoverOption.HOVER;
        isMenuOpen = false;
        isHovering = false;
        return hoverOption;
    }

    public static AntiBan getInstance() {
        if (instance == null)
            instance = new AntiBan();
        return instance;
    }
}
