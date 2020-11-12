package spof;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Graph {
    private List<Node> nodes;

    public Graph(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Node> traverseGraph() {
        int index = 0;
        List<Node> visited = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(nodes.get(index));
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                for (Node node: current.getConnections()) {
                    stack.push(node);
                }
            }

        }
        return visited;
    }

    public List<Node> findSPF() {
        List<Node> SPFs = new ArrayList<>();
        for (Node node: nodes ) {
            List<Node> testList = new ArrayList<>();
            for (Node nodeA: nodes) {
                List<Node> connection = new ArrayList<>(nodeA.getConnections());
                testList.add(new Node(nodeA.getName(), connection));
            }
            testList.remove(node);
            for (Node testNode: testList ) {
                testNode.getConnections().remove(node);
            }
            int index = 0;
            List<Node> visited = new ArrayList<>();
            Stack<Node> stack = new Stack<>();
            stack.push(testList.get(index));
            while (!stack.isEmpty()) {
                Node current = stack.pop();
                if (!visited.contains(current)) {
                    visited.add(current);
                    for (Node connection: testList.get(testList.indexOf(current)).getConnections()) {
                        stack.push(connection);
                    }
                }

            }
            if (visited.size() < testList.size()) {
                SPFs.add(node);
            }
        }
        return SPFs;
    }

    public int getNumSubnets() {
        List<Node> SPFs= this.findSPF();
        //traverse graph from each node without the spf
        //save visited list in a list
        //remove duplicates from list
        //return size
        return SPFs.size();
    }
}
