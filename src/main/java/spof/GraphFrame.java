package spof;

import javax.swing.*;

public class GraphFrame extends JFrame {
    GraphView view;

    public GraphFrame(){
        setSize(view.WIDTH, view.HEIGHT);
        add(view);
    }
}
