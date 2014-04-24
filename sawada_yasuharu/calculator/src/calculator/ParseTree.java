package calculator;

import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.List;
import java.util.LinkedList;
import java.io.IOException;

public class ParseTree {

    // Public methods
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 入力を受け取り、解析木を作成した後に根Nodeを返す.
     */
    public static Node buildParseTree(String inputStr) throws IllegalSyntaxException{
        // 解析してトークン化
        List<Token> tokens = tokenize(inputStr);
        // カッコを表すトークンを削除
        tokens = removeParenTokens(tokens);
        // ツリーの作成
        return makeParseTree(tokens);
    }

    /**
     * 入力からTokenのリストを返す.
     * トークン作成時にシンタックスエラーがある場合はIllegalSyntaxExceptionを投げる
     * @throws IllegalSyntaxException 数式のシンタックスにエラーがある場合
     */
    public static List<Token> tokenize(String str) throws IllegalSyntaxException{
        // 前処理
        String checkStr = str.replaceAll("\\(", " ( ");
        checkStr = checkStr.replaceAll("\\)", " ) ");
        // StreamTokenizerの設定
        StreamTokenizer st = new StreamTokenizer(new StringReader(checkStr));
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

        // カッコの数のカウンタ
        int parenNum = 0;
        try{
            OUTER: for(;;){
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
                        for (char c : word.toCharArray()) {
                            switch(c){
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
                                    if (parenNum < 0)
                                        throw new IllegalSyntaxException("カッコの数が一致していません");
                                    list.add(new Token(TokenType.R_PAREN, parenNum));
                                    break;
                                }
                                default:{
                                    throw new IllegalSyntaxException("不正な文字が入力されています");
                                }
                            }
                        }
                        break;
                    }
                    case StreamTokenizer.TT_EOF:{
                        break OUTER;
                    }
                    default:{
                        throw new IllegalSyntaxException("不正な文字が入力されています");
                    }
                }
            }
        } catch (IOException e){
            System.out.println("I/Oエラーが発生しました");
            System.exit(-1);
        }
        return list;
    }

    // Private methods
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Tokenのリストを受け取り、優先度の一番低いTokenを中心に木構造を作成していく.
     * 一番根のノードを返す
     * @throws IllegalSyntaxException 数式のシンタックスが不正な場合
     */
    private static Node makeParseTree(List<Token> tokens) throws IllegalSyntaxException{
        if (tokens.size() == 1){
            Token token = tokens.get(0);
            if (token.getTokenType() == TokenType.NUMBER){
                double value = token.getValue();
                // 整数かどうかチェック(少数の場合今回はシンタックスエラーにする)
                if((value == Math.floor(value)) && !Double.isInfinite(value)){
                    return new ValueNode(new RationalNumber((int)value));
                } else {
                    throw new IllegalSyntaxException("入力は整数で入力してください"); 
                }
            } else {
                // 数式の最後がオペランドで終わっているような場合
                throw new IllegalSyntaxException("数式の入力形式が不正です"); 
            }
        }
        int idx = findMinimumPriorityTokenIndex(tokens);
        Token token = tokens.get(idx);
        if (token.getTokenType() == TokenType.L_PAREN || 
            token.getTokenType() == TokenType.R_PAREN ||
            token.getTokenType() == TokenType.NUMBER) {
            throw new IllegalSyntaxException("数式の入力形式が不正です"); 
        }
        List<Token> lhsList = tokens.subList(0, idx);
        List<Token> rhsList = tokens.subList(idx + 1, tokens.size());
        Node lhsNode = makeParseTree(lhsList);
        Node rhsNode = makeParseTree(rhsList);
        ExpressionNode node = new ExpressionNode(token.getTokenType(), lhsNode, rhsNode);
        return node;
    }

    /**
     * 数字とカッコ以外のTokenの中で優先度の一番低いTokenのインデックスを返す.
     */
    private static int findMinimumPriorityTokenIndex(List<Token> tokens){
        // 0以外で優先度の低いオペランドの位置を取ってくる
        int idx = -1;
        Token lasttoken = null;
        
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            if (token.getTokenType().getPriority() == 0) {
                continue;
            }
            if (lasttoken == null || lasttoken.compareTo(token) > 0) {
                lasttoken = token;
                idx = i;
            }
        }
        return idx;
    }

    /**
     * カッコを除いたリストを返す.
     */
    private static List<Token> removeParenTokens(List<Token> tokens){
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            TokenType type = token.getTokenType();
            if (type == TokenType.L_PAREN || type == TokenType.R_PAREN){
                tokens.remove(i);
                i--;
            }
        }
        return tokens;
    }
}
