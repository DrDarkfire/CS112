package application;

public class MulOp extends Operator{
	protected int precedence;
	public MulOp() {
		precedence = 2;
	}
	@Override
	public double evaluate(double left, double right) {
		return left * right;
		
	}

	@Override
	public int getPrecedence() {
		// TODO Auto-generated method stub
		return precedence;
	}

}
