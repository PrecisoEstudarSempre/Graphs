import java.util.Stack;

public class Graph {
	private final int MAX_VERTEX = 10;
	private Vertex[] vertexs;
	private Stack<Integer> stack;
	private int[][] adjacencyMatrix;
	private int currentAmountOfVerxtes;

	public Graph(){
		vertexs = new Vertex[MAX_VERTEX];
		stack = new Stack<>();
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

	public void depthFirstSearch(){
		vertexs[0].setWasVisited(true);
		this.displayVertex(0);
		stack.push(0);
		

		while (!stack.empty()) {
			int vertexIndex = this.getAdjUnvisitedVertex(stack.peek());
			if(vertexIndex==-1){
				stack.pop();
			} else {
				vertexs[vertexIndex].setWasVisited(true);
				this.displayVertex(vertexIndex);
				stack.push(vertexIndex);
			}
		}

		for (int i=0; i<currentAmountOfVerxtes; i++) {
			vertexs[i].setWasVisited(false);
		}
	}

	private int getAdjUnvisitedVertex(int vertexIndex) {
		for (int i=0; i<currentAmountOfVerxtes; i++) {
			if (adjacencyMatrix[vertexIndex][i] == 1 && !vertexs[i].getWasVisited()) {
				return i;
			}
		}
		return -1;
	}
}