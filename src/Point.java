public class Point implements Comparable<Point> {
    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Returns true if the other point is below and to the left of this point
    public boolean isBelowAndLeftOf(Point other) {
        return other.x < this.x && other.y < this.y;
    }

    // Compare by x-coordinate only
    @Override
    public int compareTo(Point other) {
        return Double.compare(this.x, other.x);
    }
}

