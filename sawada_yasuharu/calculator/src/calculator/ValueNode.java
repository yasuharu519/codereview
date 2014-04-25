package calculator;

/**
 * 数字を表すクラス
 */
public class ValueNode extends Node{
    private final RationalNumber value;

    // Constructor
    ///////////////////////////////////////////////////////////////////////////

    public ValueNode(RationalNumber value){
        this.value = value;
    }

    // Getter
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 数字自体のGetter.
     */
    public RationalNumber getValue(){
        return this.value;
    }
}
