/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slots;

import casino.Player;
import constants.Constants;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Samuel Menaged UCF COP 3330
 */
public class Slots
{
    //declare member variables
    private final int SLOTS = 3;
    private final char[] SYMBOLS = {'$', '%', '&', '#', '@', '!'};
    private char[] spins;
    private Random rand;
    private Player player;
    private boolean play;
    private Scanner scan;
    
    //create constructor with default values
    public Slots(Player player)
    {
        this.player = player;
        scan = new Scanner(System.in);
        play = true;
        rand = new Random();
        spins = new char[3];
        
    }   
    
    
    //simulates a basic slot machine using random characters
    public void play() {
        int input;
        
        //Slot machine display
        System.out.println("\n\nLet's play the slot machines!");
        player.printBalance();
        System.out.println("The bet is $" + Constants.SLOTS_BET);
        System.out.println("Match two symbols to win $" + Constants.PAIR_PAYOUT);
        System.out.println("Match all three symbols to win $" + Constants.TRIPLE_PAYOUT);
        System.out.println("Match $ $ $ to win the Jackpot, current Jackpot is: $" + Constants.JACKPOT);
        
        //if cash < minimum bet set play to false.
        if(player.getCash() < Constants.SLOTS_BET) {
            System.out.println("Sorry, you don't have enough cash to play");
            play = false;
        }
        
        //jackpot for getting $ $ $ in slot machine
        int jackPot = Constants.JACKPOT;
        
        //while they can play and want to keep playing, play.
        while(play) {
            
           //increase jackpot as they spin
           jackPot++;
           
           //take their bet once they play
           player.setCash(player.getCash() - 5);
           
           //display spin result for slot machine
           System.out.println("\nSpinning...");
           for(int i = 0; i < Constants.SLOTS; i++) {
            spins[i] = randomSymbol();
            System.out.print(spins[i] + " ");
           }
           
           //calculate their winnings (or loss)
           //this is a jackpot
           if (spins[0] == '$' && spins[1] == '$' && spins[2] == '$')
           {
               for (int i = 0; i < 4; i++)
                System.out.println("\nJACKPOT!!!");
               System.out.println("JACKPOT!!!, You won $" + jackPot + "!!!");
               
               //give the player their jackpot
               player.setCash(player.getCash() + jackPot);
               
               //reset the jackpot to default value after they win
               jackPot = Constants.JACKPOT;
           } 
           
           //triple payout
           else if(spins[0] == spins[1] && spins[0] == spins[2]) {
               System.out.println("3 symbols matched, You won $" + Constants.TRIPLE_PAYOUT + "!");
               player.setCash(player.getCash() + Constants.TRIPLE_PAYOUT);
           } 
           
           //pair payout
           else if (spins[0] == spins[1] || spins[0] == spins[2] || spins[1] == spins[2]) {
               System.out.println("Two symbols matched, You won $" + Constants.PAIR_PAYOUT + "!");
               player.setCash(player.getCash()+ Constants.PAIR_PAYOUT);
           } 
           
           //otherwise they lose :(
           else {
               System.out.println("You lost, Sorry.");
           }
           
          // if they can still keep playing display slot information
           if (player.getCash() >= Constants.SLOTS_BET) {
               
               //slot information
               player.printBalance();
               System.out.println("JACKPOT = $" + jackPot);
               
               //ask to keep playing
               System.out.println("Do you want to keep playing? (Yes = 1, No = 0)");
               input = scan.nextInt();
               
               //if they enter 1 keep playing
               if (input == 1) {
                   play = true;
               }
               //otherwise stop playing
               else {
                   play = false;
                   break;
               }
           } 
           //if they run out of money they can no longer play
           else {
               System.out.println("\nYou are out of cash, you cannot play");
               play = false;
               break;
           }
        }
        //exited the slot machine here, make sure to thank them for their money :)
         System.out.println("Thank you for playing slots at Knights Casino! Your cash out is $" + player.getCash());
        
    }
    
    //pick a random symbol for slot machine
    private char randomSymbol() {
        int num;
        char symbol = ' ';
        
        //generate random number between 0 and 5 for our 6 possible symbols
        num = rand.nextInt(SYMBOLS.length);
        //evaluate the symbol
        switch (num) {
            case 0: 
                symbol = SYMBOLS[0];
                break;
            case 1:
                symbol = SYMBOLS[1];
                break;
            case 2: 
                symbol = SYMBOLS[2];
                break;
            case 3: 
                symbol = SYMBOLS[3];
                break;
            case 4: 
                symbol = SYMBOLS[4];
                break;
            case 5: 
                symbol = SYMBOLS[5];
                break;
            default:
                //if our random number is very weird let me know
                System.out.println("INVALID SYMBOL");
                break;
        }
        //return our symbol
        return symbol;
    }
}
