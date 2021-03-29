package scripts.task.subtask;

import org.tribot.api.DynamicClicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.Objects;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import scripts.ProgressiveMiner;
import scripts.antiban.AntiBan;
import scripts.data.UIData;
import scripts.helpers.PositionableHelper;
import scripts.helpers.StructuresHelper;
import scripts.helpers.WorldHelper;
import scripts.interacting.Clicking;
import scripts.interacting.Walking;
import scripts.task.ProgressiveTask;
import scripts.task.Task;

import java.util.Arrays;

public class Mine implements Task {
    int averageWaitTimeMS = 0;
    int totalWaits = 0;
    int averageMineTimeMS = 0;
    int totalMines = 0;
    RSObject nextTarget = null;

    @Override
    public boolean shouldCarryOutTask() {
        return UIData.lastRecordedTask != null;
    }

    @Override
    public boolean carryOutTask() {
        RSTile[] rockLocations = UIData.lastRecordedTask.getRockLocations();
        RSTile closestTile = (RSTile) PositionableHelper.getClosestPositionable(rockLocations);
        if (Player.getPosition().distanceTo(closestTile) > 15) {
            return Walking.getInstance().walkTo(closestTile);
        }
        RSObject[] availableRocks = getAvailableRocks(rockLocations);
        if (availableRocks.length < 1) {
            ProgressiveTask curTask = UIData.lastRecordedTask;
            if (curTask.getShouldHopOnNoRocks())
                return WorldHelper.hopWorlds(curTask.getUseMembersWorlds(), curTask.getUsePvpWorlds());
            long startTime = System.currentTimeMillis();
            while (availableRocks.length < 1) { //TODO: Is there a better solution than this if -> while loop with the same condition?
                AntiBan.getInstance().performTimedActions();
                availableRocks = getAvailableRocks(rockLocations);
                General.sleep(50); //TODO: Is there a better solution than a static sleep here?
            }
            updateTimersAndSleep(startTime, false);
        }
        RSObject target;
        if (nextTarget != null && StructuresHelper.existsInArray(nextTarget, availableRocks))
            target = nextTarget;
        else
            target = (RSObject) AntiBan.getInstance().getNextTarget(availableRocks);
        if (availableRocks.length > 1) {
            RSObject[] availableClone = Arrays
                    .stream(availableRocks.clone())
                    .filter(rsObject -> !rsObject.equals(target))
                    .toArray(RSObject[]::new);
            nextTarget = (RSObject) AntiBan.getInstance().getNextTarget(availableClone);
        } else
            nextTarget = null;
        ProgressiveMiner.availableRocks = availableRocks;
        ProgressiveMiner.target = target;
        ProgressiveMiner.nextTarget = nextTarget;
        switch (AntiBan.getInstance().getAndResetHover()) {
            case HOVER -> Mouse.click(1);
            case OPEN_MENU -> ChooseOption.select("Mine");
            case NONE -> Clicking.clickObjectUntilSuccessful(target);
        }
        long startTime = System.currentTimeMillis();
        while (getRockAtTile(target.getPosition()) != null) {
            AntiBan.getInstance().performTimedActions();
            if (nextTarget != null) {
                switch (AntiBan.getInstance().shouldHoverNextTarget()) {
                    case HOVER -> nextTarget.hover();
                    case OPEN_MENU -> DynamicClicking.clickRSObject(target, 2);
                }
            }
            if (Player.isMoving())
                continue;
            if (!Timing.waitCondition(
                    () -> {
                        if (getRockAtTile(target.getPosition()) == null) {
                            updateTimersAndSleep(startTime, true);
                            return false;
                        }
                        return Player.getAnimation() != -1;
                    }, General.random(1000, 2000) //TODO: Maybe find a better solution than a random?
            ))
                return true;
            General.sleep(50); //TODO: Again, static sleep
        }
        updateTimersAndSleep(startTime, true);
        return true;
    }

    private RSObject getRockAtTile(RSTile tile) {
        RSObject[] objectsAtTile = Objects.getAt(tile);
        for (RSObject object : objectsAtTile) {
            if (object.getDefinition().getName().equals("Rocks")
                    && object.getID() != 11390
                    && object.getID() != 11391
            ) {
                return object;
            }
        }
        return null;
    }

    private RSObject[] getAvailableRocks(RSTile[] rockLocations) {
        return Arrays
                .stream(rockLocations)
                .map(this::getRockAtTile)
                .filter(java.util.Objects::nonNull)
                .toArray(RSObject[]::new);
    }

    private void updateTimersAndSleep(long startTime, boolean wasMining) { //TODO: This feels like a poor solution
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        long tempTotalTime;
        if (wasMining) {
            tempTotalTime = (long) averageMineTimeMS * totalMines;
            tempTotalTime += elapsedTime;
            tempTotalTime /= ++totalMines;
            averageMineTimeMS = (int) tempTotalTime;
            AntiBan.getInstance().generateReactionTimeAndSleep(averageMineTimeMS, elapsedTime);
        } else {
            tempTotalTime = (long) averageWaitTimeMS * totalWaits;
            tempTotalTime += elapsedTime;
            tempTotalTime /= ++totalWaits;
            averageWaitTimeMS = (int) tempTotalTime;
            AntiBan.getInstance().generateReactionTimeAndSleep(averageWaitTimeMS, elapsedTime);
        }
    }
}
