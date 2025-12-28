package main.java.tetris.render;


import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.text.Font;

public class GameOverRenderer extends StackPane {
    private final Rectangle dim = new Rectangle();
    private final VBox box = new VBox(12);

    private final Label title = new Label("GAME OVER!");
    private final Label hint = new Label("Press R to Restart");

    public GameOverRenderer() {
        setPickOnBounds(false);
        setVisible(false);

        dim.setFill(Color.rgb(0, 0, 0, 0.65));
        dim.widthProperty().bind(widthProperty());
        dim.heightProperty().bind(heightProperty());

        Font titleFont = Font.loadFont(
                getClass().getResourceAsStream("/PressStart2P.ttf"),
                36
        );

        Font hintFont = Font.loadFont(
                getClass().getResourceAsStream("/PressStart2P.ttf"),
                36
        );

        title.setFont(titleFont);
        hint.setFont(hintFont);
        title.setTextFill(Color.WHITE);
        hint.setTextFill(Color.WHITE);

        box.setAlignment(Pos.CENTER);
        box.getChildren().addAll(title, hint);

        setAlignment(Pos.CENTER);
        getChildren().addAll(dim, box);
    }

    public void show() {
        setVisible(true);
    }

    public void hide() {
        setVisible(false);
    }

    public boolean isShow() {
        return isVisible();
    }
}
