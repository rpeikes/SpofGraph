package spof;

import java.util.List;

public class Main {
    public static void main(String[] args){

        String filePath = args[0];
        NodeList nodeList = new NodeList(filePath);

        List<Node> graphNodeList = nodeList.getNodeList();
        Graph graph = new Graph(graphNodeList);
        List<Node> SPFs = graph.findSPF();
        List<Integer> SPFsubnets = graph.getNumSubnets();
        for(int i = 0; i < SPFs.size(); i++){
            System.out.println("Network #1:");
            System.out.println("SPF node " +  SPFs.get(i) + "leaves " + SPFsubnets.get(i)  + "subnets");
        }
    }
}
