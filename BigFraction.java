import java.math.*;

// BigFraction is a class designed to handle fractions that are created by strings
// and done to the specifications of this: http://cs.gettysburg.edu/~tneller/cs112/bigfraction/BigFraction.html
public class BigFraction {
	public static final BigFraction ONE;
	public static final BigFraction ZERO;
	private BigInteger numerator;
	private BigInteger denominator;
	
	// simplifies the fraction and makes the denominator non-negative
	public BigFraction(BigInteger numerator, BigInteger denominator) {
		this.numerator = numerator.divide(numerator.gcd(denominator));
		this.denominator = denominator.divide(numerator.gcd(denominator).abs());
	}
	
	// takes the string that is the "BigFraction" and separates it into two BigIntegers
	public BigFraction(String s) {
		int l = s.length();
		int slash = 0;
		for(int i = 0; i < l; ++i) {
			if (s.charAt(i) == '/') {
				slash = i;
			}
		}
		numerator   = new BigInteger(s.substring(0, slash - 1));
		denominator = new BigInteger(s.substring(slash + 1, l));
	}
	
	// converts long values into a string so it can be used as BigIntegers/BigFractions
	public BigFraction(long numerator, long denominator) {
		String n = String.valueOf(numerator);
		String d = String.valueOf(denominator);
	}
	
	public BigFraction(BigFraction f) {
		numerator = f.numerator;
		denominator = f.denominator;
	}
	
	// get methods
	public BigInteger getNum() {
		return numerator;
	}
	public BigInteger getDen() {
		return denominator;
	}
	
	// toString
	public String toString() {
		String s = numerator.toString() + "/" + denominator.toString();
		return s;
	}
	
	public BigDecimal asBigDecimal(int scale, int roundingMode) {
		BigDecimal top = new BigDecimal(numerator).setScale(scale, roundingMode);
		BigDecimal bottom = new BigDecimal(denominator).setScale(scale, roundingMode);
		return top / bottom;
	
	public BigFraction negate() {
		return BigFraction(numerator.negate(), denominator);
	}
	
	public BigFraction add(BigFraction b) { 
		BigInteger top1 = numerator.multiply(b.denominator);
		BigInteger top2 = denominator.multiply(b.numerator);
		BigInteger topfinal = top1.add(top2);
		BigInteger bottom = denominator.multiply(denominator);
		BigFraction sum = new BigFraction(topfinal, bottom);
		return sum;
	}
	
	public BigFraction subtract(BigFraction b) {
		BigInteger top1 = numerator.multiply(b.denominator);
		BigInteger top2 = denominator.multiply(b.numerator);
		BigInteger topfinal = top1.subtract(top2);
		BigInteger bottom = denominator.multiply(denominator);
		BigFraction difference = new BigFraction(topfinal, bottom);
		return difference;
	}
	
	public BigFraction multiply(BigFraction b) {
		BigInteger top = numerator.multiply(b.numerator);	
		BigInteger bottom = denominator.multiply(b.denominator);
		BigFraction product = new BigFraction(top, bottom);
		return product;
	}
	
	public BigFraction divide(BigFraction b) {
		BigInteger top = numerator.multiply(b.denominator);	
		BigInteger bottom = denominator.multiply(b.numerator);
		BigFraction quotient = new BigFraction(top, bottom);
		return quotient;
	}
}
