

public class Sample {
    public static void main(String[] args) {
        GameArena arena = new GameArena(500,300);
        Ball b = new Ball(250, 150, 20, "GREEN");

        arena.addBall(b);

        while(true) {
            arena.pause();
        }
    }
}
