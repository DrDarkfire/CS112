import java.util.Random;

public class Deck extends CardStack {
	public Deck(long seed) {
		// add all cards to deck
		for (Card c : Card.allCards)
			addCard(c);
		// shuffle the cards Knuth / Fisher-Yates Shuffle
		Random random = new Random(seed);
		for (int i = stack.size() - 1; i > 0; --i) {
			int j = random.nextInt(i + 1);
			Card c = stack.get(i);
			stack.set(i, stack.get(j));
			stack.set(j, c);
		}
	}
	
	public boolean playTo(Card card) {
		return false;
	}
	
	public boolean canPlayFrom() {
		return false;
	}
}
