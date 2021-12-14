import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Converter extends RuntimeException {
    public List<int[]> convert(List<String> linesInFile) throws IOException {
        List<int[]> listOfIntegers = new ArrayList<>();
        for (String s : linesInFile) {
            String[] splitStringInfo = s.split(";");
            int[] eachEdgeInfoInt = new int[splitStringInfo.length];
            for (int i = 0; i < splitStringInfo.length; i++) {
                int parsedInt = Integer.parseInt(splitStringInfo[i]);
                eachEdgeInfoInt[i] = parsedInt;
            }
            listOfIntegers.add(eachEdgeInfoInt);
        }
        return listOfIntegers;
    }

}

