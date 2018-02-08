import java.util.*;
public class PigRollSequences {
		private static int[] numHolder = new int[200];
	public static int getNumSequences(int turnTotal) {
		if( turnTotal == 0)
			numHolder[0] = 0;
		else if ( turnTotal == 1)
			numHolder[1] = 0;
		else if ( turnTotal == 2)
			numHolder[2] = 1;
		else if ( turnTotal == 3)
			numHolder[3] = 1;
		else
			numHolder[turnTotal - 1] = getNumSequences(turnTotal - 2) + getNumSequences(turnTotal - 3) + getNumSequences(turnTotal - 4) + getNumSequences(turnTotal - 5);
		return numHolder[turnTotal];
	}
	}
	public static void main(String[] args) {
		System.out.println(getNumSequences(2));
	}
}
import java.util.*;
public class PigRollSequences {

	//private static ArrayList<Integer> numHolder = new ArrayList<Integer>();
	private static int[] numHolder = new int[200];
	public static int getNumSequences(int turnTotal) {
		{
			for(int i = 0; i <= turnTotal; i++)
				if (turnTotal == 1 || turnTotal == 0)
					numHolder[i] = 0;
				else if (turnTotal == 2 || turnTotal == 3)
					numHolder[i] = 1;
				else if (turnTotal == 4)
					numHolder[i] = 2;
				else if (turnTotal == 5)
					numHolder[i] = 3;
				else if (turnTotal == 6)
					numHolder[i] = 5;
				else if (turnTotal == 7)
					numHolder[i] = 7;
				else if (turnTotal == 8)
					numHolder[i] = 12;
				else {
					numHolder[i] = numHolder[i - 2] + numHolder[i - 3] + numHolder[i - 4] + numHolder[i - 5] + numHolder[i - 6];
				}

		}
		return numHolder[turnTotal - 1];
	}	

	public static void main(String[] args) {
		System.out.println(getNumSequences(2));
		System.out.println(getNumSequences(3));
		System.out.println(getNumSequences(4));
		System.out.println(getNumSequences(5));
		System.out.println(getNumSequences(6));
		System.out.println(getNumSequences(10));

	}

}
