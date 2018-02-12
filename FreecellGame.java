
public class FreecellGame {
	
	private CardStack[] stacks = new CardStack[17];
	
    public FreecellGame(long seed) {
    	stacks[0] = new Deck(seed);
    	// change this to make specific CardStacks (e.g. Cell)
    	for (int i = 1; i < stacks.length; ++i)
    		stacks[i] = new CardStack();
    	// deal deck into the cascades
    	int i = 9;
    	while (!stacks[0].isEmpty()) {
    		stacks[i++].addCard(stacks[0].removeTopCard());
    		if (i == stacks.length)
    			i = 9;
    	}
    }

    public boolean play(int srcStackNum, int destStackNum) {
    	return stacks[destStackNum].playTo(stacks[srcStackNum]);
    }

    public Card[] getStack(int stackNum) {
    	return null;
    }

    public boolean isGameOver() {
    	return false;
    }


    public java.lang.String toString() {
    	return null;
    }
}
