package levels;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * BlocksDefinitionReader.
 *
 * @author Daniel Greenspan.
 */
public class BlocksDefinitionReader {

    /**
     * fromReader.
     * @param reader - an IO reader.
     * @return - a new BlocksFromSymbolsFactory object.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {

        Integer val = null;
        String symbol = null;
        String[] definition;
        String[] currentDefinition;
        Map<String, String> bdefMap;
        Map<String, String> defaultMap;
        BlocksFromSymbolsFactory blocksFactory = new BlocksFromSymbolsFactory();
        try {
            BufferedReader r = new BufferedReader(reader);
            String line = r.readLine();
            defaultMap = new TreeMap<>();
            while (line != null) {

                if ((line.contains("#") || (line.equals("")))) {
                    try {
                        line = r.readLine();
                        continue;
                    } catch (IOException e) {
                        line = null;
                    }
                } else {
                    definition = line.split(" ");
                    if (definition[0].equals("bdef")) {
                        bdefMap = new TreeMap<>();
                        int range = howManyDots(line, ':');
                        for (int i = 1; i <= range; i++) {
                            currentDefinition = definition[i].split(":");
                            if (currentDefinition[0].equals("symbol")) {
                                symbol = currentDefinition[1];
                                continue;
                            }
                            bdefMap.put(currentDefinition[0], currentDefinition[1]);
                        }
                        BlockCreator blockCreator = new CreateOneBlock(bdefMap, defaultMap);
                        blocksFactory.addBlockCreator(symbol, blockCreator);
                        line = r.readLine();


                    } else if (definition[0].equals("sdef")) {
                        int range = howManyDots(line, ':');
                        for (int i = 1; i <= range; i++) {
                            currentDefinition = definition[i].split(":");
                            if (currentDefinition[0].equals("symbol")) {
                                symbol = currentDefinition[1];
                            } else if (currentDefinition[0].equals("width")) {
                                val = Integer.parseInt(currentDefinition[1]);
                            }
                        }
                        blocksFactory.addSpaceWidth(symbol, val);
                        line = r.readLine();

                    } else if (definition[0].equals("default")) {
                        int range = howManyDots(line, ':');
                        for (int i = 1; i <= range; i++) {
                            currentDefinition = definition[i].split(":");
                            defaultMap.put(currentDefinition[0], currentDefinition[1]);
                        }
                        line = r.readLine();
                    }
                }
            }
        } catch (IOException e) {
            e.getCause();
        }
        return blocksFactory;
    }

    /**
     * howManyDots.
     * @param line - a line in the file.
     * @param letter - a letter to search in the line.
     * @return - the amount of times the letter appears in the line.
     */
    public static int howManyDots(String line, char letter) {
        char[] lineAsChars = line.toCharArray();
        int i = 0;
        int count = 0;
        while (i < lineAsChars.length) {
            if (lineAsChars[i] == letter) {
                count++;
            }
            i++;
        }
        return count;
    }
}
