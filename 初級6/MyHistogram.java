import java.util.*;

public class MyHistogram{
    private int maxNum;
    private int[] inputNums;
    private char[][] hist;

    /**
     * コンストラクタ
     */
    public MyHistogram(String[] input){
        // データを格納
        inputNums = new int[input.length];
        for(int i = 0; i < input.length; i++){
            inputNums[i] = Integer.parseInt(input[i]);
        }
        // 最大値の取得
        maxNum = getMaxFromArray(inputNums);

        // 配列の準備
        hist = new char[maxNum][inputNums.length];
        // ヒストグラムデータの作成
        for (int row = 0; row < maxNum; row++) {
            for(int column = 0; column < inputNums.length; column++){
                if(inputNums[column] >= maxNum - row){
                    hist[row][column] = '*';
                }else{ 
                    hist[row][column] = ' ';
                }
            }
        }
    }
    
    private int getMaxFromArray(int[] numbers){
        int max = -1;
        if(numbers.length > 0){
            max = numbers[0];
            for (int i = 0; i < numbers.length; i++) {
                max = Math.max(max, numbers[i]);
            }
        }
        return max;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");
        for (char[] line : hist) {
            for (char c: line) {
                sb.append(c);
            }
            sb.append(NEW_LINE);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        if(args.length < 1){
            System.out.println("引数を入力して実行してください。");
            return;
        }
        MyHistogram hist = new MyHistogram(args);
        System.out.print(hist);
    }
}
