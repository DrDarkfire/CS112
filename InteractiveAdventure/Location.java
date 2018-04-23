import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Location {

	public String name;
	public String description;
	boolean isVisited = false;
	public Set<Item> items = new HashSet<>();
	public HashMap<String, Location> exits = new HashMap<String, Location>();

	public Location(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		if (!isVisited) {
			sb.append("\n" + description);
			isVisited = true;
		}
		if (!items.isEmpty()) {
			sb.append("\nYou see:");
			for (Item item : items)
				sb.append(" " + item);
		}
		if (!exits.isEmpty()) {
			sb.append("\nExits:");
			for (String dir : exits.keySet())
				sb.append(" " + dir);
		}
		return sb.toString();
	}
}
