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


    public GraphView(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void paintComponent(Graphics g) {
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

            double spacer = (i != 0 ? angleSpace * i : 0);
            xCenters[i] = getNodeCenterX(spacer);
            yCenters[i] = getNodeCenterY(spacer);

            //set appropriate color
            g.setColor(spofs.contains(graphMembers.get(i)) ? SPOF_COLOR : NODE_COLOR);

            //draw the node
            g.fillOval(xCenters[i] - NODE_RADIUS, yCenters[i] - NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS);

            //draw any connections to existing nodes
            for (Node connection : graphMembers.get(i).getConnections()) {
                if (drawn.contains(connection)) {
                    g.setColor(LINE_COLOR);
                    g.drawLine(xCenters[i], yCenters[i], xCenters[graphMembers.get(i).getConnections().indexOf(connection)],
                            yCenters[graphMembers.get(i).getConnections().indexOf(connection)]);
                }
            }

            //add node to list of existing nodes
            drawn.add(graphMembers.get(i));
        }
    }


    //find spot on imaginary circle's circumference using parametric equation
    private int getNodeCenterX(double angleSpace) {
        return (int) (X_CENTER + GRAPH_RADIUS * Math.cos(angleSpace));
    }

    private int getNodeCenterY(double angleSpace) {
        return (int) (Y_CENTER + GRAPH_RADIUS * Math.sin(angleSpace));
    }
}
