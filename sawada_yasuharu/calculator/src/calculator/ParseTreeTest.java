package calculator;

import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ParseTreeTest {

    @Test
    public void 数字が一つだけ入力された場合正しく解析木が作成される() throws IllegalSyntaxException {
        Node root = ParseTree.buildParseTree("1");
        
        assertTrue(root instanceof ValueNode);
        assertThat(((ValueNode)root).getValue().toString(false), is("1"));
    }
    
    @Test
    public void 数字がカッコにかこまれて一つだけ入力された場合正しく解析木が作成される() throws IllegalSyntaxException {
        Node root = ParseTree.buildParseTree("(1)");
        
        assertTrue(root instanceof ValueNode);
        assertThat(((ValueNode)root).getValue().toString(false), is("1"));
    }

    @Test(expected=IllegalSyntaxException.class)
    public void 記号が一つだけ入力された場合エラーが投げられる() throws IllegalSyntaxException {
        Node root = ParseTree.buildParseTree("(");
    }

    @Test(expected=IllegalSyntaxException.class)
    public void カッコの数にミスマッチがあった場合エラーが投げられる() throws IllegalSyntaxException {
        Node root = ParseTree.buildParseTree("(1");
    }

    @Test(expected=IllegalSyntaxException.class)
    public void カッコの数にミスマッチがあった場合エラーが投げられる2() throws IllegalSyntaxException {
        Node root = ParseTree.buildParseTree("1+(1");
    }

    @Test
    public void 数字が一つだけ入力された場合トークン化が正しく行われる() throws IllegalSyntaxException {
        List<Token> tokens = ParseTree.tokenize("1");
        
        assertThat(tokens.size(), is(1));
        assertThat(tokens.get(0).getTokenType(), is(TokenType.NUMBER));
    }

    @Test
    public void カッコつきの数字が一つだけ入力された場合トークン化が正しく行われる() throws IllegalSyntaxException {
        List<Token> tokens = ParseTree.tokenize("(1)");
        
        assertThat(tokens.size(), is(3));
        assertThat(tokens.get(0).getTokenType(), is(TokenType.L_PAREN));
        assertThat(tokens.get(1).getTokenType(), is(TokenType.NUMBER));
        assertThat(tokens.get(2).getTokenType(), is(TokenType.R_PAREN));
    }

    @Test
    public void 二項の足し算が入力された場合トークン化が正しく行われる() throws IllegalSyntaxException {
        List<Token> tokens = ParseTree.tokenize("1+1");
        
        assertThat(tokens.size(), is(3));
        assertThat(tokens.get(0).getTokenType(), is(TokenType.NUMBER));
        assertThat(tokens.get(1).getTokenType(), is(TokenType.PLUS));
        assertThat(tokens.get(2).getTokenType(), is(TokenType.NUMBER));
    }

    @Test
    public void 二項の足し算が入力されスペースが混ざっていてもトークン化が正しく行われる() throws IllegalSyntaxException {
        List<Token> tokens = ParseTree.tokenize("1 + 1");
        
        assertThat(tokens.size(), is(3));
        assertThat(tokens.get(0).getTokenType(), is(TokenType.NUMBER));
        assertThat(tokens.get(1).getTokenType(), is(TokenType.PLUS));
        assertThat(tokens.get(2).getTokenType(), is(TokenType.NUMBER));
    }

    @Test
    public void 二項の引き算が入力された場合トークン化が正しく行われる() throws IllegalSyntaxException {
        List<Token> tokens = ParseTree.tokenize("1-1");
        
        assertThat(tokens.size(), is(3));
        assertThat(tokens.get(0).getTokenType(), is(TokenType.NUMBER));
        assertThat(tokens.get(1).getTokenType(), is(TokenType.MINUS));
        assertThat(tokens.get(2).getTokenType(), is(TokenType.NUMBER));
    }

    @Test
    public void 二項の引き算が入力されスペースが混ざっていてもトークン化が正しく行われる() throws IllegalSyntaxException {
        List<Token> tokens = ParseTree.tokenize("1 - 1");
        
        assertThat(tokens.size(), is(3));
        assertThat(tokens.get(0).getTokenType(), is(TokenType.NUMBER));
        assertThat(tokens.get(1).getTokenType(), is(TokenType.MINUS));
        assertThat(tokens.get(2).getTokenType(), is(TokenType.NUMBER));
    }

    @Test
    public void 二項の掛け算が入力された場合トークン化が正しく行われる() throws IllegalSyntaxException {
        List<Token> tokens = ParseTree.tokenize("1*1");
        
        assertThat(tokens.size(), is(3));
        assertThat(tokens.get(0).getTokenType(), is(TokenType.NUMBER));
        assertThat(tokens.get(1).getTokenType(), is(TokenType.MULTIPLY));
        assertThat(tokens.get(2).getTokenType(), is(TokenType.NUMBER));
    }

    @Test
    public void 二項の掛け算が入力されスペースが混ざっていてもトークン化が正しく行われる() throws IllegalSyntaxException {
        List<Token> tokens = ParseTree.tokenize("1 * 1");
        
        assertThat(tokens.size(), is(3));
        assertThat(tokens.get(0).getTokenType(), is(TokenType.NUMBER));
        assertThat(tokens.get(1).getTokenType(), is(TokenType.MULTIPLY));
        assertThat(tokens.get(2).getTokenType(), is(TokenType.NUMBER));
    }

    @Test
    public void 二項の割り算が入力された場合トークン化が正しく行われる() throws IllegalSyntaxException {
        List<Token> tokens = ParseTree.tokenize("1/1");
        
        assertThat(tokens.size(), is(3));
        assertThat(tokens.get(0).getTokenType(), is(TokenType.NUMBER));
        assertThat(tokens.get(1).getTokenType(), is(TokenType.DIVIDE));
        assertThat(tokens.get(2).getTokenType(), is(TokenType.NUMBER));
    }

    @Test
    public void 二項の割り算が入力されスペースが混ざっていてもトークン化が正しく行われる() throws IllegalSyntaxException {
        List<Token> tokens = ParseTree.tokenize("1 / 1");
        
        assertThat(tokens.size(), is(3));
        assertThat(tokens.get(0).getTokenType(), is(TokenType.NUMBER));
        assertThat(tokens.get(1).getTokenType(), is(TokenType.DIVIDE));
        assertThat(tokens.get(2).getTokenType(), is(TokenType.NUMBER));
    }

    @Test
    public void カッコと足し算が連続して入力された場合トークン化が正しく行われる() throws IllegalSyntaxException {
        List<Token> tokens = ParseTree.tokenize("1+(1)");
        
        assertThat(tokens.size(), is(5));
        assertThat(tokens.get(0).getTokenType(), is(TokenType.NUMBER));
        assertThat(tokens.get(1).getTokenType(), is(TokenType.PLUS));
        assertThat(tokens.get(2).getTokenType(), is(TokenType.L_PAREN));
        assertThat(tokens.get(3).getTokenType(), is(TokenType.NUMBER));
        assertThat(tokens.get(4).getTokenType(), is(TokenType.R_PAREN));
    }

    @Test
    public void カッコと引き算が連続して入力された場合トークン化が正しく行われる() throws IllegalSyntaxException {
        List<Token> tokens = ParseTree.tokenize("1-(1)");
        
        assertThat(tokens.size(), is(5));
        assertThat(tokens.get(0).getTokenType(), is(TokenType.NUMBER));
        assertThat(tokens.get(1).getTokenType(), is(TokenType.MINUS));
        assertThat(tokens.get(2).getTokenType(), is(TokenType.L_PAREN));
        assertThat(tokens.get(3).getTokenType(), is(TokenType.NUMBER));
        assertThat(tokens.get(4).getTokenType(), is(TokenType.R_PAREN));
    }

    @Test
    public void カッコと掛け算が連続して入力された場合トークン化が正しく行われる() throws IllegalSyntaxException {
        List<Token> tokens = ParseTree.tokenize("1*(1)");
        
        assertThat(tokens.size(), is(5));
        assertThat(tokens.get(0).getTokenType(), is(TokenType.NUMBER));
        assertThat(tokens.get(1).getTokenType(), is(TokenType.MULTIPLY));
        assertThat(tokens.get(2).getTokenType(), is(TokenType.L_PAREN));
        assertThat(tokens.get(3).getTokenType(), is(TokenType.NUMBER));
        assertThat(tokens.get(4).getTokenType(), is(TokenType.R_PAREN));
    }

    @Test
    public void カッコと割り算が連続して入力された場合トークン化が正しく行われる() throws IllegalSyntaxException {
        List<Token> tokens = ParseTree.tokenize("1/(1)");
        
        assertThat(tokens.size(), is(5));
        assertThat(tokens.get(0).getTokenType(), is(TokenType.NUMBER));
        assertThat(tokens.get(1).getTokenType(), is(TokenType.DIVIDE));
        assertThat(tokens.get(2).getTokenType(), is(TokenType.L_PAREN));
        assertThat(tokens.get(3).getTokenType(), is(TokenType.NUMBER));
        assertThat(tokens.get(4).getTokenType(), is(TokenType.R_PAREN));
    }

    @Test
    public void 開きカッコが連続して入力された場合トークン化が正しく行われる() throws IllegalSyntaxException {
        List<Token> tokens = ParseTree.tokenize("((1 ) )");
        
        assertThat(tokens.size(), is(5));
        assertThat(tokens.get(0).getTokenType(), is(TokenType.L_PAREN));
        assertThat(tokens.get(1).getTokenType(), is(TokenType.L_PAREN));
        assertThat(tokens.get(2).getTokenType(), is(TokenType.NUMBER));
        assertThat(tokens.get(3).getTokenType(), is(TokenType.R_PAREN));
        assertThat(tokens.get(4).getTokenType(), is(TokenType.R_PAREN));
    }

    @Test
    public void 閉じカッコが連続して入力された場合トークン化が正しく行われる() throws IllegalSyntaxException {
        List<Token> tokens = ParseTree.tokenize("( ( 1))");
        
        assertThat(tokens.size(), is(5));
        assertThat(tokens.get(0).getTokenType(), is(TokenType.L_PAREN));
        assertThat(tokens.get(1).getTokenType(), is(TokenType.L_PAREN));
        assertThat(tokens.get(2).getTokenType(), is(TokenType.NUMBER));
        assertThat(tokens.get(3).getTokenType(), is(TokenType.R_PAREN));
        assertThat(tokens.get(4).getTokenType(), is(TokenType.R_PAREN));
    }
}
