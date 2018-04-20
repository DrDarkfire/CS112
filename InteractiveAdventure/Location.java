import java.util.HashMap;

public class Location {
	
	String name;
	String description;
	boolean isVisited = false;
	
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
		sb.append("\nExits:");
		for (String dir : exits.keySet())
			sb.append(" " + dir);
		return sb.toString();
	}
}
