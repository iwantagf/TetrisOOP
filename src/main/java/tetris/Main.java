package main.java.tetris;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import main.java.tetris.game.Board;
import main.java.tetris.ui.GamePane;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Tetris");
        Canvas canvas = new Canvas(Board.SQUARE_W, Board.SQUARE_H);
        GraphicsContext g = canvas.getGraphicsContext2D();

        Board board = new Board();

        GamePane gamePane = new GamePane(canvas);


        Scene scene = new Scene(gamePane, 1280, 720);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                board.drawBoard(g);
            }
        };

        timer.start();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
