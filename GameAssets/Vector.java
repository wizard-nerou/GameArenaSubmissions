package GameAssets;

public class Vector {
    public double x;
    public double y;

    /**
     * Create a blank Vector with value (0, 0)
     */
    public Vector() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Create a new Vector with specified values (x, y)
     * @param x     magnitude of X
     * @param y     magnitude of Y
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Formats a new vector such the absolute value of the length is 1, but 'points' in the same direction
     * @return  the normalised vector
     */
    public Vector getNormalised() {
        Vector normalised = new Vector(this.x, this.y);
        normalised.normalise();
        return normalised;
    }

    /**
     * Formats this vector such the absolute value of the length is 1, but 'points' in the same direction
     */
    public void normalise() {
        double abs = getMagnitude();
        this.x /= abs;
        this.y /= abs;
    }

    /**
     * Gets the magnitude (absolute value / length) of this vector
     * @return  the magnitude of this vector
     */
    public double getMagnitude() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     *
     * @return  String - (x, y)
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
