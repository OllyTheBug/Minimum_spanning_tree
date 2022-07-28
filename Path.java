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

    private void connect_adjacent_nodes_in_grid() {
        for (int node = 0; node < nodeCount; node++) {
            int x = node % size;
            int y = node / size;

            if (x > 0) {
                this.addEdge(node, node - 1, (int) (Math.random() * 50));
            }
            if (x < (size - 1)) {
                this.addEdge(node, node + 1, (int) (Math.random() * 50));
            }
            if (y > 0) {
                this.addEdge(node, node - size, (int) (Math.random() * 50));
            }
            if (y < (size - 1)) {
                this.addEdge(node, node + size, (int) (Math.random() * 50));
            }
        }
    }

    private void MST_by_prims() {
        // create priority queue to store nodeCount nodes
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new CompareNodes());
        // add nodes to pq
        for (int i = 0; i < nodeCount; i++) {
            pq.add(nodes[i]);
        }
        // set the first node to have a cost of 0
        pq.peek().setCost(0);
        // while the queue is not empty
        while (!pq.isEmpty()) {
            // get the node with the lowest cost
            pq.peek().setVisited(true);
            // pop the node with the lowest cost
            Node u = pq.poll();
            // get neighbors of u
            for (int i = 0; i < nodeCount; i++) {
                // Todo: change to using arithmetic instead of for/if
                if (adjacencyMatrix[u.getN()][i] != -1) {
                    // if the neighbor is not visited
                    if (!nodes[i].getVisited()) {
                        // if the weight between u and the neighbor is less than the neighbor's known
                        // cost
                        if (adjacencyMatrix[u.getN()][i] < pq.peek().getCost()) {
                            // set the neighbor's parent to u
                            pq.peek().setParent(u);
                            // set the neighbor's new cost
                            pq.peek().setCost(adjacencyMatrix[u.getN()][i]);
                        }
                    }
                }

            }
        }

    }

    // getter for nodes
    public Node[] getNodes() {
        return nodes;
    }
}