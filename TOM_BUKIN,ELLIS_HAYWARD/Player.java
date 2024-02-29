//hdsuivf
public class Player {
    private Rectangle[] rectangles;
    boolean border = false;

    public void render(GameArena arena) {

        int i = 0;
        for (i = 0; i < rectangles.length; i++) {
            arena.addRectangle(rectangles[i]);
        }
    }

    public void move(double dx, double dy) {
        for (int i = 0; i < rectangles.length; i++) {
            rectangles[i].setXPosition(rectangles[i].getXPosition() + dx);
            rectangles[i].setYPosition(rectangles[i].getYPosition() + dy);
        }
            if(rectangles[0].getYPosition() >= 500){
            rectangles[0].setYPosition(500);
            rectangles[1].setYPosition(505);
            }

            if(rectangles[0].getYPosition() <= 0){
            rectangles[0].setYPosition(0);
            rectangles[1].setYPosition(5);
            }

            if(rectangles[0].getXPosition() >= 420){
            rectangles[0].setXPosition(420);
            rectangles[1].setXPosition(425);
            }

            if(rectangles[0].getXPosition() <= 0){
            rectangles[0].setXPosition(0);
            rectangles[1].setXPosition(5);
            }
    }

    public Player(GameArena arena) {
        rectangles = new Rectangle[2];
        rectangles[0] = new Rectangle(212, 480, 30, 50, "BLUE");
        rectangles[1] = new Rectangle(217, 485, 20, 40, "CARBLUE");

        render(arena);

    }

}