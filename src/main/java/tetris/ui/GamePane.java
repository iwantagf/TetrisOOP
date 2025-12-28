package main.java.tetris.ui;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;

public class GamePane extends StackPane {

    @SuppressWarnings("FieldCanBeLocal")
    private final Group root;

    public GamePane(Canvas canvas) {
        this.root = new Group(canvas);
        getChildren().add(root);
        setStyle("-fx-background-color: black;");
    }
}
