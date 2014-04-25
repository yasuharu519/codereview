import java.util.*;

public class MyCalendar_v1{
    public int year;
    public int month;
    private int[][] matrix = new int[6][7];
    private int startDay;
    private int lastDay;

    /**
     * MyCalendarのコンストラクタ
     * プログラム実行時の年月を取得
     */
    public MyCalendar_v1(){//{{{
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;

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
        MyCalendar_v1 cal = new MyCalendar_v1();

        System.out.printf("%d年%d月のカレンダーを出力します\n", cal.year, cal.month);
        System.out.printf("%d年 %d月\n", cal.year, cal.month);

        System.out.print(cal);
    }
}
