public class Main {
    public static void main(String[] args) {
        GameArena arena = new GameArena(450, 550);
        Background newBackground = new Background(arena);
        Player newPlayer = new Player(arena);
        roadBlock newRoadBlock = new roadBlock(arena);
        roadBlock newRoadBlock2 = new roadBlock(arena);
        roadBlock newRoadBlock3 = new roadBlock(arena);
        roadBlock newRoadBlock4 = new roadBlock(arena);

        while (true) {
            if(newPlayer.getHitbox().collides(newRoadBlock.roadblockGetHitbox())) {
                System.out.println("you crashed");
            }
            if(newPlayer.getHitbox().collides(newRoadBlock2.roadblockGetHitbox())) {
                System.out.println("you crashed");
            }
            if(newPlayer.getHitbox().collides(newRoadBlock3.roadblockGetHitbox())) {
                System.out.println("you crashed");
            }
            if(newPlayer.getHitbox().collides(newRoadBlock4.roadblockGetHitbox())) {
                System.out.println("you crashed");
            }
            if (arena.rightPressed()) {
                newPlayer.move(5, 0);
            }
            if (arena.leftPressed()) {
                newPlayer.move(-5, 0);
            }
            if (arena.upPressed()) {
                newPlayer.move(0, -5);
            }
            if (arena.downPressed()) {
                newPlayer.move(0, 5);
            }
            newRoadBlock.move(0, 5);
            newRoadBlock2.move(0, 4);
            newRoadBlock3.move(0, 3);
            newRoadBlock4.move(0, 2);            
            arena.pause();

        }
    }
}