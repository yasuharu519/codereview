import java.io.*;

public class MyRPS {
    enum Hand {
        Rock,
        Paper,
        Sissors
    }

    enum Result{
        Youwon,
        Youlose,
        Draw
    }

    /**
     * 入力されたintを手を示す列挙型に変換
     * @param num 入力されたint
     * @return 手を示す列挙型
     */
    public Hand parseFromInt(int num){
        switch (num) {
            case 1:
                return Hand.Rock;
            case 2:
                return Hand.Paper;
            case 3:
                return Hand.Sissors;
        }
        return null;
    }
    
    /**
     * 列挙型を文字列に変換
     * @param hand 手を示す列挙型
     * @return 手を示す文字列
     */
    public String convertToString(Hand hand){
        switch (hand) {
            case Rock:
                return "グー";
            case Paper:
                return "パー";
            case Sissors:
                return "チョキ";
        }
        return null;
    }

    /**
     * 入力された数字に不正がないか確かめる
     * @param inputNum 入力された数字
     * @return 入力に不正がないかを示すboolean
     */
    public boolean isValidInput(int inputNum){
        if(inputNum < 1 || inputNum >= 4){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 勝敗を示す列挙型を返す
     * @param ownHand ユーザの手を表す列挙型
     * @param enemyHand コンピュータの手を表す列挙型
     * @return 勝敗を示す列挙型を返す
     */
    public Result play(Hand ownHand, Hand enemyHand){
        if(ownHand == enemyHand){
            return Result.Draw;
        }else{
            if( (ownHand == Hand.Rock && enemyHand == Hand.Sissors) || 
                (ownHand == Hand.Paper && enemyHand == Hand.Rock) ||
                (ownHand == Hand.Sissors && enemyHand == Hand.Paper)){
                return Result.Youwon;
            }
            else{
                return Result.Youlose;
            }
        }
    }

    /**
     * じゃんけんを繰り返す部分
     * ユーザに入力を求める際には、不正な入力がされるまで入力を繰り返し求める
     */
    public void doGame(){
        boolean firstTime = true;
        int ownNum = 0;
        int enemyNum = 0;
        Hand ownHand;
        Hand enemyHand;
        Result result;

        do{
            // 適切な入力があるまで繰り返し 
            do{
                System.out.print("出す手を入力 => ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    ownNum = Integer.parseInt(reader.readLine());
                }catch(IOException e){
                    System.out.println(e);
                }
            }while(!isValidInput(ownNum));

            // 最初はぽん！
            if(firstTime){
                System.out.println("ぽん！");
                firstTime = false;
            }else{
                System.out.println("しょ！");
            }

            // 敵の出す手を計算
            enemyNum = (int)(Math.random() * 3) + 1;
            // 列挙型に変換
            ownHand = parseFromInt(ownNum);
            enemyHand = parseFromInt(enemyNum);
            // 出した手を出力
            System.out.printf("あなた: %s、コンピュータ: %s\n",
                    convertToString(ownHand), convertToString(enemyHand));
            // 結果
            result = play(ownHand, enemyHand);
            if(result == Result.Draw){
                System.out.println("あーいこーで・・");
            }
        }while(result == Result.Draw);

        // 最終的な結果の出力
        if(result == Result.Youwon){
            System.out.println("あなたの勝ちです！");
        }else{
            System.out.println("あなたの負けです！");
        }
    }

    public static void main(String[] args) {
        System.out.println("じゃんけんをしましょう!");
        System.out.println("1: グー、2: チョキ、3: パー です。");
        System.out.println("じゃーんけーん・・");
        
        MyRPS rps = new MyRPS();
        rps.doGame();
    }
}

