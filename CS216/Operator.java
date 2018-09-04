package application;

public abstract class Operator {
	Operator() {}
	abstract void evaluate(double left, double right);
	abstract int getPrecedence();
}
class AddOp extends Operator {
	int precedence;
	AddOp() {
		precedence = 1;
	}
	void evaluate(double left, double right) {
		
	}
	int getPrecedence() {
		return precedence;
	}
}
