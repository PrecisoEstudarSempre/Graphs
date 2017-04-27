class WarshallAlgorithm {
	public static void main(String[] args) {
		Graph graph = new Graph();

		graph.addVertex('A');					//0
		graph.addVertex('B');					//1
		graph.addVertex('C');					//2
		graph.addVertex('D');					//3
		graph.addVertex('E');					//4

		graph.addOrientedEdge(0,1);				//A->B

		graph.addOrientedEdge(1,2);				//B->C
		graph.addOrientedEdge(1,4);				//B->E

		graph.addOrientedEdge(3,1);				//D->B

		graph.warshallAlgorithm();

		graph.checkPossibleWay(4, 0);
		graph.checkPossibleWay(3, 4);

		Graph anotherGraph = new Graph();

		anotherGraph.addVertex('A');					//0
		anotherGraph.addVertex('B');					//1
		anotherGraph.addVertex('C');					//2
		anotherGraph.addVertex('D');					//3
		anotherGraph.addVertex('E');					//4

		anotherGraph.addOrientedEdge(0,2);				//A->C

		anotherGraph.addOrientedEdge(1,0);				//B->A
		anotherGraph.addOrientedEdge(1,2);				//B->C
		anotherGraph.addOrientedEdge(1,4);				//B->E

		anotherGraph.addOrientedEdge(3,4);				//D->E

		anotherGraph.addOrientedEdge(4,2);				//E->C

		anotherGraph.warshallAlgorithm();
	}
}