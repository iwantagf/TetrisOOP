package main.java.tetris.game;
import javafx.scene.paint.Color;

public enum TetrominoType {
    I(new int[][] {
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
    }, Color.LIGHTGREEN),

    O(new int[][] {
            {1, 1},
            {1, 1}
    }, Color.YELLOW),

    T(new int[][] {
            {0, 1, 0},
            {1, 1, 1}
    }, Color.MEDIUMPURPLE),

    S(new int[][]{
            {0, 1, 1},
            {1, 1, 0},
            {0, 0, 0}
    }, Color.INDIANRED),

    Z(new int[][] {
            {1, 1, 0},
            {0, 1, 1},
            {0, 0, 0}
    }, Color.GREEN),

    J(new int[][] {
            {1, 0, 0},
            {1, 1, 1},
            {0, 0, 0}
    }, Color.ORANGE),

    L(new int[][] {
            {0, 0, 1},
            {1, 1, 1},
            {0, 0, 0}
    }, Color.BLUE);

    public final int[][] shape;
    public final Color color;
    TetrominoType(int [][] shape, Color color) {
        this.shape = shape;
        this.color = color;
    }
}
