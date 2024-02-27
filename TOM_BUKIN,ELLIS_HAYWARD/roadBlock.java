
public class roadBlock {
    private Rectangle[] roadBlockrectangles;

    public void render(GameArena arena) {

        int i = 0;
        for (i = 0; i < roadBlockrectangles.length; i++) {
            arena.addRectangle(roadBlockrectangles[i]);
        }
    }

    public roadBlock(GameArena arena) {
        roadBlockrectangles = new Rectangle[5];
        roadBlockrectangles[0] = new Rectangle(200, 400, 70, 20, "RED");
        roadBlockrectangles[1] = new Rectangle(200, 400, 10, 20, "WHITE");
        roadBlockrectangles[2] = new Rectangle(220, 400, 10, 20, "WHITE");
        roadBlockrectangles[3] = new Rectangle(240, 400, 10, 20, "WHITE");
        roadBlockrectangles[4] = new Rectangle(260, 400, 10, 20, "WHITE");

        render(arena);
    }

}
