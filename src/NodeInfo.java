import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NodeInfo {
    Converter converter = new Converter();
    List<int[]> allNodesInfo = converter.convert();
    int numOfEdges = converter.convert().size();

    public NodeInfo() throws IOException {
    }

    public int getSource() {
        return allNodesInfo.get(0)[0];
    }

    ////ERROR !
    public int getSink() {
        return allNodesInfo.get(numOfEdges - 1)[1];
    }

    public int[] eachEdge() {
        int[] edge = new int[0];
        for (int i = 0; i < allNodesInfo.size(); i++) {
           edge = allNodesInfo.get(i);
        }
        return edge; //consists of tail, head, capacity
    }

    public List<Integer> getAllHeads(){
        List<Integer> allHeads = new ArrayList<>();
        for (int[] iThEdge : allNodesInfo) {
            int head = iThEdge[1];
            allHeads.add(head);
        }
        return allHeads;
    }

    public List<Integer> getAllTails(){
        List<Integer> allTails = new ArrayList<>();
        for (int[] iThEdge : allNodesInfo) {
            int tail = iThEdge[0];
            allTails.add(tail);
        }
        return allTails;
    }

    public int getNumOfNodes() {
            int maxHead = 0;
            int maxTail = 0;
        for (Integer allHead : getAllHeads()) {
            if (allHead > maxHead) {
                maxHead = allHead;
            }
        }
        for (Integer allTail : getAllTails()) {
            if (allTail > maxTail) {
                maxTail = allTail;
            }
        }
        return Math.max(maxHead, maxTail);
        }
}

