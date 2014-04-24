package calculator;

public class Calculator {

    public static String calculate(String fomula, boolean doubleFlag){
        try{
            Node root = ParseTree.buildParseTree(fomula);
            RationalNumber result = traverseTree(root);
            return result.toString(doubleFlag);
        } catch (IllegalSyntaxException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static RationalNumber traverseTree(Node root) {
        if (root instanceof ValueNode){
            return ((ValueNode)root).getValue();
        } else if (root instanceof ExpressionNode) {
            ExpressionNode node = (ExpressionNode)root;
            TokenType type = node.getTokenType();
            Node lhs = node.getLeftNode();
            Node rhs = node.getRightNode();
            switch (type) {
                case PLUS:
                    return traverseTree(lhs).plus(traverseTree(rhs));
                case MINUS:
                    return traverseTree(lhs).minus(traverseTree(rhs));
                case MULTIPLY:
                    return traverseTree(lhs).multiply(traverseTree(rhs));
                case DIVIDE:
                    return traverseTree(lhs).divide(traverseTree(rhs));
                default:
                    throw new RuntimeException();
            }
        } else{
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
        String str = "(1 + 2 * 3 + (4 + 3)) / 222";
        String result = Calculator.calculate(str, false);

        System.out.println(result);
//         if (args.length > 1) {
//             String option = args[0];
//             if (option.equals("-d")) {
//                 Calculator.calculate(args[1], true);
//             }else{
//                 System.out.println("オプションが無効です");
//                 return;
//             }
//         }else{
//             Calculator.calculate(args[0], false);
//         }
    }
}
