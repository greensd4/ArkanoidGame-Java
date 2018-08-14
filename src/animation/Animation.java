package animation;

import biuoop.DrawSurface;

/**
 * Animation.
 * @author Daniel Greenspan.
 */
public interface Animation {
    /**
     * doOneFrame.
     * in charge of the logic in the animation loop.
     * @param d - the draw surface.
     * @param dt - the amount of seconds passed since the last call.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * shouldStop.
     * provides the stop condition for the animation.
     * @return true if the loop should stop and false otherwise.
     */
    boolean shouldStop();
}