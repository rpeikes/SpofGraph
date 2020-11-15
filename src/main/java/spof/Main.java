package spof;

import java.util.List;

public class Main {
    public static void main(String[] args){

        String filePath = args[0];
        FileReader fileReader = new FileReader(filePath);
        List<List<String>> networks = fileReader.getNetworks();

        for(int index = 0; index < networks.size(); index++){
            List<String> nodePairs = networks.get(index);
            if(nodePairs.size() > 0) {
                findNetworksPOF(nodePairs);
                }
            }

        }



    private static void findNetworksPOF(List<String> nodePairs) {
        Network network = new Network(nodePairs);
        Graph graph = new Graph(network.getNodeList());
        List<Node> SPFs = graph.findSPF();
        System.out.println("SPFS = " + SPFs);

    }
}
