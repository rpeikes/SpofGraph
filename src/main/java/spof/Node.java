package spof;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    public String getName() {
        return name;
    }

    private String name;

    public void setConnections(List<Node> connections) {
        this.connections = connections;
    }

    private List<Node> connections = new ArrayList<Node>();

    public Node(String name) {
        this.name = name;
    }
    public Node(String name, List<Node> connections) {

        this.name = name;
        this.connections = connections;
    }

    public List<Node> getConnections() {
        return connections;
    }

    public void addConnection(Node node) {
        connections.add(node);
    }

    public void removeConnection(Node node) {
        connections.remove(node);
    }

    public boolean isConnectedTo(Node node) {
        return connections.contains(node);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(name, node.name);
    }

    @Override
    public String toString() {
        return name;
    }

}
