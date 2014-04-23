package calculator;

public class Calculator {

    public static void calculate(String fomula, boolean doubleFlag){
        if (doubleFlag) {
            // 結果をdoubleで返す
        } else {
            // 結果を分数で返す
        }
    }

    private static double traverseTree(Node root) throws IllegalSyntaxException{
        if (root instanceof ValueNode){
            return ((ValueNode)root).getValue();
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
                    throw new IllegalSyntaxException();
            }
        } else{
            throw new Exception(); // TODO: Nodeクラスがセットされている場合エラー
        }
    }

    public static void main(String[] args) {
        if (args.length > 1) {
            String option = args[0];
            if (option == "-d") {
                Calculator.calculate(args[1], true);
            }else{
                System.out.println("オプションが無効です);
                return;
            }
        }else{
            Calculator.calculate(args[0], false);
        }
    }
}
