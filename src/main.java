import java.io.IOException;
import java.util.Arrays;

public class main {

    public static void main(String[] args) throws IOException {
        EdgeInfo edgeInfo = new EdgeInfo();
        int n = edgeInfo.getNumOfNodes();
        int s = edgeInfo.getSource();
        int t = edgeInfo.getSink();
        int tail = 0;
        int head = 0;
        int capacity = 0;
        EdmondsKarp edmondsKarp = new EdmondsKarp(n, s, t);
        for (int i = 0; i < edgeInfo.numOfEdges; i++) {

            tail = edgeInfo.eachEdge(i)[0] - 1;
            System.out.println(tail + "tail" + i);
            head = edgeInfo.eachEdge(i)[1] - 1;
            capacity = edgeInfo.eachEdge(i)[2];
            edmondsKarp.addEdge(tail, head, capacity);

        }
//        edmondsKarp.addEdge(0, 1, 20);
//        edmondsKarp.addEdge(0, 2, 30);
//        edmondsKarp.addEdge(1, 4, 10);
//        edmondsKarp.addEdge(1, 5, 30);
//        edmondsKarp.addEdge(2, 3, 20);
//        edmondsKarp.addEdge(2, 6, 20);
//        edmondsKarp.addEdge(3, 5, 10);
//        edmondsKarp.addEdge(4, 6, 10);
//        edmondsKarp.addEdge(5, 7, 20);
//        edmondsKarp.addEdge(6, 7, 30);
        edmondsKarp.solve();
        System.out.println(edmondsKarp.getMaxFlow());
    }
}
