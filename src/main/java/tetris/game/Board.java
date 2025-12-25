package main.java.tetris.game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board {
    public static final int W = 10;
    public static final int H = 20;
    public static final int TILE = 30;

    public static final int SQUARE_W = W * TILE;
    public static final int SQUARE_H = H * TILE;

    private final int[][] grid = new int[H][W];

    public void drawBoard(GraphicsContext g) {
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, SQUARE_W, SQUARE_H);

        for (int y = 0; y < H; y++)
            for (int x = 0; x < W; ++x) {
                if (grid[y][x] != 0) {
                    drawCell(g, x, y, Color.CYAN);
                }
                else {
                    drawGrid(g, x, y);
                }
            }
    }

    private void drawCell(GraphicsContext g, int x, int y, Color c) {
        g.setFill(c);
        g.fillRect(x * TILE, y * TILE, TILE, TILE);
        g.setStroke(Color.BLACK);
        g.strokeRect(x * TILE, y * TILE, TILE, TILE);
    }

    private void drawGrid(GraphicsContext g, int x, int y) {
        g.setStroke(Color.rgb(40, 40, 40));
        g.strokeRect(x * TILE, y * TILE, TILE, TILE);
    }
}
