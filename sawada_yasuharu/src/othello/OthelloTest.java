/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package othello;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import java.util.*;

/**
 *
 * @author a13499
 */
public class OthelloTest {

    @Test
    public void 最初手札が5枚配られる() {//{{{
        othello othello = new othello();

        assertThat(othello.getHand().size(), is(5));
    }//}}}

    @Test
    public void ゼロを入力すると手札を交換しない() {//{{{
        othello othello = new othello();
        
        List<Card> hand = othello.getHand();
        int[] exchangeNums = {0};

        othello.exchangeCards(exchangeNums);

        assertThat(hand, is(othello.getHand()));
    }//}}}

    @Test
    public void 手札は交換できる_1枚() {//{{{
        othello othello = new othello();

        Card oldCard = othello.getHand().get(0);
        int[] exchangeNums = {1};

        othello.exchangeCards(exchangeNums);
        
        assertThat(othello.getHand().get(0), is(not(oldCard)));
    }//}}}

    @Test
    public void 手札は交換できる_5枚全部() {//{{{
        othello othello = new othello();

        List<Card> oldCards = new ArrayList<Card>();
        for (Card c : othello.getHand()) {
            oldCards.add(c);
        }

        int[] exchangeNums = {1, 2, 3, 4, 5};

        othello.exchangeCards(exchangeNums);
        List<Card> newCards = othello.getHand();
        
        for (int i = 0; i < oldCards.size(); i++) {
            assertThat(oldCards.get(i), is(not(newCards.get(i))));
        }
    }//}}}
}
