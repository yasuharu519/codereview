import java.util.*;
import java.io.*;

public class MyCalendar{
    public int year;
    public int month;
    private int[][] matrix = new int[6][7];
    private int startDay;
    private int lastDay;

    /**
     * カレンダークラスのコンストラクタ
     * @param _year 年を表すint
     * @param _month 月を表すint
     */
    public MyCalendar(int _year, int _month){//{{{
        Calendar cal = Calendar.getInstance();
        year = _year;
        month = _month;

        calculateField();
    }//}}}

    /**
     * matrix配列に日付を埋めていく
     *
     * - matrix[0]は第1週目を示す
     * - 1日が火曜日から始まる場合 matrix[0] = {0, 0, 1, 2, 3, 4, 5}
     */
    private void calculateField(){//{{{
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(year, month - 1, 1);
        startDay = cal.get(Calendar.DAY_OF_WEEK);
        // 月末
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        lastDay = cal.get(Calendar.DATE);

        int row = 0;
        int column = startDay - 1;
        for (int i = 1; i <= lastDay; i++) {
            matrix[row][column] = i;
            column++;
            if(column == 7){
                row++;
                column = 0;
            }
        }
    }//}}}

    /**
     * カレンダークラスを文字列表現する関数
     */
    @Override
    public String toString(){//{{{
        int day;
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        result.append("日 月 火 水 木 金 土" + NEW_LINE);
        outer:for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 7; column++) {
                day = matrix[row][column];
                if(day == 0){
                    if(row != 0){
                        break outer;
                    }
                    result.append("   ");
                }else{
                    if(day < 10){
                        result.append(" ").append(day).append(" ");
                    }else{
                        result.append(day).append(" ");
                    }
                }
            }
            result.append(NEW_LINE);
        }
        return result.toString();
    }//}}}

    public static void main(String[] args) {
        int year;
        int month;
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("カレンダーを出力します");
            System.out.println("年を入力してください（例:2014）");
            year = Integer.parseInt(reader.readLine());
            System.out.println("月を入力してください（例:4）");
            month = Integer.parseInt(reader.readLine());

            // インスタンス作成
            MyCalendar cal = new MyCalendar(year, month);

            System.out.printf("%d年 %d月\n", year, month);

            System.out.print(cal);
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
