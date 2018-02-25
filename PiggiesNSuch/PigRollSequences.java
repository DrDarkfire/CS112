
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
		else if ( turnTotal == 4)
			numHolder[4] = 2;
		else if ( turnTotal == 5)
			numHolder[5] = 3;
		else if ( turnTotal == 6)
			numHolder[6] = 5;
		else
			numHolder[turnTotal] = getNumSequences(turnTotal - 2) + getNumSequences(turnTotal - 3) + getNumSequences(turnTotal - 4) + getNumSequences(turnTotal - 5) + getNumSequences(turnTotal - 6);
		return numHolder[turnTotal];
	}

	public static void main(String[] args) {
		System.out.println(getNumSequences(2));
		System.out.println(getNumSequences(3));
		System.out.println(getNumSequences(4));
		System.out.println(getNumSequences(5));
		System.out.println(getNumSequences(6));
		System.out.println(getNumSequences(7));
		System.out.println(getNumSequences(8));
		System.out.println(getNumSequences(9));
		System.out.println(getNumSequences(10));
		//addNumbers(5);
		//System.out.println(numHolder);
		//System.out.println(numHolder.get(3));
	}
}
