

public class Main {

    public static void main(String[] args) {
        // smaller y size and larger x size makes game easier
        final int x = 2000, y = 600;

        SpaceArena arena = new SpaceArena(x, y);
        // arena.setSeed(/*YOUR SEED HERE - otherwise RANDOM SEED*/);

        // infinite loop: when game ends (your death), the startGame function returns, so go back and start it again
        while (true) {
            arena.startGame();
        }

    }
}