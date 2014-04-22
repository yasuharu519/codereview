package poker;

public class ZeroInputException extends Exception{
    public ZeroInputException(){
        super();
    }
    public ZeroInputException(String str){
        super(str);
    }
}
