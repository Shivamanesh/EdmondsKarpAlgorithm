import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Converter extends RuntimeException {
    DataReader dataReader = new DataReader();
    public List<int[]> convert() throws IOException {
        List<int[]> listOfIntegers = new ArrayList<>();
        for (String s : dataReader.readFiles()) {
            String[] splitStringInfo = s.split(";");
            int[] integerFormOfInfo = new int[splitStringInfo.length];
            for (int i = 0; i < splitStringInfo.length; i++) {
                int parsedInt = Integer.parseInt(splitStringInfo[i]);
                integerFormOfInfo[i] = parsedInt;
            }
            listOfIntegers.add(integerFormOfInfo);
        }
        return listOfIntegers;
    }

}

