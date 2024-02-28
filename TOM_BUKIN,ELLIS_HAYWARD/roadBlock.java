
public class roadBlock {
    private Rectangle[] roadBlockrectangles;

    public void render(GameArena arena) {

        int i = 0;
        for (i = 0; i < roadBlockrectangles.length; i++) {
            arena.addRectangle(roadBlockrectangles[i]);
        }
    }

    public void move(double dx, double dy) {

        for (int i = 0; i < roadBlockrectangles.length; i++) {
            roadBlockrectangles[i].setXPosition(roadBlockrectangles[i].getXPosition() + dx);
            roadBlockrectangles[i].setYPosition(roadBlockrectangles[i].getYPosition() + dy);
        }
    }

    public roadBlock(GameArena arena) {
        roadBlockrectangles = new Rectangle[5];
        roadBlockrectangles[0] = new Rectangle(190, 0, 70, 20, "RED");
        roadBlockrectangles[1] = new Rectangle(190, 0, 10, 20, "WHITE");
        roadBlockrectangles[2] = new Rectangle(210, 0, 10, 20, "WHITE");
        roadBlockrectangles[3] = new Rectangle(230, 0, 10, 20, "WHITE");
        roadBlockrectangles[4] = new Rectangle(250, 0, 10, 20, "WHITE");

        render(arena);
    }

}
