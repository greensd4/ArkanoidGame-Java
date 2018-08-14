package objects;

import biuoop.DrawSurface;
import gameproccess.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import info.Velocity;
import listeners.HitListener;
import listeners.HitNotifier;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Block.
 *
 * @author Daniel Greenspan.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private List<HitListener> hitListeners;
    private Rectangle block;
    private int numOfHits;
    private Map<Integer, Color> colorMap;
    private Map<Integer, Image> imageMap;
    private Color frame;

    /**
     * contractors.
     *
     * @param block        - the block.
     * @param blockRemover - the blockRemover listener.
     */
    public Block(Rectangle block, HitListener blockRemover) {
        this.block = block;
        //this.color = block.getColor();
        this.block.setBoundaries(this.block.getUpperLeft(), this.block.getLowerRight());
        this.hitListeners = new ArrayList<>();
        this.hitListeners.add(blockRemover);
    }
    /**
     * contractors.
     *
     * @param block        - the block.
     * @param imageMap - mapping containing remaining hits and appropriate image.
     * @param frameColor - the frame color of the block.
     * @param colorMap - mapping containing remaining hits and appropriate color.
     * @param hitPoint - number of hits to start the block with.
     */
    public Block(Rectangle block, Map<Integer, Color> colorMap, Map<Integer, Image> imageMap,
                 Color frameColor, int hitPoint) {
        this.block = block;
        //this.color = block.getColor();
        this.block.setBoundaries(this.block.getUpperLeft(), this.block.getLowerRight());
        this.hitListeners = new ArrayList<>();
        this.colorMap = colorMap;
        this.imageMap = imageMap;
        this.frame = frameColor;
        this.numOfHits = hitPoint;
    }

    /**
     * contractor.
     *
     * @param upperLeft     - block location.
     * @param width         - block width.
     * @param height        - block height.
     * @param color         - block color.
     * @param ballRemover   - the ballRemover  hitListener.
     * @param blockRemover  - hit listener.
     * @param scoreTracking - the score hit listener.
     */
    public Block(Point upperLeft, double width, double height, Color color,
                 HitListener ballRemover, HitListener blockRemover, HitListener scoreTracking) {
        this.block = new Rectangle(upperLeft, width, height, color);
        this.hitListeners = new ArrayList<>();
        this.hitListeners.add(ballRemover);
        this.hitListeners.add(blockRemover);
        this.hitListeners.add(scoreTracking);
    }

    /**
     * contractor.
     *
     * @param upperLeft     - block location.
     * @param width         - block width.
     * @param height        - block height.
     * @param color         - block color.
     * @param blockRemover  - hit listener.
     * @param scoreTracking - the score hit listener.
     */
    public Block(Point upperLeft, double width, double height, Color color,
                 HitListener blockRemover, HitListener scoreTracking) {
        this.block = new Rectangle(upperLeft, width, height, color);
        this.hitListeners = new ArrayList<>();
        this.hitListeners.add(blockRemover);
        this.hitListeners.add(scoreTracking);
    }

    /**
     * contractor.
     *
     * @param upperLeft   - block location.
     * @param width       - block width.
     * @param height      - block height.
     * @param color       - block color.
     * @param ballRemover - hit listener.
     */
    public Block(Point upperLeft, double width, double height, Color color,
                 HitListener ballRemover) {
        this.block = new Rectangle(upperLeft, width, height, color);
        this.hitListeners = new ArrayList<>();
        this.hitListeners.add(ballRemover);
    }

    /**
     * contractors.
     *
     * @param block         - the block.
     * @param blockRemover  - the blockRemover listener.
     * @param scoreTracking - the score hit listener.
     */
    public Block(Rectangle block, HitListener blockRemover, HitListener scoreTracking) {
        this.block = block;
       //this.color = block.getColor();
        this.block.setBoundaries(this.block.getUpperLeft(), this.block.getLowerRight());
        this.hitListeners = new ArrayList<>();
        this.hitListeners.add(blockRemover);
        this.hitListeners.add(scoreTracking);
    }

    /**
     * constructor.
     *
     * @param upperLeft - block location.
     * @param width     - block width.
     * @param height    - block height.
     * @param color     - block color.
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.block = new Rectangle(upperLeft, width, height, color);
    }

    /**
     * constructor.
     *
     * @param rec - the block.
     */
    public Block(Rectangle rec) {
        this.block = rec;
    }


    /**
     * getCollisionRectangle.
     *
     * @return the shape of block - a rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.block;
    }

    /**
     * addHitListener.
     * Add hl as a listener to hit events.
     *
     * @param hl - a listener to ba added.
     */
    public void addHitListener(HitListener hl) {
        if (this.hitListeners == null) {
            List hit = new ArrayList<>();
            hit.add(hl);
            this.hitListeners = hit;
        } else {
            this.hitListeners.add(hl);
        }
    }

    /**
     * removeHitListener.
     * Remove hl from the list of listeners.
     *
     * @param hl - a listener to be removed.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * getHitListenerList.
     *
     * @return this block listeners list.
     */
    public List<HitListener> getHitListenerList() {
        return this.hitListeners;
    }

    /**
     * hit.
     * Checks if the object hit the block on top/bottom or left/right and changes
     * the velocity accordingly.
     *
     * @param collisionPoint  - the collision point of an object with block.
     * @param currentVelocity - the current velocity of the object.
     * @param hitter          - the ball how hits the block.
     * @return v - a new velocity.
     */


    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dX = hitter.getVelocity().getDx();
        double dY = hitter.getVelocity().getDy();
        // this.block.setBoundaries(this.block.getUpperLeft(), this.block.getLowerRight());

        if (collisionPoint.getY() == this.block.getLowerRight().getY()) {
            dY = (-1) * dY;
        }
        if (collisionPoint.getY() == this.block.getUpperLeft().getY()) {
            dY = (-1) * dY;
        }
        if (collisionPoint.getX() == this.block.getUpperLeft().getX()) {
            dX = (-1) * dX;
        }
        if (collisionPoint.getX() == this.block.getLowerRight().getX()) {
            dX = (-1) * dX;
        }
        currentVelocity.setVelocity(dX, dY);
        if (this.numOfHits == 9) {
            notifyHit(hitter);
            return currentVelocity;
        }
        if (this.numOfHits >= 0) {
            this.numOfHits = getNumOfHits() - 1;

            currentVelocity = new Velocity(dX, dY);
            this.notifyHit(hitter);
            return currentVelocity;
        }

        return currentVelocity;
    }

    /**
     * notifyHit.
     *
     * @param hitter - the ball who hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * drawOn.
     *
     * @param surface - the draw surface.
     */
    public void drawOn(DrawSurface surface) {
        Integer hits = this.getNumOfHits();
        int blockX = (int) block.getUpperLeft().getX();
        int blockY = (int) block.getUpperLeft().getY();
        int blockWidth = (int) block.getWidth();
        int blockHeight = (int) block.getHeight();
        if (colorMap != null || imageMap != null) {
            if (colorMap.containsKey(hits)) {
                surface.setColor(this.colorMap.get(numOfHits));
                surface.fillRectangle(blockX, blockY, blockWidth, blockHeight);

            }
            if (imageMap.containsKey(hits)) {
                surface.drawImage(blockX, blockY, imageMap.get(hits));

            }
        } else {
            surface.setColor(Color.gray);
            surface.fillRectangle(blockX, blockY, blockWidth, blockHeight);
        }
        if (frame != null) {
            surface.setColor(frame);
            surface.drawRectangle(blockX, blockY, blockWidth, blockHeight);
            return;
        }
    }

    /**
     * timePassed.
     * @param dt - the amount.
     */
    public void timePassed(double dt) {
    }

    /**
     * addToGame.
     * adds the block to the game as collidable and as sprite.
     *
     * @param g - the game to be added to.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * removeFromGame.
     * Removes the block from the gameLevel as collidable and as sprite.
     *
     * @param gameLevel - the gameLevel to be added to.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * setNumOfHits.
     * sets the number of hits initialized for the block.
     *
     * @param n - number of hits to set for block.
     */
    public void setNumOfHits(int n) {
        this.numOfHits = n;
    }

    /**
     * getNumOfHits.
     *
     * @return - the number of hits of block.
     */
    public int getNumOfHits() {
        return this.numOfHits;
    }


    /**
     * setRandomColor.
     * Creates a new color.
     *
     * @return A color
     */
    public static Color setRandomColor() {
        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        Color c = new Color(r, g, b);
        return c;
    }
}
