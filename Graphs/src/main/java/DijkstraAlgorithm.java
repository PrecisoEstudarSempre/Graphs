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

	public void addVertex(String label){					//função que adiciona o vértice do grafo
		vertexList[nVerts++] = new Vertex(label);
	}

	public void addEdge(int start, int end, int weight){	//função que adiciona uma aresta no vértice
		adjMat[start][end] = weight;						//o peso fica armazenado na matriz de adjacência
	}

	public void findAllShortestPaths(){
		int startTree = 0;
		vertexList[startTree].setIsInTree(true);								//adiciono o primeiro vértice do grafo na árvore
		nTree = 1;																//mudo o contador porque acabei de adicionar um nó na árvore

		for (int i=0; i<nVerts; i++) {											//inicializo o array de menores caminhos com as distâncias
			int distance = adjMat[startTree][i];								//das adjacências do primeiro vértice
			shortestPathArray[i] = new ShortestPathObject(startTree, distance);
		}

		while (nTree < nVerts) {														//até todos os nós estarem na árvore
			int shortestVertex = getMin();												//seleciona o vértice que tem o valor mínimo, essa função provê
			int shortestDistance = shortestPathArray[shortestVertex].getDistance();		//a direção que o algoritmo vai seguir

			if(shortestDistance == INFINITY){											//se todos forem infinito ou todos na árvore
				System.out.println("There are unreachable vertices");
				break;
			} else {
				currentVert = shortestVertex;
				startToCurrent = shortestDistance;
			}

			vertexList[currentVert].setIsInTree(true);									//coloca o vértice atual na árvore
			nTree++;																	//mais um nó entrou na árvore
			updateShortestPathArray();													//atualizo os valores mínimos
		}

		displayPaths();																	//exibo todos os nós

		nTree = 0;																		//limpa a ávore
		for (int i=0; i<nVerts; i++) {
			vertexList[i].setIsInTree(false);
		}		
	}

	private int getMin(){
		int shortestDistance = INFINITY;
		int shortestVertex = 0;

		for (int i=1; i<nVerts; i++) {																	//passo por todos os nós
			if (!vertexList[i].isInTree() && shortestPathArray[i].getDistance() < shortestDistance) {	//analiso se o nó já está na árvore e se a distância dele
				shortestDistance = shortestPathArray[i].getDistance();									//é a menor. Se for, atualizo as variáveis
				shortestVertex = i;
			}
		}
		return shortestVertex;
	}

	private void updateShortestPathArray(){
		int column = 1;
		while (column < nVerts) {														//percorre todas as colunas da "tabelinha"
			if (vertexList[column].isInTree()) {										//se a coluna da "tabelinha" já estiver na árvore não há
				column++;																//de atualizar sua entrada no array de valores mínimos
				continue;
			}

			int currentToTarget = adjMat[currentVert][column];							//variável que guarda o valor da distância do nó atual(RJ) até um nó de destino que não está na árvore, nem sempre esse vértice de destino tem uma conexão de fato
			int startToTarget = startToCurrent + currentToTarget;						//variável que guarda a distância total do início até o destino
			int shortestDistance = shortestPathArray[column].getDistance();				//recupera o valor do array de valores mínimos

			if (startToTarget < shortestDistance) {										//essa comparação é essencial pois se não há uma conexão de fato entre dois vértices, a soma total será INFINITY + algum valor de aresta e este é menor que somente INFINITY, logo não há necessidade de atualização nesse caso.
				shortestPathArray[column].setParentVert(currentVert);					//caso a soma seja de valores que realmente possuem conexão (RJ-SP-RP-BN) ela será menor que INFINITY e logo há porque atualizar
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