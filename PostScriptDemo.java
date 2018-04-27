import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class PostScriptDemo { //PostScript stack machine demonstration
	private static String doubleRegex = "[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?"; // Java double regex
	private static String idRegex = "/?[A-Za-z_][A-Za-z0-9_]*"; // (PostScript identifiers are more general)
	private static String tokenRegex = String.format("(%s)|(%s)|[\\{\\}]", doubleRegex, idRegex);
	private static boolean verbose = true; // show stack and next token with each step
	private static Map<String, Object> map = new HashMap<String, Object>(); 
	private static Stack<Object> stack = new Stack<Object>();

	public static ArrayList<Object> getTokens(Scanner in) {
		ArrayList<Object> tokens = new ArrayList<Object>();
		// Read and return a list of tokens from the standard input.
		while (in.hasNext(tokenRegex)){
			String t = in.next(tokenRegex);
			if (t.matches(doubleRegex))
				tokens.add(Double.parseDouble(t));
			else
				tokens.add(t);
		}
		if (!in.hasNext(tokenRegex) && in.hasNext()) {
			System.err.println("Invalid token: " + in.next());
			System.exit(1);
		}
		return tokens;
	}

	@SuppressWarnings("unchecked")
	public static void interpret(List<Object> tokens) {
		// For each token, interpret (i.e. execute) the PostScript program
		for (Object t : tokens) {
			if (verbose)
				System.out.println("Stack: " + stack + " Token: " + t);
			if (t instanceof String) {
				String s = (String) t;
				if (s.equals("show"))
					System.out.println(stack.pop());
				else if (s.equals("add"))
					stack.push((Double)stack.pop() + (Double)stack.pop());
				else if (s.equals("sub"))
					stack.push((Double)stack.pop() - (Double)stack.pop());
				else if (s.equals("mul"))
					stack.push((Double)stack.pop() * (Double)stack.pop());
				else if (s.equals("div")) {
					Double divisor = (Double) stack.pop();
					stack.push((Double)stack.pop() / divisor);
				}
				else if (s.equals("eq"))
					stack.push(stack.pop().equals(stack.pop()));
				else if (s.equals("gt"))
					stack.push((Double) stack.pop() < (Double) stack.pop());
				else if (s.contains("and")) {
					Boolean b = (Boolean) stack.pop();
					stack.push((Boolean) stack.pop() && b);
				}
				else if (s.contains("or")) {
					Boolean b = (Boolean) stack.pop();
					stack.push((Boolean) stack.pop() || b);
				}
				else if (s.contains("not"))
					stack.push(!(Boolean) stack.pop());
				else if (s.charAt(0) == '/')
					stack.push(s);
				else if (s.equals("def")) {
					Object value = stack.pop();
					String literal = (String) stack.pop();
					map.put(literal.substring(1), value);
				}
				else {
					Object value = map.get(s);
					stack.push(value);
				}
			}
			else
				stack.push(t);
		}
	} 

	public static void main(String[] args) {
		System.out.println("Enter PostScript Program:");
		interpret(getTokens(new Scanner(System.in)));
	}
}

@SuppressWarnings("serial")
class LoopExitException extends RuntimeException {}

/*

Mini-PostScript Demo code: (copy and paste bits between % comment lines)

% print 1 + 2:

	1 2 add show

% if ( 1 == 2 ) { print 3 } else { print 4 }

	1 2 eq { 3 show } { 4 show } ifelse

% x = 1
% print 5 - x

	/x 1 def 5 x sub show

% define function incr(x) = x + 1; print incr(2)

	/incr { 1 add } def
	2 incr show

% recursive sum to n:

	/sumto { dup 0 eq { pop 0 } { dup 1 sub sumto add } ifelse } def
	5 sumto show

% iterative sum to n: put sentinel value -1, stack numbers to add, add down to token, remove token

	/sumtoiter { 
	-1 exch
	{ dup 0 eq { exit } { dup 1 sub } ifelse } loop
	{ exch dup -1 eq { pop exit } { add } ifelse } loop
	} def
	5 sumtoiter show

% iterative sum to n, single loop with temp variable sumtoiter_n,
%   trading stack places of sum and loop control variable: 

/sumtoiter {
  0 
  {
    exch dup 0 eq
    { pop exit }
    { dup /sumtoiter_n exch def 1 sub exch sumtoiter_n add }
    ifelse 
  } loop
} def
5 sumtoiter show

% iterative sum to n with multiple temporary variables:

/sumtoiter { /sumtoiter_n exch def /sumtoiter_sum 0 def sumtoiter_helper sumtoiter_sum } def
/sumtoiter_helper { { sumtoiter_n 0 eq 
                      { exit } 
                      { sumtoiter_sum sumtoiter_n add /sumtoiter_sum exch def 
                        sumtoiter_n 1 sub /sumtoiter_n exch def } 
                      ifelse } loop } def
3 sumtoiter show

% Recursive factorial
% (Factorial example from https://www.math.ubc.ca/~cass/graphics/manual/pdf/ch9.pdf)

/factorial { dup 0 eq { pop 1 } { dup 1 sub factorial mul } ifelse } def
5 factorial show

% Recursive Fibonacci followed by iterative sequence printing fib(iinit) through fib(istop - 1)
% (Fibonacci example adapted from https://en.wikipedia.org/wiki/Stack-oriented_programming_language)

  /fib
  {
     dup dup 1 eq exch 0 eq or not
     {
        dup 1 sub fib
        exch 2 sub fib
        add
     } { } ifelse
  } def

  /iinit 1 def
  /istop 10 def
  iinit { dup istop eq { exit } { dup fib show 1 add }  ifelse } loop

 */
