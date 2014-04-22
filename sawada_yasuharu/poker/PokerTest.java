/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package poker;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import java.util.*;

/**
 *
 * @author a13499
 */
public class PokerTest {

    @Test
    public void 最初手札が5枚配られる() {
        Poker poker = new Poker();

        assertThat(poker.getHand().size(), is(5));
    }

    @Test
    public void ゼロを入力すると手札を交換しない() {
        Poker poker = new Poker();
        
        List<Card> hand = poker.getHand();
        int[] exchangeNums = {0};

        poker.exchangeCards(exchangeNums);

        assertThat(hand, is(poker.getHand()));
    }

    @Test
    public void 手札は交換できる_1枚() {
        Poker poker = new Poker();

        Card oldCard = poker.getHand().get(0);
        int[] exchangeNums = {1};

        poker.exchangeCards(exchangeNums);
        
        assertThat(poker.getHand().get(0), is(not(oldCard)));
    }

    @Test
    public void 手札は交換できる_5枚全部() {
        Poker poker = new Poker();

        List<Card> oldCards = new ArrayList<Card>();
        for (Card c : poker.getHand()) {
            oldCards.add(c);
        }

        int[] exchangeNums = {1, 2, 3, 4, 5};

        poker.exchangeCards(exchangeNums);
        List<Card> newCards = poker.getHand();
        
        for (int i = 0; i < oldCards.size(); i++) {
            assertThat(oldCards.get(i), is(not(newCards.get(i))));
        }
    }
}



