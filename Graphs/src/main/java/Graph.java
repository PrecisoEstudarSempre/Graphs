public class Graph {
	private final int MAX_VERTEX = 10;
	private Vertex[] vertexs;
	private int[][] adjacencyMatrix;
	private int currentAmountOfVerxtes;

	public Graph(){
		vertexs = new Vertex[MAX_VERTEX];
		adjacencyMatrix = new int[MAX_VERTEX][MAX_VERTEX];
		currentAmountOfVerxtes = 0;

		//inicialização da matriz de adjacências com 0s
		for(int i=0; i<adjacencyMatrix.length; i++){
			for(int j=0; j<adjacencyMatrix[i].length; j++){
				adjacencyMatrix[i][j] = 0;
			}
		}
	}

	public void addVertex(char label){
		vertexs[currentAmountOfVerxtes++] = new Vertex(label);
	}

	public void addEdge(int start, int end){
		adjacencyMatrix[start][end] = 1;
		adjacencyMatrix[end][start] = 1;
	}

	public void displayVertex(int vertexIndex){
		System.out.print(vertexs[vertexIndex].getLabel());
	}

	public Vertex[] getVertexs(){
		return this.vertexs;
	}

	public void setWasVisitedVertex(vertexIndex){
		vertexs[vertexIndex].setWasVisited(true);
	}
}