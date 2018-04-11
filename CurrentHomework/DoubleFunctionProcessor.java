import java.util.function.DoubleFunction;
public class DoubleFunctionProcessor {
	double initValue;
	DoubleFunctionProcessor(double initValue) {
		this.initValue = initValue;
	}
	
	double getValue() {
		return initValue;
	}
	
	void process(DoubleFunction<Double> function) {
		function.apply(initValue);
	}
}
