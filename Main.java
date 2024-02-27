import GameAssets.SpaceArena;


public class Main {

    public static void main(String[] args) {
        final int x = 1000, y = 600;

        SpaceArena arena = new SpaceArena(x, y);
        while (true) {
            arena.startGame();
        }
    }
}