import java.util.Iterator;
import java.util.LinkedList;

// This class represents an undirected graph using adjacency list representation
public class Graph {

    private int numberOfVertices;

    private LinkedList<LinkedList<Integer>> longestPath;

    private LinkedList<Integer> arrayOfAdjacencyLists[];

    public Graph(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        this.longestPath = new LinkedList<>();
        this.arrayOfAdjacencyLists = new LinkedList[numberOfVertices];
        for (int i = 0; i < numberOfVertices; ++i) {
            arrayOfAdjacencyLists[i] = new LinkedList();
        }
    }

    public void addEdge(int go, int to) throws IllegalStateException {
        addDirectedEdge(go, to);
        addDirectedEdge(to, go);
    }

    public void calculateLongestPath() {
        for (int i = 0; i < numberOfVertices; i++) {
            LinkedList<Integer> path = new LinkedList();
            LinkedList<Integer> arrayOfVisitedEdges[] = new LinkedList[numberOfVertices];
            for (int j = 0; j < numberOfVertices; ++j) {
                arrayOfVisitedEdges[j] = new LinkedList();
            }
            eulerianWalk(i, i, arrayOfVisitedEdges, path);
        }
    }

    public LinkedList<LinkedList<Integer>> getLongestPath() {
        return longestPath;
    }

    public int getMaximumDistance(){
        if(longestPath.isEmpty()){
            return 0;
        }
        return longestPath.getFirst().size()-1;
    }

    private void eulerianWalk(int previousVertices, int vertices, LinkedList<Integer> visited[], LinkedList<Integer> path) {
        // Mark the current vertices as visited
        if (vertices != previousVertices) {
            visited[previousVertices].add(vertices);
            visited[vertices].add(previousVertices);
        }
        path.add(vertices);

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> adjacency = arrayOfAdjacencyLists[vertices].listIterator();
        while (adjacency.hasNext()) {
            int nextVertex = adjacency.next();

            Iterator<Integer> adjacencyVisited = visited[vertices].listIterator();
            if (!adjacencyVisited.hasNext()) {
                eulerianWalk(vertices, nextVertex, visited, path);
            } else {
                boolean canVisit = true;
                while (adjacencyVisited.hasNext()) {
                    int visitedVertex = adjacencyVisited.next();
                    if (nextVertex == visitedVertex) {
                        canVisit = false;
                    }
                }
                if (canVisit) {
                    eulerianWalk(vertices, nextVertex, visited, path);
                }
            }
        }
        Iterator<Integer> adjacencyVisited = visited[previousVertices].listIterator();
        while (adjacencyVisited.hasNext()) {
            int visitedVertex = adjacencyVisited.next();
            if (vertices == visitedVertex) {
                adjacencyVisited.remove();
            }
        }
        adjacencyVisited = visited[vertices].listIterator();
        while (adjacencyVisited.hasNext()) {
            int visitedVertex = adjacencyVisited.next();
            if (previousVertices == visitedVertex) {
                adjacencyVisited.remove();
            }
        }
        if (longestPath.isEmpty()) {
            longestPath.add((LinkedList<Integer>) path.clone());
        } else if (path.size() > longestPath.getFirst().size()) {
            longestPath = new LinkedList<>();
            longestPath.add((LinkedList<Integer>) path.clone());
        } else if (path.size() == longestPath.getFirst().size()) {
            longestPath.add((LinkedList<Integer>) path.clone());
        }
        path.removeLast();
    }

    private void addDirectedEdge(int go, int to) throws IllegalStateException {
        if (arrayOfAdjacencyLists[go].size() < 3) {
            arrayOfAdjacencyLists[go].add(to);
        } else {
            throw new IllegalStateException("List is already at maximum size of 3");
        }
    }
}
