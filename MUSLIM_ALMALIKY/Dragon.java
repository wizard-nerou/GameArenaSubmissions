/**
 * Main enemy in the game
 */
public class Dragon {
    private GameArena gameArena;

    private int x;
    private int y;

    private long shootTimer;
    private long shootRechargeDuration;

    private int health;

    private static final int length = 40;
    private final String col = "MAGENTA";
    private Rectangle shape;

    /**
     * Create a Dragon object and adds it to <code>gameArena</code>
     * <p>
     * @param x X position of top left corner, in pixels
     * @param y Y position of top left corner, in pixels
     * @param gameArena GameArena object to display the dragon on
     */
    public Dragon(int x, int y, GameArena gameArena) {
        /*
         * Initializes all the constants and attributes
         * Creates hero shape and adds it to gameArena
         */
        this.gameArena = gameArena;

        this.x = x;
        this.y = y;
        shape = new Rectangle(this.x, this.y, length, length, col);
        gameArena.addRectangle(shape);
        this.health = 10;

        shootTimer = System.currentTimeMillis();
        shootRechargeDuration = 2000;
    }

    /**
     * Gets the horizontal length of the dragon, in pixels
     * @return Horizontal length, in pixels
     */
    public static int getLengthX()
    {
        return length;
    }

    /**
     * Gets the vertical length of the dragon, in pixels
     * @return vertical length, in pixels
     */
    public static int getLengthY()
    {
        return length;
    }

    /**
     * Creates a <code>Bomb</code> object and returns it if dragon wants, else returns null
     * <p>
     * Deploy the bomb under the dragon and shoot it in the down direction
     * @param hero (poor) hero
     * @return <code>Bomb</code> object if dragon wants, else null
     */
    public Bomb shoot(Hero hero)
    {
        /* Shoot only if a certain amount of time has passed since last shot */
        if ((System.currentTimeMillis() - shootTimer) >= shootRechargeDuration) {

            /* Calculate angle to player */
            double theta = Math.atan((double)(hero.getX() - x) / (double)(hero.getY() - y)) * (180.0 / Math.PI);
            int dir = 180 - (int)Math.round(theta);
            
            Bomb bomb = new Bomb((x + getLengthX()/ 2), y + getLengthY() + (Bomb.length / 2), dir, gameArena);

            // Reset timer
            shootTimer = System.currentTimeMillis();

            return bomb;
        }
        return null;
    }

    /**
     * Damages the dragon
     * <p>
     * Removes the dragon from arena if health <= 0
     * @return true if health > 0, false otherwise
     */
    public boolean beDamaged()
    {
        health -= 1;
        if (health <= 0) {
            gameArena.removeRectangle(shape);
            return false;
        }
        return true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}