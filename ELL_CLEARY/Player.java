public class Player extends Sprite {
  public Player(double x, double y, GameArena arena) {
    balls = new Ball[1];
    rectangles = new Rectangle[5];

    // TODO: make the rocket ship look better
    // - make the point more pointy
    // - make the boosters look like they're firing

    // rocket ship
    // have a main body, with two smaller rectangles on top for the point
    // a circle for the window and two boosters on the bottom
    rectangles[0] = new Rectangle(x, y + 20, 20, 40, "LIGHTGREY");
    // point
    rectangles[1] = new Rectangle(x + 5, y + 7.5, 10, 12.5, "LIGHTGREY");
    rectangles[2] = new Rectangle(x + 7.75, y, 5, 7.5, "LIGHTGREY");
    // boosters
    rectangles[3] = new Rectangle(x + 4, y + 60, 4, 5, "DARKGREY");
    rectangles[4] = new Rectangle(x + 12, y + 60, 4, 5, "DARKGREY");
    // window
    balls[0] = new Ball(x + 10, y + 30, 10, "WHITE", 1);

    hitbox = new Rectangle(x, y, 20, 65, "BLACK", -5);

    render(arena);
  }
}