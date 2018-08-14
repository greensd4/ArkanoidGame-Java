package levels;

/**
 * LevelSetInfo.
 *
 * @author Daniel Greenspan.
 */
public class LevelSetInfo {
    private String key;
    private String message;
    private String levelPath;
    /**
     * constructor.
     * @param k - the level's key.
     * @param m - the message.
     * @param p - the level's path.
     */
    public LevelSetInfo(String k, String m, String p) {
        this.key = k;
        this.levelPath = p;
        this.message = m;
    }

    /**
     * getKey.
     * @return the level's key.
     */
    public String getKey() { return this.key; }

    /**
     * getLevelPath.
     * @return - the level's path.
     */
    public String getLevelPath() { return this.levelPath; }

    /**
     * getMessage.
     * @return the level's message.
     */
    public String getMessage() { return this.message; }
}

