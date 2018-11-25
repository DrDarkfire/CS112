import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Graph {
	HashMap<Vertex, List<Edge>> map;
	public Graph(String filename) throws FileNotFoundException {
		//edges = new List<Edge>();
		map = new HashMap<Vertex, List<Edge>>();
		Scanner scanner = new Scanner(new File(filename));
		while (scanner.hasNext()) {
			String src = scanner.next();
			String tgt = scanner.next();
			Double  w  = scanner.nextDouble();
			addEdge(src, tgt, w);
		}
		scanner.close();
	}

	private void addEdge(String src, String tgt, Double w) {
		if (getVertex(src) == null) {
			map.put(new Vertex(src), null);
		}
		if (getVertex(tgt) == null) {
			map.put(new Vertex(tgt), null);
		}
		Vertex s = getVertex(src);
		map.get(s).add(new Edge(s, getVertex(tgt), w));
	}
	
	public Vertex getVertex(String label) {
		for (Vertex v : map.keySet()) {
			if (v.label == label) {
				return v;
			}
		}
		return null;
	}
	
	public List<Edge> getAdjacent(Vertex src) {
		return map.get(src);
	}
	
	public Set<Vertex> getVerticies() {
		return map.keySet();
	}
	
	public String[] getLabels() {
		Set<Vertex> vertexSet = getVerticies();
		String[] labels = new String[vertexSet.size()];
		Vertex[] verticies = new Vertex[vertexSet.size()];
		getVerticies().toArray(verticies);
		for (int i = 0; i  < verticies.length; i++) {
			labels[i] = verticies[i].label;
		}
		return labels;
	}
	
	public double[][] getMatrix() {
		int verticiesSize = map.keySet().size();
		double[][] matrix = new double[verticiesSize][verticiesSize];
		for (int i = 0; i < verticiesSize; i++) {
			for (int j = 0; j < verticiesSize; j++) {
//				matrix[i][j]
			}
		}
		return null;
	}
}