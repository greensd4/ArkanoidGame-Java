package indicators;

import biuoop.DrawSurface;
import gameproccess.GameLevel;
import objects.Sprite;

import java.awt.Color;

/**
 * LivesIndicator.
 *
 * @author Daniel Greenspan.
 */
public class LivesIndicator implements Sprite {
    private GameLevel gameLevel;

    /**
     * constructor.
     *
     * @param g - a game level.
     */
    public LivesIndicator(GameLevel g) {
        this.gameLevel = g;
        g.addSprite(this);
    }

    /**
     * drawOn.
     *
     * @param d - the drew surface.
     */
    public void drawOn(DrawSurface d) {

        d.setColor(Color.BLACK);
        String s = Integer.toString(gameLevel.getNumOfLives());
        d.drawText(100, 25, "Lives: " + s, 19);
    }

    /**
     * timePassed.
     * Notify the sprite that time has passed.
     * @param dt - the time passed since last call.
     */
    public void timePassed(double dt) {
    }
}
