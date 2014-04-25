/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package poker;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 *
 * @author a13499
 */
public class PokerTest {
    private Poker poker;
    private List<Card> cards;

    @Before
    public void initialize(){
        this.poker = new Poker();
        this.cards = new ArrayList<Card>();
    }

    @Test
    public void 最初手札が5枚配られる() {
        assertThat(poker.getHand().size(), is(5));
    }

    @Test
    public void ゼロを入力すると手札を交換しない() {
        List<Card> hand = poker.getHand();
        int[] exchangeNums = {0};

        poker.exchangeCards(exchangeNums);

        assertThat(hand, is(poker.getHand()));
    }

    @Test
    public void 手札は交換できる_1枚() {
        Card oldCard = poker.getHand().get(0);
        int[] exchangeNums = {1};

        poker.exchangeCards(exchangeNums);
        
        assertThat(poker.getHand().get(0), is(not(oldCard)));
    }

    @Test
    public void 手札は交換できる_5枚全部() {
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

    @Test
    public void ストレートフラッシュの役のテスト() {
        Card card1 = new Card(Suit.Club, 1);
        Card card2 = new Card(Suit.Club, 2);
        Card card3 = new Card(Suit.Club, 3);
        Card card4 = new Card(Suit.Club, 4);
        Card card5 = new Card(Suit.Club, 5);
        
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        poker.setHand(cards);

        assertThat(poker.checkHand(), is(HandType.STRAIGHT_FLUSH));
    }

    @Test
    public void フォーオブアカインドの役のテスト() {
        Card card1 = new Card(Suit.Club, 1);
        Card card2 = new Card(Suit.Heart, 1);
        Card card3 = new Card(Suit.Diamond, 1);
        Card card4 = new Card(Suit.Spade, 1);
        Card card5 = new Card(Suit.Club, 5);
        
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        poker.setHand(cards);

        assertThat(poker.checkHand(), is(HandType.FOUR_OF_A_KIND));
    }

    @Test
    public void フルハウスの役のテスト() {
        Card card1 = new Card(Suit.Club, 1);
        Card card2 = new Card(Suit.Spade, 1);
        Card card3 = new Card(Suit.Club, 2);
        Card card4 = new Card(Suit.Heart, 2);
        Card card5 = new Card(Suit.Spade, 2);
        
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        poker.setHand(cards);

        assertThat(poker.checkHand(), is(HandType.FULL_HOUSE));
    }

    @Test
    public void フラッシュの役のテスト() {
        Card card1 = new Card(Suit.Club, 1);
        Card card2 = new Card(Suit.Club, 10);
        Card card3 = new Card(Suit.Club, 3);
        Card card4 = new Card(Suit.Club, 4);
        Card card5 = new Card(Suit.Club, 5);
        
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        poker.setHand(cards);

        assertThat(poker.checkHand(), is(HandType.FLUSH));
    }

    @Test
    public void ストレートの役のテスト() {
        Card card1 = new Card(Suit.Club, 1);
        Card card2 = new Card(Suit.Heart, 2);
        Card card3 = new Card(Suit.Club, 3);
        Card card4 = new Card(Suit.Club, 4);
        Card card5 = new Card(Suit.Club, 5);
        
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        poker.setHand(cards);

        assertThat(poker.checkHand(), is(HandType.STRAIGHT));
    }

    @Test
    public void スリーオブアカインドの役のテスト() {
        Card card1 = new Card(Suit.Club, 1);
        Card card2 = new Card(Suit.Club, 2);
        Card card3 = new Card(Suit.Club, 3);
        Card card4 = new Card(Suit.Heart, 3);
        Card card5 = new Card(Suit.Spade, 3);
        
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        poker.setHand(cards);

        assertThat(poker.checkHand(), is(HandType.THREE_OF_A_KIND));
    }

    @Test
    public void ツーペアの役のテスト() {
        Card card1 = new Card(Suit.Club, 1);
        Card card2 = new Card(Suit.Club, 2);
        Card card3 = new Card(Suit.Spade, 2);
        Card card4 = new Card(Suit.Heart, 3);
        Card card5 = new Card(Suit.Spade, 3);
        
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        poker.setHand(cards);

        assertThat(poker.checkHand(), is(HandType.TWO_PAIR));
    }

    @Test
    public void ワンペアの役のテスト() {
        Card card1 = new Card(Suit.Club, 1);
        Card card2 = new Card(Suit.Club, 2);
        Card card3 = new Card(Suit.Club, 3);
        Card card4 = new Card(Suit.Heart, 4);
        Card card5 = new Card(Suit.Spade, 4);
        
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        poker.setHand(cards);

        assertThat(poker.checkHand(), is(HandType.ONE_PAIR));
    }

    @Test
    public void ノーペアの役のテスト() {
        Card card1 = new Card(Suit.Club, 1);
        Card card2 = new Card(Suit.Club, 2);
        Card card3 = new Card(Suit.Club, 3);
        Card card4 = new Card(Suit.Heart, 6);
        Card card5 = new Card(Suit.Spade, 7);
        
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
        poker.setHand(cards);

        assertThat(poker.checkHand(), is(HandType.NO_PAIR));
    }
}



