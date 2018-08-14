package menu;


/**
 * QuitTask.
 * @author Daniel Greenspan.
 */
public class QuitTask implements Task<Void> {
    /**
     * constructor.
     */
    public QuitTask() { }

    /**
     * run.
     * @return - exit the game.
     */
    public Void run() {
        System.exit(0);
        return null;
    }
}
