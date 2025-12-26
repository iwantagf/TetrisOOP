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
import main.java.tetris.input.InputHandler;
import main.java.tetris.game.Game;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Tetris");
        Canvas canvas = new Canvas(Board.SQUARE_W, Board.SQUARE_H);
        GraphicsContext g = canvas.getGraphicsContext2D();
        Board board = new Board();
        Game game = new Game();
        GamePane gamePane = new GamePane(canvas);
        Scene scene = new Scene(gamePane, 1280, 720);
        InputHandler input = new InputHandler(scene);

        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();




        AnimationTimer timer = new AnimationTimer() {
            Tetromino piece = new Tetromino(TetrominoType.T);
            long last = 0;
            double acc = 0;

            double downAcc = 0;
            double leftAcc = 0;
            double rightAcc = 0;

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
                    if (game.checkPlace(board, piece)) {
                        board.placeTetromino(piece);
                        piece = new Tetromino(TetrominoType.J);
                    }
                    acc = 0;
                }

                if (input.isDown()) {
                    downAcc += dt;

                    if (downAcc >= 0.05) {
                        if (game.checkPlace(board, piece)) {
                            board.placeTetromino(piece);
                            piece = new Tetromino(TetrominoType.J);
                        }
                        downAcc = 0;
                    }
                }
                else
                    downAcc = 0;

                if (input.isLeft()) {
                    leftAcc += dt;

                    if (leftAcc >= 0.12) {
                        game.tryMove(board, piece, -1, 0);
                        leftAcc = 0;
                    }
                }
                else
                    leftAcc = 0;

                if (input.isRight()) {
                    rightAcc += dt;

                    if (rightAcc >= 0.12) {
                        game.tryMove(board, piece, 1, 0);
                        rightAcc = 0;
                    }
                }
                else
                    rightAcc = 0;

                if (input.useRotate()) {
                    game.tryRotate(board, piece);
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
