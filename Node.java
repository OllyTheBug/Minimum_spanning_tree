import java.util.Comparator;

//Implement new comparator to prioritize nodes by cost in pq
class CompareNodes implements Comparator<Node> {
    public int compare(Node n1, Node n2) {
        if (n1.getCost() > n2.getCost()) {
            return 1;
        } else if (n1.getCost() < n2.getCost()) {
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