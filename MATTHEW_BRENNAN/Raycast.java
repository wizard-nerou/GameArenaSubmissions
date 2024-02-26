import java.util.ArrayList;

public class Raycast {
    private Rectangle r = new Rectangle(0, 0, 10, 10, "ALPHARED", 100);

    public int cast(int b_x, int b_y, Gun gun, GameArena arena, Platform[] platformsList) {
        double angle = gun.getAngle(b_x, b_y, arena);
        for (int i = 1; i < gun.getMaxRange(); i += 1) {
            r.setXPosition((int) (b_x + ((i * 10) * Math.cos(angle))));
            r.setYPosition((int) (b_y + ((i * 10) * Math.sin(angle))));
            for (Platform p : platformsList) {
                if (r.collides(p.getPlatform())) {
                    return gun.getMaxRange() - i;
                }
            }
        }
        return 0;
    }

    public void addTo(GameArena gameArena) {
        gameArena.addRectangle(r);
    }

}
