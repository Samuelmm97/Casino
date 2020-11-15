/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.HashSet;
import java.util.Set;
import constants.Constants.*;
import constants.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author Samuel Menaged UCF COP 3330
 */
public class Deck {
   private Set<Card> deck;

    public Set<Card> getDeck() {
        return deck;
    }

    public void setDeck(Set<Card> deck) {
        this.deck = deck;
    }
   
    public Deck() {
        
        //create deck of cards
        generateDeck();
        
        //display the deck
//        System.out.println("****************************");
//        System.out.println("Displaying the deck of cards");
//        System.out.println("****************************");
   //     displayDeck();
        
        //shuffle the deck
//        System.out.println("***************************");
//        System.out.println("Shuffling the deck of cards");
//        System.out.println("***************************");
        shuffleDeck();
        
        //display the shuffled deck
//        System.out.println("*************************************");
//        System.out.println("Displaying the shuffled deck of cards");
//        System.out.println("*************************************");
//        displayDeck();
    }
    
    //create a hashSet of card and put it in our deck
    public void generateDeck() {
        
        //create deck of 52 cards with HashSet
        deck = new HashSet<Card>(Constants.DECKSIZE);
        
        //for each card in the deck set its attributes (Face, Suit, Color)
        int i;
        i = 2;
        for (Face face: Face.values()) {
            for (Suit suit: Suit.values()) {
                //create each card
                Card card = new Card();
                
                //set attributes
                card.setFace(face);
                card.setSuit(suit);
                if (face == face.ACE)
                   card.setValue(11);
                else
                    card.setValue(i);
                
                //if its Clubs or Spades set color to black
                if (card.getSuit() == suit.CLUBS || card.getSuit().equals(suit.SPADES)) 
                    card.setColor(Color.BLACK);
                
                //otherwise we know it's red
                else
                    card.setColor(Color.RED);
                
                //if the deck doesn't already contain the card add it.
                if (!deck.contains(card))
                    deck.add(card);
            }
            if (i < 10)
                i++;
        }
    }
    
    
    //print all 52 cards with their attributes
    public void displayDeck() {
        int i = 0;
        for (Card card : deck) {
            System.out.println("Card " + (i + 1) + ": " + card.getFace() + " of " + card.getSuit() + " is color " + card.getColor());
            i++;
        }
        System.out.println("\n\n");
    }
    
    //I think you can guess what this does
    public void shuffleDeck() {
        
        //make a temporary list since you can't shuffle HashSets
        List<Card> shuffler = new ArrayList<Card>(deck);
        
        //fancy function that java gives us
        Collections.shuffle(shuffler);
        
        //put the shuffled cards back in the deck
        deck = new HashSet<Card>(shuffler);
    }
}
