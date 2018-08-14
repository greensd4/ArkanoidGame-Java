package levels;

import objects.Block;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * LevelSpecificationReader.
 *
 * @author Daniel Greenspan.
 */
public class LevelSpecificationReader {

    /**
     * fromReader.
     *
     * @param reader - a file reader.
     * @return = a list of LevelInformation objects.
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<String> blocksList;
        List<LevelInformation> levels = new ArrayList<>();
        String[] currentDef;
        List<Block> levelBlocks;
        try {
            BufferedReader r = new BufferedReader(reader);
            String line = r.readLine();
            TreeMap<String, String> levelProperties = new TreeMap<>();

            while (line != null) {
                if (line.contains("#") || line.equals("")) {
                    line = r.readLine();
                    continue;
                }
                if (line.contains("START_LEVEL")) {
                    try {
                        blocksList = new ArrayList<>();
                        line = r.readLine();
                        while (!line.equals("END_LEVEL")) {
                            if (line.contains("START_BLOCKS")) {
                                line = r.readLine();
                                while (!line.equals("END_BLOCKS")) {
                                    blocksList.add(line);
                                    line = r.readLine();
                                }
                            } else if (line.contains(":")) {
                                currentDef = line.split(":");
                                levelProperties.put(currentDef[0], currentDef[1]);
                            }
                            line = r.readLine();
                        }
                        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(levelProperties.
                                get("block_definitions"));
                        InputStreamReader stream = new InputStreamReader(is);
                        BlocksDefinitionReader factory = new BlocksDefinitionReader();
                        BlocksFromSymbolsFactory fact = factory.fromReader(stream);
                        levelBlocks = readBlock(blocksList, fact, levelProperties);

                        if (levelBlocks != null && containAllDefinitions(levelProperties)) {

                            LevelCreator levelCreator = new LevelCreator(levelProperties, levelBlocks);
                            LevelInformation l = levelCreator.getLevel();
                            levels.add(l);
                            levelProperties.clear();
                        }

                    } catch (IOException ex) {
                        ex.getCause();
                    }
                    line = r.readLine();
                } else {
                    System.out.println("missing block definitions");
                }
            }

        } catch (IOException e) {
            e.getCause();
        }
        return levels;
    }

    /**
     * Reads the blocks and space sequences and adds the blocks(by location) to the blocks list.
     *
     * @param blockList       - the blocks list of the level.
     * @param factory         - factory that knows how to create the blocks.
     * @param levelProperties - the properties of the level.
     * @return list of blocks.
     */
    public static List<Block> readBlock(List<String> blockList, BlocksFromSymbolsFactory factory,
                                        TreeMap<String, String> levelProperties) {
        List<Block> blocksList = new ArrayList<>();
        int blocksStartX = Integer.parseInt(levelProperties.get("blocks_start_x"));
        int blocksStartY = Integer.parseInt(levelProperties.get("blocks_start_y"));
        int rowHeight = Integer.parseInt(levelProperties.get("row_height"));
        int currentX = blocksStartX;
        int currentY = blocksStartY;
        Block block;

        for (String line : blockList) {
            for (char c : line.toCharArray()) {
                String symbol = String.valueOf(c);
                if (factory.isSpaceSymbol(symbol)) {
                    currentX += factory.getSpaceWidth(symbol);
                } else if (factory.isBlockSymbol(symbol)) {
                    block = factory.getBlock(symbol, currentX, currentY);
                    if (block != null) {
                        currentX += block.getCollisionRectangle().getWidth();
                        blocksList.add(block);
                    } else {
                        return null;
                    }
                }
            }
            currentX = blocksStartX;
            currentY += rowHeight;
        }
        return blocksList;
    }

    /**
     * containAllDefinitions.
     * @param levelProperties - a map of strings of the the level properties.
     * @return - true if the file contains all the definitions needed and false otherwise.
     */
    public boolean containAllDefinitions(TreeMap<String, String> levelProperties) {
        String s = "level_name:ball_velocities:background:paddle_speed:paddle_width:block_definitions:"
                + "blocks_start_x:blocks_start_y:row_height:num_blocks";
        String[] definitions = s.split(":");
        for (int i = 0; i < definitions.length; i++) {
            if (!levelProperties.containsKey(definitions[i])) {
                return false;
            }
        }
        return true;
    }
}
