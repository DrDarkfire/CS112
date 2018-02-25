import java.util.InputMismatchException;
import java.util.Scanner;

public class Freecell extends CardStack {

	public Freecell() {}
	
	public static void play() throws IllegalPlayException {
		long seed = (long) (Math.random() * 10000);
		play(seed);
	}
	
	public static void play(long seed) throws IllegalPlayException {
		FreecellGame game = new FreecellGame(seed);
		System.out.println(game);
		int count = 0;
		Scanner in = new Scanner(System.in);
		while(!game.isGameOver() && count < 500) {
			try {
			System.out.print("Please enter source and destination card stacks, or \"-1\" to quit: ");
			int move = in.nextInt();
			int to   = in.nextInt();
			System.out.println("");
				game.play(move, to);
			}
			catch (InputMismatchException e) {
				in.nextLine();
				System.out.println("");
				System.out.println("Source and destination card stacks must be entered as integers (1-16).");
			}
			catch (IllegalPlayException e) {
				System.out.println("Illegal play: " + e);
			}
			System.out.println("\n" + game);
			count++;
		}
		in.close();
		if (count >= 499)
		{
			System.out.println("YOU LOSE");
		}
		System.out.println("YOU WIN!");
	}
	
	public static void main(String[] args) throws IllegalPlayException {
		play(0);
	}

}
