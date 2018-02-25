import java.util.Stack;
public class CardStack {
	
	protected Stack<Card> stack = new Stack<>();
	public CardStack() {}
	
	public void addCard(Card card) {
		stack.push(card);
	}
	
	public boolean canPlayFrom() {
		return !stack.empty();
	}
	
	public Card getTopCard() {
		return !stack.empty() ? stack.peek() : null;
	}
	
	public boolean isEmpty() {
		return stack.empty();
	}
	
	public boolean playTo(Card card) {
		if (card == null)
			return false;
		addCard(card);
		return true;
	}
	
	public boolean playTo(CardStack otherStack) {
		if (!otherStack.canPlayFrom())
			return false;
		boolean legalPlay = playTo(otherStack.getTopCard());
		if (legalPlay)
			otherStack.removeTopCard();
		return legalPlay;
	}
	
	public Card removeTopCard() {
		return stack.empty() ? null : stack.pop();
	}
	
	public int size() {
		return stack.size();
	}
	
	public java.lang.String toString() {
		String s = stack.toString();
		return s.substring(1, s.length());
	}
	
	public Card[] toArray() {
		return stack.toArray(new Card[stack.size()]);
	}
}
