
public class Foundation extends CardStack {

	public Foundation() {}

	public boolean canPlayFrom() {
		return false;
	}

	public boolean playTo(Card card) {
		if ((stack.empty() && card.getRank() == 0)) {
			stack.add(card);
			return true;
		}

		else if ((!stack.empty() && (card.getSuit() == stack.peek().getSuit()) &&
				(card.getRank() == stack.peek().getRank() + 1))) {
			stack.add(card);
			return true;
		}
		return false;
	}
}

