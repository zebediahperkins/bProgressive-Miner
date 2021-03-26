package scripts.task;

public interface Task {
    /**
     *
     * @return Whether the task should be carried out
     */
    boolean shouldCarryOutTask();

    /**
     *
     * @return Whether the task loop should continue on completion of this task
     */
    boolean carryOutTask();
}
