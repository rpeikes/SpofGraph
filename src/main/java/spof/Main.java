package spof;

import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args){

        String filePath = args[0];
        FileReader fileReader = new FileReader(filePath);
        List<List<String>> networks = fileReader.getNetworks();

        List<String> nodePairs = networks.get(0);

        createView(nodePairs);
//
//        for(int index = 0; index < networks.size(); index++){
//            List<String> nodePairs = networks.get(index);
//            if(nodePairs.size() > 0) {
//                findNetworksPOF(nodePairs);
//                }
//            if(index == 0){
//                createView(nodePairs);
//            }
            }






    private static void findNetworksPOF(List<String> nodePairs) {
        Network network = new Network(nodePairs);
        Graph graph = new Graph(network.getNodeList());

        List<Node> SPFs = graph.findSPF();
        System.out.println("SPFS = " + SPFs);

    }
    private static void createView(List<String> nodePairs){
        Network network = new Network(nodePairs);
        Graph graph = new Graph(network.getNodeList());

        List<Node> SPFs = graph.findSPF();
        System.out.println("SPFS = " + SPFs);
        System.out.println("nodepairs = " + nodePairs);
        System.out.println("nodeList = " + network.getNodeList().toString());
        GraphFrame graphFrame = new GraphFrame(new GraphView(graph));
        graphFrame.setVisible(true);



    }
}
