package objects;

import gameproccess.GameEnvironment;
import gameproccess.GameLevel;
import geometry.Line;
import geometry.Point;
import biuoop.DrawSurface;
import info.CollisionInfo;
import info.Velocity;
import listeners.HitListener;

import java.awt.Color;
import java.util.List;

/**
 * Ball.
 *
 * @author Daniel Greenspan.
 */
public class Ball implements Sprite {

    private List<HitListener> hitListeners;
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity velocity;
    private Boundaries boundaries;
    private GameEnvironment game;


    /**
     * constructor.
     *
     * @param center - the center of the ball.
     * @param r      - the radius of the ball.
     * @param color  - the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.setVelocity(0, 0);
    }

    /**
     * getX.
     *
     * @return The X value of center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * getY.
     *
     * @return The Y value of center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * getSize.
     *
     * @return The radius size of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * getVelocity.
     *
     * @return The velocity value of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * getBoundaries.
     *
     * @return The boundaries values of the ball.
     */
    public Boundaries getBoundaries() {
        return this.boundaries;
    }

    /**
     * getColor.
     *
     * @return The color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * getGameEnvironment.
     *
     * @return the ball's game environment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.game;
    }

    /**
     * removeHitListener.
     *
     * @param hl - an hit listener to be removed.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * getTrajectory.
     * @param dt -the amount of time.
     * @return trajectory - the trajectory of the ball.
     */
    public Line getTrajectory(double dt) {
        Point endOfTrajectory = this.velocity.applyToPoint(this.center, dt);
        Line trajectory = new Line(this.center, endOfTrajectory);
        return trajectory;
    }

    /**
     * drawOn.
     * draw the ball on the given DrawSurface.
     *
     * @param surface - the drew surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * setVelocity.
     * Set the ball's velocity by assigning a velocity.
     *
     * @param v - a velocity value..
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v.getDx(), v.getDy());
    }

    /**
     * setVelocity.
     * Set the ball's velocity by assigning dx and dy.
     *
     * @param dx -  velocity dx value.
     * @param dy -  velocity dy value.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * setBoundaries.
     * Set the ball's boundaries by assigning it a start and end point.
     *
     * @param p1 - point to start the boundaries.
     * @param p2 - point to end the boundaries.
     */
    public void setBoundaries(Point p1, Point p2) {
        this.boundaries = new Boundaries(p1, p2);
    }

    /**
     * setGameEnvironment.
     *
     * @param g - a list of objects in ball's game environment.
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.game = g;
    }

    /**
     * moveOneStep.
     * Moves the ball on step forward.
     * If the ball gets to his boundaries then it switches its direction.
     * @param dt - the amount of seconds passed since the last call.
     */
    public void moveOneStep(double dt) {

        CollisionInfo collision = this.game.getClosestCollision(this.getTrajectory(dt));
        if (collision == null) {
            //this.setVelocity(this.velocity.getDx(), dt);
            this.center = this.velocity.applyToPoint(this.center, dt);
            return;
        }
        Collidable block = collision.collisionObject();
        // draws the ball in the correct location
        almostHit(collision.collisionPoint());
        this.setVelocity(block.hit(this, collision.collisionPoint(), this.getVelocity()));
        if (this.center.getY() <= 56) {
            this.center = new Point(this.center.getX(), 57);
        }
    }

    /**
     * almostHit.
     * moves the center of the ball a bit closer to collision point.
     *
     * @param collision - the closest collision point.
     */
    public void almostHit(Point collision) {

        Line l = new Line(collision, this.center);
       for (int i = 0; i < 2; i++) {
            Point mid = l.middle();
            l = new Line(collision, mid);
        }
        this.center = l.end();
    }

    /**
     * timePassed.
     * @param dt - the amount of seconds passed since the last call.
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * addToGame.
     * adds the ball to to game.
     *
     * @param g - the game to be added to.
     */

    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * removeFromGame.
     *
     * @param g - the game the ball being removed from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}