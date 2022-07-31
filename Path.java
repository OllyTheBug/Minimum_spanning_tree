import java.util.PriorityQueue;

//Path class for MST. Has a size, and adjacency matrix, a vertex count.
public class Path {
    private int size;
    private int[][] adjacencyMatrix;
    private int nodeCount;
    private Node[] nodes;

    // Constructor for Path.
    public Path(int size) {
        this.size = size;
        this.nodeCount = size * size;
        this.nodes = new Node[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            this.nodes[i] = new Node(i, i % this.size, i / this.size, Integer.MAX_VALUE, null);
            this.nodes[i].setNeighbors(getNodeNeighbors(this.nodes[i]));
        }
        
        this.adjacencyMatrix = new int[size * size][size * size];
        // initialize adjacency matrix to -1
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.addEdge(i, j, -1);
            }
        }

        this.connect_adjacent_nodes_in_grid();
        this.MST_by_prims();
    }

    private void addEdge(int i, int j, int weight) {
        adjacencyMatrix[i][j] = weight;
    }

    private int getEdge(int u, int v){
        return adjacencyMatrix[u][v];
    }

    public int[] getNodeNeighbors(Node node) {
        int x = node.getX();
        int y = node.getY();
        int[] neighbors = new int[4];
        if (x > 0) {
            neighbors[0] = node.getN() - 1;
        }
        if (x < size - 1) {
            neighbors[1] = node.getN() + 1;
        }
        if (y > 0) {
            neighbors[2] = node.getN() - size;
        }
        if (y < size - 1) {
            neighbors[3] = node.getN() + size;
        }
        return neighbors;
    }

    private void connect_adjacent_nodes_in_grid() {
        for (int noden = 0; noden < nodeCount; noden++) {
            int x = noden % size;
            int y = noden / size;

            int[] neighbors = getNodeNeighbors(nodes[noden]);
            for (int i = 0; i < 4; i++) {
                if (neighbors[i] != 0) {
                    // add edge between neighbors with random weight
                    addEdge(noden, neighbors[i], (int) (Math.random() * 10));
                }
            }

        }
    }

    private void MST_by_prims() {
        // create priority queue to store nodeCount nodes
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new CompareNodes());
        //add each node in this.nodes to the priority queue
        //keys
        int[] keys = new int[nodeCount];
        
        for (int i = 0; i < nodeCount; i++) {
            pq.add(this.nodes[i]);
            keys[i] = Integer.MAX_VALUE;
        }
        keys[0] = 0;
        //While pq is not empty; while nodes are left
        while (!pq.isEmpty()) {
            //remove the node with the lowest cost from pq
            Node u = pq.poll();
            //set the node's visited flag to true
            u.setVisited(true);
            int[] neighbors = u.getNeighbors();
            //for each neighbor of u
            for (int i = 0; i < 4; i++){
                int u_v = getEdge(u.getN(), neighbors[i]);
                //if edge is less than keys[v]
                if (u_v < keys[neighbors[i]]) {
                    //set keys[v] to edge
                    keys[neighbors[i]] = u_v;
                    //set the parent of v to u
                    nodes[neighbors[i]].setParent(u);


                }
            }

        }

    }

    // getter for nodes
    public Node[] getNodes() {
        return nodes;
    }
    
}