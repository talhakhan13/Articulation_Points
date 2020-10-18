package a_pts;

public class Graph {
	
	public boolean[][] adj;

	public Graph(int numVertices, int[] edges) {
		// initialize adjacency list
		adj= new boolean[numVertices][numVertices];
		for (int i = 0; i < numVertices; i++) {
			for (int j = 0; j < numVertices; j++) {
				adj[i][j] = false;
			}
		}
		// add the edges
		for(int i = 0; i < edges.length; i += 2) {
			adj[edges[i]][edges[i + 1]] = true;
			adj[edges[i + 1]][edges[i]] = true;
		}
	}
	
	public static void AP(boolean[][] adj, int[] disc, int[] low, boolean[] visited, int[] parent, boolean[] AP, int currVertex, int time, int numVertices){
		disc[currVertex] = time;
		low[currVertex] = time;
		visited[currVertex] = true;
		int child = 0;
		
		
		for (int i = 0; i < numVertices; i++) {
			// if edge exists b/w curr and i
			if(adj[currVertex][i] == true) {
				// not visited before
				if(visited[i] == false) {
					child += 1;
					parent[i] = currVertex;
					// recursion
					AP(adj, disc, low, visited, parent, AP, i, time + 1, numVertices);
					// low is min of itself of min of one of its children
					low[currVertex] = Math.min(low[currVertex], low[i]);
					
					// if node is root
					if(parent[currVertex] == -1 && child > 1) {
						AP[currVertex] = true;
					}
					//if node is not root
					// if it has child that has back edge to some ancestor
					if(parent[currVertex] != -1 && low[i] >= disc[currVertex]) {
						AP[currVertex] = true;
					}
				}
				else if(parent[currVertex] != i) {
					low[currVertex] = Math.min(low[currVertex], disc[i]);
				}
			}
		}
		
		
		
	}

	
	public static void main(String[] args) {
		// initialzie the graph
		int numVertices = 7;
		int[] edges = new int[]{0,1, 1,2, 1,3, 3,4, 4,5, 4,6};
		Graph g = new Graph(numVertices, edges);
		
		//intialize the necessary stuff
		int[] disc = new int[numVertices];
		int[] low = new int[numVertices];
		boolean[] visited = new boolean[numVertices];
		int[] parent = new int[numVertices];
		boolean[] AP = new boolean[numVertices];
		for(int i = 0; i < numVertices; i++) {
			disc[i] = 0;
			low[i] = 0;
			visited[i] = false;
			parent[i] = -1;
			AP[i] = false;
			
		}
		AP(g.adj, disc, low, visited, parent, AP, 0, 0, numVertices);
		
		for(int i = 0; i < numVertices; i++) {
			System.out.println(AP[i]);
		}
		
	}
	
	
}
