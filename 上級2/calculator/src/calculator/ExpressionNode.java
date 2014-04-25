package calculator;

/**
 * オペランドと計算するNodeを持つクラス.
 * 計算のために2分木を構成する.
 */
public class ExpressionNode extends Node{
    private final TokenType type;
    private final Node lhs;
    private final Node rhs;

    // Getter
    ///////////////////////////////////////////////////////////////////////////

    public ExpressionNode(TokenType type, Node lhs, Node rhs){
        this.type = type;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    // Getter
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 2分木の左のNodeのGetter.
     */
    public Node getLeftNode(){
        return this.lhs;
    }

    /**
     * 2分木の右のNodeのGetter.
     */
    public Node getRightNode(){
        return this.rhs;
    }

    /**
     * ExpressionNodeのオペランドのGetter.
     */
    public TokenType getTokenType(){
        return this.type;
    }
}
