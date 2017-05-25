public class WeightedGraphApp {
	public static void main(String[] args) {
		WeightedGraph weightedGraph = new WeightedGraph();

		weightedGraph.addVertex('A');
		weightedGraph.addVertex('B');
		weightedGraph.addVertex('C');
		weightedGraph.addVertex('D');
		weightedGraph.addVertex('E');

		weightedGraph.addEdgeNonOriented(0,3,4);				//A-D: 4
		weightedGraph.addEdgeNonOriented(0,4,3);				//A-E: 3
		weightedGraph.addEdgeNonOriented(0,2,10);				//A-C: 10

		weightedGraph.addEdgeNonOriented(1,3,7);				//B-D: 7
		weightedGraph.addEdgeNonOriented(1,2,1);				//B-C: 1

		weightedGraph.displayGraph();
	}
}