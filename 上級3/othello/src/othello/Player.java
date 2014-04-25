package othello;

public class Player {
    private String _name;
    private char _stone;

    public Player(String name, char stone){
        this._name = name;
        this._stone = stone;
    }

    public void put(Table table, char x, char y){
        table.put(x, y, this._stone);
    }

}
