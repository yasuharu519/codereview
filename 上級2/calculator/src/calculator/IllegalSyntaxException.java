package calculator;

/**
 * 数式のシンタックスエラーを表すクラス
 * ・開きカッコと閉じカッコの数が合わない
 * ・オペランドと数字の数が合わない
 * など
 */
public class IllegalSyntaxException extends Exception{
	private static final long serialVersionUID = 2712179481911159039L;

	public IllegalSyntaxException(String str){
        super(str);
    }
}
