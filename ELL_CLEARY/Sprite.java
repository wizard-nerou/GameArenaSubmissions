abstract class Sprite {
  protected Ball[] balls;
  protected Rectangle[] rectangles;
  public Rectangle hitbox;

  protected void render(GameArena arena) {
    for (int i = 0; i < balls.length; i++) {
      arena.addBall(balls[i]);
    }
    for (int i = 0; i < rectangles.length; i++) {
      arena.addRectangle(rectangles[i]);
    }
    arena.addRectangle(hitbox);
  }

  public void move(double dx, double dy) {
    for (int i = 0; i < balls.length; i++) {
      balls[i].setXPosition(balls[i].getXPosition() + dx);
      balls[i].setYPosition(balls[i].getYPosition() + dy);
    }
    for (int i = 0; i < rectangles.length; i++) {
      rectangles[i].setXPosition(rectangles[i].getXPosition() + dx);
      rectangles[i].setYPosition(rectangles[i].getYPosition() + dy);
    }
    hitbox.setXPosition(hitbox.getXPosition() + dx);
    hitbox.setYPosition(hitbox.getYPosition() + dy);
  }

  public void remove(GameArena arena) {
    for (int i = 0; i < balls.length; i++) {
      arena.removeBall(balls[i]);
    }
    for (int i = 0; i < rectangles.length; i++) {
      arena.removeRectangle(rectangles[i]);
    }
    arena.removeRectangle(hitbox);
  }
}
