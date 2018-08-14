package menu;

/**
 * TaskInfo.
 * @param <T> - the task type of the task info.
 * @author Daniel Greenspan.
 */
public class TaskInfo<T> {
    private String key;
    private String message;
    private T task;

    /**
     * constructor.
     * @param key - the task key.
     * @param m - the task message.
     * @param task - the task itself.
     */
    public TaskInfo(String key, String m, T task) {
        this.key = key;
        this.message = m;
        this.task = task;
    }

    /**
     * getKey.
     * @return - the task's key.
     */
    public String getKey() { return this.key; }

    /**
     * getMessage.
     * @return - the task's message.
     */
    public String getMessage() { return this.message; }

    /**
     * getTask.
     * @return - the task itself.
     */
    public T getTask() { return this.task; }
}
