import GameArenaClasses.Ball;
import GameArenaClasses.GameArena;

public class Background extends Body {
    private double averageStarSize = 2.5;


    public Background() {
        super();
    }

    public Background(int numStars) {
        this.velocity = new Vector();
        this.position = new Vector();
        this.shape = new Ball[numStars];
    }

    public void generateStars(GameArena arena) {
        for (int i = 0; i < shape.length; i++) {
            double x = SpaceArena.randomGenerator.nextDouble() * arena.getArenaWidth();
            double y = SpaceArena.randomGenerator.nextDouble() * arena.getArenaHeight();

            // make 20% of stars giant/close stars
            double size = SpaceArena.randomGenerator.nextDouble() < 0.2
                    ? this.averageStarSize*2 + (SpaceArena.randomGenerator.nextDouble()-0.5)
                    : this.averageStarSize + (SpaceArena.randomGenerator.nextDouble()-0.5);

            shape[i] = new Ball(x, y, size, makeStarColour(size), -10);
        }
    }

    private String makeStarColour(double size) {
        if (size <= averageStarSize*2) return "#FFFFFF";

        // only larger stars are coloured
        double determiner = SpaceArena.randomGenerator.nextDouble();
        if (determiner < 0.25) {
            // make star slighly red
            int redTint = SpaceArena.randomGenerator.nextInt(40)+35;
            return "#" + "FF" + hexOf(255-redTint) + hexOf(255-redTint);
        } else if (determiner < 0.5) {
            // make star slightly blue
            int blueTint = SpaceArena.randomGenerator.nextInt(40)+35;
            return "#" + hexOf(255-blueTint) + hexOf(255-blueTint) + "FF";
        }

        return "#FFFFFF";
    }
    private String hexOf(int num) {
        return Integer.toHexString(num).toUpperCase();
    }

    // accessor methods - none for setting star array, as I don't want people to change that
    public Ball[] getStars() {
        return shape;
    }
    public double getAverageStarSize() {
        return averageStarSize;
    }

    public void setAverageStarSize(double averageStarSize) {
        this.averageStarSize = averageStarSize;
    }
}
