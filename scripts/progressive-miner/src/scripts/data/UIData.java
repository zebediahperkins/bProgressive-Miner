package scripts.data;

import org.tribot.api2007.Skills;
import scripts.task.ProgressiveTask;

public class UIData {
    public static boolean shouldPaint = false;
    public static ProgressiveTask[] progressiveTasks;
    public static ProgressiveTask lastRecordedTask;

    public static ProgressiveTask getCurrentProgressiveTask() {
        for (ProgressiveTask progressiveTask : progressiveTasks) {
            if (progressiveTask.isInLevelRange(Skills.getActualLevel(Skills.SKILLS.MINING)))
                return progressiveTask;
        }
        return null;
    }
}
