/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package constants;

/**
 *
 * @author Samuel Menaged UCF COP 3330
 */
public class Constants
{
    // casino constants
    public static final int BLACK_JACK = 1;
    public static final int SCRATCH = 2;
    public static final int SLOTS = 3;
    
    // slots constants
    public static final int SLOTS_BET = 5;
    public static final int PAIR_PAYOUT = 5;
    public static final int TRIPLE_PAYOUT = 50;
    public static final int JACKPOT = 100;
    public static final int DECKSIZE = 52;
    
    // blackjack constants
    public static final int BLACKJACK_BET = 10;
    public static final int WIN_SET = 20;
    public static final int HIT = 1;
    public static final int STAND = 0;
    public static final int MUST_HIT = 16;
    public static final int MUST_STAND = 17;
    public static final int ZERO = 0;
    public static final int BUST = 21;
    
    // scratchers constants
    public static final int ONE_DOLLAR = 1;
    public static final int FIVE_DOLLAR = 5;
    public static final int TEN_DOLLAR = 10;
    
    // enums to represent cards
    public static enum Color {
        RED,
        BLACK
    }
    public static enum Suit {
        CLUBS,
        DIAMONDS,
        HEARTS,
        SPADES
    }
    public static enum Face {
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE
    }
}
