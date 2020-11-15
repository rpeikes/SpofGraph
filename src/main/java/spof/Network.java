package spof;

import java.util.ArrayList;
import java.util.List;

public class Network {
    private List<List<String>> networks;
    private List<Node> nodeList = new ArrayList<>();
    public List<Node> getNodeList() {
        return nodeList;
    }

    public Network(List<List<String>> networks) {
            this.networks = networks;
            for(int index = 0; index < networks.size(); index++){
                List<String> network = networks.get(index);
                if(network.size() > 0){
                    createConnections(network);
                }
            }

    }



    private void createConnections(List<String> network) {

        String[] nodesList = network.get(0).split(",");
        for(String nodePair: nodesList){
            String[] nodes = nodePair.split(" ");
            Node firstNode = new Node(nodes[0]);
            Node secondNode = new Node(nodes[1]);

            verifyConnection(firstNode, secondNode);
        }

    }

    private void verifyConnection(Node firstNode, Node secondNode) {
        //If nodeList has that node, then don't add it.
        //If node is in list:
        if(nodeList.contains(firstNode)){
            //find the node
            Node foundNode = nodeList.get(nodeList.indexOf(firstNode));
            //here he is!

            if(!foundNode.getConnections().contains(secondNode)) {
                foundNode.addConnection(secondNode);
            }
        }
        //If the node isn't in the list then add it to the list
        else{
            nodeList.add(firstNode);
            Node foundNode = nodeList.get(nodeList.indexOf(firstNode));
            foundNode.addConnection(secondNode);
        }


        if(nodeList.contains(secondNode)){
            Node foundNode = nodeList.get(nodeList.indexOf(secondNode));
            if(!foundNode.getConnections().contains(firstNode)) {
                foundNode.addConnection(firstNode);
            }
        }
        else{
            nodeList.add(secondNode);
            Node foundNode = nodeList.get(nodeList.indexOf(secondNode));
            foundNode.addConnection(firstNode);
        }


    }



}


