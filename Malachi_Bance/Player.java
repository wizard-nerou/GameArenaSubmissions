public class Player {

    private GameArena arena;
    private int gridSize;
    private int size;
    public int x;
    public int y;
    public int mx;
    public int my; 
    private Ball b;
    private double speed = 3;
    private Boolean snapped = false;
    private int toggler = 0;
    private Boolean toggled = true;
    private Ball mouth0;
    private Ball mouth1;

    public Ball life1;
    public Ball life2;
    public Ball life3;

    public Text lifeText;
    public Text scoreText;
    public Text scoreTextValue;

    public int score = 0;

    public int life = 3;

    public Player(GameArena a, int g) {
        arena = a;
        gridSize = g;
        size = (int)(a.getArenaWidth()/g);
        x = (int)(a.getArenaWidth()  / 2);
        y = (int)(a.getArenaWidth() / 3)-16;
        b = new Ball(x, y, size, "#ffe500");
        arena.addBall(b);
        mouth0 = new Ball(x, y, size/1.75, "BLACK");
        mouth1 = new Ball(x, y, size/3, "BLACK");
        
        life1 = new Ball((a.getArenaWidth())-16, a.getArenaHeight()-16, 32, "#ffe500");
        life2 = new Ball((a.getArenaWidth())-48, a.getArenaHeight()-16, 32, "#ffe500");
        life3 = new Ball((a.getArenaWidth())-80, a.getArenaHeight()-16, 32, "#ffe500");
        lifeText = new Text("LIVES:", 32, (a.getArenaWidth())-200, a.getArenaHeight()-4, "WHITE");
        scoreText = new Text("SCORE:", 32, 0, a.getArenaHeight()-4, "WHITE");
        scoreTextValue = new Text(String.format("%d",score), 32, 126, a.getArenaHeight()-4, "#ff00aa");

        arena.addBall(life1);
        arena.addBall(life2);
        arena.addBall(life3);
        arena.addText(lifeText);
        arena.addText(scoreText);
        arena.addText(scoreTextValue);

        System.out.println("Player Created");
    }

    public void updateScore() {
        arena.removeText(scoreTextValue);
        scoreTextValue = new Text(String.format("%d",score), 32, 126, arena.getArenaHeight()-4, "#ff00aa");
        arena.addText(scoreTextValue);
    }

    public void setDirection(int dx, int dy) {
        mx = dx;
        my = dy;
        snapped = false;
        this.snap();
    }

    public void snap() {
        if (!snapped) {
            x = (x / size)*size + size/2;
            y = (y / size)*size + size/2;
            snapped = true;
        }
    }

    public void move() {
        x+=mx*speed;y+=my*speed;
        toggler += 1;
        if (x > arena.getArenaWidth()) {
            x = 0;
        }
        if (x < 0) {
            x = arena.getArenaWidth();
        }
        if (y > arena.getArenaWidth()) {
            y = 0;
        }
        if (y < 0) {
            y = arena.getArenaWidth();
        }
        b.setXPosition(x);
        b.setYPosition(y);
        mouth0.setXPosition(x+(mx*8));
        mouth0.setYPosition(y+(my*8));
        mouth1.setXPosition(x+(mx*2));
        mouth1.setYPosition(y+(my*2));
        if (toggler > 4 && (mx != 0 || my != 0)) {
            if (!toggled) {
                arena.removeBall(mouth0);
                arena.removeBall(mouth1);
            } else {
                mouth0.setSize(size/1.75);
                mouth1.setSize(size/3);
                arena.addBall(mouth0);
                arena.addBall(mouth1);
            }
            toggled = !toggled;
            toggler = 0;
        } else if (toggler > 2 && (mx != 0 || my != 0)) {
            if (!toggled) {
                arena.removeBall(mouth0);
                arena.removeBall(mouth1);
                mouth0.setSize(size/2);
                mouth1.setSize(size/5);
                arena.addBall(mouth0);
                arena.addBall(mouth1);
            }
        }
    }

    private double distance(int x1, int y1, int x2, int y2) {
        return Math.hypot(Math.abs(y2-y1),Math.abs(x2-x1));
    }

    public int collisions(Pellets p, Ghost bl, Ghost pi, Ghost in, Ghost cl, int runaway) {
        int apx = (int)(x / size)+mx;
        int apy = (int)(y / size)+my;

        if (this.distance(bl.x,bl.y,x,y) < size) {
            if (!bl.runaway) {
                System.out.println("LIFELOST");
                life--;
                return 1;
            } else {
                score+=200;
                return 10;
            }
        }
        if (this.distance(pi.x,pi.y,x,y) < size) {
            if (!pi.runaway) {
                System.out.println("LIFELOST");
                life--;
                return 1;
            } else {
                score+=200;
                return 20;
            }
        }
        if (this.distance(in.x,in.y,x,y) < size) {
            if (!in.runaway) {
                System.out.println("LIFELOST");
                life--;
                return 1;
            } else {
                score+=200;
                return 30;
            }
        }
        if (this.distance(cl.x,cl.y,x,y) < size) {
            if (!cl.runaway) {
                System.out.println("LIFELOST");
                life--;
                return 1;
            } else {
                score+=200;
                return 40;
            }
        }

        if (apx-mx >= 0 && apx-mx <= gridSize-1 && apy-my >= 0 && apy-my <= gridSize-1) {
            if (p.map[apx-mx][apy-my] == 2) {
                p.map[apx-mx][apy-my] = 0;
                score += 10;
                Boolean ate = p.eat(apx-mx,apy-my);
                if (ate) {
                    return 2;
                }
            }else if (p.map[apx-mx][apy-my] == 3) {
                p.map[apx-mx][apy-my] = 0;
                score += 50;
                Boolean ate = p.eat(apx-mx,apy-my);
                if (ate) {
                    return 2;
                } else {
                    return 3;
                }
            }
        }
        if (apx > gridSize-1) {
            apx = 0;
        } else if (apx < 0) {
            apx = gridSize-1;
        }
        if (apy > gridSize-1) {
            apy = 0;
        } else if (apy < 0) {
            apy = gridSize-1;
        }
        if (p.map[apx][apy] == 1) {
            mx = 0;
            my = 0;
            snapped = false;
            // this.snap();
            arena.removeBall(mouth0);
            arena.removeBall(mouth1);
            toggler = 0;
            toggled = false;
        }
        return 0;
    }
}
