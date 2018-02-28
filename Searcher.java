
public abstract class Searcher {
	/**
	 * nodeCount = number of nodes that have been
	 */
	int nodeCount = 0;
	
	/**
	 * goalNode - a goal node if the previous search
	 * was successful; null otherwise.
	 */
	SearchNode goalNode = null;
	
	public abstract boolean search(SearchNode node);

	public int getNodeCount() {
		return nodeCount;
	}

	public SearchNode getGoalNode() {
		return goalNode;
	}
	
	public void printGoalPath() {
		if (goalNode == null)
			System.out.println("Goal node not found");
		else
			printPath(goalNode);
	}
	
	private void printPath(SearchNode node) {
		if (node.parent != null)
			printPath(node.parent);
		System.out.println(node);
	}
}
