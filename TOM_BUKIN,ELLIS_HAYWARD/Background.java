public class Background {
    private Rectangle[] backgroundRectangles;

    public void render(GameArena arena) {

        int i = 0;
        for (i = 0; i <backgroundRectangles.length; i++) {
            arena.addRectangle(backgroundRectangles[i]);
        }
    }
    public Background(GameArena arena) {
        backgroundRectangles = new Rectangle[20];
        backgroundRectangles[0] = new Rectangle(0, 0, 75, 600, "DARKGREEN");
        backgroundRectangles[1] = new Rectangle(375, 0, 75, 600, "DARKGREEN");

        backgroundRectangles[2] = new Rectangle(75, 0, 300, 600, "ROADGREY");

        backgroundRectangles[3] = new Rectangle(175, 0, 3, 600, "GREY");
        backgroundRectangles[4] = new Rectangle(275, 0, 3, 600, "GREY");

        backgroundRectangles[5] = new Rectangle(125, 50, 5, 25, "YELLOW");
        backgroundRectangles[6] = new Rectangle(125, 150, 5, 25, "YELLOW");
        backgroundRectangles[7] = new Rectangle(125, 250, 5, 25, "YELLOW");
        backgroundRectangles[8] = new Rectangle(125, 350, 5, 25, "YELLOW");
        backgroundRectangles[9] = new Rectangle(125, 450, 5, 25, "YELLOW");

        backgroundRectangles[10] = new Rectangle(225, 50, 5, 25, "YELLOW");
        backgroundRectangles[11] = new Rectangle(225, 150, 5, 25, "YELLOW");
        backgroundRectangles[12] = new Rectangle(225, 250, 5, 25, "YELLOW");
        backgroundRectangles[13] = new Rectangle(225, 350, 5, 25, "YELLOW");
        backgroundRectangles[14] = new Rectangle(225, 450, 5, 25, "YELLOW");

        backgroundRectangles[15] = new Rectangle(325, 50, 5, 25, "YELLOW");
        backgroundRectangles[16] = new Rectangle(325, 150, 5, 25, "YELLOW");
        backgroundRectangles[17] = new Rectangle(325, 250, 5, 25, "YELLOW");
        backgroundRectangles[18] = new Rectangle(325, 350, 5, 25, "YELLOW");
        backgroundRectangles[19] = new Rectangle(325, 450, 5, 25, "YELLOW");

        render(arena);
    }
}
