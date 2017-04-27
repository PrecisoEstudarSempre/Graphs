class WarshallAlgorithm {
	public static void main(String[] args) {
		Graph graph = new Graph();

		graph.addVertex('A');
		graph.addVertex('B');
		graph.addVertex('C');
		graph.addVertex('D');
		graph.addVertex('E');

		graph.addOrientedEdge(0,1);				//A->B

		graph.addOrientedEdge(1,2);				//B->C
		graph.addOrientedEdge(1,4);				//B->E

		graph.addOrientedEdge(3,1);				//D->B

		graph.warshallAlgorithm();

		graph.checkPossibleWay(4, 0);
		graph.checkPossibleWay(3, 4);
	}
}