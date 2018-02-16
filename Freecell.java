import java.util.Scanner;

public class Freecell extends CardStack {

	public Freecell() {}
	
	public static void play() {
		long seed = (long) (Math.random() * 10000);
		play(seed);
	}
	
	public static void play(long seed) {
		FreecellGame game = new FreecellGame(seed);
		System.out.println(game);
		int count = 0;
		while(!game.isGameOver() && count < 500) {
			Scanner in = new Scanner(System.in);
			System.out.print("Please enter source and destination card stacks, or \"-1\" to quit: ");
			int move = in.nextInt();
			int to   = in.nextInt();
			game.play(move, to);
			System.out.println("\n" + game);
			count++;
		}
		if (count >= 499)
		{
			System.out.println("YOU LOSE");
		}
		System.out.println("YOU WIN!");
	}
	
	public static void main(String[] args) {
		play(0);
	}

}
