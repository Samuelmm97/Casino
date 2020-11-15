/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import constants.Constants.*;

/**
 *
 * @author Samuel Menaged UCF COP 3330
 */
public class Card {
    
    //member variables
    private Face face;
    private Suit suit;
    private Color color;
    private int value;
    private Boolean isOne = false;
    
    //getters and setters for each attribute
    
    //get value based on face
    public int getValue() {
        if (isOne)
            value = 1;
        return value;
    }
    
    public Boolean getIsOne() {
        return isOne;
    }

    public void setIsOne(Boolean isOne) {
        this.isOne = isOne;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
        
    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }
    
    public Suit getSuit() {
        return suit;
    }
    
    public void setSuit(Suit suit) {
        this.suit = suit;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public String toString() {
        return face + " of " + suit;
    }
    
    
    //hashCode for hashSets
    public int hashCode() {
        int hashcode = 0;
        return hashcode;
    }
    
    //checks if a Card equals another card.
    public boolean equals(Object obj) {
        if (obj instanceof Card) {
            Card card = (Card)obj;
            return (card.face.equals(this.face)) 
                    && (card.color.equals(this.color)) 
                    && (card.suit.equals(this.suit));
        }
                
        return false; 
    }
    
}
