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

    public boolean checkPlace(Board board, Tetromino p) {
        if (tryMove(board, p, 0, 1))
            return false;

        return true;
    }
}
