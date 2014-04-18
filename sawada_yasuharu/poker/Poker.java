package poker;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.IllegalFormatException;

enum HandType {
    NO_PAIR("ノーペア"),
    ONE_PAIR("ワンペア"),
    TWO_PAIR("ツーペア"),
    THREE_OF_A_KIND("スリー・オブ・ア・カインド"),
    STRAIGHT("ストレート"),
    FLUSH("フラッシュ"),
    FULL_HOUSE("フルハウス"),
    FOUR_OF_A_KIND("フォー・オブ・ア・カインド"),
    STRAIGHT_FLUSH("ストレートフラッシュ");

    private String name;
    private HandType(String _name){
        this.name = _name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}

public class Poker{

    private Deck deck;
    private List<Card> hand;

    public Poker(){
        this.deck = new Deck();
        this.hand = new ArrayList<Card>(5);

        dealCards();
    }

    private void dealCards(){//{{{
        try{
            hand = this.deck.drawCards(5);
        }catch(NumberExceedException e){
            System.out.println("デッキにカードがありません");
            System.exit(1);
        }
    }//}}}

    private void playGame(){//{{{
        boolean isValidInput = false;
        String inputStr;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        while (!isValidInput) {
            try {
                inputStr = reader.readLine();
                // 1~5文字以外の入力がされた場合
                if ( inputStr.length() < 1 || inputStr.length() > 5) {
                    System.out.println("1~5文字を入力してください");
                    continue;
                }
                char[] inputChars = inputStr.toCharArray();
                int[] inputNums = new int[inputChars.length];
                // 1-5以外が入力された場合エラー
                for (int i = 0; i < inputChars.length; i++) {
                    int num = Integer.parseInt(String.valueOf(inputChars[i]));
                    if(num == 0 && inputChars.length > 1){
                        throw new ZeroInputException();
                    }
                    if(num < 0 || num > 5){
                        throw new IllegalArgumentException();
                    }
                    inputNums[i] = num;
                }
                // 入力は不正なし
                isValidInput = true;
                // 0が入力された場合カードを入れ替えずに終了
                if (inputNums.length == 1 && inputNums[0] == 0){
                    break;
                }
                // カードの入れ替え
                for (int n : inputNums) {
                    exchangeCard(n);
                }
            } catch (NumberFormatException e) {
                System.out.println("数字を入力してください");
                continue;
            } catch (IllegalArgumentException e) {
                System.out.println("1~5の数字で入力してください");
                continue;
            } catch (ZeroInputException e) {
                System.out.println("0を入力する際は1文字だけ入力してください");
                continue;
            } catch (IOException e) {
                System.out.println("入力が不正です");
                continue;
            }
        }
    }//}}}

    private boolean isStraight(){//{{{
        int[] numbers = new int[this.hand.size()];
        for (int i = 0; i < this.hand.size(); i++) {
            numbers[i] = this.hand.get(i).number;
        }
        Arrays.sort(numbers);

        int lastNum = numbers[0];
        for (int i = 1; i < this.hand.size(); i++) {
            if (numbers[i] + 1 != lastNum) {
                return false;
            }
            lastNum = numbers[i];
        }
        return true;
    }//}}}

    private boolean isFlush(){//{{{
        Suit suit = this.hand.get(0).suit;
        for (int i = 1; i < this.hand.size(); i++) {
            if (this.hand.get(i).suit != suit) {
                return false;
            }
        }
        return true;
    }//}}}

    private int[] getCardNumbers(){//{{{
        int[] numbersList = new int[13];
        Arrays.fill(numbersList, 0);

        for (Card c : this.hand) {
            numbersList[c.number - 1] += 1;
        }

        Arrays.sort(numbersList);
        return numbersList;
    }//}}}

    public HandType checkHand(){//{{{
        int[] cardNumbers = getCardNumbers();
            
        if ( isStraight() && isFlush()) {
            // ストレート・フラッシュ
            return HandType.STRAIGHT_FLUSH;
        } else if ( cardNumbers[cardNumbers.length - 1] == 4) {
            // フォーカード
            return HandType.FOUR_OF_A_KIND;
        } else if ( cardNumbers[cardNumbers.length - 1] == 3 &&
                cardNumbers[cardNumbers.length - 2] == 2) {
            // フルハウス
            return HandType.FULL_HOUSE;
        } else if (isFlush()) {
            // フラッシュ
            return HandType.FLUSH;
        } else if ( isStraight()) {
            // ストレート
            return HandType.STRAIGHT;
        } else if ( cardNumbers[cardNumbers.length - 1] == 3) {
            // スリーカード
            return HandType.THREE_OF_A_KIND;
        } else if ( cardNumbers[cardNumbers.length - 1] == 2 &&
                cardNumbers[cardNumbers.length - 2] == 2) {
            // ツーペア
            return HandType.TWO_PAIR;
        } else if ( cardNumbers[cardNumbers.length - 1] == 2) {
            // ワンペア
            return HandType.ONE_PAIR;
        } else{
            // ノーペア
            return HandType.NO_PAIR;
        }
    }//}}}

    public List<Card> getHand(){//{{{
        return this.hand;
    }//}}}

    public void exchangeCard(int num){//{{{
        try{
            Card card = deck.drawCards(1).get(0);
            int idx = num - 1;
            hand.set(idx, card);
        }catch(NumberExceedException e){
            System.out.println("デッキにカードがありません");
            System.exit(1);
        }
    }//}}}

    public void printHands(){//{{{
        for (int i = 0; i < 5; i++) {
            System.out.printf("%d:%s\n", i+1, hand.get(i));
        }
    }//}}}

    public void printResult(){
        HandType result = checkHand();
        System.out.println("役は" + result + "でした。");
    }

    public static void main(String[] args) {
        System.out.println("交換するカードの番号を入力してください(例：135)。");
        System.out.println("0を入力するとカードを交換しません。");
        
        // ゲームを開始する
        Poker game = new Poker();
        game.printHands();
        game.playGame();
        game.printHands();
        game.printResult();
    }
}
