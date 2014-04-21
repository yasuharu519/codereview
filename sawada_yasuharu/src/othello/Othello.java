package othello;

import java.util.Arrays;

public class Othello{
    private Table table = new Table();
    private Player playerA;
    private Player playerB;
    private Player currentPlayer;

    // Public
    public Othello(){//{{{
        playerA = new Player("A", '●');
        playerB = new Player("B", '○');
        currentPlayer = playerA;
    }//}}}

    public Table getTable(){//{{{
        return table;
    }//}}}

    // Private
    private switchPlayer(){//{{{
        if(currentPlayer == playerA){
            currentPlayer = playerB;
        }else{
            currentPlayer = playerA;
        }
    }//}}}

    public static void main(String[] args) {
        Othello othello = new Othello();
        othello.getTable().printCurrentState();
    }
}

