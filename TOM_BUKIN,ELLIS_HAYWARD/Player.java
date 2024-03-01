//hdsuivf
public class Player {
    double XcarHitbox;
    double YcarHitbox;
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

            if(rectangles[0].getXPosition() >= 345){
            rectangles[0].setXPosition(345);
            rectangles[1].setXPosition(350);
            }

            if(rectangles[0].getXPosition() <= 75){
            rectangles[0].setXPosition(75);
            rectangles[1].setXPosition(80);
            }


    }

    public Rectangle getHitbox(){
        return rectangles[0];
    }

    //public double yHitbox(){
    //    return rectangles[0].getYPosition();
    //}

    public Player(GameArena arena) {
        rectangles = new Rectangle[2];
        rectangles[0] = new Rectangle(212, 480, 30, 50, "BLUE");
        rectangles[1] = new Rectangle(217, 485, 20, 40, "CARBLUE");

        render(arena);

    }

}