import java.util.ArrayList;

public abstract class SearchNode implements Cloneable {
	public SearchNode parent = null;
	public int depth = 0;
	
	public SearchNode() {}
	
	public abstract boolean isGoal();
	
	public abstract ArrayList<SearchNode> expand();
	
	public SearchNode childClone() {
		SearchNode child = (SearchNode) clone();
		child.depth = depth + 1;
		child.parent = this;
		return child;
	}
	
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// unreachable
		}
		return null;
	}
	
	// NOTE 1: For a deep copy/clone, override clone() in your subclass
	// NOTE 2: It's also good to create a toString method for representing
	//		   your node
}
