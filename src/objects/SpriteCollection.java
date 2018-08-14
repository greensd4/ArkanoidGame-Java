package objects;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * SpriteCollection.
 *
 * @author Daniel Greenspan.
 */
public class SpriteCollection {

    private List spriteCollection;

    /**
     * contractor.
     */
    public SpriteCollection() {
        this.spriteCollection = new ArrayList<Sprite>();
    }

    /**
     * addSprite.
     * adds a sprite to the collection.
     *
     * @param s - the sprite to be added.
     */
    public void addSprite(Sprite s) {
        this.spriteCollection.add(s);
    }

    /**
     * removeSprite.
     *
     * @param s - a Sprite to be removed.
     *          Removes a Sprite from game's sprite list.
     */
    public void removeSprite(Sprite s) {
        this.spriteCollection.remove(s);
    }

    /**
     * notifyAllTimePassed.
     * Call timePassed() on all sprites.
     * @param dt - the amount of seconds passed since the last call.
     */
    public void notifyAllTimePassed(double dt) {
        List<Sprite> temp = new ArrayList<>();
        temp.addAll(this.spriteCollection);
        for (Object entity : temp) {
            ((Sprite) entity).timePassed(dt);
        }
    }

    /**
     * drawAllOn.
     * Call drawOn(d) on all sprites.
     *
     * @param d - the draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Object entity : spriteCollection) {
            ((Sprite) entity).drawOn(d);
        }
    }
}