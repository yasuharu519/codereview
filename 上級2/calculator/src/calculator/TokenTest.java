package calculator;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TokenTest {
    
    @Test
    public void TokenTypeのみのコンストラクタが正しく動くこと() {
        Token tokenNumber = new Token(TokenType.NUMBER);    
        Token tokenPlus = new Token(TokenType.PLUS);    
        Token tokenMinus = new Token(TokenType.MINUS);    
        Token tokenMultiply = new Token(TokenType.MULTIPLY);    
        Token tokenDivide = new Token(TokenType.DIVIDE);    
        Token tokenRParen = new Token(TokenType.R_PAREN);    
        Token tokenLParen = new Token(TokenType.L_PAREN);    

        assertTrue(tokenNumber.getTokenType() == TokenType.NUMBER);
        assertTrue(tokenPlus.getTokenType() == TokenType.PLUS);
        assertTrue(tokenMinus.getTokenType() == TokenType.MINUS);
        assertTrue(tokenMultiply.getTokenType() == TokenType.MULTIPLY);
        assertTrue(tokenDivide.getTokenType() == TokenType.DIVIDE);
        assertTrue(tokenRParen.getTokenType() == TokenType.R_PAREN);
        assertTrue(tokenLParen.getTokenType() == TokenType.L_PAREN);
    }

    @Test
    public void TokenTypeとカッコの数を設定するコンストラクタが正しく動くこと() {
        Token tokenNumber = new Token(TokenType.NUMBER, 1);    
        
        assertThat(tokenNumber.getParenNum(), is(1));
    }

    @Test
    public void TokenTypeと保持する数を設定するコンストラクタ() {
        Token tokenNumber = new Token(TokenType.NUMBER, 10.0);

        assertThat(tokenNumber.getValue(), is(10.0));
    }

    @Test
    public void カッコなしの場合で優先度の大小関係が正しく設定されていること() {
        Token tokenNumber = new Token(TokenType.NUMBER);    
        Token tokenRParen = new Token(TokenType.R_PAREN);    
        Token tokenLParen = new Token(TokenType.L_PAREN);    
        Token tokenPlus = new Token(TokenType.PLUS);    
        Token tokenMinus = new Token(TokenType.MINUS);    
        Token tokenMultiply = new Token(TokenType.MULTIPLY);    
        Token tokenDivide = new Token(TokenType.DIVIDE);    

        assertTrue(tokenDivide.compareTo(tokenMultiply) == 0);
        assertTrue(tokenMultiply.compareTo(tokenMinus) > 0);
        assertTrue(tokenMinus.compareTo(tokenPlus) == 0);
        assertTrue(tokenPlus.compareTo(tokenNumber) > 0);
        assertTrue(tokenLParen.compareTo(tokenRParen) == 0);
        assertTrue(tokenRParen.compareTo(tokenNumber) == 0);
    }

    public void カッコありの場合で優先度の大小関係が正しく設定されていること() {
        Token tokenPlus = new Token(TokenType.PLUS);    
        Token tokenPlusParen1 = new Token(TokenType.PLUS, 1);
        Token tokenPlusParen2 = new Token(TokenType.PLUS, 2);
        Token tokenMultiply = new Token(TokenType.MULTIPLY);
        Token tokenMultiplyParen1 = new Token(TokenType.MULTIPLY, 1);

        assertTrue(tokenPlusParen2.compareTo(tokenPlusParen1) > 0);
        assertTrue(tokenPlusParen1.compareTo(tokenPlus) > 0);

        assertTrue(tokenMultiplyParen1.compareTo(tokenMultiply) > 0);
        assertTrue(tokenMultiplyParen1.compareTo(tokenPlusParen1) > 0);
        assertTrue(tokenPlusParen2.compareTo(tokenMultiplyParen1) > 0);
    }
}
