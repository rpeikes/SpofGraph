package spof;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import java.util.List;

public class GraphView extends JComponent {
    public static final Color NODE_COLOR = Color.BLACK;
    public static final Color SPOF_COLOR = Color.RED;
    public static final Color TEXT_COLOR = Color.WHITE;
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
        List<Node> graphMembers = graph.getNodes();
        int totalNodes = graphMembers.size();
        List<Node> marked = new ArrayList<Node>();
        int[] xCenters = new int[totalNodes];
        int[] yCenters = new int[totalNodes];
        double angleSpace = (2 * Math.PI) / totalNodes;


        for (int i = 0; i < totalNodes; i++) {
            //increment to next spot in the imaginary circle and add its center coordinates to the list

            double spacer = (i != 0 ? angleSpace * i : 0);
            xCenters[i] = getNodeCenterX(spacer);
            yCenters[i] = getNodeCenterY(spacer);

            //draw any connections to existing nodes
            List<Node> connections = graphMembers.get(i).getConnections();
            for (Node connection : connections) {
                if (marked.contains(connection)) {
                    g.setColor(LINE_COLOR);
                    g.drawLine(xCenters[i], yCenters[i], xCenters[graphMembers.indexOf(connection)],
                            yCenters[graphMembers.indexOf(connection)]);
                }
            }

            //add node to list of existing nodes
            marked.add(graphMembers.get(i));
        }

        //draw the nodes
        for (int i = 0; i < marked.size(); i++) {
            g.setColor(spofs.contains(marked.get(i)) ? SPOF_COLOR : NODE_COLOR);
            g.fillOval(xCenters[i] - NODE_RADIUS, yCenters[i] - NODE_RADIUS, 2 * NODE_RADIUS, 2 * NODE_RADIUS);
            g.setColor(TEXT_COLOR);
            g.drawString(marked.get(i).getName(),xCenters[i] , yCenters[i] );
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
