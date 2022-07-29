    //import require classes and packages  
    import java.awt.*;  
    import javax.swing.*;  
    import java.awt.geom.*;  
      
    //Extends JPanel class  
    public class Plot extends JPanel{  
        //declare variables
        private int size;
        private int margin = 30;
        //constructor
        public Plot(int size){  
            //set background color  
            setBackground(Color.WHITE);
            this.size = size;
        }
          
        protected void paintComponent(Graphics grf){  
            //create instance of the Graphics to use its methods  
            super.paintComponent(grf);  
            Graphics2D graph = (Graphics2D)grf;  
              
            //Sets the value of a single preference for the rendering algorithms.  
            graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  
              
            // get width and height  
            int width = getWidth();  
            int height = getHeight();  

            // draw evenly spaced points on the grid
            for(int i = 0; i < size; i++){
                for(int j = 0; j < size; j++){
                    int x = (width - margin) / size * i + margin;
                    int y = (height - margin) / size * j + margin;
                    graph.drawOval(x, y, 5, 5);
                }
            }

            // draw a circle
            Ellipse2D.Double circle = new Ellipse2D.Double(width/2-50, height/2-50, 100, 100);
            graph.setPaint(Color.BLACK);
            graph.fill(circle);
              
        }
          
        //main() method start  
        public void draw(){  
            //create an instance of JFrame class  
            JFrame frame = new JFrame();  
            // set size, layout and location for frame.  
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
            frame.add(new Plot(this.size));  
            frame.setSize(400, 400);  
            frame.setLocation(200, 200);  
            frame.setVisible(true);  
        }  
    }  