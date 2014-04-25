package calculator;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CalculatorTest {
    @Test
    public void 木構造走査の足し算が正しく行われる() {
        ValueNode node1 = new ValueNode(new RationalNumber(1));
        ValueNode node2 = new ValueNode(new RationalNumber(2));
        ExpressionNode root = new ExpressionNode(TokenType.PLUS, node1, node2);
        RationalNumber result = Calculator.traverseTree(root);

        RationalNumber matcher = new RationalNumber(3);
        assertTrue(result.equals(matcher));
    } 

    @Test
    public void 木構造走査の引き算が正しく行われる() {
        ValueNode node1 = new ValueNode(new RationalNumber(2));
        ValueNode node2 = new ValueNode(new RationalNumber(1));
        ExpressionNode root = new ExpressionNode(TokenType.MINUS, node1, node2);
        RationalNumber result = Calculator.traverseTree(root);

        RationalNumber matcher = new RationalNumber(1);
        assertTrue(result.equals(matcher));
    } 

    @Test
    public void 木構造走査の掛け算が正しく行われる() {
        ValueNode node1 = new ValueNode(new RationalNumber(2));
        ValueNode node2 = new ValueNode(new RationalNumber(3));
        ExpressionNode root = new ExpressionNode(TokenType.MULTIPLY, node1, node2);
        RationalNumber result = Calculator.traverseTree(root);

        RationalNumber matcher = new RationalNumber(6);
        assertTrue(result.equals(matcher));
    } 

    @Test
    public void 木構造走査の割り算が正しく行われる() {
        ValueNode node1 = new ValueNode(new RationalNumber(2));
        ValueNode node2 = new ValueNode(new RationalNumber(3));
        ExpressionNode root = new ExpressionNode(TokenType.DIVIDE, node1, node2);
        RationalNumber result = Calculator.traverseTree(root);

        RationalNumber matcher = new RationalNumber(2, 3);
        assertTrue(result.equals(matcher));
    } 

    @Test
    public void dフラッグを渡さない場合有理数で結果が表示される() {
        String result = Calculator.calculate("2/3", false);

        String matcher = "2/3";
        assertTrue(result.equals(matcher));
    } 

    @Test
    public void dフラッグを渡さない場合有理数は約分された形で結果が表示される() {
        String result = Calculator.calculate("4/6", false);

        String matcher = "2/3";
        assertTrue(result.equals(matcher));
    } 

    @Test
    public void dフラッグを渡した場合少数で結果が表示される() {
        String result = Calculator.calculate("4/6", true);

        String matcher = "0.666667";
        assertThat(result, is(matcher));
    } 
}
