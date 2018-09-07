package application;

public abstract class Operator {
	Operator() {}
	abstract double evaluate(double left, double right);
	abstract int getPrecedence();
}