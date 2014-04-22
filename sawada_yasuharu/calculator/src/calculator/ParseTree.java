package calculator;

import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ParseTree {
    private String inputStr;
    private Node root;
    private Queue<Token> tokens;

    public ParseTree(String inputStr){
        this.inputStr = inputStr;
        // トークン化
        tokens = tokenize(inputStr);
        // ツリーの作成
        root = getExpression();
    }

    private Node getExpression(){//{{{
        Node lhs, rhs;
        lhs = getTerm();

        Token token = tokens.poll();
        if(token == null){
            throw new Exception() // TODO: エラー！
        }

    }//}}}

    private Node getTerm(){//{{{
        Node lhs, rhs;

        lhs = getFactor();
    }//}}}

    private Node getFactor(){//{{{
        Node node;
        
        if(!tokens.isEmpty()){
            Token token = tokens.poll();
            switch (token.type) {
                TokenType.L_PAREN://{{{
                    node = getExpression();
                    Token token2 = tokens.poll();
                    if(token2 == null){
                        throw new Exception(); // TODO: 新しいExceptionを作成
                    }else if(token2.type == TokenType.R_PAREN){
                        return node;
                    }else{
                        throw new Exception(); // TODO: 新しいExceptionを作成
                    }
                    break;//}}}
                TokenType.NUMBER:
                    return new FactorNode(token.nval);
                default:
                    throw new Exception(); // TODO: 新しいExceptionを作成
            }
        }
    }//}}}

    public Queue<Token> tokenize(String str){//{{{
        try{
            StreamTokenizer st = new StreamTokenizer(new StringReader(str));
            st.resetSyntax();
            st.parseNumbers();
            st.whitespaceChars('\t', '\t');
            st.whitespaceChars(' ', ' ');
            st.wordChars('+', '+');
            st.wordChars('-', '-');
            st.wordChars('*', '*');
            st.wordChars('/', '/');
            st.wordChars('(', '(');
            st.wordChars(')', ')');
            Queue<Token> list = new LinkedList<Token>();
            LOOP: for(;;){
                int tt = st.nextToken();
                switch(tt){
                    case '-':
                        list.add(new Token(TokenType.MINUS));
                        break;
                    case StreamTokenizer.TT_NUMBER:{
                        list.add(new Token(TokenType.NUMBER, st.nval));
                        break;
                    }
                    case StreamTokenizer.TT_WORD:{
                        switch(st.sval){
                            case "+":{
                                list.add(new Token(TokenType.PLUS));
                                break;
                            }
                            case "-":{
                                list.add(new Token(TokenType.MINUS));
                                break;
                            }
                            case "*":{
                                list.add(new Token(TokenType.MULTIPLY));
                                break;
                            }
                            case "/":{
                                list.add(new Token(TokenType.DIVIDE));
                                break;
                            }
                            case "(":{
                                list.add(new Token(TokenType.L_PAREN));
                                break;
                            }
                            case ")":{
                                list.add(new Token(TokenType.R_PAREN));
                                break;
                            }
                            default:{
                                break;
                            }
                        }
                        break;
                    }
                    case StreamTokenizer.TT_EOF:{
                        break LOOP;
                    }
                    default:{
                        break;
                    }
                }
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }//}}}

    public static void main(String[] args) {
        String testStr = "123 * ( 23 + ( 3 / 2)) - 10";
        System.out.println("testStr = " + testStr);
        ParseTree ptree = new ParseTree(testStr);

        Queue<Token> tokens = ptree.tokenize(testStr);
        for (Token token : tokens) {
            System.out.println(token.type);
        }
    }

}
