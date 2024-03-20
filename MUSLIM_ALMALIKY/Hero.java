/**
 * Main player in the game
 */
public class Hero {
    private static final int length = 40;
    private final String col = "BLUE";
    private Rectangle shape;
    private GameArena gameArena;

    private int x;
    private int y;
    private final int speed = 7;

    private long shootTimer;
    private long shootRechargeDuration;

    /**
     * Create a Hero object and adds it to <code>gameArena</code>
     * <p>
     * The displayed shape of the hero is a square
     * @param x X position of top left corner, in pixels
     * @param y Y position of top left corner, in pixels
     * @param gameArena GameArena object to display the hero on
     */
    public Hero(int x, int y, GameArena gameArena) {
        /*
         * Initializes all the constants and attributes
         * Creates hero shape and adds it to gameArena
         */
        this.x = x;
        this.y = y;
        shape = new Rectangle(this.x, this.y, length, length, col);
        this.gameArena = gameArena;

        gameArena.addRectangle(shape);

        shootTimer = System.currentTimeMillis();
        shootRechargeDuration = 500;
    }

    
    /**
     * Make hero move in specified direction
     * <p>
     * The hero moves in a certain speed in the given direction.
     * If both directions are specified, the hero moves diagonally where <code>speed = xSpeed + ySpeed</code>
     * <p>
     * Movement is restricted inside the 
     * @param xDir Horizontal movement, 1 for right, -1 for left or 0 for no movement
     * @param yDir Vertical movement, 1 for down, -1 for up or 0 no movement
     * @param maxWidth maximum horizontal point possible, in pixels
     * @param minHeight minimum vertical point possible, in pixels
     * @param maxHeight maximum vertical point possible, in pixels
     */
    public void move(int xDir, int yDir, int maxWidth, int minHeight , int maxHeight)
    {
        /* Update internal x and y positions and move shape(s) */
        
        x += xDir * speed;
        /* Constrain to arena width */
        if ((x + getLengthX()) >= maxWidth) {
            x = maxWidth - getLengthX();
        } else if (x <= 0) {
            x = 0;
        }
        shape.setXPosition(x);
        
        y += yDir * speed;
        /* Constrain between arena and firewall */
        if ((y + getLengthY()) >= maxHeight) {
            y = maxHeight - getLengthY();
        } else if (y <= minHeight) {
            y = minHeight;
        }
        shape.setYPosition(y);
    }
    
    /**
     * Shoots a bullet in going straight up if the hero can
     * <p>
     * Only shoots if the reload time has passed
     * @return a bullet going up if can shoot, null otherwise
     */
    public Bullet shoot()
    {
        if ((System.currentTimeMillis() - shootTimer) >= shootRechargeDuration) 
        {
            // Reset timer
            shootTimer = System.currentTimeMillis();

            return new Bullet(x + getLengthX()/2, y, 0, gameArena, "GREEN");
        }
        return null;
    }

    public void removeSelf()
    {
        gameArena.removeRectangle(shape);
    }
    
    /**
     * X location of left corner
     * @return left corner position, in pixels
     */
    public int getX() {
        return x;
    }
    
    /**
     * Y location of upper corner
     * @return upper corner position, in pixels
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the horizontal length of the hero, in pixels
     * @return Horizontal length, in pixels
     */
    public static int getLengthX()
    {
        return length;
    }
    
    /**
     * Gets the vertical length of the hero, in pixels
     * @return vertical length, in pixels
     */
    public static int getLengthY()
    {
        return length;
    }
    
}