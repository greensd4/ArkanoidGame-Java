package levels;

import java.awt.Color;
/**
 * ColorsParser.
 *
 * @author Daniel Greenspan.
 */
public class ColorsParser {

    /**
     * stringToColor.
     * @param s - a string to turn to color.
     * @return - a Color object with selected color.
     */
    public static java.awt.Color stringToColor(String s) {
        if (s.contains("black")) {
            return (java.awt.Color.black);
        }
        if (s.contains("blue")) {
            return (java.awt.Color.blue);
        }
        if (s.contains("cyan")) {
            return (java.awt.Color.cyan);
        }
        if (s.contains("gray")) {
            return (java.awt.Color.gray);
        }
        if (s.contains("green")) {
            return (java.awt.Color.green);
        }
        if (s.contains("lightGray")) {
            return (java.awt.Color.lightGray);
        }
        if (s.contains("magenta")) {
            return (java.awt.Color.magenta);
        }
        if (s.contains("orange")) {
            return (java.awt.Color.orange);
        }
        if (s.contains("pink")) {
            return (java.awt.Color.pink);
        }
        if (s.contains("red")) {
            return (java.awt.Color.red);
        }
        if (s.contains("white")) {
            return (java.awt.Color.white);
        }
        if (s.contains("yellow")) {
            return (java.awt.Color.yellow);
        }
        if (s.contains("RGB")) {
            String rgbString = s.substring(4, s.length() - 1);
            String[] rgb = rgbString.split(",");
            return new Color(Integer.parseInt(rgb[0]),
                    Integer.parseInt(rgb[1]),
                    Integer.parseInt(rgb[2]));
        }
        return null;
    }
}