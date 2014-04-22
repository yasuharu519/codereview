package calculator;

enum TokenType {
    NUMBER,
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,
    R_PAREN,
    L_PAREN;
}

public class Token{
    public double nval;
    public TokenType type;

    public Token(TokenType type){
        this.type = type;
    }

    public Token(TokenType type, double nval){
        this(type);
        this.nval = nval;
    }
}
