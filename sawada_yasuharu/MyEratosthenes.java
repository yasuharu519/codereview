import java.util.*;
import java.io.*;

public class MyEratosthenes{

    private int maxNum;
    private ArrayList<Integer> searchList = new ArrayList<Integer>();
    private ArrayList<Integer> primeList = new ArrayList<Integer>();

    /**
     * エラトステネスクラスのコンストラクタ
     * 最大の数を引数にとり、2以上の数が入力された際、
     * そこまでの数をsearchListに入力する
     * 10が入力された場合、{2,3,4,5,6,7,8,9,10}が代入される
     * @param _maxNum 篩がけする最大の数
     */
    public MyEratosthenes(int _maxNum){
        maxNum = _maxNum;
        if(maxNum >= 2){
            for (int i = 2; i <= maxNum; i++) {
                searchList.add(i);
            }
            search();
        }
    }

    /**
     * ふるいがけから残った数を出力する
     */
    public void printList(){
        for (int data : primeList) {
            System.out.print(data + " ");
        }
    }

    /**
     * ふるいがけを行い、素数を探す
     */
    private void search(){
        int popped;
        while (true) {
            // 先頭を取り出し
            popped = searchList.get(0);
            searchList.remove(0);
            // primeListに追加
            primeList.add(popped);

            // ふるいがけ
            for(int i = 0; i < searchList.size(); i++){
                if(searchList.get(i) % popped == 0){
                    searchList.remove(i);
                }
            }
            if((double)popped > Math.sqrt(maxNum)){
                break;
            }
        }
        if(searchList.size() > 0){
            primeList.addAll(searchList);
        }
    }

    public static void main(String[] args) {
        System.out.println("素数リストを出力するプログラムです。");
        System.out.println("リストの最大値を整数で入力してください。");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int max = Integer.parseInt(reader.readLine());
            MyEratosthenes myeratosthenes = new MyEratosthenes(max);
            myeratosthenes.printList();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
