package spof;

import javax.swing.*;
import java.awt.*;

public class GraphFrame extends JFrame {
    private GraphView view;
    public static final int MARGIN = 100;
    Graphics2D graphics2D;


    public GraphFrame(GraphView view) {
        this.view = view;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(view.WIDTH + MARGIN, view.HEIGHT + MARGIN);
        setLayout(new BorderLayout());
        add(view, BorderLayout.CENTER);
        view.repaint();
    }
}
