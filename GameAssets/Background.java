package GameAssets;

public class Background extends Body {
    private String colour = "WHITE";
    private double averageStarSize = 3;


    public Background() {
        super();
    }

    public Background(int numStars) {
        this.velocity = new Vector();
        this.position = new Vector();
        this.shape = new Ball[numStars];
    }

    public Background(String colour, int numStars) {
        this.velocity = new Vector();
        this.position = new Vector();
        this.colour = colour;
        this.shape = new Ball[numStars];
    }

    public void generateStars(GameArena arena) {
        for (int i = 0; i < shape.length; i++) {
            double x = SpaceArena.randomGenerator.nextDouble() * arena.getArenaWidth();
            double y = SpaceArena.randomGenerator.nextDouble() * arena.getArenaHeight();
            double size = this.averageStarSize + (SpaceArena.randomGenerator.nextDouble()-0.5)*3;
            shape[i] = new Ball(x, y, size, this.colour, -10);
        }
    }

    // accessor methods - none for setting star array, as I don't want people to change that
    public Ball[] getStars() {
        return shape;
    }
    public String getColour() {
        return colour;
    }
    public double getAverageStarSize() {
        return averageStarSize;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
    public void setAverageStarSize(double averageStarSize) {
        this.averageStarSize = averageStarSize;
    }
}
