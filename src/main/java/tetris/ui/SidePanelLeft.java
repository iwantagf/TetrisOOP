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

public class SidePanelLeft extends VBox {
    private final Label scoreLabel = new Label("SCORE");
    private final Label scoreValue = new Label("0");
    private final Label linesLabel = new Label("LINES");
    private final Label linesValue = new Label("0");
    private final Label levelLabel = new Label("LEVEL");
    private final Label levelValue = new Label("1");

    private Font titleFont;
    private Font valueFont;

    private final int previewTile = 20;

    @SuppressWarnings("FieldCanBeLocal")
    private final int previewSize = previewTile * 6;


    public SidePanelLeft() {
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

        getChildren().addAll(
                scoreLabel, scoreValue,
                spacer(),
                linesLabel, linesValue,
                spacer(),
                levelLabel, levelValue,
                spacer()
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
    }



    private Label spacer() {
        Label sp = new Label("");
        sp.setMinHeight(12);
        return sp;
    }
}
