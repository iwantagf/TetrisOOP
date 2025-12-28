package main.java.tetris.ui;


import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import main.java.tetris.render.GameOverRenderer;


public class GamePane extends BorderPane {

    @SuppressWarnings("FieldCanBeLocal")
    private final StackPane centerWrap = new StackPane();
    private final GameOverRenderer gameOver = new GameOverRenderer();

    public GamePane(Canvas canvas) {
        centerWrap.getChildren().addAll(canvas, gameOver);
        setStyle("-fx-background-color: black;");
        setCenter(centerWrap);

        gameOver.prefWidthProperty().bind(widthProperty());
        gameOver.prefHeightProperty().bind(heightProperty());
        gameOver.hide();
    }


    public void showGameOver(boolean on) {
        if (on)
            gameOver.show();
        else
            gameOver.hide();
    }

    public boolean isGameOver() {
        return gameOver.isShow();
    }

}
