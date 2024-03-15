import java.util.ArrayList;

/**
 * Bombs shot by dragon
 */
public class Bomb {
    public static final int length = 30;
    private final String col = "RED";
    private Ball shape;
    private GameArena gameArena;

    private int x;
    private int y;
    private int dir;
    private final int speed = 10;

    private int explosionPointY;

    /**
     * Creates a Bomb object in given x and y location, moving in dir
     * @param x starting x location of the center of the bomb
     * @param y starting y location of the center of the bomb
     * @param dir angle of movement from initial position, [0, 360),
     *            where 0 is up, 90 is right, 180 is down, 270 is left
     * @gameArena GameArena object to display the bomb on
     */
    public Bomb(int x, int y, int dir, GameArena gameArena) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        shape = new Ball(x, y, length, col);

        /* Pick random point from vertical starting point (+offset) to arena edge */
        explosionPointY = (int)(Math.random() * (gameArena.getArenaHeight() - y)) + y + 100;

        this.gameArena = gameArena;
        gameArena.addBall(shape);
    }

    /**
     * Move bomb
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
     * Explode the bomb when explosion location is reached
     * <p>
     * 
     * @return true if the bomb exploded, false otherwise
     */
    public boolean explode(ArrayList<Bullet> bullets)
    {
        if (y >= explosionPointY) {
            gameArena.removeBall(shape);

            /* Create 8 bullets in 8 directions
             * Add them to Driver game loop bullets
             */
            Bullet bullet;
            for (int i = 0; i < 8; i++)
            {
                bullet = new Bullet(x, y, 45 * i, gameArena);
                bullets.add(bullet);
            }

            return true;
        }
        return false;
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
}