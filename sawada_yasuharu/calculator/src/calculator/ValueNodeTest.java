package calculator;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ValueNodeTest {
   
    @Test
    public void コンストラクタが正しく動作すること() {
        RationalNumber number = new RationalNumber(10);
        ValueNode node = new ValueNode(number);

        assertThat(node.getValue(), is(number));
    }
}

