
public class Cascade extends CardStack {
	
	public static boolean[] suitIsRed = {false, true, true, false};

	public Cascade() {
		return;
	}
	
	public boolean playTo(Card card) {
		if (stack.isEmpty() || (suitIsRed[card.getSuit()] != suitIsRed[stack.peek().getSuit()] 
				&& card.getRank() == (stack.peek().getRank() - 1)))
			return true;
		return false;
	}

}
