import java.util.HashSet;
import java.util.Set;

public class Item {
	String name, description, gettable;
	public Set<Object> attributes = new HashSet<>();

	public Item(String name, String description, String gettable) {
		this.name = name;
		this.description = description;
		this.gettable = gettable;
	}
	
	public String toString() {
		return name;
	}
}
