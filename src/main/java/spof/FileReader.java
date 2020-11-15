package spof;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    private List<List<String>> networks = new ArrayList<>();

    public List<List<String>> getNetworks() {
        return networks;
    }



    public FileReader(String filePath) {
        try {
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            List<String> network = new ArrayList<>();
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();

                if(data.compareTo("0") == 0) {
                    networks.add(network);
                    network = new ArrayList<>();
                }
                else{
                    if(!data.equals("")){
                        network.add(data);
                    }

                }

            }
            myReader.close();


        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
