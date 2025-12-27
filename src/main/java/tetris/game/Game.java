package main.java.tetris.game;

import java.util.Random;

public class Game {
    public boolean tryMove(Board board, Tetromino p, int dx, int dy) {
        int mx = p.getX() + dx;
        int my = p.getY() + dy;

        if (board.canPlace(mx, my, p.getShape())) {
            p.move(dx, dy);
            return true;
        }

        return false;
    }

    public boolean tryRotate(Board board, Tetromino p) {
        int[][] before = Tetromino.copy(p.getShape());
        p.rotate();

        if (board.canPlace(p.getX(), p.getY(), p.getShape())) {
            return true;
        }

        p.setShape(before);
        return false;
    }

    public void dropTetromino(Board board, Tetromino p) {
        int lo = 0, hi = Board.H;

        while (hi - lo > 1) {
            int m = (lo + hi)/2;

            if (board.canPlace(p.getX(), p.getY() + m, p.getShape())) {
                lo = m;
            }
            else
                hi = m;
        }

        tryMove(board, p, 0, lo);
        return;
    }

    public boolean checkPlace(Board board, Tetromino p) {
        if (tryMove(board, p, 0, 1))
            return false;

        return true;
    }

    public Tetromino spawnTetromino() {
        TetrominoType[] values = TetrominoType.values();

        return new Tetromino(values[new Random().nextInt(values.length)]);
    }


    public void clearFilledRows(Board board) {
        for (int y = Board.H - 1; y >= 2; y--) {
            boolean filled = true;

            for (int x = 0; x < Board.W; ++x)
                if (board.grid[y][x] == 0) {
                    filled = false;
                    break;
                }

            if (filled)
                board.clearRow(y);
        }
    }
}
