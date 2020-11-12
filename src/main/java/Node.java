import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    private String name;
    private List<Node> connections = new ArrayList<Node>();

    public Node(String name) {
        this.name = name;
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
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return name.equals(node.name) &&
                getConnections().equals(node.getConnections());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, getConnections());
    }
}
