/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratchoffs;

import casino.Player;
import java.util.Scanner;
import constants.Constants;

/**
 *
 * @author Samuel Menaged UCF COP 3330
 */
public class ScratchOffs
{
    //member variables
    private Player player;
    private boolean play;
    private Scanner scan;
    private Ticket ticket;
    
    //constants
    private static final int ONE_DOLLAR_BASE = 20;
    private static final int ONE_DOLLAR_NUMS = 5;
    private static final int ONE_DOLLAR_NUM_WINNING_NUMBERS = 1;
    private static final int ONE_DOLLAR_NUMSYMS = 2;
    private static final double ONE_DOLLAR_BONUSVALUE = 10.00;
    
    private static final int FIVE_DOLLAR_BASE = 40;
    private static final int FIVE_DOLLAR_NUMS = 10;
    private static final int FIVE_DOLLAR_NUM_WINNING_NUMBERS = 2;
    private static final int FIVE_DOLLAR_NUMSYMS = 2;
    private static final double FIVE_DOLLAR_BONUSVALUE = 20.00;
    
    private static final int TEN_DOLLAR_BASE = 60;
    private static final int TEN_DOLLAR_NUMS = 15;
    private static final int TEN_DOLLAR_NUM_WINNING_NUMBERS = 4;
    private static final int TEN_DOLLAR_NUMSYMS = 4;
    private static final double TEN_DOLLAR_BONUSVALUE = 30.00;
    
    //custom constructor
    public ScratchOffs(Player player) {
        this.player = player;
        this.play = true;
        scan = new Scanner(System.in);
    }
    
    public void play() {
        int type;
        int quantity;
        int input;
        
        // output instructions
        System.out.println("Let's scratch some tickets!");
        System.out.println("Players can select from One Dollar, Five Dollar, and Ten Dollar tickets");
        System.out.println("Prizes are based on the ticket selected");
        
        // Determine if player has enough cash to play
        if (player.getCash() < Constants.ONE_DOLLAR) {
            System.out.println("Sorry, you don't have enough cash to play :-(");
            play = false;
        }
        
        // For each game check if player is still playing
        while(play == true) {
            
            // prompting user for inputs for type of scratchOff and quantity
            System.out.println("Which type of scratch off would you like? (1 = One Dollar, 5 = Five Dollar, 10 = Ten Dollar)");
            type = scan.nextInt();
            if ((player.getCash() + 1) - type  < Constants.ONE_DOLLAR) {
                System.out.println("Sorry, you don't have enough cash to play :-(");
                play = false;
                break;
            }
            System.out.println("How many scratch offs would you like?");
            quantity = scan.nextInt();
            System.out.println("Getting your scratch offs...");
            
            // output cash balance
            player.printBalance();
            
            // for each ticket
            for (int i = 0; i < quantity; i++) {
                
                // evaluate the type
                switch(type) {
                    
                    // player selected one dollar
                    case Constants.ONE_DOLLAR:
                        
                        // update cash balance
                        player.setCash(player.getCash() - Constants.ONE_DOLLAR);
                        
                        // instantiate one dollar ticket
                        ticket = new Ticket(player, ONE_DOLLAR_BASE, ONE_DOLLAR_NUMS, ONE_DOLLAR_NUM_WINNING_NUMBERS, ONE_DOLLAR_NUMSYMS, ONE_DOLLAR_BONUSVALUE, type);
                        
                        break;
                    
                    case Constants.FIVE_DOLLAR:
                        
                        // update cash
                        player.setCash(player.getCash() - Constants.FIVE_DOLLAR);
                        
                        // instantiate five dollar ticket
                        ticket = new Ticket(player, FIVE_DOLLAR_BASE, FIVE_DOLLAR_NUMS, FIVE_DOLLAR_NUM_WINNING_NUMBERS, FIVE_DOLLAR_NUMSYMS, FIVE_DOLLAR_BONUSVALUE, type);
                        
                        break;
                     
                    case Constants.TEN_DOLLAR:
                        
                        // update cash
                        player.setCash(player.getCash() - Constants.TEN_DOLLAR);
                        
                        // instantiate ten dollar ticket
                        ticket = new Ticket(player, TEN_DOLLAR_BASE, TEN_DOLLAR_NUMS, TEN_DOLLAR_NUM_WINNING_NUMBERS, TEN_DOLLAR_NUMSYMS, TEN_DOLLAR_BONUSVALUE, type);
                        
                        break;
                    
                    default:
                        // if the player entered wrong type
                        System.out.println("\n\nInvalid ticket type\n");
                        continue;
                }
                // output players balance
                player.printBalance();
                
                // determine if player still has enough cash to play
                if ((player.getCash() + 1) - type > Constants.ONE_DOLLAR) {

                    // ask if player wants to play again
                    play = player.playAgain();
                    if(!play) {
                        System.out.println("Thanks for playing!");
                        return;
                    }
                }
                else {
                    System.out.println("Sorry you are out of Cash!");
                    return;
                }
                
                // TODO Output figure 4
            }
            
        }
    }
}
