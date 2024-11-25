/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * The class that models your game. You should create a more specific child of this class and instantiate the methods
 * given.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private final List<Player> players = new ArrayList<>();
    private final Deck deck = new Deck();
    private Card topCard;

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to UNO!");
        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= numPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = scanner.nextLine();
            players.add(new Player(name));
        }

        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.drawCard(deck.drawCard());
            }
        }

        topCard = deck.drawCard();
        while (topCard.getColor() == Card.Color.WILD) { // Ensure first card is not a Wild card
            topCard = deck.drawCard();
        }

        playGame(scanner);
    }

    private void playGame(Scanner scanner) {
        int currentPlayerIndex = 0;
        int direction = 1;

        while (true) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("\n" + currentPlayer.getName() + "'s turn.");
            System.out.println("Top card: " + topCard);
            System.out.println("Your hand:");
            for (int i = 0; i < currentPlayer.getHand().size(); i++) {
                System.out.println((i + 1) + ". " + currentPlayer.getHand().get(i));
            }

            if (!currentPlayer.hasPlayableCard(topCard)) {
                System.out.println("No playable card. Drawing a card...");
                currentPlayer.drawCard(deck.drawCard());
                currentPlayerIndex = (currentPlayerIndex + direction + players.size()) % players.size();
                continue;
            }

            System.out.print("Choose a card to play (1-" + currentPlayer.getHand().size() + ") or type 0 to draw: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                currentPlayer.drawCard(deck.drawCard());
            } else {
                Card chosenCard = currentPlayer.getHand().get(choice - 1);
                if (currentPlayer.playCard(chosenCard, topCard)) {
                    topCard = chosenCard;
                    System.out.println(currentPlayer.getName() + " played " + topCard);

                    if (currentPlayer.getHand().isEmpty()) {
                        System.out.println(currentPlayer.getName() + " wins!");
                        break;
                    }

                    if (topCard.getValue() == Card.Value.REVERSE) {
                        direction *= -1;
                    } else if (topCard.getValue() == Card.Value.SKIP) {
                        currentPlayerIndex = (currentPlayerIndex + direction + players.size()) % players.size();
                    } else if (topCard.getValue() == Card.Value.DRAW_TWO) {
                        currentPlayerIndex = (currentPlayerIndex + direction + players.size()) % players.size();
                        players.get(currentPlayerIndex).drawCard(deck.drawCard());
                        players.get(currentPlayerIndex).drawCard(deck.drawCard());
                    } else if (topCard.getValue() == Card.Value.WILD || topCard.getValue() == Card.Value.WILD_DRAW_FOUR) {
                        System.out.println(currentPlayer.getName() + ", choose a color (RED, YELLOW, GREEN, BLUE): ");
                        String colorChoice = scanner.nextLine().toUpperCase();
                        topCard = new Card(Card.Color.valueOf(colorChoice), topCard.getValue());
                    }
                } else {
                    System.out.println("Invalid card. Try again.");
                    continue;
                }
            }

            currentPlayerIndex = (currentPlayerIndex + direction + players.size()) % players.size();
        }

        scanner.close();
    }
}
