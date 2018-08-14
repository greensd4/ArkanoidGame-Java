package objects;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gameproccess.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import info.Velocity;

import java.awt.Color;
import java.util.Random;

/**
 * Paddle.
 *
 * @author Daniel Greenspan.
 */
public class Paddle implements Sprite, Collidable {
    //members
    private biuoop.KeyboardSensor keyboard;
    private Block paddle;
    private int speed;

    /**
     * constructor.
     *
     * @param b     - a new paddle block.
     * @param g     - the game.
     * @param speed - the paddle's speed.
     */
    public Paddle(GameLevel g, Block b, int speed) {
        this.paddle = b;
        this.keyboard = g.getScreen().getKeyboardSensor();
        this.speed = speed;
    }

    /**
     * moveLeft.
     * moves paddle to the left hand side.
     * @param dt - the amount of seconds passed since the last call.
     */
    public void moveLeft(double dt) {

        Point moveUpperLeft = new Point(paddle.getCollisionRectangle().getUpperLeft().getX() - (this.speed * dt),
                paddle.getCollisionRectangle().getUpperLeft().getY());
        if (moveUpperLeft.getX() <= 25) {
            moveUpperLeft = new Point(26, paddle.getCollisionRectangle().getUpperLeft().getY());
        }
        this.setLocation(moveUpperLeft);
    }

    /**
     * moveRight.
     * moves paddle to the right hand side.
     * @param dt - the amount of seconds passed since the last call.
     */
    public void moveRight(double dt) {
        Point moveUpperLeft = new Point(paddle.getCollisionRectangle().getUpperLeft().getX() + (this.speed * dt),
                paddle.getCollisionRectangle().getUpperLeft().getY());
        if (moveUpperLeft.getX() >= 775 - this.getPaddleWidth()) {
            moveUpperLeft = new Point(774 - this.getPaddleWidth(),
                    paddle.getCollisionRectangle().getUpperLeft().getY());
        }

        this.setLocation(moveUpperLeft);
    }

    /**
     * setLocation.
     *
     * @param p - a point to set the upper left of the paddle by.
     */
    public void setLocation(Point p) {
        this.paddle.getCollisionRectangle().setUpperLeft(p);
    }

    /**
     * timePassed.
     * checks which direction the user wants to move and use the correct method for it.
     * @param dt  - the time past since last call.
     */
    public void timePassed(double dt) {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft(dt);
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight(dt);
        }
    }

    /**
     * drawOn
     * Draws the paddle.
     *
     * @param d - the drew surface.
     */
    public void drawOn(DrawSurface d) {
        int blockX = (int) paddle.getCollisionRectangle().getUpperLeft().getX();
        int blockY = (int) paddle.getCollisionRectangle().getUpperLeft().getY();
        int blockWidth = (int) paddle.getCollisionRectangle().getWidth();
        int blockHeight = (int) paddle.getCollisionRectangle().getHeight();
        d.setColor(Color.orange);
        d.fillRectangle(blockX, blockY, blockWidth, blockHeight);
        d.setColor(Color.BLACK);
        d.drawRectangle(blockX, blockY, blockWidth, blockHeight);
    }

    /**
     * getCollisionRectangle.
     *
     * @return the paddle's rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle.getCollisionRectangle();
    }

    /**
     * hit.
     *
     * @param collisionPoint  - the collision point.
     * @param currentVelocity - the velocity before the hit.
     * @param hitter          - the ball how hits the block.
     * @return a new velocity according to the collision point on the paddle.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dX = currentVelocity.getDx();
        double dY = currentVelocity.getDy();

        if (collisionPoint.getY() == this.paddle.getCollisionRectangle().getLowerRight().getY()) {
            dY = (-1) * dY;
        }
        if (collisionPoint.getY() == this.paddle.getCollisionRectangle().getUpperLeft().getY()) {
            //dY = (-1) * dY;
            int length = this.getPaddleWidth();
            int funPaddleDivider = 10;
            int mid = funPaddleDivider / 2;
            for (int i = 0; i < funPaddleDivider; i++) {
                if ((collisionPoint.getX() >= this.paddle.getCollisionRectangle().getUpperLeft().getX()
                        + (i * (length / funPaddleDivider))) && (collisionPoint.getX()
                        < this.getCollisionRectangle().getUpperLeft().getX()
                        + ((i + 1) * (length / funPaddleDivider)))) {
                    if (i == mid) {
                        return new Velocity(dX, (-1) * dY);
                    } else {
                        currentVelocity =  Velocity.fromAngleAndSpeed(300 + ((160 / funPaddleDivider) * i),
                                hitter.getVelocity().getSpeed());
                        return currentVelocity;
                    }
                }
            }
        }
        if (collisionPoint.getX() == this.paddle.getCollisionRectangle().getUpperLeft().getX()) {
            dX = (-1) * dX;
        }
        if (collisionPoint.getX() == this.paddle.getCollisionRectangle().getLowerRight().getX()) {
            dX = (-1) * dX;
        }
        return new Velocity(dX, dY);
    }

    /**
     * addToGame.
     * Add this paddle to the game as collidable and as sprite.
     *
     * @param g - the game to be added to.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * removeFromGame.
     *
     * @param g - the game the ball being removed from.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    /**
     * getPaddleWidth.
     *
     * @return the paddle's width.
     */
    public int getPaddleWidth() {
        return (int) this.getCollisionRectangle().getWidth();
    }

    /**
     * getPaddleHeight.
     *
     * @return the paddle's height.
     */
    public int getPaddleHeight() {
        return (int) this.getCollisionRectangle().getHeight();
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