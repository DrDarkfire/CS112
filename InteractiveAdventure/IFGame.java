import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IFGame {
	Location location; // player location
	Location road, tunnels, dungeon, cabin, boi, secretRoom;
	Scanner in = new Scanner(System.in);
	boolean echoOn;
	public Set<Item> inventory = new HashSet<>();

	Pattern d = Pattern.compile("d|down");
	Pattern u = Pattern.compile("u|up");
	Pattern e = Pattern.compile("e|east");
	Pattern w = Pattern.compile("w|west");
	Pattern n = Pattern.compile("n|north");
	Pattern s = Pattern.compile("s|south");

	public IFGame(boolean echoOn) {
		this.echoOn = echoOn;
		initialize();
		play();
	}

	private void play() {
		while (true) {
			System.out.println("Location: " + location);
			// FIX INVENTORY
			if (inventory.equals(null))
				System.out.println("There is nothing in your inventory");
			else
				System.out.println("Your inventory is: " + inventory);
			System.out.println("What do you want to do? ");
			String command = in.nextLine().trim();
			// down
			Matcher dm = d.matcher(command);
			boolean db = dm.matches();
			// up
			Matcher um = u.matcher(command);
			boolean ub = um.matches();
			// east
			Matcher em = e.matcher(command);
			boolean eb = em.matches();
			// west
			Matcher wm = w.matcher(command);
			boolean wb = wm.matches();
			// north
			Matcher nm = n.matcher(command);
			boolean nb = nm.matches();
			// south
			Matcher sm = s.matcher(command);
			boolean sb = sm.matches();
			if (echoOn)
				System.out.println(command);
			if (command.equals("quit")) {
				System.out.println("GAME OVER");
				System.exit(0);
			}
			else if (command.equals("look")) {
				System.out.println(location.description);
			}
			else if (db) {
				command = "down";
				location = location.exits.get(command);
				System.out.println(location.toString());
			}
			else if (ub) {
				command = "up";
				location = location.exits.get(command);
				System.out.println(location.toString());
			}
			else if (eb) {
				command = "east";
				location = location.exits.get(command);
				System.out.println(location.toString());
			}
			else if (wb) {
				command = "west";
				location = location.exits.get(command);
				System.out.println(location.toString());
			}
			else if (nb) {
				command = "north";
				location = location.exits.get(command);
				System.out.println(location.toString());
			}
			else if (sb) {
				command = "south";
				location = location.exits.get(command);
				System.out.println(location.toString());
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
		// locations
		road = new Location("A long windy road", "You are on a gravelly old road in a misty town. You see a cabin east.", true);
		tunnels = new Location("Secret Tunnels", "You're in a tight, damp, dark tunnel with a faint light east.", true);
		dungeon = new Location("Dungeon", "You stumble into a dark dungeon with remenants of torture tools around you.", true);
		cabin = new Location("Cabin", "You enter a brown, old cabin with a living room and a trapdoor hidden on the ground.", true);
		boi = new Location("Dat boi", "You approach Dat boi from a distance and hear him saying 'Get your keys here!'", true);
		secretRoom = new Location("Secret Room", "A secret room with treasure everywhere.", false);
		location = road;

		// items
		Item hkey2 = new Item("Key Bottom", "It's the bottom half of a rusty golden key", "gettable");
		dungeon.items.add(hkey2);
		hkey2.attributes.add("rusty");
		Item hkey1 = new Item("Key Top", "It's the top half of a rusty golden key.", "gettable");
		boi.items.add(hkey1);
		Item secretDoor = new Item("Door", "Its a dark heavy door", "not gettable");
		dungeon.items.add(secretDoor);
		inventory = null;

		cabin.exits.put("d", tunnels);
		cabin.exits.put("w", road);
		road.exits.put("e", cabin);
		road.exits.put("w", boi);
		tunnels.exits.put("u", cabin);
		tunnels.exits.put("e", dungeon);
		dungeon.exits.put("w", tunnels);
		dungeon.exits.put("e", secretRoom);
		boi.exits.put("e", road);

		System.out.print(location.toString());
	}

	public static void main(String[] arghs) {
		new IFGame(Arrays.asList(arghs).contains("-e"));
	}
}
