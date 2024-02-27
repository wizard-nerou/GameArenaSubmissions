public class Main{
    public static void main(String[] args)
    {
        GameArena arena = new GameArena(500, 300);
        //Ball b = new Ball(250, 150, 20, "GREEN");
        //arena.addBall(b);
        Player newPlayer = new Player(arena);
        
        
        while(true)
        {
            arena.pause();
        }
    }
}