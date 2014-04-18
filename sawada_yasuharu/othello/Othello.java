package othello;

import java.util.Arrays;

public class Othello{
    private Table table = new Table();
    private Player playerA = new Player("A");
    private Player playerB = new Player("B");

    public Othello{
        // 初期石の配置
        playerA.put(table, new Place('4', 'd'));
        playerA.put(table, new Place('5', 'e'));
        playerB.put(table, new Place('4', 'e'));
        playerB.put(table, new Place('5', 'd'));
    }

    public static void main(String[] args) {
        table.printCurrentState();
    }
}

