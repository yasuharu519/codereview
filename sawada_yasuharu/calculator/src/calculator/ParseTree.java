package calculator;

import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;

public class ParseTree {
    private String inputStr;

    public ParseTree(String inputStr){
        this.inputStr = inputStr;
    }

    public String[] tokenize(String str){//{{{
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
            ArrayList<String> list = new ArrayList();
            LOOP: for(;;){
                int tt = st.nextToken();
                switch(tt){
                    case '-':
                        list.add(String.format("%c", '-'));
                        break;
                    case StreamTokenizer.TT_NUMBER:{
                        list.add(String.format("%f", st.nval));
                        break;
                    }
                    case StreamTokenizer.TT_WORD:{
                        list.add(st.sval);
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
