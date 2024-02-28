public class Main{
    public static void main(String[] args)
    {
        GameArena arena = new GameArena(450, 550);
        //Ball b = new Ball(250, 150, 20, "GREEN");
        //arena.addBall(b);
        Background newBackground = new Background(arena);
        Player newPlayer = new Player(arena);
        roadBlock newRoadblock = new roadBlock(arena);

        while(true)
        {
            if (arena.rightPressed()){
                newPlayer.move(5, 0);
            }
            if (arena.leftPressed()){
                newPlayer.move(-5, 0);
            }
            if (arena.upPressed()){
                newPlayer.move(0, -5);
            }
            if (arena.downPressed()){
                newPlayer.move(0, 5);
            }
            newRoadblock.move(0, 5);
            
            arena.pause();
        }
    }
}