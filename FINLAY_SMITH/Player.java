import GameArenaClasses.Ball;
import GameArenaClasses.GameArena;

/**
 * Models a Player which is a
 * GameArenaClasses.Ball of specific size and colour.
 * When combined with the GameArenaClasses.GameArena class,
 * instances of the Player class can be displayed on the screen.
 */
public class Player extends Body {

    public static final Vector START_POS = new Vector();
    public static final double GRAVITY = 0.65;
    public static final double JUMP_POWER = 10;

    public Player() {
        makePlayerShape();
    }

    public Player(int x, int y) {
        START_POS.x = x;
        START_POS.y = y;

        this.init(x, y);

        makePlayerShape();
    }


    private void makePlayerShape() {
        Ball mainShip = new Ball(position.x, position.y, 50, "#8a8984", 10);

        Ball mainWindow = new Ball(position.x + 13, position.y, 20, "#dedede", 11);
        Ball smallWindowTop = new Ball(position.x + 3, position.y + 15, 10, "#dedede", 11);
        Ball smallWindowBottom = new Ball(position.x + 3, position.y - 15, 10, "#dedede", 11);

        Ball bigThrusterTop = new Ball(position.x - 25, position.y + 12, 12, "#d4681c", 9);
        Ball littleThrusterTop = new Ball(position.x - 30, position.y + 12, 7, "YELLOW", 9);

        Ball bigThrusterBottom = new Ball(position.x - 25, position.y - 12, 12, "#d4681c", 9);
        Ball littleThrusterBottom = new Ball(position.x - 30, position.y - 12, 7, "YELLOW", 9);

        shape = new Ball[]{mainShip, mainWindow, smallWindowTop, smallWindowBottom, littleThrusterBottom, littleThrusterTop, bigThrusterBottom, bigThrusterTop};
//        shape = new GameArenaClasses.Ball[]{mainShip}; // for testing - shows hit-box only
    }


    public void init(){
        this.init(START_POS.x, START_POS.y);
    }

    public void moveAndCollide(GameArena arena, double x, double y){
        super.moveAndCollide(arena, x, y, 25);
    }

    public void slideAndCollide(GameArena arena){
        super.slideAndCollide(arena, 25);
    }

}
