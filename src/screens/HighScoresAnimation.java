package screens;

import biuoop.DrawSurface;
import animation.Animation;
import info.HighScoresTable;

import java.awt.Color;

/**
 * HighScoresAnimation.
 *
 * @author Daniel Greenspan.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable highScoresTable;
    /**
     * constructor.
     * @param scores - the high scores table.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.highScoresTable = scores;
    }

    /**
     * doOneFrame.
     * in charge of the logic in the animation loop.
     *
     * @param d  - the draw surface.
     * @param dt - the amount of seconds passed since the last call.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(0xE6D95E));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.blue);
        d.drawText(97, 70 , "High Score Table", 51);
        d.setColor(Color.black);
        d.drawText(100, 70 , "High Score Table", 50);


        for (int i = 0; i < this.highScoresTable.getHighScores().size(); i++) {
            d.drawText(50, 150 + (i * 40),
                    (i + 1) + ") Name: " + highScoresTable.getHighScores().get(i).getName(), 25);
            d.drawText(350, 150 + (i * 40),
                    "Score: " + highScoresTable.getHighScores().get(i).getScore(), 25);
        }
        d.drawText(450, 570 , "Press (space) to return to main menu", 18);

    }

    /**
     * shouldStop.
     * provides the stop condition for the animation.
     * @return true if the loop should stop and false otherwise.
     */
    public boolean shouldStop() {
        return false;
    }

}
