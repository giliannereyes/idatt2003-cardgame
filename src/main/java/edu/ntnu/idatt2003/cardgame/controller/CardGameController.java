package edu.ntnu.idatt2003.cardgame.controller;

import edu.ntnu.idatt2003.cardgame.model.DeckOfCards;
import edu.ntnu.idatt2003.cardgame.model.HandOfCards;
import edu.ntnu.idatt2003.cardgame.model.PlayingCard;
import edu.ntnu.idatt2003.cardgame.view.CardGameView;

import java.util.List;

/**
 * Controller for the Card Game application.
 *
 * @version 0.1
 * @since 0.1
 * @author Gilianne Reyes
 */
public class CardGameController {
    private DeckOfCards deck;
    private HandOfCards hand;
    private CardGameView view;

    /**
     * Creates a new controller with a deck of cards.
     */
    public CardGameController() {
        this.deck = new DeckOfCards();
    }

    /**
     * Sets the view for this controller to update.
     *
     * @param view is the view to update.
     */
    public void setView(CardGameView view) {
        this.view = view;
    }

    /**
     * Deals the cards and updates the view.
     */
    public void dealHand() {
        deck = new DeckOfCards(); // (Prevent running out of cards)
        view.clearCards();
        List<PlayingCard> cards = deck.dealHand(7);
        hand = new HandOfCards(cards);
        view.showCards(cards);
        clearAnalysis();
    }

    /**
     * Clears the hand and updates the view.
     */
    private void clearAnalysis() {
        view.updateSumLabel("Sum of Faces:");
        view.updateHeartsLabel("Cards of Heart:");
        view.updateQueenLabel("Queen of Spades:");
        view.updateFlushLabel("Flush:");
    }

    /**
     * Checks the current hand, updates the analysis labels in the view.
     */
    public void checkHand() {
        if (hand == null) return;

        String sumText = "Sum of Faces: " + hand.getTotalValue();
        String heartsText = "Cards of Heart: " + hand.getHearts();
        String queenText = "Queen of Spades: " + (hand.hasQueenOfSpades() ? "Yes" : "No");
        String flushText = "Flush: " + (hand.isFlush() ? "Yes" : "No");

        view.updateSumLabel(sumText);
        view.updateHeartsLabel(heartsText);
        view.updateQueenLabel(queenText);
        view.updateFlushLabel(flushText);
    }
}
