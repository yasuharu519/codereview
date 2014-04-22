package poker;

/**
 *
 * @author a13499
 */
enum Suit {
    Club("♣"),
    Diamond("◆"),
    Heart("♥"),
    Spade("♠"),
    Joker("🃏");

    private String mark;
    private Suit(String mark){
        this.mark = mark;
    }

    @Override
    public String toString(){
        return this.mark;
    }
}

public class Card extends Object implements Comparable{
    public Suit suit;
    public int number;

    public Card(Suit suit){
        this.suit = suit;
        this.number = 0;
    }

    public Card(Suit suit, int number){
        this.suit = suit;
        this.number = number;
    }

    /**
     * 比較対象が自信より順序が後かどうか調べる
     * @param other 比較対象のCard
     * @return 自身のほうが大きいか等しければ true
     */
    public boolean isBiggerThanOrEqualTo(Card other){
        return compareTo(other) >= 0;
    }

    @Override
    public int compareTo(Object other){
        Card comparedCard = (Card)other;
        int thissuit = this.suit.ordinal();
        int othersuit = comparedCard.suit.ordinal();
        if(thissuit != othersuit){
            return thissuit - othersuit;
        }else{
            return this.number - comparedCard.number;
        }
    }

    @Override
    public String toString(){
        return String.format("%s:%d", this.suit, this.number);
    }
}
