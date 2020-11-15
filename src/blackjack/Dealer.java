/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.util.ArrayList;
import constants.Constants;
import java.util.Iterator;

/**
 *
 * @author Sam
 */
public class Dealer implements IDealer {
    
    //Member variables
    private ArrayList<Card> hand;
    private int score;
    
    //Getters and Setters
    
    public ArrayList<Card> getHand() {
        return hand;
    }
    
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
    
    public int getScore() {
        score = 0;
        for (Card card: hand) {
            if (score == 21) {
                System.out.println("\nYou are not allowed to hit at 21.\n");
                break;
            }
            else if (card.getValue() == 11 && score + 11 > 21) {
                System.out.println("\nUsing Ace as 1 instead of 11...\n");
                card.setIsOne(true);
                score += 1;
                break;
            } 
            else
                score += card.getValue();
        }
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
        
    //if the Dealer should hit or stand
    public int hitOrStand() {
        if (score <= Constants.MUST_HIT)
            return Constants.HIT;
        else
            return Constants.STAND;
    }

    //deal new card for player or dealer
    public Card deal(Deck deck) {
        
        //create new card
        Card card = new Card();
        Iterator<Card> cards= deck.getDeck().iterator();
        
        //if a card is availible get the new card.
        if (cards.hasNext()) {
            card = cards.next();
        }
        
        //take that card out of the deck.
        deck.getDeck().remove(card);
        //System.out.println(card.toString());
        return card;
    }
    
}
