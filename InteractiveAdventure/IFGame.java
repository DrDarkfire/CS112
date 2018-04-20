import java.util.HashMap;
import java.util.Scanner;

public class IFGame {
	Location location; // player location
	Location glatfelter, tunnels, speakeasy;
	Scanner in = new Scanner(System.in);
	
	public IFGame() {
		initialize();
		play();
	}
	
	private void play() {
		while (true) {
			System.out.println("location");
			System.out.print("? ");
			String command = in.nextLine().trim();
			if (command.equals("quit")) {
				System.out.println("GAME OVER");
				System.exit(0);
			}
			else if (command.equals("look")) {
				System.out.println(location.description);
			}
			else { // move
				// OK for now but not for later
				// default else should be for unrecognized verbs
				Location nextLocation = location.exits.get(command);
				if (nextLocation == null)
					System.out.println("You can't go that way.");
				else
					location = nextLocation;
					System.out.println(location.toString());
			}
		}
	}

	private void initialize() {
		glatfelter = new Location("Glatfelter Lodge", "An old stone building with red pointing and cool stained-glass owls stands before you");
		tunnels = new Location("Secret Tunnels", "You're in a network of secret tunnels that tend east-west");
		speakeasy = new Location("The Undercard", "This underground gin joint has the art deco look and jazz sounds straight out of the roaring 20's");
		location = glatfelter;
		
		glatfelter.exits.put("d", tunnels);
		tunnels.exits.put("u", glatfelter);
		tunnels.exits.put("e", speakeasy);
		speakeasy.exits.put("w", tunnels);
		
	}

	public static void main(String[] arghs) {
		new IFGame();
	}
}
