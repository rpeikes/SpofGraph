package spof;

import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String filePath = args[0];
        FileReader fileReader = new FileReader(filePath);
        List<List<String>> networks = fileReader.getNetworks();

        for (int index = 0; index < networks.size(); index++) {
            List<String> nodePairs = networks.get(index);
            if (nodePairs.size() > 0) {
                System.out.println("Network #" + (index + 1));
                findNetworksPOF(nodePairs);
            }
            if (index == 0) {
                createView(nodePairs);
            }
        }
    }


    private static void findNetworksPOF(List<String> nodePairs) {
        Network network = new Network(nodePairs);
        Graph graph = new Graph(network.getNodeList());
        List<Node> SPFs = graph.findSPF();
        List<Integer> subnets = graph.getNumSubnets();

        if (SPFs.size() == 0) {
            System.out.println("\tNo SPF nodes");
        }
        for (int i = 0; i < SPFs.size(); i++) {
            System.out.println("\tSPF node " + SPFs.get(i) + " leaves " + subnets.get(i) + " subnets");
        }

        System.out.println("");
    }

    private static void createView(List<String> nodePairs) {
        Network network = new Network(nodePairs);
        Graph graph = new Graph(network.getNodeList());
        GraphFrame graphFrame = new GraphFrame(new GraphView(graph));
        graphFrame.setVisible(true);
    }
}
