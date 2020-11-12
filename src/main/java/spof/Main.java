package spof;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Node node1 = new Node("1");
        Node node2 = new Node("2");
        Node node3 = new Node("3");
        Node node4 = new Node("4");
        Node node5 = new Node("5");
        Node node6 = new Node("6");
        List<Node> connections1 = new ArrayList();
        List<Node> connections2 = new ArrayList();
        List<Node> connections3 = new ArrayList();
        List<Node> connections4 = new ArrayList();
        List<Node> connections5 = new ArrayList();
        List<Node> connections6 = new ArrayList();
        connections1.add(node2);
        connections1.add(node5);
        connections2.add(node1);
        connections2.add(node5);
        connections2.add(node3);
        connections3.add(node2);
        connections3.add(node4);
        connections3.add(node6);
        connections4.add(node3);
        connections4.add(node6);
        connections5.add(node1);
        connections5.add(node2);
        connections6.add(node3);
        connections6.add(node4);

        node1.setConnections(connections1);
        node2.setConnections(connections2);
        node3.setConnections(connections3);
        node4.setConnections(connections4);
        node5.setConnections(connections5);
        node6.setConnections(connections6);

        List<Node> nodes = new ArrayList<>();
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        nodes.add(node5);
        nodes.add(node6);

        Graph graph = new Graph(nodes);
        List<Node> SPFs = graph.findSPF();
        List<Integer> SPFsubnets = graph.getNumSubnets();

        for (int i = 0; i < SPFs.size(); i++) {
            System.out.println("SPF" + i + ": " + SPFs.get(i));
            System.out.println("SUBNET" + i + ": " + SPFsubnets.get(i));
        }
    }
}
