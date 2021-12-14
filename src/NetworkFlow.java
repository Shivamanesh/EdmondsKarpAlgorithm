import java.util.ArrayList;
import java.util.List;

public abstract class NetworkFlow {
    public static class Edge {
        int tail;
        int head;
        Edge residual;
        long flow;
        long cost;
        int capacity;
        long originalCost;

        public Edge(int tail, int head, int capacity) {
            this(tail, head, capacity, 0 /* unused */);
        }

        public Edge(int tail, int head, int capacity, long cost) {
            this.tail = tail;
            this.head = head;
            this.capacity = capacity;
            this.originalCost = this.cost = cost;
        }

        public boolean isResidual() {
            return capacity == 0;
        }

        public long remainingCapacity() {
            return capacity - flow;
        }

        public void augment(long bottleNeck) {
            flow += bottleNeck;
            residual.flow -= bottleNeck;
        }
    }

    int numOfNodes, source, sink;

    protected long maxFlow;

    protected boolean[] minCut;
    protected List<Edge>[] graph;

    // 'visited' and 'visitedToken' are variables used for graph sub-routines to
    // track whether a node has been visited or not. In particular, node 'i' was
    // recently visited if visited[i] == visitedToken is true. This is handy
    // because to mark all nodes as unvisited simply increment the visitedToken.
    private int visitedToken = 1;
    private int[] visited;

    // Indicates whether the network flow algorithm has ran. We should not need to
    // run the solver multiple times, because it always yields the same result.
    private boolean solved;

    public NetworkFlow(int numOfNodes, int source, int sink) {
        this.numOfNodes = numOfNodes;
        this.source = source;
        this.sink = sink;
        initializeGraph();
        minCut = new boolean[numOfNodes];
        visited = new int[numOfNodes];
    }

    //an empty graph
    @SuppressWarnings("unchecked")
    private void initializeGraph() {
        graph = new List[numOfNodes];
        for (int i = 0; i < numOfNodes; i++) graph[i] = new ArrayList<Edge>();
    }

    public void addEdge(int tail, int head, int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("Capacity < 0");
        Edge e1 = new Edge(tail, head, capacity);
        Edge e2 = new Edge(head, tail, 0);
        e1.residual = e2;
        e2.residual = e1;
        graph[tail].add(e1);
        graph[head].add(e2);
    }


    // we have visited node i
    public void visit(int i) {
        visited[i] = visitedToken;
    }

    // Returns whether or not node 'i' has been visited.
    public boolean visited(int i) {
        return visited[i] == visitedToken;
    }

    // Resets all nodes as unvisited. This is especially useful to do
    // between iterations of finding augmenting paths, O(1)
    public void markAllNodesAsUnvisited() {
        visitedToken++;
    }

    // Returns the maximum flow from the source to the sink.
    public long getMaxFlow() {
        execute();
        return maxFlow;
    }

    //method that checks we only call solve() once
    private void execute() {
        if (solved) return;
        solved = true;
        solve();
    }

    // Method to implement which solves the network flow problem.
    public abstract void solve();
}
