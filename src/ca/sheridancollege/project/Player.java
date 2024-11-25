/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> hand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void drawCard(Card card) {
        if (card != null) {
            hand.add(card);
        }
    }

    public boolean playCard(Card card, Card topCard) {
        if (card.getColor() == topCard.getColor() || card.getValue() == topCard.getValue() || card.getColor() == Card.Color.WILD) {
            hand.remove(card);
            return true;
        }
        return false;
    }

    public boolean hasPlayableCard(Card topCard) {
        for (Card card : hand) {
            if (card.getColor() == topCard.getColor() || card.getValue() == topCard.getValue() || card.getColor() == Card.Color.WILD) {
                return true;
            }
        }
        return false;
    }
}