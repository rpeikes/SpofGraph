package spof;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GraphView extends JComponent {
    public static final Color NODE_COLOR = Color.BLACK;
    public static final Color SPOF_COLOR = Color.RED;
    public static final Color LINE_COLOR = Color.BLACK;
    public static final int HEIGHT = 500;
    public static final int WIDTH = 500;
    public static final int Y_CENTER = HEIGHT/2;
    public static final int X_CENTER = WIDTH/2;
    public static final int NODE_SIZE = 50;

    private Graph graph;

    public GraphView(ArrayList<Node> nodes){
        this.graph = new Graph((nodes));
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        paintGraph(g);
    }

    public void paintGraph(Graphics g) {
        for (Node node: graph.findSPF()){
            g.setColor(SPOF_COLOR);

        }

    }
}
