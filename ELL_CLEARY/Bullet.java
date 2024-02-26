public class Bullet extends Sprite {
  public Bullet(double x, double y, GameArena arena, int size) {
    balls = new Ball[1];
    rectangles = new Rectangle[1];

    // a bullet is just a small rectangle with a ball at the end
    rectangles[0] = new Rectangle(x, y + size / 2, size, size * 2, "WHITE");
    balls[0] = new Ball(x + size / 2, y + size / 2, size, "WHITE", 1);

    hitbox = new Rectangle(x, y, size, size * 2.5, "BLACK", -10);

    render(arena);
  }
}