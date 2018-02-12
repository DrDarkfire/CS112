
public class Cascade extends CardStack {
	
	public Cascade() {
		return;
	}
	
	public boolean playTo(Card card) {
		if (stack.isEmpty() || (card.suitIsRed != stack.peek().suitIsRed 
				&& card.getRank() == (stack.peek().getRank() - 1)))
			return true;
		return false;
	}

}
