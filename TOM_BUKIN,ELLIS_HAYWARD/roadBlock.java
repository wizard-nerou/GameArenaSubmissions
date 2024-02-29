import java.util.Random;

public class roadBlock {
    public Rectangle[] roadBlockrectangles;

    public void render(GameArena arena) {

        int i = 0;
        for (i = 0; i < roadBlockrectangles.length; i++) {
            arena.addRectangle(roadBlockrectangles[i]);
        }
    }

    public void move(double dx, double dy) {

        int score = 0;
        Random random = new Random();
        int  min = 0;
        int max = 420;
        int num = random.nextInt(max-min+1)+min;

        for (int i = 0; i < roadBlockrectangles.length; i++) {
            roadBlockrectangles[i].setXPosition(roadBlockrectangles[i].getXPosition() + dx);
            roadBlockrectangles[i].setYPosition(roadBlockrectangles[i].getYPosition() + dy);
            if (roadBlockrectangles[i].getYPosition() == 600){
                roadBlockrectangles[i].setYPosition(0);
                roadBlockrectangles[0].setXPosition(num);
                roadBlockrectangles[1].setXPosition(num);
                roadBlockrectangles[2].setXPosition(num + 20);
                roadBlockrectangles[3].setXPosition(num + 40);
                roadBlockrectangles[4].setXPosition(num + 60);
                score = score + 1;
            }
        }
    }

    public roadBlock(GameArena arena) {
        Random random = new Random();
        int  min = 100;
        int max = 400;
        int num = random.nextInt(max-min+1)+min;

        roadBlockrectangles = new Rectangle[5];
        roadBlockrectangles[0] = new Rectangle(num, 0, 70, 20, "RED");
        roadBlockrectangles[1] = new Rectangle(num, 0, 10, 20, "WHITE");
        roadBlockrectangles[2] = new Rectangle(num + 20, 0, 10, 20, "WHITE");
        roadBlockrectangles[3] = new Rectangle(num + 40, 0, 10, 20, "WHITE");
        roadBlockrectangles[4] = new Rectangle(num + 60, 0, 10, 20, "WHITE");

        render(arena);
    }

}
