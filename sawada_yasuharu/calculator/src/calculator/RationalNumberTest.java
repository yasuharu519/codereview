package calculator;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;

public class RationalNumberTest {
    private RationalNumber testNum;

    @Test
    public void intを入力としてとるコンストラクタが正しく動くこと() {
        RationalNumber testNum = new RationalNumber(10);

        assertThat(testNum.numerator, is(10));
        assertThat(testNum.denominator, is(1));
    }

    @Test
    public void 分母分子を入力としてとるコンストラクタが正しく動くこと() {
        RationalNumber testNum = new RationalNumber(5, 3);

        assertThat(testNum.numerator, is(5));
        assertThat(testNum.denominator, is(3));
    }

    @Test
    public void 分母分子を入力とすると約分がされること() {
        RationalNumber testNum = new RationalNumber(6, 9);

        assertThat(testNum.numerator, is(2));
        assertThat(testNum.denominator, is(3));
    }

    @Test
    public void doubleを入力としてとるコンストラクタが正しく動くこと() {
        RationalNumber testNum = new RationalNumber(2.0);

        assertThat(testNum.numerator, is(2));
        assertThat(testNum.denominator, is(1));
    }

    @Test
    public void doubleを入力としてとるコンストラクタでは自動的に約分されること() {
        RationalNumber testNum = new RationalNumber(1.5);

        assertThat(testNum.numerator, is(3));
        assertThat(testNum.denominator, is(2));
    }

    @Test
    public void 分母が同じ足し算が正しく動くこと() {
        RationalNumber num1 = new RationalNumber(1, 2);
        RationalNumber num2 = new RationalNumber(3, 2);

        RationalNumber testNum = num1.plus(num2);

        assertThat(testNum.numerator, is(2));
        assertThat(testNum.denominator, is(1));
    }

    @Test
    public void 分母が異なる足し算が正しく動くこと() {
        RationalNumber num1 = new RationalNumber(1, 2);
        RationalNumber num2 = new RationalNumber(1, 3);

        RationalNumber testNum = num1.plus(num2);

        assertThat(testNum.numerator, is(5));
        assertThat(testNum.denominator, is(6));
    }

    @Test
    public void 分母が同じ引き算が正しく動くこと() {
        RationalNumber num1 = new RationalNumber(3, 5);
        RationalNumber num2 = new RationalNumber(1, 5);

        RationalNumber testNum = num1.minus(num2);

        assertThat(testNum.numerator, is(2));
        assertThat(testNum.denominator, is(5));
    }

    @Test
    public void 分母が同じ引き算が負の値となっても正しく動くこと() {
        RationalNumber num1 = new RationalNumber(-3, 5);
        RationalNumber num2 = new RationalNumber(1, 5);

        RationalNumber testNum = num1.minus(num2);

        assertThat(testNum.numerator, is(-4));
        assertThat(testNum.denominator, is(5));
    }

    @Test
    public void 分母が異なる引き算が正しく動くこと() {
        RationalNumber num1 = new RationalNumber(1, 2);
        RationalNumber num2 = new RationalNumber(1, 3);

        RationalNumber testNum = num1.minus(num2);

        assertThat(testNum.numerator, is(1));
        assertThat(testNum.denominator, is(6));
    }

    @Test
    public void 掛け算が正しく動くこと() {
        RationalNumber num1 = new RationalNumber(1, 2);
        RationalNumber num2 = new RationalNumber(1, 3);

        RationalNumber testNum = num1.multiply(num2);

        assertThat(testNum.numerator, is(1));
        assertThat(testNum.denominator, is(6));
    }

    @Test
    public void 割り算が正しく動くこと() {
        RationalNumber num1 = new RationalNumber(1, 2);
        RationalNumber num2 = new RationalNumber(1, 3);

        RationalNumber testNum = num1.divide(num2);

        assertThat(testNum.numerator, is(3));
        assertThat(testNum.denominator, is(2));
    }

    @Test
    public void toStringがfalseを渡された場合に正しく動作すること() {
        RationalNumber testNum = new RationalNumber(1, 2);

        assertThat(testNum.toString(false), is("1/2"));
    }

    @Test
    public void toStringがfalseを渡され分母が1の場合は分母の表示を行わないこと() {
        RationalNumber testNum = new RationalNumber(10);

        assertThat(testNum.toString(false), is("10"));
    }

    @Test
    public void toStringがtrueを渡された場合に正しく動作すること() {
        RationalNumber testNum = new RationalNumber(1, 2);

        assertThat(testNum.toString(true), is("0.500000"));
    }

    @Test
    public void equalsメソッドが正しく動作すること() {
        RationalNumber testNum, matcher;
        testNum = new RationalNumber(10);
        matcher = new RationalNumber(20, 2);
        assertTrue(testNum.equals(matcher));

        testNum = new RationalNumber(6, 4);
        matcher = new RationalNumber(3, 2);
        assertTrue(testNum.equals(matcher));
    }
}
