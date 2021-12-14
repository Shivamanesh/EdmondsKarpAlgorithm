import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NodeInfo {

    //final int numOfNodes;
    Converter converter = new Converter();
    int numOfEdges = converter.convert().size();
    int[] iThEdge;
    int tail;
    int head;
    long capacity;
    int source;
    int sink;
    List<Integer> allHeads = new ArrayList<>();
    List<Integer> allTails = new ArrayList<>();

    public NodeInfo() throws IOException {
    }


    public void nodeInformation(List<int[]> allNodesInfo) throws IOException {
        allNodesInfo = converter.convert();
        source = allNodesInfo.get(0)[0];
        sink = allNodesInfo.get(numOfEdges)[1];
        for (int i = 0; i < numOfEdges; i++) {
            iThEdge = allNodesInfo.get(i);
            tail = iThEdge[0];
            head = iThEdge[1];
            capacity = iThEdge[2];
            allHeads.add(head);
            allTails.add(tail);
            System.out.println("[tail, head, capacity]: " + Arrays.toString(iThEdge) + "\ntail: " + tail + ", head: " + head + ", capacity: " + capacity);
        }
    }

    public int getNumOfNodes(){
        int maxHead = 0;
        int maxTail = 0;
        for (int i = 0; i < allHeads.size(); i++) {
            if(allHeads.get(i) > maxHead){
                maxHead = allHeads.get(i);
            }
        }
        for (int i = 0; i < allTails.size(); i++) {
            if(allTails.get(i) > maxTail){
                maxTail = allTails.get(i);
            }
        }
        return Math.max(maxHead, maxTail);
    }
}

