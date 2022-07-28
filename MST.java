
//Path class for MST. Has a size, and adjacency matrix, a vertex count.
public class Path {
    private int size;
    private int[][] adjacencyMatrix;
    private int nodeCount;

    //Constructor for Path.
    public Path(int size){
        this.size = size;
        this.nodeCount = size*size;
        this.adjacencyMatrix = new int[size**2][size**2];
        //initialize adjacency matrix to -1
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                adjacencyMatrix[i][j] = -1;
            }
        }
    }
    private void addEdge(int i, int j, int weight){
        adjacencyMatrix[i][j] = weight;
    }
    private void connect_adjacent_nodes_in_grid(){
        for(int node=0; i<nodeCount; i++){
            int x = i%size;
            int y = i/size;
        }
    }
}