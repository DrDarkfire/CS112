package application;

public class PowOp extends Operator{
	protected int precedence;
	public PowOp() {
		precedence = 3;
	}
	@Override
	public double evaluate(double left, double right) {
		return Math.pow(left, right);
		
	}

	@Override
	public int getPrecedence() {
		return precedence;
	}

}
