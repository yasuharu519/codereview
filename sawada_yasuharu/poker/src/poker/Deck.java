package poker;

import java.util.*;

/**
 *
 * @author a13499
 */
class NumberExceedException extends Exception{
    public NumberExceedException(String str){
        super(str);
    }
}

public class Deck{
	private ArrayList<Card> cardList = new ArrayList<Card>();
    private int jokerNum;

    public Deck(int _jokerNum){
        jokerNum = _jokerNum;
        initialize(jokerNum);
    }

    public Deck(){
        this(0);
    }

    /**
     * カードを追加し、シャッフルを行う関数
     * @param _jokerNum 追加するジョーカーの数
     */
    private void initialize(int _jokerNum){
        for (Suit suit : Suit.values()) {
            if(suit == Suit.Joker){
                for (int i = 0; i < _jokerNum; i++) {
                    cardList.add(new Card(suit));
                }
            }
            else{
            	for (int number = 1; number <= 13; number++) {
            		cardList.add(new Card(suit, number));
            	}
            }
        }
        // シャッフル
        Collections.shuffle(cardList);
    }
    
    /**
     * デッキに残っているカードの枚数を返す
     * @return 残っているカードの枚数
     */
    public int getRemainedCardNumber(){
        return cardList.size();
    }

    /**
     * カードを頭から引いて、そのリストを返す
     * @param drawNumber 引くカードの枚数
     * @throws NumberExceedException 残数より大きいカードを引いた場合
     * @return 引かれたカードが含まれるList<Card>
     */
    public List<Card> drawCards(int drawNumber) throws NumberExceedException {
        List<Card> drawnCards = new ArrayList<>();
        if(drawNumber > getRemainedCardNumber()){
            throw new NumberExceedException("カードを引く枚数が多すぎます");
        }
        for (int i = 0; i < drawNumber; i++) {
            Card card = cardList.get(i);
            drawnCards.add(card);
            cardList.remove(i);
        }
        return drawnCards;
    }
    
    /**
     * カードの覗き見を行う関数
     * @param idx インデックス
     * @return インデックスに当たるCard
     */
    public Card peek(int idx){
    	return cardList.get(idx);
    }
    
}
