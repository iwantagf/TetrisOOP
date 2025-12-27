package main.java.tetris.input;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import main.java.tetris.game.Tetromino;

public class InputHandler {
    boolean left, right, down, rotate, drop;

    public InputHandler(Scene scene) {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) left = true;
            if (e.getCode() == KeyCode.RIGHT) right = true;
            if (e.getCode() == KeyCode.DOWN) down = true;
            if (e.getCode() == KeyCode.UP) rotate = true;
            if (e.getCode() == KeyCode.SPACE) drop = true;
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT) left = false;
            if (e.getCode() == KeyCode.RIGHT) right = false;
            if (e.getCode() == KeyCode.DOWN) down = false;
            if (e.getCode() == KeyCode.UP) rotate = false;
            if (e.getCode() == KeyCode.SPACE) drop = false;
        });
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean useDrop() {
        if (drop) {
            drop = false;
            return true;
        }

        return false;
    }

    public boolean useRotate() {
        if (rotate) {
            rotate = false;
            return true;
        }
        return false;
    }
}
