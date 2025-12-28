package main.java.tetris.ui;

import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import main.java.tetris.game.TetrominoType;
import main.java.tetris.render.ColorPalette;
import java.io.InputStream;

public class SidePanelLeft extends VBox {
    private final Label scoreLabel = new Label("SCORE");
    private final Label scoreValue = new Label("0");
    private final Label linesLabel = new Label("LINES");
    private final Label linesValue = new Label("0");
    private final Label levelLabel = new Label("LEVEL");
    private final Label levelValue = new Label("1");
    private final Label holdLabel = new Label("HOLD");
    private Canvas holdCanvas;
    private GraphicsContext holdGC;
    private ImageView logo;

    private Font titleFont;
    private Font valueFont;

    private final int previewTile = 20;

    @SuppressWarnings("FieldCanBeLocal")
    private final int previewSize = previewTile * 6;


    private ImageView loadLogo(String path) {
        InputStream is = getClass().getResourceAsStream(path);
        ImageView iv = new ImageView(new Image(is));

        iv.setPreserveRatio(true);

        iv.fitWidthProperty().bind(widthProperty().subtract(40));

        iv.setSmooth(true);
        iv.setCache(true);

        return iv;
    }


    public SidePanelLeft() {
        setPadding(new Insets(20));
        setSpacing(12);
        setAlignment(Pos.TOP_CENTER);
        setMinWidth(260);
        setPrefWidth(260);
        setStyle("-fx-background-color: black;");

        holdCanvas = new Canvas(previewSize, previewSize);
        holdGC = holdCanvas.getGraphicsContext2D();

        String path = "/images/" + levelValue.getText() + ".png";
        logo = loadLogo(path);


        getChildren().addAll(holdLabel, holdCanvas);
        loadFonts();
        build();
    }

    private void loadFonts() {
        titleFont = Font.loadFont(getClass().getResourceAsStream("/PressStart2P.ttf"), 24);
        valueFont = Font.loadFont(getClass().getResourceAsStream("/PressStart2P.ttf"), 24);
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
        styleTitle(scoreLabel);
        styleValue(scoreValue);

        styleTitle(linesLabel);
        styleValue(linesValue);

        styleTitle(levelLabel);
        styleValue(levelValue);

        styleTitle(holdLabel);

        Region S = new Region();
        VBox.setVgrow(S, Priority.ALWAYS);

        getChildren().addAll(
                scoreLabel, scoreValue,
                spacer(),
                linesLabel, linesValue,
                spacer(),
                levelLabel, levelValue,
                S,
                logo
        );
    }

    public void setScore(int score) {
        scoreValue.setText(String.valueOf(score));
    }

    public void setLines(int lines) {
        linesValue.setText(String.valueOf(lines));
    }

    public void setLevel(int level) {
        levelValue.setText(String.valueOf(level));
        String path = "/images/" + level + ".png";
        InputStream is = getClass().getResourceAsStream(path);

        logo.setImage(new Image(is));
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

    public void updateHold(TetrominoType p) {
        holdGC.clearRect(0, 0, holdCanvas.getWidth(), holdCanvas.getHeight());

        if (p == null)
            return;

        drawPreview(holdGC, p);
    }

    private Label spacer() {
        Label sp = new Label("");
        sp.setMinHeight(12);
        return sp;
    }
}
