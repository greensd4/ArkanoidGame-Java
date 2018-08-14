package geometry;
/**
 * Point.
 * @author Daniel Greenspan.
 */
public class Point {
    private double x;
    private double y;
    /**
     * constructor.
     *
     * @param x = this.x.
     * @param y = this.y.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * distance.
     * Gets another point and calculates their distance.
     *
     * @param other point.
     * @return The distance between the two points.
     */
    public double distance(Point other) {
        if (other == null) {
            return Double.POSITIVE_INFINITY;
        }
        return Math.sqrt((this.getX() - other.getX()) * (this.getX() - other.getX())
                + (this.getY() - other.getY()) * (this.getY() - other.getY()));
    }
    /**
     * equals.
     * Gets another point and checks if they are equal.
     *
     * @param other point.
     * @return True is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        if ((this.x == other.getX()) && (this.y == other.getY())) {
            return true;
        }
        return false;
    }
    /**
     * getX.
     * @return The x value of this point.
     */

    public double getX() {
        return this.x;
    }

    /**
     * getY.
     *
     * @return The Y value of this point.
     */
    public double getY() {
        return this.y;
    }
}