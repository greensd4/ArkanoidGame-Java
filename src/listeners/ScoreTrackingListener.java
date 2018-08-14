package listeners;

import info.Counter;
import objects.Ball;
import objects.Block;

/**
 * ScoreTrackingListener.
 *
 * @author Daniel Greenspan.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor.
     *
     * @param scoreCounter - the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * hitEvent.
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * adds 5 to the score counter each hit and 15 when the blocks has no more hits.
     *
     * @param beingHit - the block how is being hit.
     * @param hitter   - the ball who hits the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {

        if (beingHit.getNumOfHits() > 0) {
            currentScore.increase(5);
        }
        if (beingHit.getNumOfHits() == 0) {
            currentScore.increase(15);
        }
    }
}