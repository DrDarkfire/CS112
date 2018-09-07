package application;

public class DivOp extends Operator{
	protected int precedence;
	public DivOp() {
		precedence = 2;
	}
	@Override
	public double evaluate(double left, double right) {
		return left / right;
		// push to stack
	}

	@Override
	public int getPrecedence() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
