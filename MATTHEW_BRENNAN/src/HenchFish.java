public class HenchFish extends Enemy {
    Rectangle r = new Rectangle(600, 200, 50, 30, "RED");

    public HenchFish(int x, int y) {
        super(x, y);
    }

    public void addTo(GameArena gameArena) {
        gameArena.addRectangle(r);
    }

    public void setPosition(int x, int y) {
        r.setXPosition(x);
        r.setYPosition(y);
    }
}
