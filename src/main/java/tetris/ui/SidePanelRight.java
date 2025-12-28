package main.java.tetris.ui;

import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.java.tetris.game.TetrominoType;
import main.java.tetris.render.ColorPalette;

public class SidePanelRight extends VBox {
    private final Label nextLabel = new Label("NEXT");
    private final Canvas[] nextCanvases = new Canvas[5];
    private final GraphicsContext[] nextGC = new GraphicsContext[5];
    private Font titleFont;
    private Font valueFont;
    private Font nextFont;

    private final int previewTile = 20;

    @SuppressWarnings("FieldCanBeLocal")
    private final int previewSize = previewTile * 6;


    public SidePanelRight() {
        setPadding(new Insets(20));
        setSpacing(12);
        setAlignment(Pos.TOP_CENTER);
        setMinWidth(260);
        setPrefWidth(260);
        setStyle("-fx-background-color: black;");

        loadFonts();
        build();
    }

    private void loadFonts() {
        titleFont = Font.loadFont(getClass().getResourceAsStream("/PressStart2P.ttf"), 18);
        valueFont = Font.loadFont(getClass().getResourceAsStream("/PressStart2P.ttf"), 24);
        nextFont = Font.loadFont(getClass().getResourceAsStream("/PressStart2P.ttf"), 18);
    }

    private void styleTitle(Label l) {
        l.setTextFill(Color.WHITE);
        l.setFont(titleFont);
    }

    private void styleValue(Label l) {
        l.setTextFill(Color.WHITE);
        l.setFont(valueFont);
    }

    private void build() {
        styleTitle(nextLabel);
        nextLabel.setFont(nextFont);

        getChildren().add(nextLabel);

        for (int i = 0; i < 5; ++i) {
            nextCanvases[i] = new Canvas(previewSize, previewSize);
            nextGC[i] = nextCanvases[i].getGraphicsContext2D();
            getChildren().add(nextCanvases[i]);
        }
    }

    private Label spacer() {
        Label sp = new Label("");
        sp.setMinHeight(12);
        return sp;
    }

    private void clearPreview(GraphicsContext g) {
        g.clearRect(0, 0, g.getCanvas().getWidth(), g.getCanvas().getHeight());
    }

    private void drawPreview(GraphicsContext g, TetrominoType p) {
        clearPreview(g);

        int[][] s = p.shape;
        Color color = ColorPalette.get(p);

        int minR = 99, maxR = -1, minC = 99, maxC = -1;

        for (int r = 0; r < s.length; ++r)
            for (int c = 0; c < s[0].length; ++c)
                if (s[r][c] != 0) {
                    minR = Math.min(minR, r);
                    maxR = Math.max(maxR, r);
                    minC = Math.min(minC, c);
                    maxC = Math.max(maxC, c);
                }

        int h = maxR - minR + 1;
        int w = maxC - minC + 1;

        double ox = (g.getCanvas().getWidth() - w * previewTile) / 2.0;
        double oy = (g.getCanvas().getHeight() - h * previewTile) / 2.0;

        Color fill = new Color(color.getRed(), color.getGreen(), color.getBlue(), 0.95);

        for (int r = 0; r < s.length; ++r)
            for (int c = 0; c < s[0].length; ++c)
                if (s[r][c] != 0) {
                    double x = ox + (c - minC) * previewTile;
                    double y = oy + (r - minR) * previewTile;

                    g.setFill(fill);
                    g.fillRect(x, y, previewTile, previewTile);

                    g.setStroke(Color.BLACK);
                    g.strokeRect(x, y, previewTile, previewTile);
                }
    }

    public void updateNext(TetrominoType[] next) {
        if (next == null)
            return;

        int n = Math.min(5, next.length);

        for (int i = 0; i < n; ++i)
            drawPreview(nextGC[i], next[i]);
        for (int i = n; i < 5; ++i)
            clearPreview(nextGC[i]);
    }
}
