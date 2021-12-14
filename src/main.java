import java.io.IOException;
import java.util.List;

public class main {

    public static void main(String[] args) throws IOException {
        EdgeInfo edgeInfo = getEdgeInfo();
        int n = edgeInfo.getNumOfNodes();
        int s = edgeInfo.getSource();
        int t = edgeInfo.getSink();
        int tail = 0;
        int head = 0;
        int capacity = 0;
        EdmondsKarp edmondsKarp = new EdmondsKarp(n, s, t);
        for (int i = 0; i < edgeInfo.getNumOfEdges(); i++) {
            tail = edgeInfo.eachEdge(i)[0] - 1;
            head = edgeInfo.eachEdge(i)[1] - 1;
            capacity = edgeInfo.eachEdge(i)[2];
            edmondsKarp.addEdge(tail, head, capacity);

        }
        edmondsKarp.solve();
        System.out.println("Found a maximum 1-"+ n + "-flow with value " + edmondsKarp.getMaxFlow() + ".");
    }

    private static EdgeInfo getEdgeInfo() throws IOException {
        DataReader dataReader = new DataReader();
        Converter converter = new Converter();
        List<String> linesInFile = dataReader.readFiles();
        List<int[]> allEdgesInfoInt = converter.convert(linesInFile);
        EdgeInfo edgeInfo = new EdgeInfo(allEdgesInfoInt);
        return edgeInfo;
    }
}
