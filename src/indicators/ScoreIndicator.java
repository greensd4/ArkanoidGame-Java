package indicators;

import biuoop.DrawSurface;
import gameproccess.GameLevel;
import objects.Sprite;

import java.awt.Color;

/**
 * ScoreIndicator.
 *
 * @author Daniel Greenspan.
 */
public class ScoreIndicator implements Sprite {
    private GameLevel gameLevel;

    /**
     * constructor.
     *
     * @param g - a game level.
     */
    public ScoreIndicator(GameLevel g) {
        this.gameLevel = g;
        g.addSprite(this);
    }

    /**
     * drawOn.
     *
     * @param d - the drew surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(0xCACACA));
        d.fillRectangle(0, 0, 800, 30);
        d.setColor(Color.BLACK);
        String s = Integer.toString(gameLevel.getScore());
        d.drawText(350, 25, "Score: " + s, 19);
    }

    /**
     * timePassed.
     * Notify the sprite that time has passed.
     * @param dt - the time passed since last call.
     */
    public void timePassed(double dt) {
    }
}