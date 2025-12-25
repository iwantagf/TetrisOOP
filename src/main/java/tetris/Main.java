package main.java.tetris;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import main.java.tetris.game.Board;
import main.java.tetris.game.Tetromino;
import main.java.tetris.game.TetrominoType;
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


        Tetromino piece = new Tetromino(TetrominoType.T);

        AnimationTimer timer = new AnimationTimer() {
            long last = 0;
            double acc = 0;

            @Override
            public void handle(long now) {
                if (last == 0) {
                    last = now;
                    return;
                }
                double dt = (now - last) / 1000000000.0;
                last = now;

                acc += dt;

                if (acc >= 0.5) {
                    piece.move(0, 1);
                    acc = 0;
                }

                board.drawBoard(g);
                piece.draw(g);
            }
        };

        timer.start();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
