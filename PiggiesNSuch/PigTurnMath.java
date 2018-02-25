import java.math.BigDecimal;

public class PigTurnMath {
	
	public static BigFraction expectedTurnScore(int holdAt) {
		BigFraction[] e = new BigFraction[holdAt];
		for (int i = holdAt - 1; i >= 0; --i) {
			for (int count = 1; count <= 6; ++count) {
				BigFraction oof = new BigFraction(i + count, 6);
				e[i] = e[i].add(oof);
			}
		}
		return null;
	}
	public static void main(String[] args) {
		int holdAt = 100;
		BigFraction frac = expectedTurnScore(holdAt);
		System.out.printf("The expected score of a Pig turn holding at or above %d is approximately %f and exactly %s. \n", holdAt, frac.asBigDecimal(6, BigDecimal.ROUND_HALF_UP).doubleValue(), frac);
	}
}
