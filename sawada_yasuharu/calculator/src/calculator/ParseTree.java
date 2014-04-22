package calculator;

import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;

public class ParseTree {
    private String inputStr;
    private ExpressionNode root;

    public ParseTree(String inputStr){
        this.inputStr = inputStr;
        // トークン化
        List<Token> tokens = tokenize(inputStr);
        // ツリーの作成
//         makeTree(tokens);
    }

    private ExpressionNode makeTree(String[] tokens){
    }

    public List<Token> tokenize(String str){//{{{
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
            List<Token> list = new ArrayList<Token>();
            LOOP: for(;;){
                int tt = st.nextToken();
                switch(tt){
                    case '-':
                        list.add(new Token(TokenType.MINUS));
                        break;
                    case StreamTokenizer.TT_NUMBER:{
                        list.add(new Token(TokenType.NUMBER, nval));
                        break;
                    }
                    case StreamTokenizer.TT_WORD:{
                        switch(st.val){
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
            return list.toArray(new String[list.size()]);
        } catch (Exception e) {
            return null;
        }
    }//}}}

    public static void main(String[] args) {
        String testStr = "123 * ( 23 + ( 3 / 2)) - 10";
        System.out.println("testStr = " + testStr);
        ParseTree ptree = new ParseTree(testStr);

        String[] args2 = ptree.tokenize(testStr);
        for (String s : args2) {
            System.out.println(s);
        }
    }

}
