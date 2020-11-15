package spof;

import java.util.List;

public class Main {
    public static void main(String[] args){

        String filePath = args[0];
        FileReader fileReader = new FileReader(filePath);
        List<List<String>> networks = fileReader.getNetworks();


        Network nodeList = new Network(networks);

        List<Node> graphNodeList = nodeList.getNodeList();
        Graph graph = new Graph(graphNodeList);
        List<Node> SPFs = graph.findSPF();
        List<Integer> SPFsubnets = graph.getNumSubnets();
        System.out.printf("SPFs = " + SPFs.toString());
    }
}
