
public class Foundation extends CardStack {
	public Foundation() {
		return;
	}
	
	public boolean canPlayFrom() {
		return !stack.empty();
	}
	
	public boolean playTo(Card card) {
		if ((stack.isEmpty() && card.getRank() == 0) || (!stack.isEmpty()
				&& card.getSuit() == stack.peek().getSuit() && card.getRank()
				== (stack.peek().getRank() + 1))) {
			return true;
		}
		return false;
	}
}
