package spof;

import javax.swing.*;
import java.awt.*;

public class GraphFrame extends JFrame {
    private GraphView view;
    public static final int MARGIN = 100;



    public GraphFrame(GraphView view) {
        this.view = view;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("SPOF Identifier");
        setSize(view.WIDTH + MARGIN, view.HEIGHT + MARGIN);
        add(view);
        view.repaint();
    }
}
