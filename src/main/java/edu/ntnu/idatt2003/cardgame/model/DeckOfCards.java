package edu.ntnu.idatt2003.cardgame.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a deck of cards. A deck of cards contains 52 cards, each card
 * has a number (face) between 1 and 13 and a suit.
 */
public class DeckOfCards {
    private final char[] suits = {'S', 'H', 'D', 'C'};
    private final List<PlayingCard> cards = new ArrayList<>();

    /**
     * Creates a new deck of cards.
     */
    public DeckOfCards() {
        for (char suit : suits) {
            for (int face = 1; face <= 13; face++) {
                cards.add(new PlayingCard(suit, face));
            }
        }
    }

    /**
     * Shuffles the deck of cards.
     */
    private void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Returns a randomized hand of n cards.
     *
     * @param n is the number of cards to deal.
     *
     * @return a hand of n cards.
     *
     * @throws IllegalArgumentException if n is less than 1 or greater than 52.
     */
    public List<PlayingCard> dealHand(int n) {
        if (n < 1 || n > 52) {
            throw new IllegalArgumentException("Number of cards must be between 0 and 52");
        }
        List<PlayingCard> hand = new ArrayList<>();
        shuffle();
        for (int i = 0; i < n; i++) {
            hand.add(cards.remove(0));
        }
        return hand;
    }
}
