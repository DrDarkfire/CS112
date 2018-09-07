package application;

public class AddOp extends Operator{
	protected int precedence;
	public AddOp() {
		precedence = 1;
	}
	public double evaluate(double left, double right) {
		 return left + right;
		// answer needs to be put on top
	}
	public int getPrecedence() {
		return precedence;
	}
}
