package spof;

import java.util.*;

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
                for (Node node : current.getConnections()) {
                    stack.push(node);
                }
            }

        }
        return visited;
    }

    public List<Node> findSPF() {
        List<Node> SPFs = new ArrayList<>();
        for (Node node : nodes) {
            List<Node> testList = new ArrayList<>();
            for (Node nodeA : nodes) {
                List<Node> connection = new ArrayList<>(nodeA.getConnections());
                testList.add(new Node(nodeA.getName(), connection));
            }
            testList.remove(node);
            for (Node testNode : testList) {
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
                    for (Node connection : testList.get(testList.indexOf(current)).getConnections()) {
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

    public List<Integer> getNumSubnets() {
        List<Node> SPFs = this.findSPF();
        List<List<List<Node>>> allSPFpaths = new ArrayList<>();

        //traverse graph from each node without the spf
        //save paths in a list
        for (Node spf : SPFs) {
            List<Node> testList = new ArrayList<>();
            List<List<Node>> paths = new ArrayList<>();
            for (Node node : nodes) {
                List<Node> connection = new ArrayList<>(node.getConnections());
                testList.add(new Node(node.getName(), connection));
            }
            testList.remove(spf);
            for (Node testNode : testList) {
                testNode.getConnections().remove(spf);
            }
            for (int index = 0; index <testList.size() ;index ++) {
                List<Node> visited = new ArrayList<>();
                Stack<Node> stack = new Stack<>();
                stack.push(testList.get(index));
                while (!stack.isEmpty()) {
                    Node current = stack.pop();
                    if (!visited.contains(current)) {
                        visited.add(current);
                        for (Node connection : testList.get(testList.indexOf(current)).getConnections()) {
                            stack.push(connection);
                        }
                    }
                }
                paths.add(visited);
            }
            allSPFpaths.add(paths);
        }
        //remove duplicates from list
        List<Integer> SPFnumSubnets = new ArrayList<>();
        for (List<List<Node>> paths : allSPFpaths) {
            List<List<Node>> noDuplicates = new ArrayList<>();
            noDuplicates.add(paths.get(0));
            for (List<Node> path : paths) {
                int doubles = 0;
                for (List<Node> temp: noDuplicates) {
                    if(temp.containsAll(path)) {
                        doubles ++;
                    }
                }
                if (doubles == 0) {
                    noDuplicates.add(path);
                }
            }

            int pathSize = noDuplicates.size();
            SPFnumSubnets.add(pathSize);

        }
        //return size
        return SPFnumSubnets;
    }
}
