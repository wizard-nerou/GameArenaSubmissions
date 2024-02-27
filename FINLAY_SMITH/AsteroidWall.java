import java.util.Random;

public class AsteroidWall {
    public static double SPEED = 4;
    public static int OPENINGSIZE = 100;

    private Asteroid[] asteroidsTop;
    private Asteroid[] asteroidsBottom;
    private double xPos;
    private double openingPos;
    private boolean givenScore = false;


    public AsteroidWall(SpaceArena arena) {
        buildRandomWall(arena);
        xPos = arena.getArenaWidth() + 50;
    }

    public AsteroidWall(SpaceArena arena, int openingPos) {
        buildWall(arena, openingPos);
        xPos = arena.getArenaWidth() + 50;
    }

    public void buildWall(SpaceArena arena, int pos) {
        openingPos = pos;
        // create correct amount of asteroids to fill top half of wall
        asteroidsTop = new Asteroid[pos / Asteroid.DIAMETER];
        for (int i = 0; i < asteroidsTop.length; i++) {
            asteroidsTop[i] = new Asteroid(arena.getArenaWidth() + 50, pos - OPENINGSIZE - (i * Asteroid.DIAMETER), SPEED);
            asteroidsTop[i].rotate(SpaceArena.randomGenerator.nextDouble()/1.5);
            asteroidsTop[i].addTo(arena);
        }

        // create correct amount of asteroids to fill bottom half of wall
        asteroidsBottom = new Asteroid[(arena.getArenaHeight() - pos) / Asteroid.DIAMETER];
        for (int i = 0; i < asteroidsBottom.length; i++) {
            asteroidsBottom[i] = new Asteroid(arena.getArenaWidth() + 50, pos + OPENINGSIZE + (i * Asteroid.DIAMETER), SPEED);
            asteroidsBottom[i].rotate(SpaceArena.randomGenerator.nextDouble()/1.5);
            asteroidsBottom[i].addTo(arena);
        }


    }

    public void buildRandomWall(SpaceArena arena) {
        Random random = SpaceArena.randomGenerator;

        // ensure that the opening is not too close to the edge
        int pos = random.nextInt(arena.getArenaHeight() - 2*OPENINGSIZE) + OPENINGSIZE;
        buildWall(arena, pos);
    }

    public void removeFrom(SpaceArena arena) {
        for (Asteroid asteroid : asteroidsTop) {
            asteroid.removeFrom(arena);
        }
        for (Asteroid asteroid : asteroidsBottom) {
            asteroid.removeFrom(arena);
        }
        xPos = arena.getArenaWidth() + 50;
        givenScore = false;
    }

    public void move(SpaceArena arena) {
        for (Asteroid asteroid : asteroidsTop) {
            asteroid.setVelocity(-SPEED, 0);
            asteroid.slide();
        }
        for (Asteroid asteroid : asteroidsBottom) {
            asteroid.setVelocity(-SPEED, 0);
            asteroid.slide();
        }
        xPos -= SPEED;

        // check if player has collided with wall
        if (xPos <= SpaceArena.player.position.x + Asteroid.DIAMETER  &&  this.isCollidedWith(SpaceArena.player)) {
            arena.endGame();
        }

        // check if player has passed wall, if so, increment score, and speed up game
        if (xPos <= SpaceArena.player.position.x && !givenScore) {
            SpaceArena.scoreCounter.increment();
            givenScore = true;
            SPEED += 0.05;
        }
    }

    public boolean isCollidedWith(Player player) {
        for (Asteroid asteroid : asteroidsTop) {
            if (asteroid.isCollidedWith(player)) {
                return true;
            }
        }
        for (Asteroid asteroid : asteroidsBottom) {
            if (asteroid.isCollidedWith(player)) {
                return true;
            }
        }
        return false;
    }

    public static void resetSpeed(){
        SPEED = 4;
    }

    @Override
    public String toString() {
        return "AsteroidWall: [Opening: y=" + openingPos + ", Asteroids: " + asteroidsTop.length + ", " + asteroidsBottom.length + "]";
    }
}
