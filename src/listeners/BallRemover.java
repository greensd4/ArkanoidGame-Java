package listeners;

import gameproccess.GameLevel;
import info.Counter;
import objects.Ball;
import objects.Block;

/**
 * BallRemover.
 *
 * @author Daniel Greenspan.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * constructor.
     * @param gameLevel - a game level.
     * @param remainingBalls - a counter holds number of balls still in game.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }
    /**
     * hitEvent.
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * removes the hitter and decreasing 1 from the game's balls counter.
     * @param beingHit - the block how is being hit.
     * @param hitter - the ball who hits the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(1);
    }
}