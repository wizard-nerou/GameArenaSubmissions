package FINLAY_SMITH.GameAssets;

public class Asteroid extends Body {

    public static final int DIAMETER = 60;

    public Asteroid(int x, int y) {
        super(x, y);
        buildAsteroid();
    }

    public Asteroid(int x, int y, double speed) {
        super(x, y);
        this.velocity.x = -speed;
        buildAsteroid();
    }

    private void buildAsteroid() {
        // Build the asteroid circles
        Ball asteroid = new Ball(position.x, position.y, 50, "#353535", 0);

        Ball smallCrater = new Ball(position.x, (position.y + 14), 8, "#020202", 1);
        Ball medCrater = new Ball((position.x + 14), position.y, 12, "#020202", 1);
        Ball largeCrater = new Ball((position.x - 6), (position.y - 6), 20, "#020202", 1);

        this.shape = new Ball[]{asteroid, smallCrater, medCrater, largeCrater};
//        this.shape = new Ball[]{asteroid}; // for testing - shows hit-box only
    }

    public void rotate(double angle) {
        for (int i = 1; i < shape.length; i++) {
            // update position of shapes that make up the asteroid to pivot around the centre, effectively rotating the asteroid
            // not precisely accurate, so the craters get pulled in to the centre over time. But, fine for less than 1 rotation
            this.shape[i].setXPosition(this.position.x + (this.shape[i].getXPosition() - this.position.x)*Math.cos(angle) - (this.shape[i].getYPosition() - this.position.y)*Math.sin(angle));

            this.shape[i].setYPosition(this.position.y + (this.shape[i].getXPosition() - this.position.x)*Math.sin(angle) + (this.shape[i].getYPosition() - this.position.y)*Math.cos(angle));
        }
    }

    public void wobble(double wobble) {
        this.velocity.y = 3 * (Math.sin(wobble * 0.2));
    }


}
