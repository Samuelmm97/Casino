/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casino;

import java.util.Scanner;
import java.util.ArrayList;
import blackjack.Card;
import constants.Constants;

/**
 *
 * @author Samuel Menaged UCF COP 3330
 */
public class Player implements IPlayer
{
    //Member variables
    private String name;
    private int cash;
    Scanner scan;
    ArrayList<Card> hand;
    int score;
    Boolean busted;
    Boolean play;
    
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
    
    public Boolean getBusted() {
        return busted;
    }
    
    public void setBusted(Boolean busted) {
        this.busted = busted;
    }
    
    public Boolean getPlay() {
        return play;
    }
    
    public void setPlay(Boolean play) {
        this.play = play;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }
    
    public int getCash() {
        return cash;
    }
    
    //Custom constructor
    public Player() {
        scan = new Scanner(System.in);
        String name;
        int cash;
        System.out.println("Enter player name");
        name = scan.next();
        System.out.println("How much money do you want to play with? (minimum $10)");
        cash = scan.nextInt();
        setName(name);
        setCash(cash);
    }
    
    //Check if player wants to hit or stand and return choice.
    public int hitOrStand() {
        int choice;
        System.out.println("\nDo you want to hit or stand? (1 = Hit, 0 = Stand)");
        choice = scan.nextInt();
        if (choice == Constants.HIT)
            return Constants.HIT;
        else
            return Constants.STAND;
    }
    
    //Check if player wants to keep playing and return choice
    public boolean playAgain() {
        int choice;
        System.out.println("\nDo you want to play again? (1 = Yes, 0 = No)");
        choice = scan.nextInt();
        if (choice == 1)
            return true;
        return false;
    }
    
    public void printBalance() {
        System.out.println("Current Balance: " + cash);
    }
}
