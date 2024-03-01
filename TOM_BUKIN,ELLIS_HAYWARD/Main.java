public class Main {
    public static void main(String[] args) {
        GameArena arena = new GameArena(450, 550);
        Background newBackground = new Background(arena);
        Player newPlayer = new Player(arena);
        roadBlock newRoadBlock = new roadBlock(arena);
        roadBlock newRoadBlock2 = new roadBlock(arena);
        roadBlock newRoadBlock3 = new roadBlock(arena);
        roadBlock newRoadBlock4 = new roadBlock(arena);
        Text gameOverText = new Text("You Crashed!", 25, 140, 420, "RED");


        while (true) {
            if(newPlayer.getHitbox().collides(newRoadBlock.roadblockGetHitbox()) || newPlayer.getHitbox().collides(newRoadBlock2.roadblockGetHitbox()) || newPlayer.getHitbox().collides(newRoadBlock3.roadblockGetHitbox()) || newPlayer.getHitbox().collides(newRoadBlock4.roadblockGetHitbox()))  {
                System.out.println("You crashed!");
                arena.addText(gameOverText);

                break;
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
            newRoadBlock.move(0, 10);
            newRoadBlock2.move(0, 9);
            newRoadBlock3.move(0, 8);
            newRoadBlock4.move(0, 7);            
            arena.pause();

        }
    }
}