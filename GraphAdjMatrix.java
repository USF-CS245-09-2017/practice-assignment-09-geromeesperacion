/**
 * 
 * @author geromeesperacion
 *
 */

/*
 * Creates a minimum spanning tree. Design inspired by class notes and GeeksforGeeks.org
 */
public class GraphAdjMatrix implements Graph {
	
	private int[][] edges;
	private int vertices;
	
	/*
	 * Constructs and returns a graph with the number of vertices passed as the argument. 
	 * Vertices have IDs, numbered 0, 1, …, vertices-1. No edges exist between vertices at instantiation.
	 */
	public GraphAdjMatrix (int vertices) {
		this.edges = new int[vertices][vertices];
		this.vertices = vertices;
	}

	/*
	 * Adds an undirected edge or weight w between two vertices.
	 */
	public void addEdge(int v1, int v2, int weight) {
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
	}

	/*
	 * Returns the weight of the edge between vertices v1 and v2. Returns 0 if 
	 * such an edge does not exist.
	 */
	public int getEdge(int v1, int v2) {
		
		int weight = 0;
		
		if(edges[v1][v2] > 0) {
			weight = edges[v1][v2];
		}
		
		return weight;
	}
	
	/*
	 * Returns the number of outgoing degrees of a vertex
	 */
	public int outdegree(int vertex) {
		
		int degree = 0;
		
		for(int i = 0; i < edges.length; i++) {
			if(edges[vertex][i] != -1 && edges[vertex][i] != 0) {
				degree++;
			}
		}

		return degree;
	}

	
	/*
	 * finds the vertex with minimum key
	 */
	public int minKey(int key[], boolean mstSet[]) {
		int min = Integer.MAX_VALUE;
		int min_index = -1;
		
		for(int i = 0; i < vertices ; i++) {
			if(mstSet[i] == false && key[i] < min) {
				min = key[i];
				min_index = i;
			}
		}
		
		return min_index;
	}
	
	/*
	 * Creates a minimum spanning tree from the source graph and returns
	 * the weight of the tree
	 */
	public int createSpanningTree() {
		
		int weight = 0;
		int parent[] = new int[vertices];
		int key[] = new int[vertices];
		boolean msTree[] = new boolean[vertices];
		
		for(int i = 0; i < vertices; i++) {
			key[i] = Integer.MAX_VALUE;
			msTree[i] = false;
		}
		
		//pick first node in the tree
		key[0] = 0;
		parent[0] = -1; //root of the MST
		
		for(int i = 0; i < vertices - 1; i++) {
			
			int u = minKey(key, msTree);
			msTree[u] = true;
			
			for(int j = 0; j < vertices; j++) {
				if(edges[u][j] != 0 && msTree[j] == false && edges[u][j] < key[j]) {
					parent[j] = u;
					key[j] = edges[u][j];
				}
			}
		}
		
		for(int i = 1; i < vertices; i++) {
			weight = weight + edges[i][parent[i]];
		}
		
		return weight; 
	}
	
	//from assignment 8 - left unimplemented
	public void addEdge(int v1, int v2) {
		// TODO Auto-generated method stub
		
	}

	//from assignment 8 - left unimplemented
	public void topologicalSort() {
		// TODO Auto-generated method stub
		
	}
	
}
