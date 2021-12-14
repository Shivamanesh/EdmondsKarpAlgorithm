import static java.lang.Math.min;
import java.io.IOException;
import java.util.*;
public class EdmondsKarp extends NetworkFlowSolverBase {

    NodeInfo nodeInfo = new NodeInfo();

    /*
     * Creates an instance of a flow network solver. Use the {@link #addEdge(int, int, int)} method to
     * add edges to the graph.
     *
     * @param n - The number of nodes in the graph including source and sink nodes.
     * @param s - The index of the source node, 0 <= s < n
     * @param t - The index of the sink node, 0 <= t < n, t != s
     */
    public EdmondsKarp(int numOfNodes, int source, int sink) throws IOException {
        super(numOfNodes, source, sink);
    }

    // Run Edmonds-Karp and compute the max flow from the source to the sink node.
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

        // Perform BFS from source to sink
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

        // Sink not reachable!
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


//    public static void main(String[] args) throws IOException {
//        testSmallFlowGraph();
//    }
//
//    private static void testSmallFlowGraph() throws IOException {
//        int n = 6;
//        int source = n - 1;
//        int sink = n - 2;
//
//        EdmondsKarp solver;
//        solver = new EdmondsKarp(n, source, sink);
//
//        // Source edges
//        solver.addEdge(source, 0, 10);
//        solver.addEdge(source, 1, 10);
//
//        // Sink edges
//        solver.addEdge(2, sink, 10);
//        solver.addEdge(3, sink, 10);
//
//        // Middle edges
//        solver.addEdge(0, 1, 2);
//        solver.addEdge(0, 2, 4);
//        solver.addEdge(0, 3, 8);
//        solver.addEdge(1, 3, 9);
//        solver.addEdge(3, 2, 6);
//
//        System.out.println(solver.getMaxFlow()); // 19
//    }
}
