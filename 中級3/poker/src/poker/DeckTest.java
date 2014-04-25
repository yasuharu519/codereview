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
public class DeckTest {
    
    @Test
    public void デッキはジョーカー抜きで52枚(){
        Deck deck = new Deck(0);

        assertThat(deck.getRemainedCardNumber(), is(52));
    }

    @Test
    public void デッキはジョーカー抜きで52枚_ver2(){
        Deck deck = new Deck();

        assertThat(deck.getRemainedCardNumber(), is(52));
    }

    @Test
    public void デッキはジョーカー入れると増える(){
        Deck deck = new Deck(2);

        assertThat(deck.getRemainedCardNumber(), is(54));
    }

    @Test
    public void デッキは最初シャッフルされている() {
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();
        Card card1;
        Card card2;

        boolean flag = true;
        for (int i = 0; i < deck1.getRemainedCardNumber(); i++) {
            card1 = deck1.peek(i);
            card2 = deck2.peek(i);
            if (card1.suit != card2.suit || card1.number != card2.number)
                flag = false;
        }
        assertThat(flag, is(false));
    }

    @Test
    public void デッキから指定枚数のカードを引くことができる() {
        List<Card> drawnCards;
        Deck deck = new Deck(2);
        assertThat(deck.getRemainedCardNumber(), is(54));

        try{
            drawnCards = deck.drawCards(10);
        
            assertThat(deck.getRemainedCardNumber(), is(44));
            assertThat(drawnCards.size(), is(10));
        }catch(NumberExceedException e){
        }
    }

    @Test(expected= NumberExceedException.class)
    public void 残り枚数よりも大きな数をひこうとするとエラーが投げられる() throws NumberExceedException {
        Deck deck = new Deck();
        deck.drawCards(53);        	
    }
}


