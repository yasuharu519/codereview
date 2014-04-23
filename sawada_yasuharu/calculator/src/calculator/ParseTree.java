package calculator;

import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class ParseTree {
    private String inputStr;
    private Node root;

    public ParseTree(String inputStr){
        this.inputStr = inputStr;
        // トークン化
        List<Token> tokens = tokenize(inputStr);
        tokens = removeParenTokens(tokens);
        // ツリーの作成
        try {
            root = makeParseTree(tokens);
            System.out.println(traverseTree(root));
        } catch (Exception e) {
            System.out.println(e); // TODO: シンタックスがおかしい場合エラー
        }
    }

    private double traverseTree(Node root) throws Exception{
        if (root instanceof FactorNode){
            return ((FactorNode)root).getValue();
        } else if (root instanceof ExpressionNode) {
            ExpressionNode node = (ExpressionNode)root;
            TokenType type = node.getTokenType();
            Node lhs = node.getLeftNode();
            Node rhs = node.getRightNode();
            switch (type) {
                case PLUS:
                    return traverseTree(lhs) + traverseTree(rhs);
                case MINUS:
                    return traverseTree(lhs) - traverseTree(rhs);
                case MULTIPLY:
                    return traverseTree(lhs) * traverseTree(rhs);
                case DIVIDE:
                    return traverseTree(lhs) / traverseTree(rhs);
                default:
                    throw new Exception(); // TODO: シンタックスエラー
            }
        } else{
            throw new Exception(); // TODO: Nodeクラスがセットされている場合エラー
        }
    }

    private Node makeParseTree(List<Token> tokens) throws Exception{//{{{
        if (tokens.size() == 1){
            Token token = tokens.get(0);
            if (token.getTokenType() == TokenType.NUMBER){
                return new FactorNode(token.getValue());
            } else {
                throw new Exception(); //TODO: 数字じゃない場合エラー
            }
        }
        int idx = findMinimumPriorityTokenIndex(tokens);
        Token token = tokens.get(idx);
        List<Token> lhsList = tokens.subList(0, idx);
        List<Token> rhsList = tokens.subList(idx + 1, tokens.size());
        Node lhsNode = makeParseTree(lhsList);
        Node rhsNode = makeParseTree(rhsList);
        ExpressionNode node = new ExpressionNode(token.getTokenType(),
            lhsNode, rhsNode);
        return node;
    }//}}}

    private int findMinimumPriorityTokenIndex(List<Token> tokens){//{{{
        // 0以外で優先度の低いオペランドの位置を取ってくる
        int idx = -1;
        int priority = 0;
        
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            if (token.getPriority() == 0) {
                continue;
            }
            if (priority == 0 || priority > token.getPriority()) {
                priority = token.getPriority();
                idx = i;
            }
        }
        return idx;
    }//}}}

    private List<Token> removeParenTokens(List<Token> tokens){//{{{
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            TokenType type = token.getTokenType();
            if (type == TokenType.L_PAREN || type == TokenType.R_PAREN){
                tokens.remove(i);
                i--;
            }
        }
        return tokens;
    }//}}}

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
            List<Token> list = new LinkedList<Token>();

            int parenNum = 0;
            LOOP: for(;;){
                int tt = st.nextToken();
                switch(tt){
                    case '-': // マイナスだけここに作成
                        list.add(new Token(TokenType.MINUS, parenNum));
                        break;
                    case StreamTokenizer.TT_NUMBER:{
                        list.add(new Token(TokenType.NUMBER, st.nval));
                        break;
                    }
                    case StreamTokenizer.TT_WORD:{
                        String word = st.sval;
                        for (char c : word.toCharArray()) {//{{{
                            switch(c){//{{{
                                case '+':{
                                    list.add(new Token(TokenType.PLUS, parenNum));
                                    break;
                                }
                                case '*':{
                                    list.add(new Token(TokenType.MULTIPLY, parenNum));
                                    break;
                                }
                                case '/':{
                                    list.add(new Token(TokenType.DIVIDE, parenNum));
                                    break;
                                }
                                case '(':{
                                    parenNum++;
                                    list.add(new Token(TokenType.L_PAREN, parenNum));
                                    break;
                                }
                                case ')':{
                                    parenNum--;
                                    // TODO: ミスマッチの補足
                                    list.add(new Token(TokenType.R_PAREN, parenNum));
                                    break;
                                }
                                default:{
                                    break;
                                }
                            }//}}}
                        }//}}}
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
        String testStr = "123 - ( 23 + ( 3 / 2)) - 10";
        System.out.println("testStr = " + testStr);
        ParseTree tree = new ParseTree(testStr);
    }
}
