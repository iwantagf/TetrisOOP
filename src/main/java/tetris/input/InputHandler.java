package main.java.tetris.input;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class InputHandler {
    private boolean left;
    private boolean right;
    private boolean down;
    private boolean rotate;
    private boolean drop;
    private boolean hold;
    private boolean restartPressed = false;

    public InputHandler(Scene scene) {
        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT) left = true;
            if (e.getCode() == KeyCode.RIGHT) right = true;
            if (e.getCode() == KeyCode.DOWN) down = true;
            if (e.getCode() == KeyCode.UP) rotate = true;
            if (e.getCode() == KeyCode.SPACE) drop = true;
            if (e.getCode() == KeyCode.SHIFT) hold = true;
            if (e.getCode() == KeyCode.R) restartPressed = true;
        });

        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.LEFT) left = false;
            if (e.getCode() == KeyCode.RIGHT) right = false;
            if (e.getCode() == KeyCode.DOWN) down = false;
            if (e.getCode() == KeyCode.UP) rotate = false;
            if (e.getCode() == KeyCode.SPACE) drop = false;
            if (e.getCode() == KeyCode.SHIFT) hold = false;
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

    public boolean useRestart() {
        if (restartPressed) {
            restartPressed = false;
            return true;
        }

        return false;
    }

    public boolean useHold() {
        if (hold) {
            hold = false;
            return true;
        }
        return false;
    }
}
