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
public class CardTest {
    
    @Test
    public void カードはマークと数字を持つ(){
        Card card = new Card(Suit.Club, 1);

        assertThat(card.suit, is(Suit.Club));
        assertThat(card.number, is(1));
    }

    @Test
    public void ジョーカーが存在する() {
        Card joker = new Card(Suit.Joker);

        assertThat(joker.suit, is(Suit.Joker));
    }

    @Test
    public void 同じマークだと数字が大きいほうが大きい() {
        // クラブ
        Card card1;
        Card card2;
        Suit[] suits = {Suit.Club, Suit.Diamond, Suit.Heart, Suit.Spade};
        for (Suit suit : suits) {
            for (int i = 1; i < 13; i++) {
                card1 = new Card(suit, i);
                card2 = new Card(suit, i + 1);
                assertThat(card2.isBiggerThanOrEqualTo(card1), is(true));
            }
        }
    }

    @Test
    public void マークの大小関係のテスト() {
        Suit[] suits = {Suit.Club, Suit.Diamond, Suit.Heart, Suit.Spade, Suit.Joker};
        Card card1;
        Card card2;
        for(int i = 0; i < 3; i++){
            card1 = new Card(suits[i], 1);
            card2 = new Card(suits[i+1], 1);
            assertThat(card2.isBiggerThanOrEqualTo(card1), is(true));
        }
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void ソート可能() {
        Card card1 = new Card(Suit.Club, 1);
        Card card2 = new Card(Suit.Joker);
        List<Card> cardList = new ArrayList<>();
        
        // カードの追加
        cardList.add(card2);
        cardList.add(card1);

        // カードのソート
        Collections.sort(cardList);

        // カードの順番を見る
        assertThat(cardList.get(0), is(card1));
        assertThat(cardList.get(1), is(card2));
    }
}

