public class DepthFirstSearch {
	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.addVertex('A');
		graph.addVertex('B');
		graph.addVertex('C');
		graph.addVertex('D');
		graph.addVertex('E');
		graph.addVertex('F');
		graph.addVertex('G');
		graph.addVertex('H');
		graph.addVertex('I');

		graph.addEdge(0,1);
		graph.addEdge(0,2);
		graph.addEdge(0,3);
		graph.addEdge(0,4);

		graph.addEdge(1,5);
		graph.addEdge(1,7);

		graph.addEdge(3,6);
		graph.addEdge(3,8);

		graph.depthFirstSearch();
	}
}