package ISAAC_HARRISON;

import java.util.Arrays;
import java.util.Random;

public class GOL {
    Random random = new Random();

    private int cellSize;
    private int cellPad;
    private int topPad = 100;

    private Text title;
    private Text startInfo;
    private Text randomInfo;
    private Text frameInfo;

    private int[][] aliveNeighbours;
    private boolean[][] cellValue;
    private Rectangle[][] cells;

    /**
     * @param width    - Width of the window
     * @param height   - height of the window
     * @param cellSize - size of each cell
     * @param cellPad  - padding between cells
     */
    public GOL(int width, int height, int cellSize, int cellPad) {
        this.cellSize = cellSize;
        this.cellPad = cellPad;
        int displayAreaHeight = height - topPad;

        GameArena arena = new GameArena(width, height);
        while (true) {
            arena.clearGameArena();

            aliveNeighbours = new int[displayAreaHeight / (cellSize + cellPad)][width / (cellSize + cellPad)];
            cellValue = new boolean[displayAreaHeight / (cellSize + cellPad)][width / (cellSize + cellPad)];
            cells = new Rectangle[displayAreaHeight / (cellSize + cellPad)][width / (cellSize + cellPad)];

            for (boolean[] row : cellValue)
                Arrays.fill(row, false);

            title = new Text("Game Of Life", 20, 5, 20, "White");
            startInfo = new Text("Press SPACE to start", 10, 5, 45, "White");
            frameInfo = new Text("", 10, 5, 60, "White");
            randomInfo = new Text("Press 'R' to randomly seed the grid", 10, 5, 60, "White");

            arena.addText(title);
            arena.addText(startInfo);
            arena.addText(randomInfo);
            arena.addText(frameInfo);

            createCells(arena);

            boolean randomTrigered = false;

            while (!arena.spacePressed()) {
                arena.pause();
                if (arena.letterPressed((char) 'r') && !randomTrigered) {
                    randomSeed();
                    randomTrigered = true;
                } else if (!arena.letterPressed((char) 'r')) {
                    randomTrigered = false;
                }
            }
            startInfo.setText("");
            startInfo.setText("Hold 'q' to restart");
            randomInfo.setText("");

            int i = 0;
            while (true) {
                nextFrame();
                i++;
                frameInfo.setText("Frame: " + i);
                if (arena.letterPressed((char) 'q')) {
                    break;
                }
                arena.pause1s();
            }
        }

    }

    /**
     * Updates the colour of the cell to match the value in cellValue
     * 
     * @param row - the row of the cell
     * @param col - the column of the cell
     */
    private void setCellColour(int row, int col) {
        cells[row][col].setColour(cellValue[row][col] ? "red" : "white");
    }

    /**
     * Puts random data into cellValue
     */
    private void randomSeed() {
        for (int row = 0; row < cellValue.length; row++) {
            for (int col = 0; col < cellValue[0].length; col++) {
                cellValue[row][col] = random.nextInt(10) == 0;// 10 % chance
                setCellColour(row, col);
            }
        }
    }

    /**
     * Display the next frame
     */
    private void nextFrame() {
        checkNeighbours();
        for (int row = 0; row < cellValue.length; row++) {
            for (int col = 0; col < cellValue[0].length; col++) {

                if (cellValue[row][col] && aliveNeighbours[row][col] < 2)// Under Population
                    cellValue[row][col] = false;
                if (cellValue[row][col] && aliveNeighbours[row][col] > 3)// Overpopulation
                    cellValue[row][col] = false;
                if (!cellValue[row][col] && aliveNeighbours[row][col] == 2)// Reproduction
                    cellValue[row][col] = true;
                setCellColour(row, col);
            }

        }

    }

    /**
     * Check how many neighbors are alive
     */
    void checkNeighbours() {
        for (int row = 0; row < cellValue.length; row++) {
            for (int col = 0; col < cellValue[0].length; col++) {
                aliveNeighbours[row][col] = 0;
                boolean onTop = row == 0;
                boolean onBottom = row == cellValue.length - 1;
                boolean onLeft = col == 0;
                boolean onRight = col == cellValue[0].length - 1;

                if (!onTop && cellValue[row - 1][col])
                    aliveNeighbours[row][col]++; // Above
                if (!onBottom && cellValue[row + 1][col])
                    aliveNeighbours[row][col]++; // Below
                if (!onLeft && cellValue[row][col - 1])
                    aliveNeighbours[row][col]++; // Left
                if (!onRight && cellValue[row][col + 1])
                    aliveNeighbours[row][col]++; // Right

                if (!onTop && !onLeft && cellValue[row - 1][col - 1])
                    aliveNeighbours[row][col]++; // Top Left
                if (!onTop && !onRight && cellValue[row - 1][col + 1])
                    aliveNeighbours[row][col]++; // Top Right
                if (!onBottom && !onLeft && cellValue[row + 1][col - 1])
                    aliveNeighbours[row][col]++; // Bottom Left
                if (!onBottom && !onRight && cellValue[row + 1][col + 1])
                    aliveNeighbours[row][col]++; // Bottom Right
            }
        }
    }

    /**
     * Create Grid
     * 
     * @param arena - arena to put cells in
     */
    void createCells(GameArena arena) {
        for (int row = 0; row < cellValue.length; row++) {
            for (int col = 0; col < cellValue[0].length; col++) {
                cells[row][col] = new Rectangle((cellSize + cellPad) * col + cellPad,
                        topPad + (cellSize + cellPad) * row + cellPad, cellSize, cellSize,
                        cellValue[row][col] ? "red" : "white");
                arena.addRectangle(cells[row][col]);
            }
        }
    }

    public static void main(String[] args) {
        // new GOL(8, 1);
        new GOL(500, 400, 4, 1);
        // new GOL(2, 0);
    }
}
