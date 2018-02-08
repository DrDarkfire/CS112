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
		System.out.println(currentPlayer);
		// Algorithm: 
		// For each legal move for the current player,
		if(currentPlayer == TOAD)
			opponentPlayer = FROG;
		if(currentPlayer == FROG)
			opponentPlayer = TOAD;
		int toadcount = 0;
		int frogcount = 0;
		for (char ch : board) {
			if(ch == TOAD)
				toadcount++;
			if(ch == FROG)
				frogcount++;
		}
		if(currentPlayer == TOAD){
			for (int i = 0; i < board.length; i++) {
				if (board[i] == currentPlayer)
				{
					if(board[0] == FROG && board[frogcount - 1] == FROG) {
						System.out.println(board);
						return currentPlayer;
							}
					if(i <= (board.length - 2) && board[i+1] == EMPTY){
						board[i + 1] = currentPlayer;
						board[i] = EMPTY;
						System.out.println(board);
						return getWinner(board, opponentPlayer);
					}
					else if(i <= (board.length - 3) && board[i+2] == EMPTY && board[i+1] != TOAD){
						board[i + 2] = currentPlayer;
						board[i] = EMPTY;
						System.out.println(board);
						return getWinner(board, opponentPlayer);
					}
					
					
					//else if(i == board.length - 2 && (board[i +1] == opponentPlayer))
							//return getWinner(board, opponentPlayer);
//					else if(i <= (board.length - 3) && (board[i + 1] == opponentPlayer /*|| board[i + 1] == currentPlayer*/) && (board[i + 2] == opponentPlayer /*|| board[i + 2] == currentPlayer*/)){
//						return getWinner(board, opponentPlayer);
					//}
				}
				if(i == board.length -1)
					return opponentPlayer;
			}
			//System.out.println(board);
			return getWinner(board, opponentPlayer);
		}
		if (currentPlayer == FROG) {
			for(int i = board.length - 1; i >= 0; i--){
				if (board[i] == currentPlayer){
					if(board[board.length -1] == TOAD && board[board.length - toadcount] == TOAD) {
						return currentPlayer;
							}
					if(i >= 1 && board[i - 1] == EMPTY){
						board[i - 1] = currentPlayer;
						board[i] = EMPTY;
						System.out.println(board);
						return getWinner(board, opponentPlayer);
					}
					else if(i >= 2 && board[i - 2] == EMPTY && board[i - 1] != FROG){
						board[i - 2] = currentPlayer;
						board[i] = EMPTY;
						System.out.println(board);
						return getWinner(board, opponentPlayer);
					}
					

					//else if(i == 1 && board[0] == opponentPlayer)
						//return getWinner(board, opponentPlayer);
					
					}
				if(i == 0)
					return opponentPlayer;
//					else if(i >= 3 && (board[i - 1] == opponentPlayer  /*|| board[i - 1] == currentPlayer*/) && (board[i - 2] == opponentPlayer /*|| board[i - 2] == currentPlayer*/)){
//						return getWinner(board, opponentPlayer);
					//}
				}
			
			}
			//System.out.println(board);
			return getWinner(board, opponentPlayer);
		}
				
		//   - if we call getWinner on the resulting game state (new board, other player)
		//     and find that this leads to a win for the current player, 
		//     then return the current player.
		// Otherwise, failing to find a winning play (or any legal play), return the opponent player.
}

