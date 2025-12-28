package main.java.tetris.game;


public enum TetrominoType {
    I(new int[][] {
            {1, 1, 1, 1}
    }),

    O(new int[][] {
            {1, 1},
            {1, 1}
    }),

    T(new int[][] {
            {0, 1, 0},
            {1, 1, 1},
    }),

    S(new int[][]{
            {0, 1, 1},
            {1, 1, 0},
    }),

    Z(new int[][] {
            {1, 1, 0},
            {0, 1, 1},
    }),

    J(new int[][] {
            {1, 0, 0},
            {1, 1, 1},
    }),

    L(new int[][] {
            {0, 0, 1},
            {1, 1, 1},
    });

    public final int[][] shape;
    TetrominoType(int [][] shape) {
        this.shape = shape;
    }
}
