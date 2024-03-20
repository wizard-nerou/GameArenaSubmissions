public class Enemy extends Sprite {
  public Enemy(double x, double y, GameArena arena, int size) {
    balls = new Ball[0];
    rectangles = new Rectangle[18];

    // make the enemy look like a space invader
    // use squares to make pixels

    // top row
    rectangles[0] = new Rectangle(x + size * 2, y, size, size, "GREEN");
    rectangles[1] = new Rectangle(x + size * 8, y, size, size, "GREEN");
    // second row
    rectangles[2] = new Rectangle(x + size * 3, y + size, size, size, "GREEN");
    rectangles[3] = new Rectangle(x + size * 7, y + size, size, size, "GREEN");
    // third row
    rectangles[4] = new Rectangle(x + size * 2, y + size * 2, size * 7, size, "GREEN");
    // fourth row
    rectangles[5] = new Rectangle(x + size, y + size * 3, size * 2, size, "GREEN");
    rectangles[6] = new Rectangle(x + size * 4, y + size * 3, size * 3, size, "GREEN");
    rectangles[7] = new Rectangle(x + size * 8, y + size * 3, size * 2, size, "GREEN");
    // fifth row
    rectangles[8] = new Rectangle(x, y + size * 4, size * 11, size, "GREEN");
    // sixth row
    rectangles[9] = new Rectangle(x, y + size * 5, size, size, "GREEN");
    rectangles[10] = new Rectangle(x + size * 2, y + size * 5, size * 7, size, "GREEN");
    rectangles[11] = new Rectangle(x + size * 10, y + size * 5, size, size, "GREEN");
    // seventh row
    rectangles[12] = new Rectangle(x, y + size * 6, size, size, "GREEN");
    rectangles[13] = new Rectangle(x + size * 2, y + size * 6, size, size, "GREEN");
    rectangles[14] = new Rectangle(x + size * 8, y + size * 6, size, size, "GREEN");
    rectangles[15] = new Rectangle(x + size * 10, y + size * 6, size, size, "GREEN");
    // eighth row
    rectangles[16] = new Rectangle(x + size * 3, y + size * 7, size * 2, size, "GREEN");
    rectangles[17] = new Rectangle(x + size * 6, y + size * 7, size * 2, size, "GREEN");

    hitbox = new Rectangle(x, y, size * 11, size * 8, "BLACK", -10);

    render(arena);
  }
}