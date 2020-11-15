/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scratchoffs;

import casino.Player;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Sam
 */
public class Ticket {
    // member variables
    private char winningBonus;
    private ArrayList<Integer> winningNumbers;
    private ArrayList<Integer> playerNumbers;
    private ArrayList<Double> prizes;
    private ArrayList<Character> bonus;
    private Player player;
    private Random rand;
    private static final char[] SYMBOLS = {'$', '%', '&', '#', '@', '!'};
    private int base;
    private int nums;
    private int numWinningNumbers;
    private int numSyms;
    private double bonusValue;
    private int type;
    
    // custom constructor for one dollar ticket
    public Ticket(Player player, int base, int nums, int numWinningNumbers, int numSyms, double bonusValue, int type) {
        this.player = player;
        rand = new Random();
        this.base = base;
        this.nums = nums;
        this.numWinningNumbers = numWinningNumbers;
        this.numSyms = numSyms;
        this.bonusValue = bonusValue;
        this.type = type;
        createTicket();
        displayTicket();
        cashTicket();
    }
    
    // display ticket for user
    private void displayTicket() {
        int count = 0;
        // %NUMs == spaces %NUMd == spaces before decimal point
        System.out.println("+------------------------------------------+");
        
        if (type == 10) {
            System.out.printf("| WINNING NUMBERS");
            for (Integer i: winningNumbers) {
                System.out.printf("%5d", i);
            }
            System.out.printf("%6s|\n", " ");
        }
        else if (type == 5) {
            System.out.printf("| WINNING NUMBERS");
        
            for (Integer i: winningNumbers) {
                System.out.printf("%9d", i);
            }
            System.out.printf("%8s|\n", " ");
        }
        else {
            System.out.printf("| WINNING NUMBER %7d%19s|\n", winningNumbers.get(0), " ");
        }
        
        
        System.out.printf("|%42s|\n", " ");
        System.out.printf("| YOUR NUMBERS %28s|\n|", " ");
        
        // for each number print them with proper spacing
        for (Integer i: playerNumbers) {
            if(count % 5 == 0 && count != 0) {
                System.out.print("  |\n|");
            }
            System.out.printf("%8d", i);
            count++;
        }
        
        System.out.println("  |");
        System.out.printf("|%42s|\n", " ");
        System.out.printf("| PRIZES %34s|\n", " ");
        System.out.printf("|%42s|\n|", " ");
        
        count = 0;
        // %NUM.NUMf == spaces before and after decimal point
        for (Double i: prizes) {
            if(count % 5 == 0 && count != 0) {
                System.out.print("  |\n|");
            }
            System.out.printf("%8.2f", i);
            count++;
        }
        
        System.out.println("  |");
        System.out.printf("|%42s|\n", " ");
        System.out.printf("| WINNING SYMBOL %7c%19s|\n", winningBonus, " ");
        System.out.printf("|%42s|\n", " ");
        System.out.printf("| SYMBOLS %33s|\n", " ");
        System.out.printf("|%42s|\n|", " ");
        
        if (type == 10) {
            for (Character c: bonus) {
                System.out.printf("%8c", c);
            }
            System.out.printf("%10s|\n", " ");
        } else {
            for (Character c: bonus) {
                System.out.printf("%14c", c);
            }
            System.out.printf("%14s|\n", " ");
        }
        
        System.out.println("+------------------------------------------+");
    }
    
    
    private void cashTicket() {
        int cash = 0;
        boolean lost = true;
        
        // output winning bonus symbol
        System.out.println("\nBonus symbol is " + winningBonus);
        System.out.println("Bonus is worth $"  + bonusValue);
        
        // loop through each number
        for (int i = 0; i < playerNumbers.size(); i++) {
            // loop through each winning number
            for (int j = 0; j < winningNumbers.size(); j++) {
                // if the number matchest the winning number
                if (playerNumbers.get(i) == winningNumbers.get(j)) {
                    
                    // if they got here they didn't lose
                    lost = false;
                    
                    // output what made them win and increase cash earnings
                    System.out.println("\nYou got winning number " + playerNumbers.get(i) + "! Your prize is $" + prizes.get(i));
                    cash += prizes.get(i).intValue();
                    
                    // if they had a winning number loop through the bonus
                    for (int k = 0; k < bonus.size(); k++) {
                         // if the symbols match update their bonus
                         if (bonus.get(k) == winningBonus) {
                            System.out.println("\nYou got the bonus symbol!");
                            cash += bonusValue;
                        }
                    }
                    player.setCash(player.getCash() + cash);
                }
            }
        }
        if(lost) {
            System.out.println("\nSorry you didn't win anything\n");
        }
        else {
            System.out.println("\nYour ticket won you $" + cash + "\n");
        }
        
    }
    
    private void createTicket() {
        // instantiate variables
        this.winningBonus = randomSymbol();
        this.winningNumbers = new ArrayList<Integer>();
        this.playerNumbers = new ArrayList<Integer>();
        this.prizes = new ArrayList<Double>();
        this.bonus = new ArrayList<Character>();
        
        for (int i = 0; i < numWinningNumbers; i++) {
            winningNumbers.add(randomNumber());
        }
        // add 5 random numbers and prizes
        for (int i = 0; i < nums; i++) {
            playerNumbers.add(randomNumber());
            prizes.add(randomPrize());
        }
        
        // add two bonus symbols
        for (int i = 0; i < numSyms; i++) {
            bonus.add(randomSymbol());
        }
    }
    
    // return a random number from 0 to 20
    private int randomNumber() {
       return rand.nextInt(base + 1);
    }
    
    // return random prize from 0 to 20
    private double randomPrize() {
        return (double) rand.nextInt((base + 1));
    }
    
    // return random symbol
    private char randomSymbol() {
        return SYMBOLS[rand.nextInt(SYMBOLS.length)];
    }
}
