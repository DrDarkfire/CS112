package application;

public class SubOp extends Operator{
	protected int precedence;
	public SubOp() {
		precedence = 1;
	}
	
	@Override
	public double evaluate(double left, double right) {
		return left - right;
		// answer needs to be put on top of number stack
	}

	@Override
	public int getPrecedence() {
		// TODO Auto-generated method stub
		return precedence;
	}

}
