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

public class Token implements Comparable<Object>{
    private int parenNum;
    private double nval;
    private TokenType type;

    // Constructor
    ///////////////////////////////////////////////////////////////////////////

    /**
     * TokenTypeのみのコンストラクタ.
     */
    public Token(TokenType type){
        this.type = type;
    }

    /**
     * TokenTypeとカッコの数を設定するコンストラクタ.
     */
    public Token(TokenType type, int parenNum){
        this(type);
        this.parenNum = parenNum;
    }

    /**
     * TokenTypeとTokenTypeがNumberの場合の数を設定するコンストラクタ.
     */
    public Token(TokenType type, double nval){
        this(type);
        this.nval = nval;
    }

    // Public methods
    ///////////////////////////////////////////////////////////////////////////

    public int compareTo(Object object){
        Token otherToken = (Token)object;
        if(this.parenNum == otherToken.getParenNum()){
            return this.type.getPriority() - otherToken.getTokenType().getPriority();
        }else{
            return this.parenNum - otherToken.getParenNum();
        }
    }
    
    // Getter
    ///////////////////////////////////////////////////////////////////////////
    
    /**
     * TokenTypeのGetter.
     */
    public TokenType getTokenType(){
        return type;
    }

    /**
     * TokenTypeがNumberの場合のnvalのGetter.
     */
    public double getValue(){
        return nval;
    }

    /**
     * Tokenがある場所のカッコの数のGetter.
     */
    public int getParenNum(){
        return parenNum;
    }

}
