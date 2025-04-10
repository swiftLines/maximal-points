/*
Jeremy Underwood
Programming Project 2
4/9/25
The Point class instantiates points based on x and y coordinates. It has a method
that determines if another point is below and left of the current point, and an
overridden method to compare points by the x coordinate.
 */
public class Point implements Comparable<Point> {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = Math.round(x);
        this.y = Math.round(y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isBelowAndLeftOf(Point other) {
        return this.x < other.x && this.y < other.y;
    }

    @Override
    public int compareTo(Point other) {
        return Double.compare(this.x, other.x);
    }
}//end class

