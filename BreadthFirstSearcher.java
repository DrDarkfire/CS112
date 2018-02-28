import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearcher extends Searcher{

	public boolean search(SearchNode rootNode) {
		// IMPLEMENT:
		
		// Initialize search variables.
		nodeCount = 0;
		Queue<SearchNode> queue = new LinkedList<SearchNode>();
		queue.offer(rootNode);
		
		while (true) {
			
			// If the search queue is empty, return false
			if (queue.isEmpty())
				return false;
			
			SearchNode node = queue.poll();
			nodeCount++;
			
			if (node.isGoal()) {
				goalNode = node;
				return true;
			}
			
			for (SearchNode child : node.expand())
				queue.offer(child);
		}
	}

}
