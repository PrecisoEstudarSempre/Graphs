class ShortestPathObject{
	private int distance;			//o peso da aresta que no nosso caso é a distância de uma cidade a outra
	private int parentVert;			//o índice do vértice pai

	public ShortestPathObject(int parentVert, int distance){
		this.parentVert = parentVert;
		this.distance = distance;
	}

	public int getDistance(){
		return this.distance;
	}

	public void setDistance(int distance){
		this.distance = distance;
	}

	public int getParentVert(){
		return this.parentVert;
	}

	public void setParentVert(int parentVert){
		this.parentVert = parentVert;
	}
}

class Vertex{
	private String label;							//Representa um rótulo (exemplo: 'RJ')
	private boolean isInTree;

	public Vertex(String label){
		this.label = label;
		this.isInTree = false;
	}

	public String getLabel(){
		return this.label;
	}

	public void setIsInTree(boolean isInTree){
		this.isInTree = isInTree;
	}

	public boolean isInTree(){
		return this.isInTree;
	}
}

class Graph{
	private final int MAX_VERTS = 20;
	private final int INFINITY = 1000000;
	private Vertex vertexList[];							//lista de vértices do grafo
	private int adjMat[][];									//matriz de adjacência do grafo
	private int nVerts;										//número atual de vértices
	private int nTree;										//número atual de nós da árvore
	private ShortestPathObject shortestPathArray[];			//array de menor caminho
	private int currentVert;								//vértice atual
	private int startToCurrent;								//distância até currentVert

	public Graph(){
		vertexList = new Vertex[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;											//inicialização do contador de vértices
		nTree = 0;											//inicialização do contador de nós da árvore

		for (int i=0; i<adjMat.length; i++) {				//inicializo a matriz de adjacências com o valor infinito para que															
			for (int j=0; j<adjMat[i].length; j++) {		// posteriormente nas células corretas só existam as distâncias
				adjMat[i][j] = 	INFINITY;
			}
		}

		shortestPathArray = new ShortestPathObject[MAX_VERTS];
	}

	public void addVertex(String label){
		vertexList[nVerts++] = new Vertex(label);
	}

	public void addEdge(int start, int end, int weight){
		adjMat[start][end] = weight;
	}

	public void findAllShortestPaths(){
		int startTree = 0;
		vertexList[startTree].setIsInTree(true);								//adiciono o primeiro vértice do grafo na árvore
		nTree = 1;																//mudo o contador porque acabei de adicionar um nó na árvore

		for (int i=0; i<nVerts; i++) {											//inicializo o array de menores caminhos com as distâncias
			int distance = adjMat[startTree][i];								//das adjacências do primeiro vértice
			shortestPathArray[i] = new ShortestPathObject(startTree, distance);
		}

		while (nTree < nVerts) {
			int shortestVertex = getMin();
			int shortestDistance = shortestPathArray[shortestVertex].getDistance();

			if(shortestDistance == INFINITY){
				System.out.println("There are unreachable vertices");
				break;
			} else {
				currentVert = shortestVertex;
				startToCurrent = shortestDistance;
			}

			vertexList[currentVert].setIsInTree(true);
			nTree++;
			updateShortestPathArray();
		}

		displayPaths();

		nTree = 0;
		for (int i=0; i<nVerts; i++) {
			vertexList[i].setIsInTree(false);
		}		
	}

	private int getMin(){
		int shortestDistance = INFINITY;
		int shortestVertex = 0;

		for (int i=1; i<nVerts; i++) {
			if (!vertexList[i].isInTree() && shortestPathArray[i].getDistance() < shortestDistance) {
				shortestDistance = shortestPathArray[i].getDistance();
				shortestVertex = i;
			}
		}
		return shortestVertex;
	}

	private void updateShortestPathArray(){
		int column = 1;
		while (column < nVerts) {
			if (vertexList[column].isInTree()) {
				column++;
				continue;
			}

			int currentToTarget = adjMat[currentVert][column];
			int startToTarget = startToCurrent + currentToTarget;
			int shortestDistance = shortestPathArray[column].getDistance();

			if (startToTarget < shortestDistance) {
				shortestPathArray[column].setParentVert(currentVert);
				shortestPathArray[column].setDistance(startToTarget);
			}
			column++;
		}
	}

	private void displayPaths(){
		for (int i=0; i<nVerts; i++) {
			System.out.print(vertexList[i].getLabel() + "=");
			if (shortestPathArray[i].getDistance() == INFINITY) {
				System.out.print("inf");			
			} else {
				System.out.print(shortestPathArray[i].getDistance());
			}
			String parent = vertexList[shortestPathArray[i].getParentVert()].getLabel();
			System.out.print("("+parent+")");			
		}
		System.out.println("");
	}
}

class DijkstraAlgorithm{
	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.addVertex("RJ");
		graph.addVertex("SP");
		graph.addVertex("RP");
		graph.addVertex("PP");
		graph.addVertex("OP");
		graph.addVertex("BN");

		graph.addEdge(0,1,60);							//RJ->SP
		graph.addEdge(0,3,20);							//RJ->PP
		graph.addEdge(0,2,160);							//RJ->RP

		graph.addEdge(1,2,30);							//SP->RP
		graph.addEdge(1,4,50);							//SP->OP

		graph.addEdge(2,5,40);							//RP->BN
		graph.addEdge(2,4,10);							//RP->OP

		graph.addEdge(3,5,350);							//PP->BN

		System.out.print("Shortest paths: ");
		graph.findAllShortestPaths();	
	}
}