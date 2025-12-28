package main.java.tetris;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;


import main.java.tetris.game.Board;
import main.java.tetris.game.Tetromino;
import main.java.tetris.render.GameRenderer;
import main.java.tetris.ui.GamePane;
import main.java.tetris.input.InputHandler;
import main.java.tetris.game.Game;
import main.java.tetris.ui.SidePanelLeft;
import main.java.tetris.ui.SidePanelRight;
import main.java.tetris.util.MathUtil;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Tetris");
        Canvas canvas = new Canvas(Board.SQUARE_W + 12, Board.SQUARE_H + 12);
        GraphicsContext g = canvas.getGraphicsContext2D();
        GamePane gamePane = new GamePane(canvas);
        Scene scene = new Scene(gamePane, 1280, 720);

        InputHandler input = new InputHandler(scene);
        GameRenderer renderer = new GameRenderer(g);
        SidePanelRight right = new SidePanelRight();
        SidePanelLeft left = new SidePanelLeft();


        gamePane.setRight(right);
        gamePane.setLeft(left);

        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();




        AnimationTimer timer = new AnimationTimer() {
            Board board = new Board();
            Game game = new Game();
            Tetromino piece = game.spawnTetromino();
            MathUtil meth = new MathUtil();
            long last = 0;
            double acc = 0;
            double downAcc = 0;
            double leftAcc = 0;
            double rightAcc = 0;
            boolean gameOver = false;

            void reset() {
                board = new Board();
                game = new Game();
                piece = game.spawnTetromino();
                meth = new MathUtil();
                left.setScore(0);
                left.setLevel(1);
                left.setLines(0);

                gameOver = false;
                last = 0;
                acc = downAcc = leftAcc = rightAcc = 0;

                gamePane.showGameOver(false);
            }

            @Override
            public void handle(long now) {
                if (gameOver) {
                    if (input.useRestart()) {
                        reset();
                    }
                    return;
                }

                if (last == 0) {
                    last = now;
                    return;
                }
                double dt = (now - last) / 1000000000.0;
                last = now;

                if (input.useHold()) {
                    piece = game.hold(piece);
                    left.updateHold(game.getHoldType());
                }

                acc += dt;

                double gravityX = MathUtil.gravityThreshold(meth.getLevel(), 0.5, 0.5, 0.01);
                if (acc >= gravityX) {
                    if (game.checkPlace(board, piece)) {
                        board.placeTetromino(piece);
                        game.onPieceLocked();
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
                    game.onPieceLocked();
                    board.placeTetromino(piece);
                    piece = game.spawnTetromino();
                }

                double canvasW = canvas.getWidth();
                double canvasH = canvas.getHeight();


                int lines = game.clearFilledRows(board);
                right.updateNext(game.nextFive());
                meth.updateLevel(left);
                meth.updateLines(left, lines);
                meth.updateScore(left, lines);
                renderer.render(board, piece, canvasW, canvasH);


                if (!board.canPlace(piece.getX(), piece.getY(), piece.getShape())) {
                    gamePane.showGameOver(true);
                    gameOver = true;
                }
            }
        };

        timer.start();
    }
    static void main(String[] args) {
        launch(args);
    }
}
