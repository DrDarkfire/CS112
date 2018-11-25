
public class Edge {
	private Vertex src, tgt;
	private double w;
	
	public Edge(Vertex src, Vertex tgt, double w) {
		this.src = src;
		this.tgt = tgt;
		this.w = w;
	}
	
	public Vertex getSource() {
		return src;
	}
	public Vertex getTarget() {
		return tgt;
		
	}
	double getWeight() {
		return w;
	}
}
