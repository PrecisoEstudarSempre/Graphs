import java.util.List;
import java.util.LinkedList;

class AdjacencyMatrix {

	private static final int DOTS_QTD = 4;								//quantidade de vértices no grafo
	private char[] dots = new char[DOTS_QTD];							//array com os vértices do grafo

	AdjacencyMatrix(){
		dots[0] = 'A';
		dots[1] = 'B';
		dots[2] = 'C';
		dots[3] = 'D';
	}

	private int[][] buildAdjacencyMatrix(){
		int[][] adjacencyMatrix = new int[DOTS_QTD][DOTS_QTD];			//Matriz NxN
		this.initializeAdjacencyMatrix(adjacencyMatrix);

		adjacencyMatrix[0][1] = 1;										//AB
		adjacencyMatrix[0][3] = 1;										//AD

		adjacencyMatrix[1][0] = 1;										//BA
		adjacencyMatrix[1][2] = 1;										//BC
		adjacencyMatrix[1][3] = 1;										//BD

		adjacencyMatrix[2][1] = 1;										//CB

		adjacencyMatrix[3][0] = 1;										//DA
		adjacencyMatrix[3][1] = 1;										//DB

		return adjacencyMatrix;		
	}

	private void initializeAdjacencyMatrix(int[][] adjacencyMatrix){
		for(int i=0; i<adjacencyMatrix.length; i++){
			for(int j=0; j<adjacencyMatrix[i].length; j++){
				adjacencyMatrix[i][j] = 0;								//Inicialização da matriz
			}
		}
	}

	private void displayAdjacencyMatrix(int[][] adjacencyMatrix){
		for(int i=0; i<adjacencyMatrix.length; i++){					//itero as linhas
			for(int j=0; j<adjacencyMatrix[i].length; j++){				//itero as colunas
				if(adjacencyMatrix[i][j] != 0){							//quero imprimir somente as ligações
					System.out.println(dots[i] + "->" + dots[j]);		//Imprime as arestas
				}
			}
		}
	}

	public static void main(String[] args) {
		AdjacencyMatrix adjMatrix = new AdjacencyMatrix();
		int[][] adjacencyMatrix = adjMatrix.buildAdjacencyMatrix();
		adjMatrix.displayAdjacencyMatrix(adjacencyMatrix);
	}
}

class AdjacencyList {
	private static final int DOTS_QTD = 4;								//quantidade de vértices no grafo
	private char[] dots = new char[DOTS_QTD];							//array com os vértices do grafo

	AdjacencyList(){
		dots[0] = 'A';
		dots[1] = 'B';
		dots[2] = 'C';
		dots[3] = 'D';
	}

	/**
	* Constrói a lista de adjacências.
	*/
	private List<List<Character>> buildAdjacencyList(){
		List<List<Character>> adjacencyList = new LinkedList<>();

		List<Character> adjacencyToA = new LinkedList<>();				//lista encadeada
		adjacencyToA.add('B');											//AB
		adjacencyToA.add('D');											//AD

		List<Character> adjacencyToB = new LinkedList<>();				//lista encadeada
		adjacencyToB.add('A');											//BA
		adjacencyToB.add('C');											//BC
		adjacencyToB.add('D');											//BD

		List<Character> adjacencyToC = new LinkedList<>();				//lista encadeada
		adjacencyToC.add('B');											//CB

		List<Character> adjacencyToD = new LinkedList<>();				//lista encadeada
		adjacencyToD.add('A');											//DA
		adjacencyToD.add('B');											//DB

		adjacencyList.add(adjacencyToA);								//Adiciona todas as adjacências em um lista encadeada só.
		adjacencyList.add(adjacencyToB);
		adjacencyList.add(adjacencyToC);
		adjacencyList.add(adjacencyToD);

		return adjacencyList;
	}

	/**
	* Imprime todas as adjacências.
	*/
	private void displayAdjacencyList(List<List<Character>> adjacencyList){
		for(int i=0; i<adjacencyList.size(); i++){
			System.out.println(dots[i] + "->" + adjacencyList.get(i));		//Imprime as arestas
		}
	}

	public static void main(String[] args) {
		AdjacencyList adjList = new AdjacencyList();
		List<List<Character>> adjacencyList = adjList.buildAdjacencyList();
		adjList.displayAdjacencyList(adjacencyList);
	}
}