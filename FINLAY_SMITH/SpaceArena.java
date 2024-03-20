
import GameArenaClasses.GameArena;
import GameArenaClasses.Text;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class SpaceArena extends GameArena {

    public static Random randomGenerator;
    public static Player player;
    public static ScoreCounter scoreCounter;
    public static Background closeStars;
    public static Background farStarts;

    private int wallCreationCounter = 0;
    private boolean inPlay = false;
    private boolean spaceHeld = false;
    private int maxWalls;

    private final Queue<AsteroidWall> asteroidWalls;


    public SpaceArena(int width, int height) {
        super(width, height);

        player = new Player(300, height / 2);

        scoreCounter = new ScoreCounter(width/2.0, 50);
        this.addText(scoreCounter);

        asteroidWalls = new LinkedList<>();

        randomGenerator = new Random();

        closeStars = new Background(randomGenerator.nextInt(50)+250);
        farStarts = new Background(randomGenerator.nextInt(50)+150);
        farStarts.setAverageStarSize(1.5);

        maxWalls = width / 200;
    }

    public void addAsteroidWall() {
        // if there are less than MAX_WALLS walls, the arena is not full yet, so create more,
        // else, recycle a wall that is no longer visible, off the side of the screen, to same memory

        if (asteroidWalls.size() < maxWalls) {
            AsteroidWall asteroidWall = new AsteroidWall(this);
            asteroidWalls.add(asteroidWall);
        } else {
            AsteroidWall recycledWall = asteroidWalls.poll();
            recycledWall.removeFrom(this);
            recycledWall.buildRandomWall(this);
            asteroidWalls.add(recycledWall);
        }

    }

    public void moveWalls() {
        for (AsteroidWall asteroidWall : asteroidWalls) {
            asteroidWall.move(this);
        }
    }

    /**
     * Sets the seed to a new, Random Seed - every random event in game is dependent on this seed
     */
    public void setSeed() {
        randomGenerator = new Random();
    }

    /**
     * Sets the seed to a spefied value - every random event in game is dependent on this seed
     * @param seed      integer seed value
     */
    public void setSeed(long seed) {
        randomGenerator = new Random(seed);
    }

    private void update() {
        // move player up if space is pressed - not held
        if (spacePressed()) {
            if (!spaceHeld)
                player.setVelocity(0, -Player.JUMP_POWER);
            spaceHeld = true;
        } else {
            spaceHeld = false;
        }

        // apply gravity to player
        player.accelerate(0, Player.GRAVITY);
        player.slideAndCollide(this);

        moveWalls();

        // add asteroid walls periodically
        if (++wallCreationCounter >= 95 / (AsteroidWall.SPEED/4)) {
            addAsteroidWall();
            wallCreationCounter = 0;
        }


        pause();
    }

    private void showStartingText() {
        Text text = new Text("Jumpy Ship", 50, this.getArenaWidth()/2.0, this.getArenaHeight()/2.0, "#FFFFFF", 20);
        addText(text);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ignored) {}
        removeText(text);
    }

    private void showEndingText() {
        Text text = new Text("YOU DIED!\n YOU GOT "+scoreCounter.getScore()+" SCORE!", 30, 50, this.getArenaHeight()/2.0, "#FFFFFF", 20);
        addText(text);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ignored) {}
        removeText(text);
    }

    /**
     * Starts the Game session loop, and continues play until the game ends
     */
    public void startGame() {
        closeStars.generateStars(this);
        farStarts.generateStars(this);

        player.addTo(this);
        closeStars.addTo(this);
        farStarts.addTo(this);
        scoreCounter.setScore(0);
        showStartingText();
        addAsteroidWall();
        wallCreationCounter = 0;

        inPlay = true;
        while (inPlay) {
            update();
        }

        player.removeFrom(this);
        for (AsteroidWall wall : asteroidWalls) {
            wall.removeFrom(this);
        }
        asteroidWalls.clear();
        AsteroidWall.resetSpeed();
        player.init();
        closeStars.removeFrom(this);
        farStarts.removeFrom(this);
        showEndingText();
    }

    /**
     * Ends the game loop started from startGame function
     */
    public void endGame() {
        inPlay = false;
    }
}
