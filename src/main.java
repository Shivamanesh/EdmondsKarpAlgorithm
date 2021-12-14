import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException {
        NodeInfo nodeInfo = new NodeInfo();
        int n = nodeInfo.getNumOfNodes();
        int s = nodeInfo.getSource();
        int t = nodeInfo.getSink();
        int tail = nodeInfo.eachEdge()[0];
        int head = nodeInfo.eachEdge()[1];
        int capacity= nodeInfo.eachEdge()[2];

        EdmondsKarp edmondsKarp = new EdmondsKarp(n, s, t);
        edmondsKarp.addEdge(tail, head, capacity);
        edmondsKarp.solve();
    }
}
