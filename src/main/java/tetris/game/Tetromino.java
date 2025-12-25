package main.java.tetris.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Tetromino {
    private int x;
    private int y;
    private int[][] shape;
    private final Color color;

    public Tetromino(TetrominoType type) {
        this.shape = type.shape;
        this.color = type.color;

        this.x = (Board.W - shape[0].length) / 2;
        this.y = 0;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void rotate() {
        shape = rotateCW(shape);
    }

    public int[][] getShape() {
        return shape;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public void draw(GraphicsContext g) {
        for (int r = 0; r < shape.length; ++r) {
            for (int c = 0; c < shape[0].length; ++c) {
                if (shape[r][c] != 0) {
                    g.setFill(color);
                    g.fillRect(
                            (x + c) * Board.TILE,
                            (y + r) * Board.TILE,
                            Board.TILE,
                            Board.TILE
                    );

                    g.setStroke(Color.BLACK);
                    g.strokeRect(
                            (x + c) * Board.TILE,
                            (y + r) * Board.TILE,
                            Board.TILE,
                            Board.TILE
                    );
                }
            }
        }
    }

    private int[][] rotateCW(int[][] m) {
        int n = m.length;
        int[][] res = new int[n][n];

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                res[j][n - i - 1] = m[i][j];

        return res;
    }

    private int[][] copy(int[][] src) {
        int [][] dst = new int[src.length][src[0].length];

        for (int i = 0; i < src.length; i++)
            System.arraycopy(src[i], 0, dst[i], 0, src[0].length);

        return dst;
    }
}
