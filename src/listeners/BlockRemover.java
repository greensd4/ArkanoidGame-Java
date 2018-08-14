package listeners;

import gameproccess.GameLevel;
import info.Counter;
import objects.Ball;
import objects.Block;

/**
 * LevelInformation.
 *
 * @author Daniel Greenspan.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor.
     *
     * @param gameLevel     - a game level.
     * @param removedBlocks - a counter holds number of blocks still in game.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * hitEvent.
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * if the BeingHit block has 0 strikes left he is being remove from the game.
     *
     * @param beingHit - the block how is being hit.
     * @param hitter   - the ball who hits the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getNumOfHits() == 0) {
            this.remainingBlocks.decrease(1);
            beingHit.removeFromGame(this.gameLevel);
        }
    }
}