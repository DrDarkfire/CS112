
public class Vertex{
	public String label;
	public double w;
	public Vertex parent;
	public boolean mark;
	
	public Vertex(String label) {
		this.label = label;
		parent = null;
		w = Double.MAX_VALUE;
		mark = false;
	}
}
