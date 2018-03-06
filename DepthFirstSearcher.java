import java.util.Stack;

public class DepthFirstSearcher extends Searcher {

	public DepthFirstSearcher() {}

	public boolean search(SearchNode rootNode) {
		nodeCount = 0;
		Stack<SearchNode> stack = new Stack<SearchNode>();
		stack.add(0, rootNode);

		while (true) {
			if (stack.isEmpty())
				return false;

			SearchNode e = stack.pop();
			nodeCount++;

			if(e.isGoal()) {
				goalNode = e;
				return true;
			}

			for (SearchNode child : e.expand())
				stack.add(child);
		}
	}
}
