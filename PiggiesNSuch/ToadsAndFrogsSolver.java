import java.util.Scanner;
// https://en.wikipedia.org/wiki/Toads_and_Frogs

public class ToadsAndFrogsSolver {
	public static final char TOAD = 'T', FROG = 'F', EMPTY = '-';

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Board position (using TF-)? ");
		String board = in.nextLine().trim().toUpperCase();
		System.out.print("Current player (T/F)? ");
		char currentPlayer = in.nextLine().trim().toUpperCase().charAt(0);
		System.out.println("Winner: " + getWinner(board.toCharArray(), currentPlayer));
		in.close();
	}


	public static char getWinner(char[] board, char currentPlayer) {
		char opponentPlayer;
		if (currentPlayer == TOAD)
			opponentPlayer = FROG;
		else
			opponentPlayer = TOAD;
		int dir = 0;
		if (currentPlayer == TOAD)
			dir = 1;
		else
			dir = -1;
		// Algorithm: 
		// For each legal move for the current player,
		for(int i = 0; i < board.length; i++) {
			if(i + dir >= 0 && i + dir < board.length && board[i] == currentPlayer && board[i + dir] == EMPTY) {
				board[i] = EMPTY;
				board[i + dir] = currentPlayer;
				char winner = getWinner(board, opponentPlayer);
				board[i] = currentPlayer;
				board[i + dir] = EMPTY;
				boolean win = false; 
				if(winner == currentPlayer)
				{
					win = true;
				}
				if(win == true)
				{
					return currentPlayer;
				}
			}
			if(i + (2 * dir) >= 0 && i + (2 * dir) < board.length && board[i] == currentPlayer && board[i + (2 * dir)] == EMPTY) {
				board[i] = EMPTY;
				board[i + (2*dir)] = currentPlayer;
				char winner = getWinner(board, opponentPlayer);
				board[i] = currentPlayer;
				board[i + (2*dir)] = EMPTY;
				boolean win = false; 
				if(winner == currentPlayer)
				{
					win = true;
				}
				if(win == true)
				{
					return currentPlayer;
				}
			}
		}
		return opponentPlayer;


		//   - if we call getWinner on the resulting game state (new board, other player)
		//     and find that this leads to a win for the current player, 
		//     then return the current player.
		// Otherwise, failing to find a winning play (or any legal play), return the opponent player.
	}
}

