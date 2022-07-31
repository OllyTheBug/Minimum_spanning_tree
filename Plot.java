
//import require classes and packages  
import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;

//Extends JPanel class  
public class Plot extends JPanel {
    // declare variables
    private int size;
    private Node[] nodes;

    // constructor
    public Plot(int size, Node[] nodes) {
        // set background color
        setBackground(Color.WHITE);
        this.size = size;
        this.nodes = nodes;
    }

    protected void paintComponent(Graphics grf) {
        // create instance of the Graphics to use its methods
        super.paintComponent(grf);
        Graphics2D graph = (Graphics2D) grf;

        // Sets the value of a single preference for the rendering algorithms.
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // get width and height
        int width = getWidth();
        int height = getHeight();

        // plot each node
        for (int i = 0; i < nodes.length; i++) {
            int x = nodes[i].getX();
            int y = nodes[i].getY();
            int n = nodes[i].getN();
            int cost = nodes[i].getCost();
            Node p = nodes[i].getParent();
            Node[] neighbors = new Node[4];
            // for each node neighbor, add nodes[i] to its neighbors array
            for (int j = 0; j < 4; j++) {
                if (nodes[i].getNeighbors()[j] != 0) {
                    neighbors[j] = nodes[i].getNeighbors()[j];
                }
            }

            
            // draw node
            graph.setColor(Color.BLACK);
            graph.fillOval(x * width / size + (width / size / 2), y * height / size + (height / size / 2), 5, 5);
            // print n and p.n to console

            // draw a line from parent to node
            // plotParent(graph, width, height, x, y, n, p);

            // draw a line from node to neighbor
            plotNeighbors(graph, width, height, x, y, n, neighbors);

        }

        // // draw evenly spaced points on the grid
        // for(int i = 0; i < size; i++){
        // for(int j = 0; j < size+1; j++){
        // // center the grid with (width/size/2,height/size/2) margin
        // int x = (width) / size * i + (width/size/2);
        // int y = (height) / size * j - (height/size/2);
        // //draw point and fill it with color
        // //graph.drawOval(x, y, 5, 5);
        // graph.fillOval(x, y, 5, 5);

        // }
        // }

    }

    private void plotParent(Graphics2D graph, int width, int height, int x, int y, int n, Node p) {
        if (p != null) {
            System.out.println(n + " " + p.getN());
            graph.setColor(Color.RED);
            graph.drawLine(x * width / size + (width / size / 2), y * height / size + (height / size / 2),
                    p.getX() * width / size + (width / size / 2), p.getY() * height / size + (height / size / 2));
        }
    }

    private void plotNeighbors(Graphics2D graph, int width, int height, int x, int y, int n, Node[] neighbors) {
        if (neighbors != null) {
            for (int i = 0; i < neighbors.length; i++) {
                graph.setColor(Color.BLUE);
                graph.drawLine(x * width / size + (width / size / 2), y * height / size + (height / size / 2),
                        neighbors[i].getX() * width / size + (width / size / 2),
                        neighbors[i].getY() * height / size + (height / size / 2));
            }
        }
    }

    // main() method start
    public void draw() {
        // create an instance of JFrame class
        JFrame frame = new JFrame();
        // set size, layout and location for frame.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Plot(this.size, this.nodes));
        frame.setSize(400, 400);
        frame.setLocation(200, 200);
        frame.setVisible(true);
    }
}