package info;


import geometry.Point;
import objects.Collidable;

/**
 * CollisionInfo.
 *
 * @author Daniel Greenspan.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor.
     * @param cP - the collision point.
     * @param cO - the object involved in the collision.
     *
     */
    public CollisionInfo(Point cP, Collidable cO) {
        this.collisionPoint = cP;
        this.collisionObject = cO;
    }
    /**
     * collisionPoint.
     *
     * @return The point at which the collision occurs..
     */
    public Point collisionPoint() { return this.collisionPoint; }

    /**
     * collisionObject.
     *
     * @return The collidable object involved in the collision.
     */
    public Collidable collisionObject() { return this.collisionObject; }
}