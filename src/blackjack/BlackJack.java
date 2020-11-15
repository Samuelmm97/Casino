/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import casino.Player;
import java.util.Scanner;
import constants.Constants;
import java.util.ArrayList;

/**
 *
 * @author Samuel Menaged UCF COP 3330
 */
public class BlackJack
{
    //Member variables
    private Player player;
    private boolean play;
    private Scanner scan;
    private Deck deck;
    private Dealer dealer;
    private Card card;
    
    //custom constructor for BlackJack
    public BlackJack (Player player) {
        this.player = player;
        scan = new Scanner(System.in);
        this.play = true;
        deck = new Deck();
        dealer = new Dealer();
        card = new Card();
    }
    
    //play the game of BlackJack
    public void play() {
        displayRules();
        
        //if players cash is less than the minimum bet inform the player
        if (player.getCash() < Constants.BLACKJACK_BET) {
            System.out.println("You don't have enough cash to play.");
            play = false;
        }
        //while the player can play and wants to keep playing
        while(play) {
            deck = new Deck();
            
            player.printBalance();
            
            //before they keep playing recheck their balance
            if (player.getCash() < Constants.BLACKJACK_BET) {
                System.out.println("You don't have enough cash to play.");
                play = false;
                break;
            }
            
            //take their bet
            player.setCash(player.getCash() - Constants.BLACKJACK_BET);
            System.out.println("\nThe cards are being dealt...");
            
            //set starting values
            player.setHand(new ArrayList<Card>());
            player.setScore(Constants.ZERO);
            player.setBusted(false);
            dealer.setHand(new ArrayList<Card>());
            dealer.setScore(Constants.ZERO);
            
            
            //deal 2 cards to the player
            for (int i = 0; i < 2; i++) {
                card = dealer.deal(deck);
                player.getHand().add(card);
                System.out.println("Player's Card: " + card.toString());
            }
            System.out.println("\nPlayer's Score: " + player.getScore());
            
            //while the player wants to keep hitting
            while (player.hitOrStand() == Constants.HIT) {
                
                System.out.println("Player hitting...");
                //add a new card to the hand
                card = dealer.deal(deck);
                player.getHand().add(card);
                System.out.println("Player's Card: " + card.toString());
                System.out.println("Player's Score: " + player.getScore());
                
                //if the player is over 21
                if (player.getScore() > Constants.BUST) {
                    System.out.println("You busted. You lost $10. Better luck next time.");
                    player.setCash(player.getCash() - 10);
                    player.printBalance();
                    player.setBusted(true);
                    break;
                }        
            }  
            
            //if the player busted ask to play again and move to next turn
            if (player.getBusted()) {
                play = player.playAgain();
                continue;
            }
            
            //otherwise continue with dealers cards.
            else {
                //deal 2 cards to the dealer
                for (int i = 0; i < 2; i++) {
                    card = dealer.deal(deck);
                    dealer.getHand().add(card);
                    System.out.println("Dealer's Card: " + card.toString());
                }
                
                System.out.println("\nDealer's Score: " + dealer.getScore());
                
                //while the dealer is under 17 keep hitting
                while(dealer.hitOrStand() == Constants.HIT) {
                    
                    System.out.println("Dealer hitting...");
                    //deal new card
                    card = dealer.deal(deck);
                    dealer.getHand().add(card);
                    
                    System.out.println("Dealer's Card: " + card.toString());
                    System.out.println("\nDealer's Score: " + dealer.getScore());
                    
                    //if dealer is over 21
                    if (dealer.getScore() > Constants.BUST) {
                        System.out.println("Dealer Busted!");
                        break;
                    }
                }
            }
            //display results
            results();
            
            //offer to play again.
            play = player.playAgain();
        }
    }
    
    //print results of game of blackjack
    private void results () {
        
        //display scores
        System.out.println("\n\nRESULTS...");
        System.out.println("Player Score: " + player.getScore());
        System.out.println("Dealer Score: " + dealer.getScore());
        
        //if dealer busted
        if (dealer.getScore() > Constants.BUST) {
            System.out.println("Dealer busted you win $10!");
            player.setCash(player.getCash() + Constants.WIN_SET);
        }
        
        //if PUSH or scores are equal
        else if (dealer.getScore() == player.getScore()) {
            System.out.println("Push, you win $10!");
            player.setCash(player.getCash() + 10);
        }
        
        //if player wins
        else if (player.getScore() > dealer.getScore()) {
            System.out.println("You Won $10!");
            player.setCash(player.getCash() + Constants.WIN_SET);
        }
        
        //otherwise the dealer won
        else  {
            System.out.println("The dealer won. You lose $10.");
            player.setCash(player.getCash() - 10);
        }
        player.printBalance();
    }
    
    //just print stuff about the game
    private void displayRules() {
        System.out.println("Let's play Black Jack!");
        player.printBalance();
        System.out.println("The bet is $" + Constants.BLACKJACK_BET);
        System.out.println("Dealer must HIT if their score is 16 or less");
        System.out.println("Dealer must STAND if their score is 17 or higher");
        System.out.println("If the player wins the hand, they receive $" + Constants.WIN_SET);
        System.out.println("If the dealer wins the hand, the $" + Constants.BLACKJACK_BET + " bet is lost");
        System.out.println("If it is a PUSH, the player keeps their $10 bet");
    }
    
}
