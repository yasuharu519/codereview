package calculator;

/**
 * 数字を表すクラス
 */
public class ValueNode extends Node{
    private final double value;

    // Constructor
    ///////////////////////////////////////////////////////////////////////////

    public ValueNode(double value){
        this.value = value;
    }

    // Getter
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 数字自体のGetter.
     */
    public double getValue(){
        return this.value;
    }
}
