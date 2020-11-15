package spof;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import java.util.List;

public class GraphView extends JComponent {
    public static final Color NODE_COLOR = Color.BLACK;
    public static final Color SPOF_COLOR = Color.RED;
    public static final Color LINE_COLOR = Color.BLACK;
    public static final int HEIGHT = 800;
    public static final int WIDTH = 800;
    public static final int NODE_RADIUS = 25;
    public static final int X_CENTER = WIDTH / 2;
    public static final int Y_CENTER = HEIGHT / 2;
    public static final int GRAPH_RADIUS = 200;


    private Graph graph;

    public GraphView(ArrayList<Node> nodes) {
        this.graph = new Graph((nodes));
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        paintGraph(g);
    }

    public void paintGraph(Graphics g) {

        List<Node> spofs = graph.findSPF();
        List<Node> graphMembers = graph.traverseGraph();
        int totalNodes = graphMembers.size();
        List<Node> drawn = new ArrayList<Node>();
        int[] xCenters = new int[graphMembers.size()];
        int[] yCenters = new int[graphMembers.size()];
        double angleSpace = (2 * Math.PI) / totalNodes;


        for (int i = 0; i < totalNodes; i++) {
            //increment to next spot in the imaginary circle and its center coordinates to the list
            angleSpace *= i;
            xCenters[i] = (int) getNodeCenterX(angleSpace);
            yCenters[i] = (int) getNodeCenterY(angleSpace);

            //set appropriate color
            g.setColor(spofs.contains(graphMembers.get(i)) ? SPOF_COLOR : NODE_COLOR);

            //draw the node
            g.fillOval(xCenters[i]-NODE_RADIUS, yCenters[i]-NODE_RADIUS, NODE_RADIUS, NODE_RADIUS);

            //draw any connections to existing nodes
            for(Node connection : graphMembers.get(i).getConnections()){
                if (drawn.contains(connection)){
                    g.setColor(LINE_COLOR);
                    g.drawLine(xCenters[i], yCenters[i],xCenters[graphMembers.get(i).getConnections().indexOf(connection)],
                            yCenters[graphMembers.get(i).getConnections().indexOf(connection)]);
                }
            }

            //add node to list of existing nodes
            drawn.add(graphMembers.get(i));
        }
    }


    private double getNodeCenterX(double angleSpace) {
        return X_CENTER + GRAPH_RADIUS * Math.cos(angleSpace);
    }

    private double getNodeCenterY(double angleSpace) {
        return Y_CENTER + GRAPH_RADIUS * Math.sin(angleSpace);
    }
}
