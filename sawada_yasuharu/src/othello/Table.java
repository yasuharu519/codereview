package othello;

import java.util.Arrays;

public class Table{
    public static final int TABLE_WIDTH = 8;
    public static final int TABLE_HEIGHT = 8;
    public static final char[] COLUMN_NAME = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    public static final char[] ROW_NAME = {
        '1', '2', '3', '4', '5', '6', '7', '8'};
    private char[][] grid = new char[TABLE_HEIGHT][TABLE_WIDTH];

    // Public
    public Table(){
        for (int i = 0; i < grid.length; i++) {
            Arrays.fill(grid[i], '　');
        }
    }

    public void printCurrentState(){//{{{
        // オセロの盤自体の幅
        int width = TABLE_WIDTH * 2 + 1;
        // オセロの盤自体の縦幅
        int height = TABLE_HEIGHT * 2 + 1;

        // ヘッダの出力
        System.out.print(" ");
        for (char c : COLUMN_NAME) {
            System.out.printf("　 %c", c);
        }
        System.out.println();

        char c;
        for (int j = 1; j <= height; j++) {
            for (int i = 0; i <= width; i++) {
                // マス目以外の行
                if (j % 2 == 1 ) {
                    c = (i == 0) ? ' '
                       :(i == 1 && j == 1) ? '┏'
                       :(i == width && j == 1) ? '┓'
                       :(i == 1 && j == height) ? '┗'
                       :(i == width && j == height) ? '┛'
                       :(i == 1) ? '┣'
                       :(i == width) ? '┫'
                       :(i % 2 == 1 && j == 1) ? '┳'
                       :(i % 2 == 1 && j == height) ? '┻'
                       :(i % 2 == 1) ? '╋'
                       : '━';
                } else { // マス目の行
                    int x = i / 2;
                    int y = j / 2;
                    c = (i == 0) ? ROW_NAME[y - 1]
                       :(i % 2 == 1) ? '┃'
                       : grid[y-1][x-1];
                }
                System.out.print(c);
            }
            System.out.println();
        }
    }//}}}


}
