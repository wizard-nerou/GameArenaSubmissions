public class Main {

    private static void reset(GameArena arena, Player p, Ghost bl, Ghost pi, Ghost in, Ghost cl) {
        bl.x = arena.getArenaWidth()/2;
        bl.y = (arena.getArenaWidth()/2)-bl.size;
        bl.mx = 0; bl.my = 0;
        pi.x = arena.getArenaWidth()/2;
        pi.y = arena.getArenaWidth()/2;
        pi.mx = 0; pi.my = 0;
        in.x = (arena.getArenaWidth()/2)-in.size;
        in.y = arena.getArenaWidth()/2;
        in.mx = 0; in.my = 0;
        cl.x = (arena.getArenaWidth()/2)+cl.size;
        cl.y = arena.getArenaWidth()/2;
        cl.mx = 0; cl.my = 0;
        bl.move();
        pi.move();
        in.move();
        cl.move();
        p.x = (int)(arena.getArenaWidth()  / 2);
        p.y = (int)(arena.getArenaWidth()  / 3)-16;
        p.mx = 0; p.my = 0;
    }

    public static void main(String[] args) {
        GameArena arena = new GameArena(576,608);

        int gridSize = 24;
        
        Pellets pellets = new Pellets(arena, gridSize);
        pellets.spawn();

        Player player = new Player(arena, gridSize);

        Ghost blinky = new Ghost(arena, gridSize, "#e21414", 4);
        blinky.spawn(arena.getArenaWidth()/2, (arena.getArenaWidth()/2)-blinky.size);

        Ghost pinky = new Ghost(arena, gridSize, "#e288b8", 3);
        pinky.spawn(arena.getArenaWidth()/2, arena.getArenaWidth()/2);

        Ghost inky = new Ghost(arena, gridSize, "#84fffa", 1);
        inky.spawn((arena.getArenaWidth()/2)-inky.size, arena.getArenaWidth()/2);

        Ghost clyde = new Ghost(arena, gridSize, "#f26d00", 0);
        clyde.spawn((arena.getArenaWidth()/2)+clyde.size, arena.getArenaWidth()/2);

        Boolean play = false;

        int timer = 50*7;
        Boolean scatter = true;

        int runawayTimer = 0;

        System.out.println("SCATTER!");

        while(true) {
            player.updateScore();
            if (play) {
                timer--;
            }
            if (timer <= 0) {
                if (scatter) {
                    timer = 50*20;
                    System.out.println("CHASE!");
                } else {
                    timer = 50*7;
                    System.out.println("SCATTER!");
                }
                scatter = !scatter;
            }
            if (runawayTimer > 0) {
                runawayTimer--;
                if (runawayTimer == 0) {
                    blinky.resume();
                    pinky.resume();
                    inky.resume();
                    clyde.resume();
                }
            }
            if (arena.downPressed()) {
                play = true;
                if (player.my !=  1) { player.setDirection( 0, 1); };
            } else if (arena.upPressed()) {
                play = true;
                if (player.my != -1) { player.setDirection( 0,-1); };
            } else if (arena.rightPressed()) {
                play = true;
                if (player.mx !=  1) { player.setDirection( 1, 0); };
            } else if (arena.leftPressed()) {
                play = true;
                if (player.mx != -1) { player.setDirection(-1, 0); };
            } else {
                player.snap();
            }
            int collide = player.collisions(pellets, blinky, pinky, inky, clyde, runawayTimer);
            if (collide == 1) {
                if (player.life <= 0) {
                    arena.removeBall(player.life1);
                    System.out.println("GAME OVER!");
                    Rectangle band = new Rectangle(0,(int)(arena.getArenaHeight()/2)-60,arena.getArenaWidth(), 75, "BLACK");
                    Text shadow = new Text("GAME OVER!", 60, (int)(arena.getArenaWidth()/6)-3, (int)(arena.getArenaHeight()/2)+3, "RED", 1);
                    Text gameOver = new Text("GAME OVER!", 60, (int)(arena.getArenaWidth()/6), (int)(arena.getArenaHeight()/2), "WHITE", 2);
                    arena.addRectangle(band);
                    arena.addText(shadow);
                    arena.addText(gameOver);
                    play = false;
                    break;
                }
                if (player.life == 2) {
                    arena.removeBall(player.life3);
                }
                if (player.life == 1) {
                    arena.removeBall(player.life2);
                }
                blinky.resume();
                pinky.resume();
                inky.resume();
                clyde.resume();
                reset(arena, player, blinky, pinky, inky, clyde);
                play = false;
                timer = 50*7;
                scatter = true;
            } else if (collide == 2) {
                System.out.println("YOU WIN!");
                Rectangle band = new Rectangle(0,(int)(arena.getArenaHeight()/2)-60,arena.getArenaWidth(), 75, "BLACK");
                Text shadow = new Text("~YOU WIN!~", 60, (int)(arena.getArenaWidth()/6)-3, (int)(arena.getArenaWidth()/2)+3, "GREEN", 1);
                Text gameOver = new Text("~YOU WIN!~", 60, (int)(arena.getArenaWidth()/6), (int)(arena.getArenaWidth()/2), "WHITE", 2);
                arena.addRectangle(band);
                arena.addText(shadow);
                arena.addText(gameOver);
                play = false;
                break;
            } else if (collide == 3) {
                scatter = false;
                timer = 7*50;
                runawayTimer = 7*50;
                blinky.run();
                pinky.run();
                inky.run();
                clyde.run();
            } else if (collide == 10) {
                blinky.x = arena.getArenaWidth()/2;
                blinky.y = (arena.getArenaWidth()/2)-blinky.size;
                blinky.mx = 0; blinky.my = 0;
                blinky.resume();
            } else if (collide == 20) {
                pinky.x = arena.getArenaWidth()/2;
                pinky.y = arena.getArenaWidth()/2;
                pinky.mx = 0; pinky.my = 0;
                pinky.resume();
            } else if (collide == 30) {
                inky.x = (arena.getArenaWidth()/2)-inky.size;
                inky.y = arena.getArenaWidth()/2;
                inky.mx = 0; inky.my = 0;
                inky.resume();
            } else if (collide == 40) {
                clyde.x = (arena.getArenaWidth()/2)+clyde.size;
                clyde.y = arena.getArenaWidth()/2;
                clyde.mx = 0; clyde.my = 0;
                clyde.resume();
            }
            player.move();
            if (play && !scatter) {
                blinky.think(pellets, player.x, player.y);
                blinky.move();
                pinky.think(pellets, player.x, player.y);
                pinky.move();
                inky.think(pellets, player.x, player.y);
                inky.move();
                clyde.think(pellets, player.x, player.y);
                clyde.move();
            } else if (play && scatter) {
                blinky.think(pellets, (int)(arena.getArenaWidth()*0.9), (int)(arena.getArenaHeight()*0.1));
                blinky.move();
                pinky.think(pellets, (int)(arena.getArenaWidth()*0.1), (int)(arena.getArenaHeight()*0.1));
                pinky.move();
                inky.think(pellets, (int)(arena.getArenaWidth()*0.9), (int)(arena.getArenaHeight()*0.9));
                inky.move();
                clyde.think(pellets, (int)(arena.getArenaWidth()*0.1), (int)(arena.getArenaHeight()*0.9));
                clyde.move();
            }
            arena.pause();
        }
    }
}
