package levels;


import objects.Block;
import objects.Sprite;
import info.Velocity;

import java.util.List;

/**
 * LevelInformation.
 *
 * @author Daniel Greenspan.
 */
public interface LevelInformation {
    /**
     * numberOfBalls.
     * how many balls should be played in this level.
     *
     * @return number of balls in level.
     */
    int numberOfBalls();

    /**
     * initialBallVelocities.
     * creats a list of velocities for the balls in the level.
     *
     * @return a list of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddleSpeed.
     *
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * paddleWidth.
     *
     * @return the level's paddle width.
     */
    int paddleWidth();

    /**
     * levelName.
     *
     * @return a string with the level's name.
     */
    String levelName();

    /**
     * getBackground.
     *
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * blocks.
     * The Blocks that make up this level,
     * each block contains its size, color and location.
     *
     * @return a list of initialized blocks.
     */
    List<Block> blocks();

    /**
     * * numberOfBlocksToRemove.
     *
     * @return the number of levels that should be removed before
     * the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}