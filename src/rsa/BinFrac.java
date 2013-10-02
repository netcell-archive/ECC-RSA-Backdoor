
/* NGUYEN TUAN ANH - 20090131
 * TT1.K54.SAMI.HUST.HN.VN
 * DO AN 1 - SEMESTER 20112
 * HTTP://ANHNTCV.CO.CC
 */

package rsa;

public class BinFrac{
    
    public static final BinFrac ONE  = new BinFrac(BinNum.ONE,BinNum.ONE);
    public static final BinFrac ZERO = new BinFrac(BinNum.ZERO,BinNum.ONE);
    
    private BinNum numerator;
    private BinNum denominator;
    
    public int compareTo(BinFrac that){
        if (this.denominator.compareTo(that.denominator)==0){
            if(this.numerator.compareTo(that.numerator)==0) return 0;
            else return this.numerator.compareTo(that.numerator);
        }
        BinNum a = this.numerator.multiply(that.denominator);
        BinNum b = that.numerator.multiply(this.denominator);
        return a.compareTo(b);
    }
    
    public String toDec(){
        return numerator.toDec()+"/"+denominator.toDec();
    }
    
    public BinFrac inverse(){
        return new BinFrac(denominator,numerator);
    }
    
    public BinNum intUnderestimate(){
        return numerator.divide(denominator)[1];
    }
    
    public BinFrac add(BinNum that){
        return new BinFrac(numerator.add(that.multiply(denominator)),denominator).simplify();
    }
    
    public BinFrac add(BinFrac that){
        return new BinFrac(
                this.numerator.multiply(that.denominator).add(
                that.numerator.multiply(this.denominator))
                ,this.denominator.multiply(that.denominator)).simplify();
    }
    
    public BinFrac subtract(BinNum that){
        return new BinFrac(numerator.subtract(that.multiply(denominator)),denominator).simplify();
    }
    
    public BinFrac subtract(BinFrac that){
        return new BinFrac(
                this.numerator.multiply(that.denominator).subtract(
                that.numerator.multiply(this.denominator))
                ,this.denominator.multiply(that.denominator)).simplify();
    }
    
    public BinFrac multiply(BinFrac that){
        return new BinFrac(
                this.numerator.multiply(that.numerator),
                this.denominator.multiply(that.denominator)).simplify();
    }
    
    public BinFrac divide(BinFrac that){
        return this.multiply(that.inverse());
    }
    
    public BinFrac simplify(){
        BinNum gcd = this.numerator.GCD(this.denominator);
        if(gcd.compareTo(BinNum.ONE)!=0){
            this.numerator = this.numerator.divide(gcd)[1];
            this.denominator = this.denominator.divide(gcd)[1];
        }
        return this;
    }

    public BinFrac(BinNum numerator, BinNum denominator) {
        this.numerator = new BinNum(numerator);
        this.denominator = new BinNum(denominator);
        this.simplify();
    }
    
    public BinFrac(BinFrac that){
        this.numerator = new BinNum(that.numerator);
        this.denominator = new BinNum(that.denominator);
    }

    public BinNum getDenominator() {
        return new BinNum(denominator);
    }

    public void setDenominator(BinNum denominator) {
        this.denominator = new BinNum(denominator);
        this.simplify();
    }

    public BinNum getNumerator() {
        return new BinNum(numerator);
    }

    public void setNumerator(BinNum numerator) {
        this.numerator = new BinNum(numerator);
        this.simplify();
    }
    
    
    
}
