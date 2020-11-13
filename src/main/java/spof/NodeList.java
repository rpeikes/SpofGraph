package spof;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NodeList {
    private List<Node> nodeList = new ArrayList<>();
    private List<List<String>> = new ArrayList<>();
    public List<Node> getNodeList() {
        return nodeList;
    }

    public NodeList(String filePath) {

        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
//                createNetwork(data);
                createConnections(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


//    private String[] createNetwork(String data){
//
//        String[] nodes = data.split(" ");
//        List<String> network = new ArrayList<>();
//        if(nodes.length > 1) {
//            network.add(data);
//        }
//    }

    private void createConnections(String data) {

        String[] nodes = data.split(" ");
        if(nodes.length > 1) {

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


