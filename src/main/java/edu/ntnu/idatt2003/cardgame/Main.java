package edu.ntnu.idatt2003.cardgame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import edu.ntnu.idatt2003.cardgame.model.*;

import java.util.List;

public class Main extends Application {
    private DeckOfCards deck = new DeckOfCards();
    private HandOfCards hand;

    // Labels for analysis
    private Label sumLabel = new Label("Sum of Faces:");
    private Label heartsLabel = new Label("Cards of Heart:");
    private Label queenLabel = new Label("Queen of Spades:");
    private Label flushLabel = new Label("Flush:");

    // Box for displaying cards
    private HBox cardBox = new HBox(15);

    @Override
    public void start(Stage primaryStage) {
        // Root pane with green background
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #006400;"); // dark green

        // VBox for analysis labels on top-left
        VBox analysisBox = new VBox(10);
        analysisBox.setPadding(new Insets(20, 0, 0, 20)); // top, right, bottom, left
        analysisBox.setAlignment(Pos.TOP_LEFT);

        // Make the labels white (to contrast with green background) & style them a bit
        sumLabel.setStyle("-fx-text-fill: white; -fx-font-size: 25;");
        heartsLabel.setStyle("-fx-text-fill: white; -fx-font-size: 25;");
        queenLabel.setStyle("-fx-text-fill: white; -fx-font-size: 25;");
        flushLabel.setStyle("-fx-text-fill: white; -fx-font-size: 25;");

        // Add them to the VBox
        analysisBox.getChildren().addAll(sumLabel, heartsLabel, queenLabel, flushLabel);

        // Put the analysisBox at the top of the BorderPane
        root.setTop(analysisBox);

        // Center: card display
        cardBox.setAlignment(Pos.CENTER);
        root.setCenter(cardBox);

        // Bottom: buttons
        HBox buttonBox = new HBox(15);
        buttonBox.setPadding(new Insets(20));
        buttonBox.setAlignment(Pos.CENTER);

        Button dealHandButton = new Button("Deal Hand");
        Button checkHandButton = new Button("Check Hand");
        dealHandButton.setStyle("-fx-font-size: 25;");
        checkHandButton.setStyle("-fx-font-size: 25;");

        // Add buttons to HBox, and HBox to bottom of BorderPane
        buttonBox.getChildren().addAll(dealHandButton, checkHandButton);
        root.setBottom(buttonBox);

        // Event handlers
        dealHandButton.setOnAction(e -> dealHand());
        checkHandButton.setOnAction(e -> checkHand());

        // Set up scene and stage
        Scene scene = new Scene(root, 1000, 600); // a bit larger to fit everything nicely
        primaryStage.setTitle("Card Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void dealHand() {
        // Clear out previous cards
        cardBox.getChildren().clear();

        // Deal 5 cards
        List<PlayingCard> cards = deck.dealHand(7);
        hand = new HandOfCards(cards);

        // Show each card as a white label with a black border
        for (PlayingCard card : cards) {
            Label cardLabel = new Label(card.getAsString());
            cardLabel.setPrefSize(120, 180);
            cardLabel.setStyle(
                    "-fx-border-color: black; -fx-padding: 15;"
                    + "-fx-background-color: white; -fx-font-size: 30;"
            );
            cardBox.getChildren().add(cardLabel);
        }

        // Clear analysis labels after dealing new hand
        sumLabel.setText("Sum of Faces:");
        heartsLabel.setText("Cards of Heart:");
        queenLabel.setText("Queen of Spades:");
        flushLabel.setText("Flush:");
    }

    private void checkHand() {
        if (hand == null) return;

        // Update analysis labels
        sumLabel.setText("Sum of Faces: " + hand.getTotalValue());
        heartsLabel.setText("Cards of Heart: " + hand.getHearts());
        queenLabel.setText("Queen of Spades: " + (hand.hasQueenOfSpades() ? "Yes" : "No"));
        flushLabel.setText("Flush: " + (hand.isFlush() ? "Yes" : "No"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
