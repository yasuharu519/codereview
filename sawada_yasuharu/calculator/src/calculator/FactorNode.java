package calculator;

public class FactorNode extends Node{
    private final double value;

    public FactorNode(double value){
        this.value = value;
    }

    public double getValue(){
        return this.value;
    }
}
