//main class
class Main{
public static void main(String[] args){
    int size;
    //check whether arguments were passed in
    if(args.length == 0){
        size = 3;
    }
    else{
        size = Integer.parseInt(args[0]);
    }
    Path p = new Path(size);
    Node[] nodes = p.getNodes();
    for(int i = 0; i < nodes.length; i++){
        System.out.println(nodes[i].getN() + " " + nodes[i].getParent());
    }
    Plot plot = new Plot(size);
    plot.draw();
}
}

