package calculator;

public class ExpressionNode extends Node{
    public char op;
    public Node lhs;
    public Node rhs;

    public ExpressionNode(char op, Node lhs, Node rhs){
        this.op = op;
        this.lhs = lhs;
        this.rhs = rhs;
    }
}
