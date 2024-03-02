public class Enemy {
    protected int x;
    protected int y;
    protected int m_x = 4;
    protected Rectangle r = new Rectangle(600, 200, 50, 30, "RED", 100);

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void addTo(GameArena gameArena) {
        // addTo
    }

    public void setPosition(int x, int y) {

    }

    public boolean is_colliding(Platform[] platforms) {
        for (Platform i : platforms) {
            if (i.getPlatform().collides(r)) {
                return true;
            }
        }
        return false;
    }

    public void physics_process(GameArena gameArena) {

        x = x + m_x;
        setPosition(x, y);

    }
}
