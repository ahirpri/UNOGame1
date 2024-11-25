package ca.sheridancollege.project;

/**
 * A class to be used as the base Card class for the project.
 * Represents a standard playing card with rank and suit.
 */

public class Card {
    public enum Color { RED, YELLOW, GREEN, BLUE, WILD }
    public enum Value { ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, SKIP, REVERSE, DRAW_TWO, WILD, WILD_DRAW_FOUR }

    private final Color color;
    private final Value value;

    public Card(Color color, Value value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return color + " " + value;
    }
}