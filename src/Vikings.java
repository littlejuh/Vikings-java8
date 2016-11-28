import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class Vikings {
    public static void main(String args[]) throws Exception {

        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(args[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String firsLine = lines.get(0);
        int nrVertices = Integer.parseInt(firsLine.split(" ")[0]);

        validateNrOfVertices(nrVertices);

        Graph graph = new Graph(nrVertices);
        GraphViz graphViz = new GraphViz();

        for (String line : skipFirst(lines)) {
            if (!line.isEmpty()) {
                String[] edgesLine = line.split(" ");
                graph.addEdge(Integer.parseInt(edgesLine[0]), Integer.parseInt(edgesLine[1]));
                graphViz.addVertex(edgesLine[0], edgesLine[1]);
            }
        }

        graph.calculateLongestPath();
        for(LinkedList<Integer> path : graph.getLongestPath()) {
            System.out.println(graphViz.generateGraphViz(path));
        }
    }

    public static <T> Iterable<T> skipFirst(final Iterable<T> c) {
        return () -> {
            Iterator<T> i = c.iterator();
            i.next();
            return i;
        };
    }

    public static void validateNrOfVertices(int nrVertices){
        if (!(nrVertices >= 2 && nrVertices <= 25)) {
            throw new IllegalArgumentException("Number of vertices should be >= 2 and <= 25");
        }
    }
}