import java.io.*;

enum Hand {
    Rock,
    Paper,
    Sissors
}

public class MyRPS_v1 {
    /**
     * intから列挙型への変換
     * @param i 入力されたint
     * @return 手を示す列挙型
     */
    public static Hand parseFromInt(int i){//{{{
        Hand hand;
        switch (i) {
            case 1:
                return Hand.Rock;
            case 2:
                return Hand.Paper;
            case 3:
                return Hand.Sissors;
        }
        return null;
    }//}}}
    
    /**
     * 列挙型から文字列への変換
     * @param hand 手を示す列挙型
     * @return 手を示す文字列
     */
    public static String convertToString(Hand hand){//{{{
        switch (hand) {
            case Rock:
                return "グー";
            case Paper:
                return "パー";
            case Sissors:
                return "チョキ";
        }
        return null;
    }//}}}

    /**
     * 相手の手と自分の手を表示
     * @param own 自分の手を示す列挙型
     * @param enemy 相手の手を示す列挙型
     */
    public static void printRPS(Hand own, Hand enemy){//{{{
        System.out.printf("あなた: %s、コンピュータ: %s\n", convertToString(own), convertToString(enemy));
    }//}}}

    /**
     * 入力が正しいかチェックする
     * @param inputNum 入力されたint
     * @return 入力されたintが正しいかを示すboolean
     */
    public static boolean isValidInput(int inputNum){//{{{
        if(inputNum < 1 || inputNum >= 4){
            return false;
        }else{
            return true;
        }
    }//}}}

    /**
     * 勝敗結果を示す関数
     * @param ownHand ユーザの手を示す列挙型
     * @param enemyHand コンピュータの手を示す列挙型
     */
    public static void printResult(Hand ownHand, Hand enemyHand){//{{{
        if(ownHand == enemyHand){
            System.out.println("あいこでした！");
            return;
        }else{
            if( (ownHand == Hand.Rock && enemyHand == Hand.Sissors) || 
                (ownHand == Hand.Paper && enemyHand == Hand.Rock) ||
                (ownHand == Hand.Sissors && enemyHand == Hand.Paper)){
                System.out.println("あなたの勝ちです！");
                return;
                }
            else{
                System.out.println("あなたの負けです！");
                return;
            }
        }
    }//}}}

    public static void main(String[] args) {
        int ownNum;
        int enemyNum;
        Hand ownHand;
        Hand enemyHand;

        System.out.println("じゃんけんをしましょう!");
        System.out.println("1: グー、2: チョキ、3: パー です。");
        System.out.println("じゃーんけーん・・");
        System.out.print("出す手を入力 => ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line;
            line = reader.readLine();
            ownNum = Integer.parseInt(line);
            if(isValidInput(ownNum)){
                // 敵の出す手を計算
                enemyNum = (int)(Math.random() * 3) + 1;
                // 列挙型に変換
                ownHand = parseFromInt(ownNum);
                enemyHand = parseFromInt(enemyNum);
                // 出した手を出力
                printRPS(ownHand, enemyHand);
                // 結果を出力
                printResult(ownHand, enemyHand);
            }else{
                System.out.println("1~3で入力してください！");
                return;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
