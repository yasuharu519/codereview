import java.io.*;

public class MyRPS {
    enum Hand {
        Rock("グー"),
        Paper("パー"),
        Sissors("チョキ");

        private String name;

        private Hand(String _name){
            this.name = _name;
        }

        public static Hand parseFromInt(int num){
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

        public Result playRPS(Hand other){
            if(this == other){
                return Result.Draw;
            }else{
                if( (this == Hand.Rock && other == Hand.Sissors) ||
                    (this == Hand.Paper && other == Hand.Rock) ||
                    (this == Hand.Sissors && other == Hand.Paper)){
                    return Result.YouWon;
                }else{
                    return Result.YouLost;
                }
            }
        }

        @Override
        public String toString(){
            return this.name;
        }
    }

    enum Result{
        YouWon,
        YouLost,
        Draw;

        public void printResult(){
            switch (this){
                case YouWon:
                    System.out.println("あなたの勝ちです");
                    break;
                case YouLost:
                    System.out.println("あなたの負けです");
                    break;
                default:
                    System.out.println("引き分けです");
                    break;
            }
        }
    }

    /**
     * 入力された数字に不正がないか確かめる
     * @param inputNum 入力された数字
     * @return 入力に不正がないかを示すboolean
     */
    public boolean isValidInput(int inputNum){
        return (inputNum >= 1 && inputNum < 4);
    }

    /**
     * じゃんけんを繰り返す部分
     * ユーザに入力を求める際には、不正な入力がされるまで入力を繰り返し求める
     */
    public void doGame(){
        Result result = null;

        do{
            // 適切な入力があるまで繰り返し 
            int ownNum = 0;
            while(!isValidInput(ownNum)){
                System.out.print("出す手を入力 => ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    ownNum = Integer.parseInt(reader.readLine());
                }catch(IOException e){
                    System.out.println(e);
                }
            }

            if(result != Result.Draw){
                System.out.println("ぽん！");
            }else{
                System.out.println("しょ！");
            }

            // 敵の出す手を計算
            int enemyNum = (int)(Math.random() * 3) + 1;
            // 列挙型に変換
            Hand ownHand = Hand.parseFromInt(ownNum);
            Hand enemyHand = Hand.parseFromInt(enemyNum);
            // 出した手を出力
            System.out.printf("あなた: %s、コンピュータ: %s\n", ownHand, enemyHand);
            // 結果
            result = ownHand.playRPS(enemyHand);
            if(result == Result.Draw){
                System.out.println("あーいこーで・・");
            }
        }while(result == Result.Draw);

        // 最終的な結果の出力
        result.printResult();
    }

    public static void main(String[] args) {
        System.out.println("じゃんけんをしましょう!");
        System.out.println("1: グー、2: チョキ、3: パー です。");
        System.out.println("じゃーんけーん・・");
        
        MyRPS rps = new MyRPS();
        rps.doGame();
    }
}

