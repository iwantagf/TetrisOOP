package main.java.tetris.render;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

    public void render(Board board, Tetromino piece, double canvasW, double canvasH) {
        g.setFill(Color.BLACK);
        g.fillRect(0, 0, canvasW, canvasH);

        double ox = offSetX(canvasW);
        double oy = offSetY(canvasH);

        g.save();
        g.translate(ox, oy);
        board.drawBoard(g);
        piece.draw(g);

        g.setStroke(Color.WHITE);
        g.setLineWidth(4);
        g.strokeRect(0, 0, Board.SQUARE_W, Board.SQUARE_H);
        g.restore();
    }

    public void renderGameOver(Board board, Tetromino piece, double canvasW, double canvasH) {
        render(board, piece, canvasW, canvasH);

        g.setFill(Color.rgb(0, 0, 0, 0.65));
        g.fillRect(0, 0, canvasW, canvasH);

        Font font = Font.loadFont(
                getClass().getResourceAsStream("/PressStart2P.ttf"),
                36
        );

        g.setFont(font);
        g.setFill(Color.WHITE);

        String text = "GAME OVER!!";

        Text temp = new Text(text);
        temp.setFont(font);
        double textWidth = temp.getLayoutBounds().getWidth();

        double x = (canvasW - textWidth) / 2.0;
        double y = canvasH / 2.0;

        g.fillText(text, x, y);
    }
}
