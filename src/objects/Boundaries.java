package objects;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

/**
 * Boundaries.
 *
 * @author Daniel Greenspan.
 */
public class Boundaries {
    private Line bottom;
    private Line top;
    private Line left;
    private Line right;
    /**
     * constructor.
     *
     * @param p1 - a start point for the boundary.
     * @param p2 - an end point for the boundary.
     */
    public Boundaries(Point p1, Point p2) {
        this.bottom = new Line(p1.getX(), p2.getY(), p2.getX(), p2.getY());
        this.top = new Line(p1.getX(), p1.getY(), p2.getX(), p1.getY());
        this.left = new Line(p1.getX(), p1.getY(), p1.getX(), p2.getY());
        this.right = new Line(p2.getX(), p1.getY(), p2.getX(), p2.getY());
    }
    /**
     * getBottomBoundary.
     *
     * @return The value of bottom bound.
     */
    public Line getBottomBoundary() {
        return this.bottom;
    }
    /**
     * getTopBoundary.
     *
     * @return The value of top bound.
     */
    public Line getTopBoundary() {
        return this.top;
    }
    /**
     * getLeftBoundary.
     *
     * @return The value of left bound.
     */
    public Line getLeftBoundary() {
        return this.left;
    }
    /**
     * getRightBoundary.
     *
     * @return The value of right bound.
     */
    public Line getRightBoundary() {
        return this.right;
    }

    /**
     * rectangleBoundaries.
     *
     * @param rect - the rectangle which his boundaries being returned.
     *
     * @return rectBoundaries - a list of lines which contains the rectangle boundaries.
     */
    public Line[] rectangleBoundaries(Rectangle rect) {
        Line[] rectBoundaries = {rect.getBoundaries().getBottomBoundary(), rect.getBoundaries().getTopBoundary(),
                rect.getBoundaries().getLeftBoundary(), rect.getBoundaries().getRightBoundary() };
        return rectBoundaries;
    }
}
