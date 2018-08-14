package indicators;

import biuoop.DrawSurface;
import gameproccess.GameLevel;
import objects.Sprite;

import java.awt.Color;

/**
 * LevelIndicator.
 *
 * @author Daniel Greenspan.
 */
public class LevelIndicator implements Sprite {
    private GameLevel gameLevel;

    /**
     * constructor.
     *
     * @param g - a game level.
     */
    public LevelIndicator(GameLevel g) {
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
        d.drawText(600, 25, gameLevel.getLevelName(), 19);
    }

    /**
     * timePassed.
     * Notify the sprite that time has passed.
     * @param dt - the time passed since last call.
     */
    public void timePassed(double dt) {
    }
}