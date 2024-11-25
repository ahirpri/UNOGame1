/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author priyankaahir
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        for (Card.Color color : Card.Color.values()) {
            if (color == Card.Color.WILD) continue;

            for (Card.Value value : Card.Value.values()) {
                if (value == Card.Value.WILD || value == Card.Value.WILD_DRAW_FOUR) continue;

                cards.add(new Card(color, value)); // Add one card
                if (value != Card.Value.ZERO) cards.add(new Card(color, value)); // Add duplicate for non-zero cards
            }
        }

        for (int i = 0; i < 4; i++) {
            cards.add(new Card(Card.Color.WILD, Card.Value.WILD));
            cards.add(new Card(Card.Color.WILD, Card.Value.WILD_DRAW_FOUR));
        }

        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return cards.isEmpty() ? null : cards.remove(cards.size() - 1);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
