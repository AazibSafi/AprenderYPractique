package com.algorithms.aprenderypractique.Algorithms.LinkedList;

import com.algorithms.aprenderypractique.BaseTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *  https://leetcode.com/discuss/interview-question/689328/bloomberg-phone-shuffle-cards
 */
public class CardShuffle extends BaseTest {

    @Test
    public void test() {
        // Input cards
        char[] cards = {'A', '4', '6', '7', '9', 'K', 'Q', 'J', '2'};

        // Create a linked list of cards
        LinkedList<Character> deck = createDeck(cards);
        System.out.println("Created Linked List: " + deck);

        // Split the deck into subsets
        List<LinkedList<Character>> subsets = splitDeck(deck);
        System.out.println("Split List into decks: " + subsets);

        // Merge subsets randomly
        LinkedList<Character> shuffledDeck = mergeDecks(subsets);
        System.out.println("Merged deck randomly: " + shuffledDeck);
    }

    private static LinkedList<Character> createDeck(char[] cards) {
        LinkedList<Character> deck = new LinkedList<>();
        for (char card : cards) {
            deck.add(card);
        }
        return deck;
    }

    // Method to split the deck into random subsets
    private static List<LinkedList<Character>> splitDeck(LinkedList<Character> deck) {
        List<LinkedList<Character>> splittedDeck = new ArrayList<>();
        LinkedList<Character> subset = new LinkedList<>();

        while(!deck.isEmpty()) {
            if(Math.random() > 0.5 && !subset.isEmpty()) {
                splittedDeck.add(subset);
                subset = new LinkedList<>();
            }
            subset.add(deck.removeFirst());
        }

        if(!subset.isEmpty())
            splittedDeck.add(subset);

        return splittedDeck;
    }

    // Method to merge subsets randomly
    private static LinkedList<Character> mergeDecks(List<LinkedList<Character>> subsets) {
        LinkedList<Character> mergedDeck = new LinkedList<>();

        while(!subsets.isEmpty()) {
            // Select a random subset
            int key = (int) (Math.random() * subsets.size());
            LinkedList<Character> subset = subsets.get(key);

            // Add a card from the subset to the merged deck
            mergedDeck.add(subset.removeFirst());

            // Remove the subset if it's empty
            if(subset.isEmpty())
                subsets.remove(key);
        }
        return mergedDeck;
    }

}
