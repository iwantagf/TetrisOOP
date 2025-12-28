package main.java.tetris.render;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.java.tetris.game.Tetromino;
import main.java.tetris.game.Board;



public class GameRenderer {
    private final GraphicsContext g;

    public GameRenderer(GraphicsContext g) {
        this.g = g;
    }


    private double offSetX(double canvasW) {
        return (canvasW - Board.SQUARE_W)/2;
    }

    private double offSetY(double canvasH) {
        return (canvasH - Board.SQUARE_H)/2;
    }

    private int ghostDrop(Board board, Tetromino p) {
        int lo = 0, hi = Board.H;

        while (hi - lo > 1) {
            int m = (lo + hi)/2;

            if (board.canPlace(p.getX(), p.getY() + m, p.getShape())) {
                lo = m;
            }
            else
                hi = m;
        }

        return lo;
    }

    private void drawGhost(Board board, Tetromino p) {
        int d = ghostDrop(board, p);

        int[][] shape = p.getShape();
        int x = p.getX();
        int y = p.getY() + d;

        Color base = p.getColor();
        Color ghost = new Color(base.getRed(), base.getGreen(), base.getBlue(), 0.3);

        for (int r = 0; r < shape.length; ++r) {
            for (int c = 0; c < shape[0].length; ++c) {
                if (shape[r][c] != 0) {
                    g.setFill(ghost);
                    g.fillRect(
                            (x + c) * Board.TILE,
                            (y + r) * Board.TILE,
                            Board.TILE,
                            Board.TILE
                    );

                    g.setStroke(Color.BLACK);
                    g.strokeRect(
                            (x + c) * Board.TILE,
                            (y + r) * Board.TILE,
                            Board.TILE,
                            Board.TILE
                    );
                }
            }
        }
    }

    public void render(Board board, Tetromino piece, double canvasW, double canvasH) {
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, canvasW, canvasH);

        double ox = offSetX(canvasW);
        double oy = offSetY(canvasH);

        g.save();
        g.translate(ox, oy);
        board.drawBoard(g);
        drawGhost(board, piece);
        piece.draw(g);

        g.setStroke(Color.WHITE);
        g.setLineWidth(4);
        g.strokeRect(-4, -4, Board.SQUARE_W + 6, Board.SQUARE_H + 6);
        g.restore();
    }
}
