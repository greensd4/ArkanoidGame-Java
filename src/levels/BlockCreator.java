package levels;

import objects.Block;

/**
 * BlockCreator.
 *
 * @author Daniel Greenspan.
 */
public interface BlockCreator {

    /**
     * create.
     * Create a block at the specified location.
     * @param xpos - the x val which the block will be created at.
     * @param ypos - the y val which the block will be created at.
     * @return a new Block.
     */
    Block create(int xpos, int ypos);
}
