public class Main{
    public static void main(String[] args)
    {
        GameArena arena = new GameArena(450, 550);
        //Ball b = new Ball(250, 150, 20, "GREEN");
        //arena.addBall(b);
        Player newPlayer = new Player(arena);
        roadBlock newRoadblock = new roadBlock(arena);

        while(true)
        {
            arena.pause();
        }
    }
}