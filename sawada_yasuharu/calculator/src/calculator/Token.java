package calculator;

enum TokenType {
    NUMBER(0),
    PLUS(1),
    MINUS(1),
    MULTIPLY(2),
    DIVIDE(2),
    R_PAREN(0),
    L_PAREN(0);

    private int priority;
    private TokenType(int p){
        this.priority = p;
    }
    public int getPriority(){
        return this.priority;
    }
}

public class Token{
    private int parenNum;
    private double nval;
    private TokenType type;

    public Token(TokenType type){
        this.type = type;
    }

    // 記号と計算の優先度を作成するコンストラクタ
    public Token(TokenType type, int parenNum){
        this(type);
        this.parenNum = parenNum;
    }

    // 数字とその値を作成するコンストラクタ
    public Token(TokenType type, double nval){
        this(type);
        this.nval = nval;
    }

    public TokenType getTokenType(){
        return type;
    }

    public double getValue(){
        return nval;
    }

    public int getPriority(){
        return this.type.getPriority() * (int)Math.pow(3.0, ((double)parenNum));
    }
}
