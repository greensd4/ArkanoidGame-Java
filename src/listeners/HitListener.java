package listeners;

import objects.Ball;
import objects.Block;

/**
 * HitListener.
 *
 * @author Daniel Greenspan.
 */
public interface HitListener {
    /**
     * hitEvent.
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit - the block how is being hit.
     * @param hitter - the ball who hits the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
