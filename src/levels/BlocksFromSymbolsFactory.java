package levels;

import objects.Block;

import java.util.Map;
import java.util.TreeMap;
/**
 * BlocksFromSymbolsFactory.
 *
 * @author Daniel Greenspan.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     * constructor.
     */
    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new TreeMap<>();
        this.blockCreators = new TreeMap<>();
    }

    /**
     * isSpaceSymbol.
     * @param s - the symbol.
     * @return true if 's' is a valid space symbol.
     */
    public boolean isSpaceSymbol(String s) { return spacerWidths.containsKey(s); }

    /**
     * isBlockSymbol.
     * @param s - the symbol.
     * @return true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) { return blockCreators.containsKey(s); }

    /**
     * getBlock.
     * @param s - the block symbol.
     * @param xpos - the x val to create the block.
     * @param ypos - the y val to create the block.
     * @return a block according to the definitions associated with the symbol s.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        if (isBlockSymbol(s)) {
            Block b = this.blockCreators.get(s).create(xpos, ypos);
            if (b != null) {
                return b;
            }
        }
        return null;
    }
    /**
     * getSpaceWidth.
     * @param s - the symbol.
     * @return - the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        if (isSpaceSymbol(s)) {
            return this.spacerWidths.get(s);
        }
        return 0;
    }
    /**
     * addSpaceWidth.
     * @param s - the symbol added.
     * @param width - the width that the symbol represent.
     */
    public void addSpaceWidth(String s, Integer width) { this.spacerWidths.put(s, width); }

    /**
     * addBlockCreator.
     * @param symbol - the symbol of the block.
     * @param creator - the block creator associated with the symbol.
     */
    public void addBlockCreator(String symbol, BlockCreator creator) {
        this.blockCreators.put(symbol, creator);
    }
}
