package gameproccess;


import geometry.Line;
import geometry.Point;
import info.CollisionInfo;
import objects.Collidable;

import java.util.ArrayList;
import java.util.List;


/**
 * GameEnvironment.
 * @author Daniel Greenspan.
 */
public class GameEnvironment {
    private List gameEnvironmentList;

    /**
     * Contractor.
     */
    public GameEnvironment() {
        this.gameEnvironmentList = new ArrayList<Collidable>();
    }

    /**
     * addCollidable.
     *
     * @param c - the collidable object to add to the game environment.
     */
    public void addCollidable(Collidable c) {
        this.gameEnvironmentList.add(c);
    }
    /**
     * removeCollidable.
     * @param c - a Collidable to be removed.
     * Removes a collidable from game's collidable list.
     */
    public void removeCollidable(Collidable c) { this.gameEnvironmentList.remove(c); }

    /**
     * getClosestCollision.
     *
     * @param trajectory - the trajectory line of ball.
     * @return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.gameEnvironmentList.isEmpty()) {
            return null;
        }
        Collidable closestBlock = null;
        Collidable block;
        Point closestPoint = null;
        Point closeEnough;
        double dis = Double.POSITIVE_INFINITY;
        for (int i = 0; i < this.gameEnvironmentList.size(); i++) {
            block = (Collidable) this.getGameEnvironmentList().get(i);
            block.getCollisionRectangle().setBoundaries(block.getCollisionRectangle().getUpperLeft(),
                    block.getCollisionRectangle().getLowerRight());
            closeEnough = trajectory.closestIntersectionToStartOfLine(block.getCollisionRectangle());
            if (trajectory.start().distance(closeEnough) < dis) {
                dis = trajectory.start().distance(closeEnough);
                closestPoint = closeEnough;
                closestBlock = block;
            }
        }
        if (closestBlock == null) {
            return null;
        }
        return new CollisionInfo(closestPoint, closestBlock);
    }

    /**
     * getGameEnvironmentList.
     * @return the list of collidables.
     */
    public List getGameEnvironmentList() {
        return this.gameEnvironmentList;
    }
}