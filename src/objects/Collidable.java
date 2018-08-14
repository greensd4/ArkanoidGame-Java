package objects;

import geometry.Point;
import geometry.Rectangle;
import info.Velocity;

/**
 * Collidable.
 *
 * @author Daniel Greenspan.
 */
public interface Collidable {
    /**
     * getCollisionRectangle.
     *
     * @return the collidable rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * hit.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us.
     *
     * @param collisionPoint - the collision point.
     * @param currentVelocity - the velocity before the hit.
     * @param hitter - the ball who hits the object.
     * @return - a new velocity after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}