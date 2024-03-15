/**
 * Bullet shot by bomb
 */
public class Bullet {
    public static final int length = 10;
    private Ball shape;
    private GameArena gameArena;

    private int x;
    private int y;
    private int dir;
    private final int speed = 15;

    /**
     * Creates a Bullet object in given x and y location, moving in dir
     * <p>
     * Default color is white
     * @param x starting x location of the center of the bullet
     * @param y starting y location of the center of the bullet
     * @param dir angle of movement from initial position, [0, 360),
     *            where 0 is up, 90 is right, 180 is down, 270 is left
     * @gameArena GameArena object to display the bullet on
     */
    public Bullet(int x, int y, int dir, GameArena gameArena) {
        this(x, y, dir, gameArena, "WHITE");
    }

    /**
     * Creates a Bullet object in given x and y location, moving in dir
     * @param x starting x location of the center of the bullet
     * @param y starting y location of the center of the bullet
     * @param dir angle of movement from initial position, [0, 360),
     *            where 0 is up, 90 is right, 180 is down, 270 is left
     * @param GameArena object to display the bullet on
     */
    public Bullet(int x, int y, int dir, GameArena gameArena, String col)
    {
        this.x = x;
        this.y = y;
        this.dir = dir;
        shape = new Ball(x, y, length, col);

        this.gameArena = gameArena;
        gameArena.addBall(shape);
    }

    /**
     * Move Bullet
     */
    public void move()
    {
        // Math.cos and Math.sin use radians instead of degrees
        // Start 0 at this 90
        x += Math.cos(((double)dir - 90.0) * (Math.PI / 180.0)) * speed;
        shape.setXPosition(x);
        y += Math.sin(((double)dir - 90.0) * (Math.PI / 180.0)) * speed;
        shape.setYPosition(y);
    }

    /**
     * Detects if the bullet is completely out of the arena
     * @return true of the bullet is out of the screen, false otherwise
     */
    public boolean outOfBound() 
    {
        // To the left, right, up or under the screen
        if ((x <= -length) || (x >= (gameArena.getWidth() + length)) || (y >= gameArena.getHeight()) || (y <= (0 - length))) {
            gameArena.removeBall(shape);
            return true;
        }
        return false;
    }

    public void removeSelf()
    {
        gameArena.removeBall(shape);
    }

    /**
     * X location of center
     * @return center X position, in pixels
     */
    public int getX() {
        return x;
    }

    /**
     * Y location of center
     * @return center Y position, in pixels
     */
    public int getY() {
        return y;
    }

    /**
     * Length of a Bullet object
     * @return Length of bullet shape, in pixels
     */
    public static int getLength() {
        return length;
    }
}