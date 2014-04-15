package intermediate;

import java.util.*;

public class Intermediate2{
    
    /**
     * 与えられた配列から、連続した重複を削除する
     *
     * @param inputArray 入力する配列
     * @throws NullPointerException 引数にnullが与えられた場合
     * @return 連続する重複を除いた後の配列
     */
    public static int[] uniq(int[] inputArray) throws NullPointerException{
        if(inputArray == null){
            throw new NullPointerException();
        }
        ArrayList<Integer> newArray = new ArrayList<>();
        
        int lastNum;
        if(inputArray.length >= 1){
            lastNum = inputArray[0];
            newArray.add(inputArray[0]);
            for(int i = 1; i < inputArray.length; i++){
                if(inputArray[i] != lastNum){
                    newArray.add(inputArray[i]);
                    lastNum = inputArray[i];
                }
            }
        }
        int[] result = new int[newArray.size()];
        for(int i= 0; i < newArray.size(); i++){
            result[i] = newArray.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
		int[] src = {1, 1, 2, 2, 2, 3, 4, 4, 5, 5};
		int[] actual = Intermediate2.uniq(src);
        for (int num : actual) {
            System.out.println(num);
        }
        
    }
}
