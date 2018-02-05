import java.util.*;
public class PigRollSequences {
	public static ArrayList<Integer> numHolder = new ArrayList<Integer>();
	public int i = 0;
	public static int getNumSequences(int turnTotal) {
		int i = 0;
		if (turnTotal == 0 || turnTotal == 1)
			numHolder.add(0);
		if (turnTotal == 2 || turnTotal == 3)
			numHolder.add(1);
		else {
			i = numHolder.get(turnTotal - 2) + numHolder.get(turnTotal - 1);
		}
		return i;
	}
	public static void main(String[] args) {
		System.out.println(getNumSequences(2));
	}
}
