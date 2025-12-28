package main.java.tetris.util;

import main.java.tetris.ui.SidePanelLeft;

public class MathUtil {
    private int score;
    private int lines;
    private int level;

    public MathUtil() {
        this.score = 0;
        this.lines = 0;
        this.level = 1;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public int getLines() {
        return lines;
    }

    public void updateScore(SidePanelLeft panel, int lines) {
        if (lines == 0)
            return;

        if (lines == 1)
            this.score += 1;
        else if (lines == 2)
            this.score += 3;
        else if (lines == 3)
            this.score += 5;
        else if (lines == 4)
            this.score += 8;

        panel.setScore(this.score);
    }

    public void updateLines(SidePanelLeft panel, int lines) {
        this.lines += lines;
        panel.setLines(this.lines);
    }

    public void updateLevel(SidePanelLeft panel) {
        this.level = this.lines / 20 + 1;
        panel.setLevel(this.level);
    }

    public static double gravityThreshold(int level, double x0, double r, double xMin) {
        double x = x0 * Math.pow(r, level - 1);
        return Math.max(xMin, x);
    }
}
