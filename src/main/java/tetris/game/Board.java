package main.java.tetris.game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board {
    public static final int W = 10;
    public static final int H = 22;
    public static final int TILE = 30;

    public static final int SQUARE_W = W * TILE;
    public static final int SQUARE_H = H * TILE;

    private int[][] grid = new int[H][W];
    private Color[][] cellColor = new Color[H][W];

    public Board() {
        for (int y = 0; y < H; y++)
            for (int x = 0; x < W; x++) {
                this.cellColor[y][x] = Color.BLACK;
                this.grid[y][x] = 0;
            }
    }

    public void drawBoard(GraphicsContext g) {
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, SQUARE_W, SQUARE_H);

        for (int y = 2; y < H; y++)
            for (int x = 0; x < W; x++) {
                if (this.grid[y][x] == 0) {
                    drawGrid(g, x, y);
                }
                else
                    drawCell(g, x, y, cellColor[y][x]);
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

    public boolean canPlace(int px, int py, int[][] shape) {
        for (int r = 0; r < shape.length; ++r)
            for (int c = 0; c < shape[0].length; ++c) {
                if (shape[r][c] == 0)
                    continue;
                int x = px + c;
                int y = py + r;

                if (x < 0 || x >= W || y >= H)
                    return false;

                if (y >= 0 && grid[y][x] != 0)
                    return false;
            }

        return true;
    }

    public void placeTetromino(Tetromino p) {
        int[][] shape = p.getShape();
        int px = p.getX();
        int py = p.getY();

        for (int r = 0; r < shape.length; r++)
            for (int c = 0; c < shape[0].length; c++) {
                if (shape[r][c] == 0)
                    continue;

                cellColor[py + r][px + c] = p.getColor();
                grid[py + r][px + c] = 1;
            }
    }
}
