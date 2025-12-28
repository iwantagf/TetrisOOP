package main.java.tetris.util;

import main.java.tetris.ui.SidePanelLeft;

public class MathUtil {
    private int score;
    private int lines;
    private int level;
    private int lineCurrent;

    public MathUtil() {
        score = 0;
        lines = 0;
        level = 1;
        lineCurrent = 0;
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
        lineCurrent += lines;
        panel.setLines(this.lines);
    }

    public void updateLevel(SidePanelLeft panel) {
        if (lineCurrent > 20) {
            this.level++;
        }
        lineCurrent %= 20;
        panel.setLevel(this.level);
    }

    public static double gravityThreshold(int level, double x0, double r, double xMin) {
        double x = x0 * Math.pow(r, level - 1);
        return Math.max(xMin, x);
    }
}
