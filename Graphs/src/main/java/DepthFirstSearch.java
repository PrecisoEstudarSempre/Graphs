import java.util.Stack;

public class DepthFirstSearch {

	private Graph graph;

	public void depthFirstSearch(){
		Vertex[] vertexs = graph.getVertexs();
		Stack stack = new Stack();
		graph.setWasVisitedVertex(0);
		graph.displayVertex(0);
		stack.push(0);
	}
}