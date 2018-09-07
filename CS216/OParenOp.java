package application;

public class OParenOp extends Operator{
	protected int precedence;
	public OParenOp() {
		precedence = 4;
	}
	
	@Override
	public double evaluate(double left, double right) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getPrecedence() {
		return precedence;
	}
	
}
