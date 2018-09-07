package application;

public class EqOp extends Operator{
	protected int precedence;
	public EqOp() {
		precedence = -2;
	}
	@Override
	public double evaluate(double left, double right) {
		throw new UnsupportedOperationException();
	}

	@Override
	int getPrecedence() {
		return precedence;
	}
	
}
