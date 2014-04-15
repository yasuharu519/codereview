package cards;

/**
*
* @author a13499
*/
enum Suit {
   Club,
   Diamond,
   Heart,
   Spade,
   Joker,
}

public class Card extends Object implements Comparable{
   public Suit suit;
   public int number;

   public Card(Suit _suit){
       suit = _suit;
       number = 0;
   }

   public Card(Suit _suit, int _number){
       suit = _suit;
       number = _number;
   }

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
}
