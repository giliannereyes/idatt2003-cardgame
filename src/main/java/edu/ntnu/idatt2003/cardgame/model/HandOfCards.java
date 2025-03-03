package edu.ntnu.idatt2003.cardgame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a hand of cards.
 * A hand of cards contains a number of playing cards.
 */
public class HandOfCards {
    private final List<PlayingCard> cards = new ArrayList<>();

    /**
     * Creates a new hand of cards.
     *
     * @param cards The cards to add to the hand.
     */
    public HandOfCards(List<PlayingCard> cards) {
        this.cards.addAll(cards);
    }

    /**
     * Checks if the hand of cards contains a flush.
     *
     * @return true if the hand of cards contains a flush, false otherwise.
     */
    public boolean isFlush() {
        return cards.stream()
                .collect(Collectors.groupingBy(PlayingCard::getSuit, Collectors.counting()))
                .values().stream()
                .allMatch(count -> count >= 5);
    }

    /**
     * Returns the total value of the hand of cards.
     *
     * @return the total value of the hand of cards.
     */
    public int getTotalValue() {
        return cards.stream()
                .mapToInt(PlayingCard::getFace)
                .sum();
    }

    /**
     * Returns the hearts in the hand of cards as a string.
     *
     * @return the hearts in the hand of cards as a string.
     */
    public String getHearts() {
        String hearts = cards.stream()
                .filter(card -> card.getSuit() == 'H')
                .map(PlayingCard::getAsString)
                .collect(Collectors.joining(" "));
        return hearts.isEmpty() ? "No hearts" : hearts;
    }

    /**
     * Checks if the hand of cards contains the queen of spades.
     *
     * @return true if the hand of cards contains the queen of spades, false otherwise.
     */
    public boolean hasQueenOfSpades() {
        return cards.stream()
                .anyMatch(card -> card.getAsString().equals("S12"));
    }
}
