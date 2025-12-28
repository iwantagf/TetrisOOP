package main.java.tetris.game;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Random;

public class Game {
    private final Deque<TetrominoType> queue = new ArrayDeque<>();
    private final Random rng = new Random();

    private TetrominoType holdType = null;
    private boolean usedHold = false;

    public boolean tryMove(Board board, Tetromino p, int dx, int dy) {
        int mx = p.getX() + dx;
        int my = p.getY() + dy;

        if (board.canPlace(mx, my, p.getShape())) {
            p.move(dx, dy);
            return true;
        }

        return false;
    }

    public void tryRotate(Board board, Tetromino p) {
        int[][] before = Tetromino.copy(p.getShape());
        p.rotate();

        if (board.canPlace(p.getX(), p.getY(), p.getShape())) {
            return;
        }

        p.setShape(before);
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
    }

    public boolean checkPlace(Board board, Tetromino p) {
        return !tryMove(board, p, 0, 1);
    }

    private void refillBag() {
        List<TetrominoType> bag = new ArrayList<>();
        Collections.addAll(bag, TetrominoType.values());
        Collections.shuffle(bag, rng);
        queue.addAll(bag);
    }

    private void ensureQueue(int need) {
        while (queue.size() < need) {
            refillBag();
        }
    }
    public Tetromino spawnTetromino() {
        ensureQueue(8);
        TetrominoType t = queue.removeFirst();
        return new Tetromino(t);
    }

    public TetrominoType[] nextFive() {
        ensureQueue(5);

        TetrominoType[] res = new TetrominoType[5];

        int i = 0;

        for (TetrominoType t : queue) {
            res[i++] = t;
            if (i == 5)
                break;
        }

        return res;
    }


    public int clearFilledRows(Board board) {
        int res = 0;
        for (int y = Board.H - 1; y >= 2; y--) {
            boolean filled = true;

            for (int x = 0; x < Board.W; ++x)
                if (board.grid[y][x] == 0) {
                    filled = false;
                    break;
                }

            if (filled) {
                board.clearRow(y);
                res += 1;
                ++y;
            }
        }
        return res;
    }

    public Tetromino hold(Tetromino current) {
        if (usedHold)
            return current;

        TetrominoType curType = current.getType();

        Tetromino next;

        if (holdType == null) {
            holdType = curType;
            next = spawnTetromino();
        }
        else {
            next = new Tetromino(holdType);
            holdType = curType;
        }

        usedHold = true;
        return next;
    }

    public void onPieceLocked() {
        usedHold = false;
    }

    public TetrominoType getHoldType() {
        return holdType;
    }
}
