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
import main.java.tetris.render.GameRenderer;
import main.java.tetris.ui.GamePane;
import main.java.tetris.input.InputHandler;
import main.java.tetris.game.Game;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Tetris");
        Canvas canvas = new Canvas(1280, 720);
        GraphicsContext g = canvas.getGraphicsContext2D();
        GamePane gamePane = new GamePane(canvas);
        Scene scene = new Scene(gamePane, 1280, 720);

        canvas.widthProperty().bind(gamePane.widthProperty());
        canvas.heightProperty().bind(gamePane.heightProperty());

        InputHandler input = new InputHandler(scene);
        GameRenderer renderer = new GameRenderer(g);
        Board board = new Board();
        Game game = new Game();

        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();


        AnimationTimer timer = new AnimationTimer() {
            Tetromino piece = game.spawnTetromino();
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
                        piece = game.spawnTetromino();
                    }
                    acc = 0;
                }

                if (input.isDown()) {
                    downAcc += dt;

                    if (downAcc >= 0.05) {
                        if (game.checkPlace(board, piece)) {
                            board.placeTetromino(piece);
                            piece = game.spawnTetromino();
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

                if (input.useDrop()) {
                    game.dropTetromino(board, piece);
                    board.placeTetromino(piece);
                    piece = game.spawnTetromino();
                }

                double canvasW = canvas.getWidth();
                double canvasH = canvas.getHeight();

                game.clearFilledRows(board);
                renderer.render(board, piece, canvasW, canvasH);


                if (!board.canPlace(piece.getX(), piece.getY(), piece.getShape())) {
                    renderer.renderGameOver(board, piece, canvasW, canvasH);
                    this.stop();
                }
            }
        };

        timer.start();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
