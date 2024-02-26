public class Main {
  private final static boolean DEBUG = true;

  public static void main(String[] args) {
    GameArena arena = new GameArena(800, 500);
    int score = 0;
    int round = 1;
    int speed = 1;
    long frames = 0;

    Enemy[][] enemies = new Enemy[5][11];
    Player p = new Player(-100, -100, arena);

    Bullet[] bullets = new Bullet[1000];

    // 1 = intro, 2 = game, 3 = game over
    int stage = 1;
    boolean stageInitialRender = true;

    // intro
    // display the title screen
    Text title = new Text("Space Invaders Ripoff", 30, 220, 40, "WHITE", -1);
    Text start = new Text("Press space to start", 30, 240, 80, "WHITE", -1);
    Text controls = new Text("Use the arrow keys to move and space to shoot", 30, 40, 120, "WHITE", -1);
    arena.addText(title);
    arena.addText(start);
    arena.addText(controls);
    Enemy e = new Enemy(280, 200, arena, 20);

    Text scoreText = new Text(" Score: " + score, 20, 20, 40, "WHITE", -1);

    Text gameOver = new Text("Game Over", 30, 300, 40, "WHITE", -1);
    Text finalScore = new Text("Final Score: " + score, 30, 280, 80, "WHITE", -1);
    Text restart = new Text("Press space to restart", 30, 240, 120, "WHITE", -1);

    while (true) {
      frames++;
      switch (stage) {
        case 1:
          // detect if the user presses space
          if (arena.spacePressed()) {
            stage = 2;
            arena.removeText(title);
            arena.removeText(start);
            arena.removeText(controls);
            e.remove(arena);
            stageInitialRender = false;
          }
          break;

        case 2:
          scoreText.setText(" Score: " + score);
          if (!stageInitialRender) {
            arena.addText(scoreText);
            for (int i = 0; i < enemies.length; i++) {
              for (int j = 0; j < enemies[i].length; j++) {
                enemies[i][j] = new Enemy(140 + j * 50, 100 + i * 40, arena, 3);
              }
            }

            p.remove(arena);
            p = new Player(400, 400, arena);
            stageInitialRender = true;
          }

          // move the enemies down
          if (frames % 10 == 0) {
            for (int i = 0; i < enemies.length; i++) {
              for (int j = 0; j < enemies[i].length; j++) {
                if (enemies[i][j] != null) {
                  enemies[i][j].move(0, speed);
                }
              }
            }
          }

          // check if the player has been hit
          for (int i = 0; i < enemies.length; i++) {
            for (int j = 0; j < enemies[i].length; j++) {
              if (enemies[i][j] != null && enemies[i][j].hitbox.collides(p.hitbox)) {
                stage = 3;

                p.remove(arena);
                p = new Player(-100, -100, arena);
                for (int k = 0; k < bullets.length; k++) {
                  if (bullets[k] != null) {
                    bullets[k].remove(arena);
                    bullets[k] = null;
                  }
                }

                arena.removeText(scoreText);

                for (int k = 0; k < enemies.length; k++) {
                  for (int l = 0; l < enemies[k].length; l++) {
                    if (enemies[k][l] != null) {
                      enemies[k][l].remove(arena);
                    }
                  }
                }

                stageInitialRender = false;
              }
            }
          }

          // handle the bullets
          for (int i = 0; i < bullets.length; i++) {
            if (bullets[i] != null) {
              bullets[i].move(0, -5);
              if (bullets[i].hitbox.getYPosition() < 0) {
                bullets[i].remove(arena);
                bullets[i] = null;
              }
            }
          }

          // check if the bullets have hit an enemy
          for (int i = 0; i < bullets.length; i++) {
            if (bullets[i] != null) {
              outer: for (int j = 0; j < enemies.length; j++) {
                for (int k = 0; k < enemies[j].length; k++) {
                  if (enemies[j][k] != null && enemies[j][k].hitbox.collides(bullets[i].hitbox)) {
                    enemies[j][k].remove(arena);
                    enemies[j][k] = null;
                    bullets[i].remove(arena);
                    bullets[i] = null;
                    score += 10;
                    break outer;
                  }
                }
              }
            }
          }

          // check if the player is trying to move
          if (arena.rightPressed() && p.hitbox.getXPosition() < 650) {
            p.move(5, 0);
          }
          if (arena.leftPressed() && p.hitbox.getXPosition() > 140) {
            p.move(-5, 0);
          }
          if (arena.spacePressed() && frames % 5 == 0 && bullets[bullets.length - 1] == null) {
            for (int i = 0; i < bullets.length; i++) {
              if (bullets[i] == null) {
                bullets[i] = new Bullet(p.hitbox.getXPosition() + 10, p.hitbox.getYPosition(), arena, 2);
                break;
              }
            }
          }
          if (DEBUG && arena.upPressed()) {
            speed++;
          }
          if (DEBUG && arena.downPressed()) {
            speed--;
          }

          // check if all the enemies are dead or if the player is past the enemies
          boolean allDead = true;
          boolean playerPast = true;
          for (int i = 0; i < enemies.length; i++) {
            for (int j = 0; j < enemies[i].length; j++) {
              if (enemies[i][j] != null) {
                allDead = false;
                if (enemies[i][j].hitbox.getYPosition() < 400) {
                  playerPast = false;
                }
              }
            }
          }

          if (allDead || playerPast) {
            // remove all the bullets
            for (int i = 0; i < bullets.length; i++) {
              if (bullets[i] != null) {
                bullets[i].remove(arena);
                bullets[i] = null;
              }
            }
            // remove all the enemies
            for (int i = 0; i < enemies.length; i++) {
              for (int j = 0; j < enemies[i].length; j++) {
                if (enemies[i][j] != null) {
                  enemies[i][j].remove(arena);
                  enemies[i][j] = null;
                }
              }
            }
            // move the player back to the bottom
            p.remove(arena);
            p = new Player(400, 400, arena);
            // increase the speed
            speed++;
            // increase the round
            round++;
            // increase the score
            score += 100;
            // add the enemies back
            for (int i = 0; i < enemies.length; i++) {
              for (int j = 0; j < enemies[i].length; j++) {
                enemies[i][j] = new Enemy(140 + j * 50, 100 + i * 40, arena, 3);
              }
            }
          }

          break;

        case 3:
          if (!stageInitialRender) {
            finalScore.setText("Final Score: " + score);

            arena.addText(gameOver);
            arena.addText(finalScore);
            arena.addText(restart);
            stageInitialRender = true;
          }

          if (arena.spacePressed()) {
            stage = 2;
            score = 0;
            round = 1;
            speed = 1;

            arena.removeText(gameOver);
            arena.removeText(finalScore);
            arena.removeText(restart);

            stageInitialRender = false;
          }
          break;

        default:
          break;
      }
      arena.pause();
    }
  }
}
