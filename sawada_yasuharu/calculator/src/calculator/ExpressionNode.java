package calculator;

public class ExpressionNode extends Node{
    private final TokenType type;
    private final Node lhs;
    private final Node rhs;

    public ExpressionNode(TokenType type, Node lhs, Node rhs){
        this.type = type;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Node getLeftNode(){
        return this.lhs;
    }

    public Node getRightNode(){
        return this.rhs;
    }

    public TokenType getTokenType(){
        return this.type;
    }
}
