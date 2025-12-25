package main.java.tetris.game;


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
}
