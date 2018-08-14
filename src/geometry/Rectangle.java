package geometry;

import biuoop.DrawSurface;
import objects.Boundaries;

import java.awt.Color;

/**
 * Rectangle.
 *
 * @author Daniel Greenspan.
 */
public class Rectangle {
    private Point location;
    private double width;
    private double height;
    private Boundaries boundaries;
    private Color color;
    /**
     * constructor.
     *
     * @param upperLeft - the upper left point of rectangle.
     * @param width      - the width of rectangle.
     * @param height  - the height of rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.location = upperLeft;
        this.width = width;
        this.height = height;
        this.color  = null;
    }
    /**
     * constructor.
     *
     * @param upperLeft - the upper left point of rectangle.
     * @param width      - the width of rectangle.
     * @param height  - the height of rectangle.
     * @param color - the color of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.location = upperLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    /**
     * intersectionPoints.
     *
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line - a line to check intersection points with.
     * @return intersectionList - a list of intersection points with the rectangle.
     */
    public java.util.List intersectionPoints(Line line) {
        //the list of the intersections of the line and the rectangle.
        java.util.List<Point> intersectionList = new java.util.ArrayList<Point>();

        Line[] linesArr = boundaries.rectangleBoundaries(this);
        Point intersectionPoint;

        //checking and adding the intersection points to the intersection points list.
        for (int i = 0; i < 4; i++) {
            intersectionPoint = line.intersectionWith(linesArr[i]);
            if (intersectionPoint != null) {
                intersectionList.add(intersectionPoint);
            }
        }
        return intersectionList;
    }
    /**
     * getWidth.
     * @return The width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * getHeight.
     * @return The height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * getUpperLeft.
     * @return The upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.location;
    }
    /**
     * getBoundaries.
     * @return The boundaries of rectangle.
     */
    public Boundaries getBoundaries() { return this.boundaries; }
    /**
     * getColor.
     * @return the rectangle's color.
     */
    public Color getColor() { return this.color; }
    /**
     * setBoundaries.
     *
     * Sets the rectangle's boundaries.
     * @param p1 - upper left point of the rectangle.
     * @param p2 - lower right point of the rectangle.
     */
    public void setBoundaries(Point p1, Point p2) {
        this.boundaries = new Boundaries(p1, p2);
    }
    /**
     * setUpperLeft.
     * @param p - a point to set the upper left to.
     */
    public void setUpperLeft(Point p) { this.location = p; }
    /**
     * getLowerRight.
     * @return The lower right point of the rectangle.
     */
    public Point getLowerRight() {
        double x = getUpperLeft().getX() + getWidth();
        double y = getUpperLeft().getY() + getHeight();
        Point lowRight = new Point(x, y);
        return lowRight;
    }
    /**
     * drawOn.
     * draw the ball on the given DrawSurface.
     *
     * @param surface - the drew surface.
     */
    public void drawOn(DrawSurface surface) {
        int blockX = (int) this.getUpperLeft().getX();
        int blockY = (int) this.getUpperLeft().getY();
        int blockWidth = (int) this.getWidth();
        int blockHeight = (int) this.getHeight();
        surface.setColor(this.color);
        surface.drawRectangle(blockX, blockY, blockWidth, blockHeight);
    }
}