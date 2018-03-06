import java.util.ArrayList;

public class BucketsNode extends SearchNode {
	public static final int MAX_AMOUNT1 = 5, MAX_AMOUNT2 = 7;
	public int bucket1, bucket2; // defaulted to 0
	
	
	@Override
	public String toString() {
		return "BucketsNode [bucket1=" + bucket1 + ", bucket2=" + bucket2 + "]";
	}

	@Override
	public boolean isGoal() {
		return bucket1 + bucket2 == 4;
	}

	@Override
	public ArrayList<SearchNode> expand() {
		ArrayList<SearchNode> children = new ArrayList<>();
		// empty 5
		if (bucket1 != 0) { // change occurs
			BucketsNode child = (BucketsNode) childClone();
			// make change
			child.bucket1 = 0;
			children.add(child);
		}
		// empty 7
		if (bucket2 != 0) { // change occurs
			BucketsNode child = (BucketsNode) childClone();
			// make change
			child.bucket2 = 0;
			children.add(child);
		}
		// fill 5
		if (bucket1 != MAX_AMOUNT1) { // change occurs
			BucketsNode child = (BucketsNode) childClone();
			// make change
			child.bucket1 = MAX_AMOUNT1;
			children.add(child);
		}
		// fill 7
		if (bucket2 != MAX_AMOUNT2) { // change occurs
			BucketsNode child = (BucketsNode) childClone();
			// make change
			child.bucket2 = MAX_AMOUNT2;
			children.add(child);
		}
		// pour 5 to 7
		if (bucket1 != 0 && bucket2 != MAX_AMOUNT2) { // change occurs
			BucketsNode child = (BucketsNode) childClone();
			// make change
			int pourAmount = Math.min(bucket1, MAX_AMOUNT2 - bucket2);
			child.bucket1 -= pourAmount;
			child.bucket2 += pourAmount;
			children.add(child);
		}
		// pour 7 to 5
		if (bucket2 != 0 && bucket1 != MAX_AMOUNT1) { // change occurs
			BucketsNode child = (BucketsNode) childClone();
			// make change
			int pourAmount = Math.min(bucket2, MAX_AMOUNT1 - bucket1);
			child.bucket2 -= pourAmount;
			child.bucket1 += pourAmount;
			children.add(child);
		}
		return children;
	}

}
