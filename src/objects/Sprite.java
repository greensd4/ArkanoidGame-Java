package objects;

import biuoop.DrawSurface;

/**
 * Sprite.
 *
 * @author Daniel Greenspan.
 */
public interface Sprite {
    /**
     * drawOn.
     *
     * @param d - the drew surface.
     */
    void drawOn(DrawSurface d);

    /**
     * timePassed.
     * Notify the sprite that time has passed.
     * @param dt - the amount of seconds passed since the last call.
     */
    void timePassed(double dt);
}