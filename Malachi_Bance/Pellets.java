public class Pellets {
    private GameArena arena;
    private int gridSize;
    private double size;
    private Ball[][] balls;

    public int totalPellets = 0;

    public int map[][] = {
{1,1,1,1,1,1,1,1,1,0,1,2,2,1,0,1,1,1,1,1,1,1,1,1},
{1,2,2,2,2,2,2,2,1,0,1,2,2,1,0,1,2,2,2,2,2,2,2,1},
{1,2,1,1,2,1,2,2,1,1,1,2,2,1,1,1,2,1,2,2,1,1,2,1},
{1,2,1,1,2,1,2,2,2,2,2,2,2,2,2,2,2,1,2,2,1,1,2,1},
{1,2,2,2,3,1,2,2,1,1,1,2,2,1,1,1,2,1,2,2,2,3,2,1},
{1,2,2,1,1,1,2,2,2,2,1,2,2,1,2,2,2,1,2,1,1,1,2,1},
{1,2,2,2,2,2,2,2,2,2,1,2,2,1,2,2,2,1,2,2,2,2,2,1},
{1,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,1},
{1,2,2,2,2,1,1,2,1,2,2,2,2,2,2,1,2,1,1,2,1,2,2,1},
{1,1,2,1,2,2,2,2,1,2,1,1,1,1,2,1,2,1,1,2,1,2,1,1},
{0,1,2,1,2,2,2,2,1,2,1,0,0,1,2,1,2,2,2,2,2,2,1,0},
{0,1,2,1,1,1,1,2,1,2,0,0,0,1,2,2,2,1,1,1,1,2,1,0},
{0,1,2,1,1,1,1,2,1,2,0,0,0,1,2,2,2,1,1,1,1,2,1,0},
{0,1,2,1,2,2,2,2,1,2,1,0,0,1,2,1,2,2,2,2,2,2,1,0},
{1,1,2,1,2,2,2,2,1,2,1,1,1,1,2,1,2,1,1,2,1,2,1,1},
{1,2,2,2,2,1,1,2,1,2,2,2,2,2,2,1,2,1,1,2,1,2,2,1},
{1,2,2,2,2,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,1,1,2,1},
{1,2,2,2,2,2,2,2,2,2,1,2,2,1,2,2,2,1,2,2,2,2,2,1},
{1,2,2,1,1,1,2,2,2,2,1,2,2,1,2,2,2,1,2,1,1,1,2,1},
{1,2,2,2,3,1,2,2,1,1,1,2,2,1,1,1,2,1,2,2,2,3,2,1},
{1,2,1,1,2,1,2,2,2,2,2,2,2,2,2,2,2,1,2,2,1,1,2,1},
{1,2,1,1,2,1,2,2,1,1,1,2,2,1,1,1,2,1,2,2,1,1,2,1},
{1,2,2,2,2,2,2,2,1,0,1,2,2,1,0,1,2,2,2,2,2,2,2,1},
{1,1,1,1,1,1,1,1,1,0,1,2,2,1,0,1,1,1,1,1,1,1,1,1}
    };

    public Pellets(GameArena a, int g) {
        arena = a;
        gridSize = g;
        size = (a.getArenaWidth()/g);
        balls = new Ball[gridSize][gridSize];
        System.out.println("Pelleter Created");
    }

    public Boolean eat(int i,int j) {
        arena.removeBall(balls[i][j]);
        totalPellets--;
        if (totalPellets <= 0) {
            System.out.println("YOU WIN!");
            return true;
        }
        return false;
    }

    public void spawn() {
        for (int i=0; i < gridSize; i++) {
            for (int j=0; j < gridSize; j++) {
                if (map[i][j] == 2) {
                    balls[i][j] = new Ball((i*size) + (size/2), (j*size) + (size/2), size/5, "WHITE");
                    arena.addBall(balls[i][j]);
                    totalPellets++;
                }
                if (map[i][j] == 3) {
                    balls[i][j] = new Ball((i*size) + (size/2), (j*size) + (size/2), size/2, "WHITE");
                    arena.addBall(balls[i][j]);
                    totalPellets++;
                }
                if (map[i][j] == 1) {
                    arena.addRectangle(new Rectangle(i*size, j*size, size, size, "#503bdb"));
                    arena.addRectangle(new Rectangle(i*size +4, j*size, size -8, size -8, "#59a9ff"));
                }
            }
        }
        System.out.println("Pellets Created");
    }
}
