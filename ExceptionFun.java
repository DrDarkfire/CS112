import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionFun {

	private static Scanner in = new Scanner(System.in);
	public static void main(String[] arrghs) throws Exception {
		int failCount = 0;
		while (true) {
			try {
				tryDivide();
				break;
			}
			catch (MyException e) {
				failCount++;
				System.out.println(e.getMessage());
				if (failCount == 3) {
					System.out.println("Three strikes and you're out!");
					System.exit(1);
				}
				System.out.println("Let's try that again...");
			}
		}
	}
	
	private static void tryDivide() throws MyException {
		try {
			divide();
		}
		catch (ArithmeticException e) {
			//System.out.println("You cannot divide by 0.");
			throw new MyException("You cannot divide by 0.");
		}
		catch (InputMismatchException e) {
			//System.out.println("Integer input only, please.");
			in.nextLine();
			throw new MyException("Integer input only, please.");
		}
		catch (Exception e) {
			throw new MyException(e.getMessage());
		}
	}
	
	private static void divide() { //throws Exception {
		System.out.print("a? ");
		int a  = in.nextInt();
		System.out.print("b?");
		int b = in.nextInt();
		int quotient = a / b;
		System.out.println("a / b = " + quotient);
		//throw new Exception("message");
		
	}

}

class MyException extends Exception {
	public MyException(String message) {
		super(message);
	}
}
