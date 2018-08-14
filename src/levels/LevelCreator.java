package levels;

import biuoop.DrawSurface;
import info.Velocity;
import objects.Block;
import objects.Sprite;

import javax.imageio.ImageIO;
import java.awt.Image;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
/**
 * LevelCreator.
 *
 * @author Daniel Greenspan.
 */
public class LevelCreator {
    private Map<String, String> definitions;
    private List<Block> gameBlocks;

    /**
     * constructor.
     *
     * @param definitions - a map with level information as strings.
     * @param gameBlocks  - a list of the game blocks.
     */
    public LevelCreator(Map<String, String> definitions, List<Block> gameBlocks) {
        this.definitions = definitions;
        this.gameBlocks = gameBlocks;
    }

    /**
     * getLevel.
     * @return a new game level.
     */
    public LevelInformation getLevel() {
        List<Block> blocks = this.gameBlocks;
        List<Velocity> ballsVelocity = new LinkedList<Velocity>();
        String[] velocities = this.definitions.get("ball_velocities").split(" ");
        for (int i = 0; i < velocities.length; i++) {
            String[] currentVelocity = velocities[i].split(",");
            Velocity v = Velocity.fromAngleAndSpeed(Integer.parseInt(currentVelocity[0]),
                    Integer.parseInt(currentVelocity[1]));
            ballsVelocity.add(v);
        }
        int paddleWidth = Integer.parseInt(this.definitions.get("paddle_width"));
        int paddleSpeed = Integer.parseInt(this.definitions.get("paddle_speed"));
        int numOfBlocks = Integer.parseInt(this.definitions.get("num_blocks"));
        Sprite background = this.getBackground();
        String levelName = this.definitions.get("level_name");
        return new LevelInformation() {
            @Override
            public int numberOfBalls() {
                return ballsVelocity.size();
            }

            @Override
            public List<Velocity> initialBallVelocities() {
                return ballsVelocity;
            }

            @Override
            public int paddleSpeed() {
                return paddleSpeed;
            }
            @Override
            public int paddleWidth() {
                return paddleWidth;
            }

            @Override
            public String levelName() {
                return levelName;
            }

            @Override
            public Sprite getBackground() {
                return background;
            }

            @Override
            public List<Block> blocks() {
                return blocks;
            }

            @Override
            public int numberOfBlocksToRemove() {
                return numOfBlocks;
            }
        };
    }

        /**
         * numberOfBalls.
         * how many balls should be played in this level.
         *
         * @return number of balls in level.
         */
        public int numberOfBalls() {
            String[] velocities = definitions.get("ball_velocities").split(" ");
            return velocities.length;
        }

        /**
         * initialBallVelocities.
         * creates a list of velocities for the balls in the level.
         *
         * @return a list of velocities.
         */
        public List<Velocity> initialBallVelocities() {
            List<Velocity> ballsVelocity = null;
            String[] velocities = this.definitions.get("ball_velocities").split(" ");
            for (int i = 0; i < velocities.length; i++) {
                String[] currentVelocity = velocities[i].split(",");
                Velocity v = Velocity.fromAngleAndSpeed(Integer.parseInt(currentVelocity[0]),
                        Integer.parseInt(currentVelocity[1]));
                ballsVelocity.add(v);
            }
            return ballsVelocity;
        }

        /**
         * paddleSpeed.
         *
         * @return the speed of the paddle.
         */

    public int paddleSpeed() {
        String paddleS = definitions.get("paddle_speed");
        int paddleSpeed = Integer.parseInt(paddleS);
        return paddleSpeed;
    }

    /**
     * paddleWidth.
     *
     * @return the level's paddle width.
     */
    public int paddleWidth() {
        String paddleW = definitions.get("paddle_width");
        int paddleWidth = Integer.parseInt(paddleW);
        return paddleWidth;
    }

    /**
     * levelName.
     *
     * @return a string with the level's name.
     */
    public String levelName() {
        return definitions.get("level_name");

    }

    /**
     * getBackground.
     *
     * @return a sprite with the background of the level
     */
    public Sprite getBackground() {
        if (definitions.get("background").contains("color")) {
            String defColor = definitions.get("background");
            defColor = defColor.replace("color(", "");
            defColor = defColor.replace("))", ")");
            String color = defColor;
            return new Sprite() {
                @Override
                public void drawOn(DrawSurface d) {
                    d.setColor(ColorsParser.stringToColor(color));
                    d.fillRectangle(0, 0, 800, 600);
                }

                @Override
                public void timePassed(double dt) {
                }
            };
        } else if (definitions.get("background").contains("image")) {
            String defImage = definitions.get("background");
            defImage = defImage.replace("image(", "");
            defImage = defImage.replace(")", "");
            Image img = null;
            try {
                img = ImageIO.read(ClassLoader.getSystemResourceAsStream(defImage));
            } catch (IOException e) {
                System.out.println("no such image in directory");
            }
            Image image = img;
            return new Sprite() {
                @Override
                public void drawOn(DrawSurface d) {
                    d.drawImage(0, 0, image);
                }

                @Override
                public void timePassed(double dt) {
                }
            };
        } else { return null; }
    }
    /**
     * blocks.
     * The Blocks that make up this level,
     * each block contains its size, color and location.
     *
     * @return a list of initialized blocks.
     */
    public List<Block> blocks() {
        return gameBlocks;
    }

    /**
     * * numberOfBlocksToRemove.
     *
     * @return the number of levels that should be removed before
     * the level is considered to be "cleared".
     */
    public int numberOfBlocksToRemove() {
        int numOfBlocks = Integer.parseInt(definitions.get("num_blocks"));
        return numOfBlocks;
    }
}