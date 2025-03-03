package edu.ntnu.idatt2003.cardgame.view;

import edu.ntnu.idatt2003.cardgame.controller.CardGameController;
import edu.ntnu.idatt2003.cardgame.model.PlayingCard;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Objects;

/**
 * View for the Card Game application.
 *
 * @version 0.1
 * @since 0.1
 * @author Gilianne Reyes
 */
public class CardGameView {
    private final CardGameController controller;
    private BorderPane rootPane;
    private Label sumLabel;
    private Label heartsLabel;
    private Label queenLabel;
    private Label flushLabel;
    private HBox cardBox;
    private Button dealHandButton;
    private Button checkHandButton;

    /**
     * Creates a new view for the Card Game application.
     *
     * @param controller is the controller for the view.
     */
    public CardGameView(CardGameController controller) {
        this.controller = controller;
        initNodes();
        configureRoot();
    }

    /**
     * Initializes the nodes in the view.
     */
    private void initNodes() {
        sumLabel = new Label("Sum of Faces:");
        heartsLabel = new Label("Cards of Heart:");
        queenLabel = new Label("Queen of Spades:");
        flushLabel = new Label("Flush:");
        cardBox = new HBox(15);
        dealHandButton = new Button("Deal Hand");
        checkHandButton = new Button("Check Hand");
    }

    /**
     * Creates and configures the layout of the view.
     */
    private void configureRoot() {
        rootPane = new BorderPane();
        rootPane.setStyle("-fx-background-color: #006400;"); // dark green background
        rootPane.setPrefSize(900, 600);
        configureAnalysisBox();
        configureCardBox();
        configureButtonBox();
    }

    /**
     * Configures the analysis box in the view.
     */
    private void configureAnalysisBox() {
        VBox analysisBox = new VBox(10);
        analysisBox.setPadding(new Insets(20, 0, 0, 20));
        analysisBox.setAlignment(Pos.TOP_LEFT);
        sumLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20;");
        heartsLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20;");
        queenLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20;");
        flushLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20;");
        analysisBox.getChildren().addAll(sumLabel, heartsLabel, queenLabel, flushLabel);
        rootPane.setTop(analysisBox);
    }

    /**
     * Configures the card box in the view.
     */
    private void configureCardBox() {
        cardBox.setAlignment(Pos.CENTER);
        rootPane.setCenter(cardBox);
    }

    /**
     * Configures the button box in the view.
     */
    private void configureButtonBox() {
        HBox buttonBox = new HBox(15, dealHandButton, checkHandButton);
        buttonBox.setPadding(new Insets(20));
        buttonBox.setAlignment(Pos.CENTER);
        dealHandButton.setStyle("-fx-font-size: 20;");
        checkHandButton.setStyle("-fx-font-size: 20;");
        rootPane.setBottom(buttonBox);
        dealHandButton.setOnAction(e -> controller.dealHand());
        checkHandButton.setOnAction(e -> controller.checkHand());
    }

    /**
     * Creates an image view of a card.
     *
     * @param card is the card to create an image view of.
     *
     * @return an image view of the card.
     */
    private ImageView createCardImageView(PlayingCard card) {
        String path = getCardImagePath(card);
        Image cardImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        ImageView imageView = new ImageView(cardImage);
        imageView.setFitWidth(120);
        imageView.setFitHeight(160);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    /**
     * Returns the path to the image of the card.
     * Assumes that the card images are stored in the resources folder under the path /cards
     * and the image files are named in format: [suit][face].png
     *
     * @param card is the card to get the image path of.
     *
     * @return the path to the image of the card.
     */
    private String getCardImagePath(PlayingCard card) {
        return String.format("/cards/%s%s.png", card.getSuit(), card.getFace());
    }

    /**
     * Returns the root pane of the view.
     *
     * @return the root pane of the view
     */
    public BorderPane getRoot() {
        return rootPane;
    }

    /**
     * Clears the cards in the card box.
     */
    public void clearCards() {
        cardBox.getChildren().clear();
    }

    /**
     * Shows the cards in the card box.
     *
     * @param cards is the list of cards to show.
     */
    public void showCards(List<PlayingCard> cards) {
        for (PlayingCard card : cards) {
            ImageView cardView = createCardImageView(card);
            cardBox.getChildren().add(cardView);
        }
    }

    /**
     * Updates the sum label with the given text.
     *
     * @param text is the text to update the sum label with.
     */
    public void updateSumLabel(String text) {
        sumLabel.setText(text);
    }

    /**
     * Updates the hearts label with the given text.
     *
     * @param text is the text to update the hearts label with.
     */
    public void updateHeartsLabel(String text) {
        heartsLabel.setText(text);
    }

    /**
     * Updates the queen label with the given text.
     *
     * @param text is the text to update the queen label with.
     */
    public void updateQueenLabel(String text) {
        queenLabel.setText(text);
    }

    /**
     * Updates the flush label with the given text.
     *
     * @param text is the text to update the flush label with.
     */
    public void updateFlushLabel(String text) {
        flushLabel.setText(text);
    }
}
