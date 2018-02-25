import java.util.Arrays;

public class FreecellGame {        

	private CardStack[] stacks = new CardStack[17];

	public FreecellGame(long seed)  {
		stacks[0] = new Deck(seed);
		for (int i = 1; i <= 4; i++)
			stacks[i] = new Cell();
		for (int i = 5; i < 9; i++)
			stacks[i] = new Foundation();
		for (int i = 9; i < stacks.length; i++)
			stacks[i] = new Cascade();
		// deal deck into the cascades
		int i = 9;
		while (!stacks[0].isEmpty()) {
			stacks[i++].addCard(stacks[0].removeTopCard());
			if (i == stacks.length)
				i = 9;
		}

	}

	//Create a Freecell game with a random deal generated with a given seed.

	//Parameters:
	//    seed - seed for random deal

	//Method Detail
	//play

	public boolean play(int srcStackNum, int destStackNum){
		return stacks[destStackNum].playTo(stacks[srcStackNum]);
	}

	//Attempt to move a card from stack srcStackNum to stack destStackNum. If the play is legal, the play is made and true is returned. If the play is not legal, no change is made and false is returned.

	//Parameters:
	//    srcStackNum - source stack number
	//    destStackNum - destination stack number
	//Returns:
	//    whether or not the play was legal and performed

	//getStack

	public Card[] getStack(int stackNum) {
		
		return stacks[stackNum].toArray();
	}

	//Return an array with the Card in the given stack number listed from bottom to top.

	//Parameters:
	//    stackNum - stack number
	//Returns:
	//    an array with the Card in the given stack number listed from bottom to top

	//isGameOver

	public boolean isGameOver(){
		if (stacks[5].toArray().length == 13 && stacks[6].toArray().length == 13 && stacks[7].toArray().length == 13 && stacks[8].toArray().length == 13)
			return true;
		return false;
	}

	//Return whether or not the game has been won (that is, all cards are on foundations).

	//Returns:
	//    whether or not the game has been won (that is, all cards are on foundations)

	//toString
	public java.lang.String toString(){
		String output;
		output = "Cells:\n";
		for (int i = 1; i <= 4; i++)
			output += " " + i + ": " + Arrays.toString(getStack(i)).substring(1, Arrays.toString(getStack(i)).length() - 1) + "\n";
		output += "Foundations:\n";
		for (int j = 5; j <= 8; j++)
			output += " " + j + ": " + Arrays.toString(getStack(j)).substring(1, Arrays.toString(getStack(j)).length() -1 ) + "\n";
		output += "Cascades:\n";
		output += " 9: " + Arrays.toString(getStack(9)).substring(1, Arrays.toString(getStack(9)).length() - 1) + "\n";
		for (int k = 10; k <= 16; k++)
			output += k + ": " + Arrays.toString(getStack(k)).substring(1, Arrays.toString(getStack(k)).length() - 1) + "\n";
		return output;
	}

	//Overrides:
	//   toString in class java.lang.Object


}
