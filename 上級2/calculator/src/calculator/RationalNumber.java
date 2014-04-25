package calculator;

/**
 * 有理数を表現するクラス.
 */
public class RationalNumber{
    public int numerator;
    public int denominator;

    /**
     * intのみを入力として受け付けるコンストラクタ.
     */
    public RationalNumber(int number){
        this.numerator = number;
        this.denominator = 1;
    }

    /**
     * 分子、分母を入力として受け取るコンストラクタ.
     * 入力後約分を行う.
     */
    public RationalNumber(int numerator, int denominator){
        this.numerator = numerator;
        this.denominator = denominator;
        reduction();
    }

    /**
     * 少数を入力として受け取るコンストラクタ.
     * 有理数に変換を行う
     */
    public RationalNumber(double number){
        int count = 0;
        while(!isInteger(number)){
            number *= 10;
            count++;
        }
        this.numerator = (int)number;
        this.denominator = (int)(Math.pow(10, count));
        reduction();
    }

    // Public methods
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 有理数同士の足し算.
     */
    public RationalNumber plus(RationalNumber other){
        // 通分
        if(this.denominator != other.denominator){
            this.numerator *= other.denominator;
            other.numerator *= this.denominator;
            this.denominator *= other.denominator;
        }
        this.numerator += other.numerator;
        // 約分
        reduction();
        return this;
    }

    /**
     * 有理数同士の引き算.
     */
    public RationalNumber minus(RationalNumber other){
        // 通分
        if(this.denominator != other.denominator){
            this.numerator *= other.denominator;
            other.numerator *= this.denominator;
            this.denominator *= other.denominator;
        }
        // 引き算
        this.numerator -= other.numerator;
        // 約分
        reduction();
        return this;
    }

    /**
     * 有理数同士の掛け算.
     */
    public RationalNumber multiply(RationalNumber other){
        this.numerator *= other.numerator;
        this.denominator *= other.denominator;
        // 約分
        reduction();
        return this;
    }

    /**
     * 有理数同士の割り算.
     */
    public RationalNumber divide(RationalNumber other){
        this.numerator *= other.denominator;
        this.denominator *= other.numerator;
        // 約分
        reduction();
        return this;
    }

    /**
     * 有理数のString表現を行う.
     * trueを受け取ると少数で表現を行い、
     * falseを受け取ると分数で表現する
     */
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

    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RationalNumber otherNum = (RationalNumber)obj;
        if (numerator == otherNum.numerator && denominator == otherNum.denominator) {
            return true;
        } else {
            return false;
        }
    }

    // Private methods
    ///////////////////////////////////////////////////////////////////////////
    
    /**
     * 約分を行う関数.
     */
    private void reduction(){
        int gcd = gcd(numerator, denominator);
        this.numerator /= gcd;
        this.denominator /= gcd;
    }

    /**
     * 最大公約数を求める関数.
     */
    private int gcd(int m, int n){
        if (n == 0) {
            return m;
        } else {
            return gcd(n, m % n);
        }
    }

    /**
     * doubleで入力された値がintにキャストした値と等しいかチェックする関数.
     */
    private boolean isInteger(double value){
        return ((value == Math.floor(value)) && !Double.isInfinite(value));
    }
}
