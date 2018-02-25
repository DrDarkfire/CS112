public class Cascade extends CardStack {

	public Cascade() {}
	
	public boolean playTo(Card card) {
		if (stack.isEmpty()) {
			stack.add(card);
			return true;
		}
		else if ((Card.suitIsRed[card.getSuit()] != Card.suitIsRed[stack.peek().getSuit()] 
				&& card.getRank() == stack.peek().getRank() - 1)) {
			stack.add(card);
			return true;
		}
		return false;
	}

}
