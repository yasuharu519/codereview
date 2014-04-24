package calculator;

public class RationalNumber{
    public int numerator;
    public int denominator;

    public RationalNumber(int number){
        this.numerator = number;
        this.denominator = 1;
    }

    public RationalNumber(int numerator, int denominator){
        this.numerator = numerator;
        this.denominator = denominator;
        reduction();
    }

    public RationalNumber(double number){
        int count = 0;
        while(!isInteger(number)){
            number *= 10;
            count++;
        }
        this((int)number, (int)(Math.pow(10, count)));
    }

    // Public methods
    ///////////////////////////////////////////////////////////////////////////

    public RationalNumber plus(RationalNumber other){
        // 通分
        this.numerator *= other.denominator;
        this.denominator *= other.denominator;
        this.numerator += other.numerator;
        // 約分
        reduction();
        return this;
    }

    public RationalNumber minus(RationalNumber other){
        // 通分
        this.numerator *= other.denominator;
        this.denominator *= other.denominator;
        this.numerator -= other.numerator;
        // 約分
        reduction();
        return this;
    }

    public RationalNumber multiply(RationalNumber other){
        this.numerator *= other.numerator;
        this.denominator *= other.denominator;
        // 約分
        reduction();
        return this;
    }

    public RationalNumber divide(RationalNumber other){
        this.numerator *= other.denominator;
        this.denominator *= other.numerator;
        // 約分
        reduction();
        return this;
    }

    public String toString(boolean printInDouble){
        if (printInDouble) {
            return String.format("%f",
                    (double)this.numerator / this.denominator);
        } else {
            if (this.denominator == 1) {
                return String.format("%d", this.numerator);
            } else {
                return String.format("%d/%d", this.numerator, this.denominator);    
            }
        }
    }

    // Private methods
    ///////////////////////////////////////////////////////////////////////////
    private void reduction(){
        int gcd = gcd(numerator, denominator);
        this.numerator /= gcd;
        this.denominator /= gcd;
    }

    private int gcd(int m, int n){
        if (n == 0) {
            return m;
        } else {
            return gcd(n, m % n);
        }
    }

    private boolean isInteger(double value){
        return ((value == Math.floor(value)) && !Double.isInfinite(value))
    }
}
