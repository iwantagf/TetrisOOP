package main.java.tetris;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



import main.java.tetris.game.Board;
import main.java.tetris.render.GameRenderer;
import main.java.tetris.ui.GamePane;
import main.java.tetris.input.InputHandler;
import main.java.tetris.ui.MainMenu;
import main.java.tetris.ui.SidePanelLeft;
import main.java.tetris.ui.SidePanelRight;



public class Main extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Tetris");
        Canvas canvas = new Canvas(Board.SQUARE_W + 12, Board.SQUARE_H + 12);
        GraphicsContext g = canvas.getGraphicsContext2D();
        GamePane gamePane = new GamePane(canvas);
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1280, 720);
        MainMenu menu = new MainMenu();

        InputHandler input = new InputHandler(scene);
        GameRenderer renderer = new GameRenderer(g);
        SidePanelRight right = new SidePanelRight();
        SidePanelLeft left = new SidePanelLeft();


        gamePane.setRight(right);
        gamePane.setLeft(left);


        root.getChildren().setAll(menu);

        stage.setTitle("TETR.io Clone tat nhien roi!");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();

        Runnable goToMenu = () -> {
            root.getChildren().setAll(menu);
        };

        GameLoopTimer timer = new GameLoopTimer(
                canvas,
                input,
                gamePane,
                renderer,
                left,
                right,
                goToMenu
        );


        menu.getStartButton().setOnAction(e -> {
            root.getChildren().setAll(gamePane);
            timer.reset();
            timer.start();
        });

        menu.getExitButton().setOnAction(e -> {
            stage.close();
        });

        menu.getContinueButton().setOnAction(e -> {
            root.getChildren().setAll(gamePane);
            timer.resume();
            timer.start();
        });
    }
    static void main(String[] args) {
        launch(args);
    }
}
