package main.java.tetris.game;
import javafx.scene.paint.Color;

public enum TetrominoType {
    I(new int[][] {
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    }),

    O(new int[][] {
            {1, 1},
            {1, 1}
    }),

    T(new int[][] {
            {0, 1, 0},
            {1, 1, 1},
            {0, 0, 0}
    }),

    S(new int[][]{
            {0, 1, 1},
            {1, 1, 0},
            {0, 0, 0}
    }),

    Z(new int[][] {
            {1, 1, 0},
            {0, 1, 1},
            {0, 0, 0}
    }),

    J(new int[][] {
            {1, 0, 0},
            {1, 1, 1},
            {0, 0, 0}
    }),

    L(new int[][] {
            {0, 0, 1},
            {1, 1, 1},
            {0, 0, 0}
    });

    public final int[][] shape;
    TetrominoType(int [][] shape) {
        this.shape = shape;
    }
}
