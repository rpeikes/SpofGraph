package spof;

import java.util.ArrayList;
import java.util.List;

public class Network {

    private List<Node> nodeList = new ArrayList<>();

    public List<Node> getNodeList() {
        return nodeList;
    }

    public Network(List<String> nodePairs) {

        createConnections(nodePairs);
    }



    private void createConnections(List<String> nodePairs) {


        for(String nodes: nodePairs){


            String[] nodeListPairs = nodes.split(" ");

            Node firstNode = new Node(nodeListPairs[0]);
            Node secondNode = new Node(nodeListPairs[1]);

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


