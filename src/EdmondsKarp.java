import static java.lang.Math.min;
import java.util.*;
public class EdmondsKarp extends NetworkFlow {
    public EdmondsKarp(int numOfNodes, int source, int sink){
        super(numOfNodes, source, sink);
    }
    @Override
    public void solve() {
        long flow;
        do {
            markAllNodesAsUnvisited();
            flow = bfs();
            maxFlow += flow;
        } while (flow != 0);

        for (int i = 0; i < numOfNodes; i++) if (visited(i)) minCut[i] = true;
    }

    private long bfs() {
        Edge[] prev = new Edge[numOfNodes];

        // The queue can be optimized to use a faster queue
        Queue<Integer> q = new ArrayDeque<>(numOfNodes);
        visit(source);
        q.offer(source);

        // BFS
        while (!q.isEmpty()) {
            int node = q.poll();
            if (node == sink) break;

            for (Edge edge : graph[node]) {
                long cap = edge.remainingCapacity();
                if (cap > 0 && !visited(edge.head)) {
                    visit(edge.head);
                    prev[edge.head] = edge;
                    q.offer(edge.head);
                }
            }
        }

        // Sink not reachable
        if (prev[sink] == null) return 0;

        long bottleNeck = Long.MAX_VALUE;

        // Find augmented path and bottle neck
        for (Edge edge = prev[sink]; edge != null; edge = prev[edge.tail])
            bottleNeck = min(bottleNeck, edge.remainingCapacity());

        // Retrace augmented path and update flow values.
        for (Edge edge = prev[sink]; edge != null; edge = prev[edge.tail]) edge.augment(bottleNeck);

        // Return bottleneck flow
        return bottleNeck;
    }

}
