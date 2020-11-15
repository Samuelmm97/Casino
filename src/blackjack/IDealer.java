/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 *
 * @author Sam
 */
public interface IDealer {
    public int hitOrStand();
    public Card deal(Deck deck);
}
