package screens;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import animation.Animation;
import objects.SpriteCollection;

import java.awt.Color;


/**
 * CountdownAnimation.
 *
 * @author Daniel Greenspan.
 */

public class CountdownAnimation implements Animation {

    private double numOfSec;
    private int countFrom;
    private SpriteCollection game;

    /**
     * constructor.
     *
     * @param numOfSeconds - number of seconds to be display on the screen.
     * @param countFrom    - the number to start the count from.
     * @param gameScreen   - the game's screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSec = numOfSeconds;
        this.countFrom = countFrom;
        this.game = gameScreen;
    }

    /**
     * doOneFrame.
     *
     * @param d - the draw surface.
     * @param dt - the amount.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.game.drawAllOn(d);
        d.setColor(Color.RED);
        d.drawText(400, 300, Integer.toString(countFrom), 40);
        Sleeper sleepFor = new Sleeper();
        long sleep = ((long) ((numOfSec / 4) * 1000));
        sleepFor.sleepFor(sleep);
        this.countFrom -= 1;
    }

    /**
     * shouldStop.
     *
     * @return - true when finish counting and false otherwise.
     */
    public boolean shouldStop() {
        if (countFrom < 0) {
            return true;
        }
        return false;
    }
}