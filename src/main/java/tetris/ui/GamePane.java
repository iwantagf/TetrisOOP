package main.java.tetris.ui;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import main.java.tetris.game.Board;

public class GamePane extends StackPane {

    private final Group root;

    public GamePane(Canvas canvas) {
        this.root = new Group(canvas);
        getChildren().add(root);
    }
}
