package scripts.task;

public interface TaskHandler {
    /**
     * Adds all tasks into an array
     *
     * @param tasks To be added to the array
     *
     * @return Array of type Task
     */
    default Task[] initTasks(Task... tasks) {
        return tasks;
    }

    /**
     * Handles tasks in an array
     *
     * @param tasks Array of type Task
     *
     * @return Whether a task was carried out
     */
    default boolean handleTasks(Task[] tasks) {
        for (Task task : tasks) {
            if (task.shouldCarryOutTask()) {
                return task.carryOutTask();
            }
        }
        return false;
    }
}
