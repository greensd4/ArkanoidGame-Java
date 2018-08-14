package info;

import geometry.Point;

/**
 * Velocity.
 *
 * @author Daniel Greenspan.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor.
     *
     * @param dx = this.dx.
     * @param dy = this.dy.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * getDx.
     *
     * @return The dx value of the velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * getDy.
     *
     * @return The dy value of the velocity.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * fromAngleAndSpeed.
     *
     * @param angle - the angel of the ball movement.
     * @param speed - the speed of the ball movement.
     * @return a new velocity for the ball.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dX = Math.sin(Math.toRadians(angle)) * speed;
        double dY = -Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dX, dY);
    }

    /**
     * applyToPoint.
     *
     * @param p - a point.
     * @param dt - the amount of time passed since last call.
     * @return a new Point - with adding dx and dy to the (x,y) of the point.
     */
    public Point applyToPoint(Point p, double dt) {
        return new Point(p.getX() + (this.dx * dt), p.getY() + (this.dy * dt));
    }

    /**
     * setVelocity.
     *
     * @param dX - the angel of the object.
     * @param dY - the speed of the object.
     */
    public void setVelocity(double dX, double dY) {
        this.dx = dX;
        this.dy = dY;
    }

    /**
     * getSpeed.
     * @return the object's speed.
     */
    public double getSpeed() { return Math.sqrt((dx * dx) + (dy * dy));
    }
}
