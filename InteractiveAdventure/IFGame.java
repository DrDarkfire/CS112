import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class IFGame {
	// create set or ArrayList for items
	Location location; // player location
	Location road, tunnels, dungeon, cabin, boi, secretRoom;
	Scanner in = new Scanner(System.in);
	boolean echoOn;
	public Set<Item> inventory = new HashSet<>();

	public IFGame(boolean echoOn) {
		this.echoOn = echoOn;
		initialize();
		play();
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

		cabin.exits.put("d", tunnels);
		cabin.exits.put("w", road);
		road.exits.put("e", cabin);
		road.exits.put("w", boi);
		tunnels.exits.put("u", cabin);
		tunnels.exits.put("e", dungeon);
		dungeon.exits.put("w", tunnels);
		dungeon.exits.put("e", secretRoom);
		boi.exits.put("e", road);

		// items
		Item hkey2 = new Item("Key Bottom", "It's the bottom half of a rusty golden key", "gettable");
		dungeon.items.add(hkey2);
		hkey2.attributes.add("rusty");
		Item hkey1 = new Item("Key Top", "It's the top half of a rusty golden key.", "gettable");
		boi.items.add(hkey1);
		Item secretDoor = new Item("Door", "Its a dark heavy door", "not gettable");
		dungeon.items.add(secretDoor);
		inventory = null;
	}

	private void play() {
		while (true) {
			System.out.println("Location: " + location);
			System.out.println("Your inventory is: " + inventory);
			System.out.println("What do you want to do? ");			
			String command = in.nextLine().trim();
			if (echoOn)
				System.out.println(command);
			if (command.equals("quit")) {
				System.out.println("GAME OVER");
				System.exit(0);
			}
			else if (command.equals("look")) {
				location.isVisited = false;
			}
			else if (command.equals("take Key Bottom")) {
				//inventory.add(location.items.contains(o));
				//location.items.remove(thing);
				
			}
			else { //move
				// OK for now but not for later
				// default else should be for unrecognized verbs
				Location nextLocation = location.exits.get(command);
				if (nextLocation == null)
					System.out.println("You can't go that way.");
				else if (nextLocation.enterable == false)
					System.out.println("The door is locked");
				else
					location = nextLocation;
			}
		}			
	}

	public static void main(String[] args) {
		new IFGame(Arrays.asList(args).contains("-e"));
	}

}
