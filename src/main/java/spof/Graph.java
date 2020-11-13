package spof;

import java.util.*;

public class Graph {
    private List<Node> nodes;

    public Graph(List<Node> nodes) {
        this.nodes = nodes;
    }

    private void removeNode(Node node, List<Node> subnet){
        subnet.remove(node);
        for (Node testNode : subnet) {
            testNode.getConnections().remove(node);
        }
    }
    private List<Node> traverseSubnet(List<Node> subnet) {
        int index = 0;
        List<Node> visited = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(subnet.get(index));
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                for (Node node : subnet.get(subnet.indexOf(current)).getConnections()) {
                    stack.push(node);
                }
            }

        }
        return visited;
    }
    private List<Node> traverseSubnet(List<Node> subnet, int index) {
        List<Node> visited = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(subnet.get(index));
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                for (Node node : subnet.get(subnet.indexOf(current)).getConnections()) {
                    stack.push(node);
                }
            }

        }
        return visited;
    }

    private int getSubnetSize(List<Node> graph) {
        return traverseSubnet(graph).size();
    }

    public List<Node> traverseGraph() {
        return traverseSubnet(nodes);
    }

    public List<Node> findSPF() {
        List<Node> SPFs = new ArrayList<>();

        for (Node node : nodes) {
            List<Node> testList = new ArrayList<>();
            //make deep copy of graph
            for (Node nodeA : nodes) {
                List<Node> connection = new ArrayList<>(nodeA.getConnections());
                testList.add(new Node(nodeA.getName(), connection));
            }

            //remove specified node
            removeNode(node,testList);

            //traverse graph without specified node and add node to SPFs if traversal did not visit every node
            if (getSubnetSize(testList) < testList.size()) {
                SPFs.add(node);
            }
        }
        return SPFs;
    }

    public List<Integer> getNumSubnets() {
        List<Node> SPFs = this.findSPF();
        List<List<List<Node>>> allSPFpaths = new ArrayList<>();

        //traverse graph from each node without the spf
        //save paths in a list
        for (Node spf : SPFs) {
            List<Node> testList = new ArrayList<>();
            List<List<Node>> paths = new ArrayList<>();

            //deep copy of Graph
            for (Node node : nodes) {
                List<Node> connection = new ArrayList<>(node.getConnections());
                testList.add(new Node(node.getName(), connection));
            }
            //remove specified SPF from Graph copy
            removeNode(spf,testList);

            //traverse graph from each node without the spf
            //save paths in a list
            for (int index = 0; index < testList.size(); index++) {

                if (!paths.contains(traverseSubnet(testList,index))) {
                    paths.add(traverseSubnet(testList,index));
                }

            }
            allSPFpaths.add(paths);
        }
        //write list of unique paths
        List<Integer> SPFnumSubnets = new ArrayList<>();
        for (List<List<Node>> path : allSPFpaths) {
            SPFnumSubnets.add(allSPFpaths.size());
        }
        //return list of sizes
        return SPFnumSubnets;
    }
}
