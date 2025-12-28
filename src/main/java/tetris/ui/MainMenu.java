package main.java.tetris.ui;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.InputStream;

public class MainMenu extends StackPane {

    private final Button startButton = new Button("START");
    private final Button exitButton = new Button("EXIT");
    private final Button continueButton = new Button("CONTINUE");
    private final ImageView backGround;
    public MainMenu() {
        //BackGround Image
        InputStream is = getClass().getResourceAsStream("/images/menu_background.jpg");
        backGround = new ImageView(new Image(is));

        backGround.setPreserveRatio(true);
        backGround.fitWidthProperty().bind(widthProperty());
        backGround.fitHeightProperty().bind(heightProperty());


        //Overlay
        Region dim = new Region();
        dim.setStyle("-fx-background-color: rgb(0, 0, 0, 0.5)");
        dim.prefWidthProperty().bind(widthProperty());
        dim.prefHeightProperty().bind(heightProperty());


        //Title and Button
        VBox content = new VBox(24);
        content.setAlignment(Pos.CENTER);

        Font titleFont = Font.loadFont(
                getClass().getResourceAsStream("/PressStart2P.ttf"),
                60
        );

        Font buttonFont = Font.loadFont(
                getClass().getResourceAsStream("/PressStart2P.ttf"),
                36
        );


        Label title = new Label("TheTris");
        title.setFont(titleFont);
        title.setTextFill(Color.WHITE);

        styleButton(startButton, buttonFont);
        styleButton(exitButton, buttonFont);
        styleButton(continueButton, buttonFont);

        content.getChildren().addAll(title, continueButton, startButton, exitButton);

        getChildren().addAll(backGround, dim, content);
    }

    private void styleButton(Button b, Font f) {
        b.setFont(f);
        b.setTextFill(Color.WHITE);

        b.setStyle("""
            -fx-background-color: transparent;
            -fx-border-color: white;
            -fx-border-width: 2;
            -fx-padding: 12 32 12 32;
        """);

        b.setOnMouseEntered(e ->
                b.setStyle("""
                -fx-background-color: white;
                -fx-text-fill: black;
                -fx-border-color: white;
                -fx-border-width: 2;
                -fx-padding: 12 32 12 32;
            """)
        );

        b.setOnMouseExited(e ->
                b.setStyle("""
                -fx-background-color: transparent;
                -fx-text-fill: white;
                -fx-border-color: white;
                -fx-border-width: 2;
                -fx-padding: 12 32 12 32;
            """)
        );
    }


    public Button getStartButton() {
        return startButton;
    }

    public Button getExitButton() {
        return exitButton;
    }

    public Button getContinueButton() {
        return continueButton;
    }
}
