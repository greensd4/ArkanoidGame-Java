package geometry;
import java.util.List;

/**
 * Line.
 *
 * @author Daniel Greenspan.
 */
public class Line {
    private Point start;
    private Point end;
    /**
     * constructor.
     * @param start = this.start.
     * @param end   = this.end.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor.
     *
     * @param x1 = this.start.X.
     * @param y1 = this.start.Y.
     * @param x2 = this.end.X.
     * @param y2 = this.end.Y.
     */
    public Line(double x1, double y1, double x2, double y2) {
        double tempX1 = x1;
        double tempY1 = y1;
        double tempX2 = x2;
        double tempY2 = y2;
        this.start = new Point(tempX1, tempY1);
        this.end = new Point(tempX2, tempY2);
    }
    /**
     * length.
     * @return The length of the line.
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * middle.
     * calculates the middle point of a line
     *
     * @return The middle point of the line
     */
    public Point middle() {
        double tempX = (this.start.getX() + this.end.getX()) / 2;
        double tempY = (this.start.getY() + this.end.getY()) / 2;
        Point mid = new Point(tempX, tempY);
        return mid;
    }
    /**
     * start.
     *
     * @return The start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * end.
     * @return The end point of the line.
     */
    public Point end() {
        return this.end;
    }
    /**
     * isIntersecting.
     *
     * @param other line.
     * @return True if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        return (this.intersectionWith(other) != null);
    }
    /**
     * intersectionWith.
     * Gets another line and finds the intersection
     * point between the two lines.
     *
     * @param other Line.
     * @return The intersection point if there is one and null if not.
     */
    public Point intersectionWith(Line other) {
        Point intersection;
        double tempX, tempY;
        double x1 = this.start().getX();
        double x2 = this.end().getX();
        double y1 = this.start().getY();
        double y2 = this.end().getY();
        double x3 = other.start().getX();
        double x4 = other.end().getX();
        double y3 = other.start().getY();
        double y4 = other.end().getY();
        //checks if the lines are equal.
        if (this.equals(other)) {
            return null;
        }
        if (this.slope() == other.slope()) {
            return null;
        }
        //checks if one of the lines is vertical.
        if (this.slope() == Double.POSITIVE_INFINITY || other.slope() == Double.POSITIVE_INFINITY
                || this.slope() == Double.NEGATIVE_INFINITY
                || other.slope() == Double.NEGATIVE_INFINITY) {
            //if the first line is vertical.
            if (this.slope() == Double.POSITIVE_INFINITY || this.slope() == Double.NEGATIVE_INFINITY) {
                if ((Math.min(x3, x4) <= x1 && x1 <= Math.max(x4, x3))) {
                    tempX = x1;
                    tempY = x1 * other.slope() + other.intercept();
                    if (Math.min(y1, y2) <= tempY && tempY <= Math.max(y1, y2)) {
                        intersection = new Point(tempX, tempY);
                        return intersection;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }

            } else if (other.slope() == Double.POSITIVE_INFINITY || other.slope() == Double.NEGATIVE_INFINITY) {
                if ((Math.min(x1, x2) <= x3 && x3 <= Math.max(x1, x2))) {
                    tempX = x3;
                    tempY = x3 * this.slope() + this.intercept();
                    if (Math.min(y3, y4) < tempY && tempY < Math.max(y3, y4)) {
                        intersection = new Point(tempX, tempY);
                        return intersection;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }
        } else {
            //if the slopes are not equal.
            tempX = (other.intercept() - this.intercept()) / (this.slope() - other.slope());
            tempY = this.slope() * tempX + this.intercept();
            intersection = new Point(tempX, tempY);
            //checks if the intersection point is on the lines.
            if ((x1  <= tempX && tempX <= x2  && x3 <= tempX && tempX <= x4)
                    || (x1 <= tempX && tempX <= x2 && x4 <= tempX && tempX <= x3)
                    || (x2 <= tempX && tempX <= x1 && x3 <= tempX && tempX <= x4)
                    || (x2 <= tempX && tempX <= x1 && x4 <= tempX && tempX <= x3)) {
                return intersection;
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * equals.
     * Gets another Line and checks if they are equal.
     *
     * @param other Line.
     * @return True is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start)) {
            if (this.end.equals(other.end)) {
                return true;
            }
        }
        if (this.end.equals(other.start)) {
            if (this.start.equals(other.end)) {
                return true;
            }
        }
        return false;
    }
    /**
     * slope.
     * @return The slope value of this Line.
     */
    public double slope() {
        double m = (this.end.getY() - this.start.getY())
                / (this.end.getX() - this.start.getX());
        if (m == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        } else {
            if (m == Double.NEGATIVE_INFINITY) {
                return Double.NEGATIVE_INFINITY;
            } else {
                return m;
            }
        }
    }
    /**
     * intercept.
     *
     * @return The intercept value of this Line.
     */
    public double intercept() {
        return (this.start.getY() - (this.slope() * this.start.getX()));
    }

    /**
     * closestIntersectionToStartOfLine.
     *
     * @param rect - the rectangle beeing checked for intersection points.
     * @return the closest intersection point with rectangle rect.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> listOfIntersections = rect.intersectionPoints(this);
        Point closest = null;
        double minDist = Double.POSITIVE_INFINITY;
        for (Point p : listOfIntersections) {
            if (this.start().distance(p) < minDist) {
                minDist = this.start().distance(p);
                closest = p;
            }
        }
        return closest;
    }
}