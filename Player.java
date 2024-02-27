//hdsuivf
public class Player {
    private Rectangle[] rectangles;

    public void render(GameArena arena) {

        int i = 0;
        for (i = 0; i < rectangles.length; i++) {
            arena.addRectangle(rectangles[i]);
        }
    }

    public void move(double dx, double dy) {
        for (int i = 0; i < rectangles.length; i++) {
            rectangles[i].setXPosition(rectangles[i].getXPosition() + dx);
            rectangles[i].setYPosition(rectangles[i].getXPosition() + dy);
        }
    }

    public Player(GameArena arena) {
        rectangles = new Rectangle[10];
        rectangles[0] = new Rectangle(100, 150, 50, 30, "RED");
        rectangles[1] = new Rectangle(100, 150, 40, 20, "ORANGE");

        render(arena);

    }

}