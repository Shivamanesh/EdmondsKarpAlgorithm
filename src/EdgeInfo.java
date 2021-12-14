import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EdgeInfo {
    Converter converter = new Converter();
    List<int[]> allEdgesInfoInt = converter.convert();
    int numOfEdges = converter.convert().size();
    int[] edge;

    public EdgeInfo() throws IOException {
    }

    public int getSource() {
        return allEdgesInfoInt.get(0)[0] - 1;
    }

    public int getSink() {
        return allEdgesInfoInt.get(getNumOfNodes())[1] - 1;
    }

    public int[] eachEdge(int i) {
        return allEdgesInfoInt.get(i);
    }


    public List<Integer> getAllHeads(){
        List<Integer> allHeads = new ArrayList<>();
        for (int[] iThEdge : allEdgesInfoInt) {
            int head = iThEdge[1];
            allHeads.add(head);
        }
        return allHeads;
    }

    public List<Integer> getAllTails(){
        List<Integer> allTails = new ArrayList<>();
        for (int[] iThEdge : allEdgesInfoInt) {
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

