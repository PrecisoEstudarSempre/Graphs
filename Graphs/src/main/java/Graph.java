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

	public void depthFirstSearch(){									//método que executa a busca em profundidade
		vertexs[0].setWasVisited(true);								//inicia pelo ponto de partida e já marca ele como visitado
		this.displayVertex(0);										//exibe o nó
		stack.push(0);												//empilha o nó
		

		while (!stack.empty()) {									//enquanto houverem nós na pilha a busca não terminou
			int vertexIndex = this.getAdjUnvisitedVertex(stack.peek());		//recupera o próximo nó adjacente não visitado
			if(vertexIndex==-1){											//não existe ? Remove um nó da pilha
				stack.pop();
			} else {														//existe
				vertexs[vertexIndex].setWasVisited(true);					//executa os passos da regra 1: visite, marque como visitado e empilhe
				this.displayVertex(vertexIndex);
				stack.push(vertexIndex);
			}
		}			

		for (int i=0; i<currentAmountOfVerxtes; i++) {						//Se chegou aqui é porque não haviam mais nós na pilha
			vertexs[i].setWasVisited(false);								//Reseta todos os nós novamente para que a busca possa ser executada depois
		}
	}

	private int getAdjUnvisitedVertex(int vertexIndex) {									//método que recupera o próximo nó adjacente não visitado
		for (int i=0; i<currentAmountOfVerxtes; i++) {										//para a quantidade atual de nós
			if (adjacencyMatrix[vertexIndex][i] == 1 && !vertexs[i].getWasVisited()) {		//utiliza a matriz de adjacências para determinar se para um nó existem nós adjacentes a ele
				return i;																	//caso exista, retorna a posição deste nó no array de nós
			}
		}
		return -1;																			//caso não exista, retorna -1
	}
}