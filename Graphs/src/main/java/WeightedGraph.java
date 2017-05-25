public class WeightedGraph {
	private final int MAX_VERTEX = 10;
	private Vertex[] vertexs;
    
    private Edge[][] adjacencyMatrix;
    private int currentAmountOfVerxtes;
    
    public WeightedGraph(){
        this.vertexs = new Vertex[MAX_VERTEX];
        adjacencyMatrix = new Edge[MAX_VERTEX][MAX_VERTEX];
		currentAmountOfVerxtes = 0;
    }
    
    public void addVertex(char label){
		vertexs[currentAmountOfVerxtes++] = new Vertex(label);
	}

	public void addEdgeNonOriented(int start, int end, int weight){
        Vertex vertexStart = vertexs[start];
        Vertex vertexEnd = vertexs[end];
		adjacencyMatrix[start][end] = new Edge(vertexStart, vertexEnd, weight);
		adjacencyMatrix[end][start] = new Edge(vertexEnd, vertexStart, weight);
	}

	public void displayGraph(){
		for(int i=0; i<adjacencyMatrix.length; i++){
			for(int j=0; j<adjacencyMatrix[i].length; j++){
				Edge edge = adjacencyMatrix[i][j];
				if(edge != null){
					System.out.println(edge.getVertexBegin().getLabel() + " - " + edge.getVertexEnd().getLabel() + ": " + edge.getWeight());
				}
			}
		}
	}
}