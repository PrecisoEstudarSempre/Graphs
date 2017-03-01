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

		graph.addEdge(0,1);						//AB
		graph.addEdge(0,2);						//AC
		graph.addEdge(0,3);						//AD
		graph.addEdge(0,4);						//AE

		graph.addEdge(1,5);						//BF

		graph.addEdge(5,7);						//FH

		graph.addEdge(3,6);						//DG
		
		graph.addEdge(6,8);						//GI

		graph.depthFirstSearch();
	}
}