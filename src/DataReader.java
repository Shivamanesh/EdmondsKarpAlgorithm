import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    List<String> eachEdgeInfoString = new ArrayList<>();

    public List<String> readFiles() throws IOException {
        File file = new File("src/network1.txt");
        System.out.println(file.exists());
        BufferedReader reader;

        reader = new BufferedReader(new FileReader("src/network1.txt"));
        reader.readLine();
        String line1 = null;
        while((line1 = reader.readLine()) != null) {
            System.out.println(line1);
            eachEdgeInfoString.add(line1);
        }
        reader.close();
        return eachEdgeInfoString;
    }

}


