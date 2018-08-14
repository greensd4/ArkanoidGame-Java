package levels;

import geometry.Point;
import geometry.Rectangle;
import objects.Block;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/**
 * CreateOneBlock.
 *
 * @author Daniel Greenspan.
 */
public class CreateOneBlock implements BlockCreator {

    private Map<String, String> blockInfo;
    private Map<String, String> defaultInfo;
    private int height;
    private int width;
    private int numOfHits;
    private Color frameColor;
    private String symbol;
    private Map<Integer, Color> colorMap;
    private Map<Integer, Image> imageMap;

    /**
     * constructor.
     *
     * @param blockMap - a map of strings from block definition file.
     * @param defMap   - a map of strings of the defaults values from block definition file.
     */
    public CreateOneBlock(Map<String, String> blockMap, Map<String, String> defMap) {
        this.blockInfo = blockMap;
        this.defaultInfo = defMap;
        this.colorMap = new TreeMap<>();
        this.imageMap = new TreeMap<>();
    }

    /**
     * create.
     * Create a block at the specified location.
     * @param xpos - the x val which the block will be created at.
     * @param ypos - the y val which the block will be created at.
     * @return a new Block.
     */
    public Block create(int xpos, int ypos) {
        Image img;
        //gets height for the block
        if (blockInfo.containsKey("height")) {
            this.height = Integer.parseInt(blockInfo.get("height"));
        } else if (defaultInfo.containsKey("height")) {
            this.height = Integer.parseInt(defaultInfo.get("height"));
        } else {
            System.out.println("missing height parameter");
            return null;
        }
        if (blockInfo.containsKey("width")) {
            this.width = Integer.parseInt(blockInfo.get("width"));
        } else if (defaultInfo.containsKey("width")) {
            this.width = Integer.parseInt(defaultInfo.get("width"));
        } else {
            System.out.println("missing width parameter");
            return null;
        }
        if (blockInfo.containsKey("hit_points")) {
            this.numOfHits = Integer.parseInt(blockInfo.get("hit_points"));
        } else if (defaultInfo.containsKey("hit_points")) {
            this.numOfHits = Integer.parseInt(defaultInfo.get("hit_points"));
        } else {
            System.out.println("missing hit_points parameter");
            return null;
        }
        if (blockInfo.containsKey("fill") || blockInfo.containsKey("fill-1")
                || blockInfo.containsKey("fill-2") || blockInfo.containsKey("fill-3")) {
            try {
                if (blockInfo.containsKey("fill")) {
                    String s = blockInfo.get("fill").substring(6, blockInfo.get("fill").length() - 1);
                    if (blockInfo.get("fill").contains("color")) {
                        Color fillColor = ColorsParser.stringToColor(s);
                        this.colorMap.put(1, fillColor);
                    } else if (blockInfo.get("fill").contains("image")) {
                        img = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(s));
                        this.imageMap.put(1, img);
                    }
                }
                if (blockInfo.containsKey("fill-1")) {
                    String s = blockInfo.get("fill-1").substring(6, blockInfo.get("fill-1").length() - 1);
                    if (blockInfo.get("fill-1").contains("color")) {
                        Color fillColor = ColorsParser.stringToColor(s);
                        this.colorMap.put(1, fillColor);
                    } else if (blockInfo.get("fill-1").contains("image")) {
                        img = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(s));
                        this.imageMap.put(1, img);
                    }
                }
                if (blockInfo.containsKey("fill-2")) {
                    String s = blockInfo.get("fill-2").substring(6, blockInfo.get("fill-2").length() - 1);
                    if (blockInfo.get("fill-2").contains("color")) {
                        Color fillColor = ColorsParser.stringToColor(s);
                        this.colorMap.put(2, fillColor);
                    } else if (blockInfo.get("fill-2").contains("image")) {
                        img = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(s));
                        this.imageMap.put(2, img);
                    }
                }
                if (blockInfo.containsKey("fill-3")) {
                    String s = blockInfo.get("fill-3").substring(6, blockInfo.get("fill-3").length() - 1);
                    if (blockInfo.get("fill-3").contains("color")) {
                        Color fillColor = ColorsParser.stringToColor(s);
                        this.colorMap.put(3, fillColor);
                    } else if (blockInfo.get("fill-3").contains("image")) {
                        img = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(s));
                        this.imageMap.put(3, img);
                    }
                }
            } catch (IOException e) {
                System.out.println("no such image in directory");
                return null;
            }
            //if no definition checks if there is a default definition.
        } else if (defaultInfo.containsKey("fill") || defaultInfo.containsKey("fill-1")
                || defaultInfo.containsKey("fill-2") || defaultInfo.containsKey("fill-3")) {
            try {
                if (defaultInfo.containsKey("fill")) {
                    String s = defaultInfo.get("fill").substring(6, defaultInfo.get("fill").length() - 1);
                    if (defaultInfo.get("fill").contains("color")) {
                        Color fillColor = ColorsParser.stringToColor(s);
                        this.colorMap.put(1, fillColor);
                    } else if (defaultInfo.get("fill").contains("image")) {
                        img = ImageIO.read(ClassLoader.getSystemClassLoader().
                                getResourceAsStream(s));
                        this.imageMap.put(1, img);
                    }
                }
                if (defaultInfo.containsKey("fill-1")) {
                    String s = defaultInfo.get("fill-1").substring(6, defaultInfo.get("fill-1").length() - 1);

                    if (defaultInfo.get("fill-1").contains("color")) {
                        Color fillColor = ColorsParser.stringToColor(s);
                        this.colorMap.put(1, fillColor);
                    } else if (defaultInfo.get("fill-1").contains("image")) {
                        img = ImageIO.read(ClassLoader.getSystemClassLoader().
                                getResourceAsStream(s));
                        this.imageMap.put(1, img);
                    }
                }
                if (defaultInfo.containsKey("fill-2")) {
                    String s = defaultInfo.get("fill-2").substring(6, defaultInfo.get("fill-2").length() - 1);

                    if (defaultInfo.get("fill-2").contains("color")) {
                        Color fillColor = ColorsParser.stringToColor(s);
                        this.colorMap.put(2, fillColor);
                    } else if (defaultInfo.get("fill-2").contains("image")) {
                        img = ImageIO.read(ClassLoader.getSystemClassLoader().
                                getResourceAsStream(s));
                        this.imageMap.put(2, img);
                    }
                }
                if (defaultInfo.containsKey("fill-3")) {
                    String s = defaultInfo.get("fill-2").substring(6, defaultInfo.get("fill-2").length() - 1);
                    if (defaultInfo.get("fill-3").contains("color")) {
                        Color fillColor = ColorsParser.stringToColor(s);
                        this.colorMap.put(3, fillColor);
                    } else if (defaultInfo.get("fill-3").contains("image")) {
                        img = ImageIO.read(ClassLoader.getSystemClassLoader().
                                getResourceAsStream(s));
                        this.imageMap.put(3, img);
                    }
                }
            } catch (IOException e) {
                System.out.println("no such image in directory");
                return null;
            }
        } else {
            System.out.println("missing fill parameter");
            return null;
        }
        if (blockInfo.containsKey("stroke") || defaultInfo.containsKey("stroke")) {
            if (blockInfo.containsKey("stroke")) {
                String colorName = blockInfo.get("stroke").substring(6, blockInfo.get("stroke").length() - 1);
                this.frameColor = ColorsParser.stringToColor(colorName);
            } else if (defaultInfo.containsKey("stroke")) {
                String colorName = defaultInfo.get("stroke").substring(6, defaultInfo.get("stroke").length() - 1);
                this.frameColor = ColorsParser.stringToColor(colorName);
            } else {
                System.out.println("missing stroke parameter");
                return null;
            }
        }
        return new Block(new Rectangle(new Point(xpos, ypos), width, height), this.colorMap,
                this.imageMap, frameColor, numOfHits);
    }
}