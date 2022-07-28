//Implement new comparator to prioritize nodes by cost in pq
static class CompareNodes implements Comparator<Node> {
    public int compare(Node n1, Node n2) {
        if (n1.cost > n2.cost) {
            return 1;
        } else if (n1.cost < n2.cost) {
            return -1;
        } else {
            return 0;
        }
    }
}

// Node class for for the grid of nodes in Path.
// Contains coordinates, and cost to get to that node, and a reference to the parent node.
public class Node{
    private int n;
    private int x;
    private int y;
    private int cost;
    private Node parent;
    private boolean visited;
/* --------------------------------- Methods -------------------------------- */
    // Constructor
    public Node(int n, int x, int y, int cost, Node parent){
        this.n = n;
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.parent = parent;
    }
    // Getters
    public int getN(){
        return n;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getCost(){
        return cost;
    }
    public Node getParent(){
        return parent;
    }
    public boolean getVisited(){
        return visited;
    }
    // Setters
    public void setCost(int cost){
        this.cost = cost;
    }
    public void setParent(Node parent){
        this.parent = parent;
    }
    public void setVisited(boolean visited){
        this.visited = visited;
    }

}

//Path class for MST. Has a size, and adjacency matrix, a vertex count.
public class Path {
    private int size;
    private int[][] adjacencyMatrix;
    private int nodeCount;
    private Node[] nodes;

    //Constructor for Path.
    public Path(int size){
        this.size = size;
        this.nodeCount = size*size;
        this.nodes = new Node[nodeCount];
        for(int i = 0; i < nodeCount; i++){
            this.nodes[i] = new Node(i, i%this.size, i/this.size, Integer.MAX_VALUE, null);
        }
        this.adjacencyMatrix = new int[size*size][size*size];
        //initialize adjacency matrix to -1
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                adjacencyMatrix[i][j] = -1;
            }
        }
        this.connect_adjacent_nodes_in_grid();
        this.MST_by_prims();
    }
    private void addEdge(int i, int j, int weight){
        adjacencyMatrix[i][j] = weight;
    }
    private void connect_adjacent_nodes_in_grid(){
        for(int node=0; i<nodeCount; i++){
            int x = i%size;
            int y = i/size;
        }
        if(x>0){
            this.addEdge(i,i-1, (int)(Math.random()*50));
        }
        if(x<size){
            this.addEdge(i,i+1, (int)(Math.random()*50));
        }
        if(y>0){
            this.addEdge(i, i-size, (int)(Math.random()*50));
        }
        if(y<size){
            this.addEdge(i, i+size, (int)(Math.random()*50));
        }
        
    }
    private void MST_by_prims(){
        // create priority queue to store nodeCount nodes
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new CompareNodes());
        // add nodes to pq
        for(int i = 0; i < nodeCount; i++){
            pq.add(nodes[i]);
        }
        //set the first node to have a cost of 0
        pq.peek().setCost(0);
        //while the queue is not empty
        while(!pq.isEmpty()){
            //get the node with the lowest cost
            pq.peek().setVisited(true);
            //pop the node with the lowest cost
            Node u = pq.poll();
            //get neighbors of u
            for(int i = 0; i < nodeCount; i++){
                //Todo: change to using arithmetic instead of for/if
                if(adjacencyMatrix[u.getN()][i] != -1){
                    //if the neighbor is not visited
                    if(!pq.peek().getVisited()){
                        //if the weight between u and the neighbor is less than the neighbor's known cost
                        if(adjacencyMatrix[u.getN()][i] < pq.peek().getCost()){
                            //set the neighbor's parent to u
                            pq.peek().setParent(u);
                            //set the neighbor's new cost
                            pq.peek().setCost(adjacencyMatrix[u.getN()][i]);
                        }
                    }
                }

            }
        }




        }
    //getter for nodes
    public Node[] getNodes(){
        return nodes;
    }
}
//main class
class Main{
public static void main(String[] args){
    Path p = new Path(3);
    Node[] nodes = p.getNodes();
    for(int i = 0; i < nodes.length; i++){
        System.out.println(nodes[i].getN() + " " + nodes[i].getParent());
    }
}
}

