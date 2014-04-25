package calculator;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ExpressionNodeTest {
   
    @Test
    public void コンストラクタが正しく動作すること() {
        Node r_node = new ExpressionNode(TokenType.NUMBER, null, null);
        Node l_node = new ExpressionNode(TokenType.NUMBER, null, null);
        ExpressionNode node = new ExpressionNode(TokenType.PLUS, l_node, r_node);

        assertThat(node.getLeftNode(), is(l_node));
        assertThat(node.getRightNode(), is(r_node));
        assertThat(node.getTokenType(), is(TokenType.PLUS));
    }
}
