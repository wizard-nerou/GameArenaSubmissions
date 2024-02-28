import java.util.ArrayList;

/**
 * A simple Bullet Hell game centered around a player avoiding bullets shot by a dragon
 * <p>
 * This class is the main driver of the game
 */
public class DragonBulletHell {

    /**
     * Detect if the Hero object has collided with the Bomb object
     * @param hero Hero object
     * @param bomb Bomb object
     * @return True if hero and bomb collided, false otherwise
     */
    private static boolean isHeroBombCollision(Hero hero, Bomb bomb)
    {
        /*
         * Find hero's closest corner to bomb
         * Find the distance between the corner and the center of bomb
         * Return (distance to bomb center from corner) <= (bomb radius)
         */
        // clamp(val, min, max), val = bomb center, min = left side, max = right side
        double closestXSideToBomb = Math.max(hero.getX(), Math.min(hero.getX() + Hero.getLengthX(), bomb.getX()));
        // clamp(val, min, max), val = bomb center, min = upper side, max = bottom side
        double closestYSideToBomb = Math.max(hero.getY(), Math.min(hero.getY() + Hero.getLengthY(), bomb.getY()));

        int xDistance = (int)Math.round(closestXSideToBomb) - bomb.getX();
        int yDistance = (int)Math.round(closestYSideToBomb) - bomb.getY();

        int distanceToCenter = (int)Math.round(Math.sqrt((xDistance * xDistance) + (yDistance * yDistance)));

        return distanceToCenter <= Bomb.length/2;
    }

    /**
     * Detect is there is a collision between hero and bullet
     * <p>
     * The function treats bullets as having a rectangle(square) hitbox regardless of their actual shape
     * @param hero Hero object
     * @param bullet Bullet object
     * @return True if there is a collision, false otherwise
     */
    private static boolean isHeroBulletCollision(Hero hero, Bullet bullet)
    {
        /* Determine if hero rectangle hitbox hit the bullet rectangle hitbox */
        Rectangle rectangle = new Rectangle(bullet.getX(), bullet.getY(), Bullet.getLength(), Bullet.getLength(), null);
        return rectangle.collides(new Rectangle(hero.getX(), hero.getY(), Hero.getLengthX(), Hero.getLengthY(), null));
    }

    /**
     * Detect is there is a collision between dragon and bullet
     * <p>
     * The function treats bullets as having a rectangle(square) hitbox regardless of their actual shape
     * @param dragon Dragon object
     * @param bullet Bullet object
     * @return True if there is a collision, false otherwise
     */
    private static boolean isDragonBulletCollision(Dragon dragon, Bullet bullet)
    {
        /* Determine if dragon rectangle hitbox hit the bullet rectangle hitbox */
        Rectangle rectangle = new Rectangle(bullet.getX(), bullet.getY(), Bullet.getLength(), Bullet.getLength(), null);
        return rectangle.collides(new Rectangle(dragon.getX(), dragon.getY(), Dragon.getLengthX(), Dragon.getLengthY(), null));
    }

    public static void main(String[] args) {
        /* Init arena */
        int arenaWidth = 1000;
        int arenaHeight = 1000;
        GameArena gameArena = new GameArena(arenaWidth, arenaHeight);

        /* Init hero */
        int heroInitialX = (arenaWidth / 2) - (Hero.getLengthX() / 2);
        int heroInitialY = (arenaHeight * 4 / 5) - (Hero.getLengthY() / 2);
        Hero hero = new Hero(heroInitialX, heroInitialY, gameArena);

        /* Init dragon */
        int dragonInitialX = (arenaWidth / 2) - (Dragon.getLengthX() / 2);
        int dragonInitialY = (arenaHeight / 10) - (Dragon.getLengthY() / 2);
        Dragon dragon = new Dragon(dragonInitialX, dragonInitialY, gameArena);

        /* Init firewall */
        int firewallDragonDistance = 100;
        int firewallInitialY = dragonInitialY + firewallDragonDistance;
        Line firewall = new Line(0, firewallInitialY, arenaWidth, firewallInitialY, 5, "ORANGE");
        gameArena.addLine(firewall);

        /* Init bombs */
        ArrayList<Bomb> bombs = new ArrayList<Bomb>();

        /* Init bullets */
        ArrayList<Bullet> bullets = new ArrayList<Bullet>();

        /* Init bullets */
        ArrayList<Bullet> heroBullets = new ArrayList<Bullet>();

        boolean notEnd = true;
        boolean won = false;

        /* Game loop */
        while (notEnd) {

            /* Input detection:
             * 
             * Moving player based on input
             */
            if (gameArena.upPressed()) {
                hero.move(0, -1, arenaWidth, firewallInitialY, arenaHeight);
            }
            if (gameArena.downPressed()) {
                hero.move(0, 1, arenaWidth, firewallInitialY, arenaHeight);
            }
            if (gameArena.leftPressed()) {
                hero.move(-1, 0, arenaWidth, firewallInitialY, arenaHeight);
            }
            if (gameArena.rightPressed()) {
                hero.move(1, 0, arenaWidth, firewallInitialY, arenaHeight);
            }
            if (gameArena.spacePressed()) {
                Bullet hb = hero.shoot();
                if (hb != null) {
                    heroBullets.add(hb);
                }
            }
            // Exit on esc
            if (gameArena.escPressed()) {
                gameArena.exit();
            }

            /* letting dragon shoot and add shot bomb to bombs array */
            Bomb bomb = dragon.shoot(hero);
            if (bomb != null) {
                bombs.add(bomb);
            }

            /* let bombs explode
             * Moving Bomb objects
             * detect hero-bomb collisions
            */
            for (Bomb b : bombs) {
                if (b.explode(bullets)) {
                    bombs.remove(b);
                    break;
                }

                b.move();

                if (isHeroBombCollision(hero, b)) {
                    hero.removeSelf();
                    notEnd = false;
                    won = false;
                }
            }

            /* Moving Bullet objects 
             * Detect hero-bullet collisions
             * Detect out of bounds bullets
            */
            for (Bullet bullet : bullets) {
                bullet.move();

                if (bullet.outOfBound()) {
                    bullets.remove(bullet);
                    break;
                }

                if (isHeroBulletCollision(hero, bullet)) {
                    hero.removeSelf();
                    notEnd = false;
                    won = false;
                }
            }

            /* Detect dragon-bullet collision
             * Move hero bullets 
             * Detect out of bounds
             */
            for (Bullet bullet : heroBullets) {
                if (isDragonBulletCollision(dragon, bullet)) {
                    bullet.removeSelf();
                    heroBullets.remove(bullet);
                    // If dragon is dead
                    if (!dragon.beDamaged()) {
                        notEnd = false;
                        won = true;
                    }
                    break;
                }

                bullet.move();

                if (bullet.outOfBound()) {
                    heroBullets.remove(bullet);
                    break;
                }
            }

            // Slows down the computation, for the sake of the animation (10000/s acceleration is not nice)
            gameArena.pause();
        }

        long startTime = System.currentTimeMillis();
        long duration = 1000;
        while (!notEnd) {
            if (won) {
                gameArena.addText(new Text("VICTORY", 100, 300, arenaHeight/2, "GREEN"));
            } else {
                gameArena.addText(new Text("GAMEOVER", 100, 200, arenaHeight/2, "RED"));
            }
            if ((System.currentTimeMillis() - startTime) >= duration) {
                notEnd = true;
            }
            gameArena.pause();
        }

        gameArena.exit();
    }
}