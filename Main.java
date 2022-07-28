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

