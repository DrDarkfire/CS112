import java.util.Scanner;

public class Freecell {

	public Freecell() {}
	
	public static void play() {
		Scanner in = new Scanner(System.in);
		long seed = (long) (Math.random() * 10000);
		FreecellGame game = new FreecellGame(seed);
		System.out.println(game.toString());
		//while(!FreecellGame.isGameOver()) {
		System.out.println("\r\n" + 
				"Please enter source and destination card stacks, or \"-1\" to quit: \n");
		int move = in.nextInt();
			
		//}
		
		
	}
	
	public static void play(long seed) {
		Scanner in = new Scanner(System.in);
		//System.out.println("Play Freecell with seed: ");
		//long seed = in.nextLong();
		FreecellGame game = new FreecellGame(seed);
		System.out.println(game + "\n");
		//while(!FreecellGame.isGameOver()) {
		System.out.println("\r\n" + 
				"Please enter source and destination card stacks, or \"-1\" to quit: \n");
		int move = in.nextInt();
	}
	
	public static void main(String[] args) {
		long zero = 0;
		play(zero);
	}

}
