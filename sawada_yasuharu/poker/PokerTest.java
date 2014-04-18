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
    public void 最初手札が5枚配られる() {//{{{
        Poker poker = new Poker();

        assertThat(poker.getHand().size(), is(5));
    }//}}}

    @Test
    public void 0を入力すると手札を交換しない() {//{{{
        Poker poker = new Poker();
        
        List<Card> hand = poker.getHand();
        poker.exchangeCard("0");

        assertThat(hand, is(poker.getHand()));
    }//}}}

    @Test
    public void 手札は交換できる_1枚() {//{{{
        Poker poker = new Poker();

        Card oldCard = poker.getHand().get(0);

        poker.exchangeCard("1");
        
        assertThat(poker.getHand().get(0), is(not(oldCard)));
    }//}}}

    @Test
    public void 手札は交換できる_5枚全部() {//{{{
        Poker poker = new Poker();

        Card oldCards = poker.getHand();

        poker.exchangeCard("12345");
        
        assertThat(oldCards, is(not(poker.getHand())));
    }//}}}
}



