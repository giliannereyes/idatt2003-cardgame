package edu.ntnu.idatt2003.cardgame;

import edu.ntnu.idatt2003.cardgame.controller.CardGameController;
import edu.ntnu.idatt2003.cardgame.view.CardGameView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class for the Card Game application.
 *
 * @version 0.1
 * @since 0.1
 * @author Gilianne Reyes
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        CardGameController controller = new CardGameController();
        CardGameView view = new CardGameView(controller);
        controller.setView(view);
        Scene scene = new Scene(view.getRoot(), 1000, 600);
        primaryStage.setTitle("Card Game");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
