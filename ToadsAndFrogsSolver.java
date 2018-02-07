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
		char opponentPlayer = 0;
		// Algorithm: 
		// For each legal move for the current player,
		if(currentPlayer == TOAD)
			opponentPlayer = FROG;
		if(currentPlayer == FROG)
			opponentPlayer = TOAD;
		if(currentPlayer == TOAD)
			for (int i = 0; i < board.length; i++) {
				if (board[i] == currentPlayer)
				{
					if(i <= (board.length - 2) && board[i+1] == EMPTY){
						board[i + 1] = currentPlayer;
						board[i] = EMPTY;
					}
					else if(i <= (board.length - 3) && board[i+2] == EMPTY){
						board[i + 2] = currentPlayer;
						board[i] = EMPTY;
					}
					else if(i == board.length - 2 && board[i +1] == opponentPlayer)
							return getWinner(board, opponentPlayer);
					else if(i <= (board.length - 3) && board[i + 1] == opponentPlayer && board[i + 2] == opponentPlayer){
						return getWinner(board, opponentPlayer);
					}
					else
						return currentPlayer;
				}
			}
		else
			for(int i = board.length - 1; i >= 0; i--){
				if (board[i] == currentPlayer){
					if(i >= 1 && board[i - 1] == EMPTY){
						board[i - 1] = currentPlayer;
						board[i] = EMPTY;
					}
					else if(i >= 2 && board[i - 2] == EMPTY){
						board[i - 2] = currentPlayer;
						board[i] = EMPTY;
					}
					else if(i == 2 && board[i - 1] == opponentPlayer)
						return getWinner(board, opponentPlayer);
					else if(i <= 3 && (board[i - 1] == opponentPlayer) && board[i - 2] == opponentPlayer){
						return getWinner(board, opponentPlayer);
					}
					else
						return currentPlayer;
				}
			}

		return getWinner(board, opponentPlayer);
				
		//   - if we call getWinner on the resulting game state (new board, other player)
		//     and find that this leads to a win for the current player, 
		//     then return the current player.
		// Otherwise, failing to find a winning play (or any legal play), return the opponent player.
	}

}
