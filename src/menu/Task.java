package menu;

/**
 * Task.
 * @param <T> - the task type.
 * @author Daniel Greenspan.
 */
public interface Task<T> {
    /**
     * runs the task.
     * @return - a value.
     */
    T run();
}
