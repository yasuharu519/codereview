package intermediate;

import java.util.*;

public class Intermediate1{

    /**
     * intを入力としてうけとり、2進数表現の文字列で返す
     *
     * @param input 2進数変換したいint
     * @throws IllegalArgumentException 負の数が入力された場合
     * @return inputを2進数表現した文字列
     */
    public static String toBinaryString(int input) throws IllegalArgumentException{
        ArrayList<Integer> modList = new ArrayList<Integer>();
        int tmpValue;

        if(input < 0){
            throw new IllegalArgumentException();
        }
        else{
            tmpValue = input;
            while (true) {
                modList.add(tmpValue % 2);
                tmpValue /= 2;
                if(tmpValue == 0){
                    break;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");
        for (int i = modList.size() - 1; i >= 0; i--) {
            sb.append(modList.get(i));
        }
        return sb.toString();
    }
}

