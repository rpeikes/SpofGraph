package spof;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    private List<Node> nodeList = new ArrayList<>();
    public FileReader(String filePath) {

        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                createConnections(data);
                createGraph();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


    private void createConnections(String data) {

        String[] nodes = data.split(" ");
        if(nodes.length > 1) {
            //Create Node
            String firstName = nodes[0];
            String secondName = nodes[1];


            Node firstNode = new Node(firstName);
            Node secondNode = new Node(secondName);

            //Create Arraylist
            List<Node> firstConnection = new ArrayList();
            List<Node> secondConnection = new ArrayList();

            //Create Connections
            firstConnection.add(secondNode);
            secondConnection.add(firstNode);
            firstNode.setConnections(firstConnection);
            secondNode.setConnections(secondConnection);

            //Add the nodes to the member arraylist
            nodeList.add(firstNode);
        }

    }


    private void createGraph() {

        Graph graph = new Graph(nodeList);
        List<Node> SPFs = graph.findSPF();
        List<Integer> SPFsubnets = graph.getNumSubnets();

        for(int i = 0; i<SPFs.size(); i++){
            System.out.println("SPF " + i + " : " + SPFs.get(i));
            System.out.println("SUBNET " + i + " : " + SPFsubnets.get(i));
        }

    }

}


