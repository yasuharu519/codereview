package poker;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.IllegalFormatException;

/**
 * 役一覧を表す列挙型
 */
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

    /**
     * コンストラクタ.
     * デッキと手札を表す変数を作成
     */
    public Poker(){
        this.deck = new Deck();
        this.hand = new LinkedList<Card>(5);

        dealCards();
    }

////////////////////////////////////////////////////////////////////////////////
// Public
////////////////////////////////////////////////////////////////////////////////

    /**
     * ユーザに交換するカードの番号を尋ねる.
     * 0が入力された場合は{0}が返される
     */
    public int[] askExchangeCards(){
        boolean isValidInput = false;
        String inputStr;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] inputNums = {};

        while (!isValidInput) {
            try {
                inputStr = reader.readLine();
                // 1~5文字以外の入力がされた場合
                if ( inputStr.length() < 1 || inputStr.length() > 5) {
                    System.out.println("1~5文字を入力してください");
                    continue;
                }
                char[] inputChars = inputStr.toCharArray();
                inputNums = new int[inputChars.length];

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
        return inputNums;
    }

    /**
     * 手札の役を確かめる.
     * 手札の役を判断し、役を表す列挙型を返す
     */
    public HandType checkHand(){
        int[] cardNumbers = getCardNumbers();
            
        if ( isStraight() && isFlush()) {
            // ストレート・フラッシュ
            return HandType.STRAIGHT_FLUSH;
        } else if ( cardNumbers[0] == 4) {
            // フォーカード
            return HandType.FOUR_OF_A_KIND;
        } else if ( cardNumbers[0] == 3 &&
                cardNumbers[1] == 2) {
            // フルハウス
            return HandType.FULL_HOUSE;
        } else if (isFlush()) {
            // フラッシュ
            return HandType.FLUSH;
        } else if ( isStraight()) {
            // ストレート
            return HandType.STRAIGHT;
        } else if ( cardNumbers[0] == 3) {
            // スリーカード
            return HandType.THREE_OF_A_KIND;
        } else if ( cardNumbers[0] == 2 &&
                cardNumbers[1] == 2) {
            // ツーペア
            return HandType.TWO_PAIR;
        } else if ( cardNumbers[0] == 2) {
            // ワンペア
            return HandType.ONE_PAIR;
        } else{
            // ノーペア
            return HandType.NO_PAIR;
        }
    }

    /**
     * hand変数のgetter.
     */
    public List<Card> getHand(){
        return this.hand;
    }

    /**
     * テスト用のhand変数のsetter.
     */
    public void setHand(List<Card> cards){
        this.hand = cards;
    }

    /**
     * 交換する手札の番号を表す配列を受け取り、手札を交換する.
     */
    public void exchangeCards(int[] nums){
        try{
            if (nums.length == 1 && nums[0] == 0) {
                return;
            }
            List<Card> cards = deck.drawCards(nums.length);
            for (int i = 0; i < nums.length; i++) {
                Card card = cards.get(i);
                int idx = nums[i] - 1;
                hand.set(idx, card);
            }
        }catch(NumberExceedException e){
            System.out.println("デッキにカードがありません");
            System.out.println("1人ポーカーなのでこんなことは起きないはずなので、終了させてください(๑╹ڡ╹๑)");
            System.exit(1);
        }
    }

////////////////////////////////////////////////////////////////////////////////
// Private
////////////////////////////////////////////////////////////////////////////////
    /**
     * デッキから手札を配る.
     */
    private void dealCards(){
        try{
            hand = this.deck.drawCards(5);
        }catch(NumberExceedException e){
            System.out.println("デッキにカードがありません");
            System.exit(1);
        }
    }

    /**
     * ストレートが成立しているかどうか確かめる.
     */
    private boolean isStraight(){
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
    }

    /**
     * フラッシュが成立しているかどうか確かめる.
     */
    private boolean isFlush(){
        Suit suit = this.hand.get(0).suit;
        for (int i = 1; i < this.hand.size(); i++) {
            if (this.hand.get(i).suit != suit) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1~13の数がいくつずつあるか調べる.
     * 同じ数のものがいつくずつあるか、降順で並べられた配列が返される.
     */
    private int[] getCardNumbers(){
        int[] numbersList = new int[13];
        Arrays.fill(numbersList, 0);

        for (Card c : this.hand) {
            numbersList[c.number - 1] += 1;
        }

        // 降順にソート
        Arrays.sort(numbersList, Collections.reverseOrder());
        return numbersList;
    }

    /**
     * 手札を標準出力に出力する.
     */
    public void printHands(){
        for (int i = 0; i < 5; i++) {
            System.out.printf("%d:%s\n", i+1, hand.get(i));
        }
    }

    /**
     * 役を標準出力に出力する.
     */
    public void printResult(){
        HandType result = checkHand();
        System.out.println("役は" + result + "でした。");
    }

    /**
     * main関数.
     * 手札を配った後に一度手札を出力する
     * 一度だけ交換するかユーザに聞き、
     * 交換する場合はカードを交換して役を出力する.
     */
    public static void main(String[] args) {
        System.out.println("交換するカードの番号を入力してください(例：135)。");
        System.out.println("0を入力するとカードを交換しません。");
        
        // ゲームを開始する
        Poker game = new Poker();
        game.printHands();
        int[] exchangeCardNums = game.askExchangeCards();
        game.exchangeCards(exchangeCardNums);
        game.printHands();
        game.printResult();
    }
}
