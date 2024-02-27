package FINLAY_SMITH;

public class ScoreCounter extends Text {
    private int scoreValue;

    public ScoreCounter(double x, double y) {
        super("0", 50, x, y, "#FFFFFF", 20);
        scoreValue = 0;
    }

    private void updateScoreText() {
        this.setText(String.valueOf(scoreValue));
    }

    public void setScore(int score) {
        scoreValue = score;
        updateScoreText();
    }

    public int getScore() {
        return scoreValue;
    }

    public void increment() {
        scoreValue++;
        setScore(scoreValue);
        // algorithm to incrementally speed up the walls
        AsteroidWall.SPEED += 0.3 / ((double) scoreValue/2 + 3);
    }
}
