package main.java.tetris;

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
import javafx.scene.canvas.Canvas;


public class GameLoopTimer extends AnimationTimer {
    private final Canvas canvas;
    private final InputHandler input;
    private final GamePane gamePane;
    private final GameRenderer renderer;
    private final SidePanelLeft left;
    private final SidePanelRight right;
    private final Runnable goToMenu;


    private Board board = new Board();
    private Game game = new Game();
    private Tetromino piece = game.spawnTetromino();
    private MathUtil meth = new MathUtil();
    private long last = 0;
    private double acc = 0;
    private double downAcc = 0;
    private double leftAcc = 0;
    private double rightAcc = 0;
    private boolean gameOver = false;


    public GameLoopTimer(Canvas canvas,
                         InputHandler input,
                         GamePane gamePane,
                         GameRenderer renderer,
                         SidePanelLeft left,
                         SidePanelRight right,
                         Runnable goToMenu) {
        this.canvas = canvas;
        this.input = input;
        this.gamePane = gamePane;
        this.renderer = renderer;
        this.left = left;
        this.right = right;
        this.goToMenu = goToMenu;
    }

    public void reset() {
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

    public void resume() {
        last = 0;
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

        if (input.useBack()) {
            this.stop();
            goToMenu.run();
            return;
        }

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
}