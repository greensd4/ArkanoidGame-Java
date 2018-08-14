package info;

import java.io.Serializable;
/**
 * ScoreInfo.
 *
 * @author Daniel Greenspan.
 */
public class ScoreInfo implements Serializable {

    private String name;
    private int score;

    /**
     * constructor.
     * @param name - the player's name.
     * @param score - the player's score.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * getName.
     * @return - the player's name.
     */
    public String getName() { return this.name; }

    /**
     * getScore.
     * @return - the playe's score.
     */
    public int getScore() { return this.score; }
}
