package spof;

import javax.swing.*;
import java.awt.*;

public class GraphFrame extends JFrame {
    private GraphView view;


    public GraphFrame(GraphView view) {
        this.view = view;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("SPOF Identifier");
        setSize(view.WIDTH, view.HEIGHT);
        add(view);
        view.repaint();
    }
}
