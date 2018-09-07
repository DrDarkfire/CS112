package application;

public class CParenOp extends Operator{
	protected int precedence;
	public CParenOp() {
		precedence = -1;
	}
	@Override
	public double evaluate(double left, double right) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getPrecedence() {
		// TODO Auto-generated method stub
		return precedence;
	}

}
