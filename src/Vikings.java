import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Vikings {

    private static String graphVizString;

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

        for (String line : skipFirst(lines)) {
            if (!line.isEmpty()) {
                String[] edgesLine = line.split(" ");
                graph.addEdge(Integer.parseInt(edgesLine[0]), Integer.parseInt(edgesLine[1]));
                toGraphViz(edgesLine[0], edgesLine[1]);
            }
        }

        graph.calculateLongestPath();

       /* graph G {
            rankdir = LR;
            node [shape= circle style=filled fillcolor="#00ff005f"]
            2
            node [style=filled fillcolor="#fff"]
            0 -- 2
            1 -- 2
            2 -- 3
            3 -- 4
            3 -- 5
            4 -- 6
            5 -- 7
            6 -- 8
            7 -- 8
            7 -- 9
            8 -- 10
            9 -- 11
            10 -- 12
            11 -- 12
            10 -- 13
            12 -- 14
        }*/


        System.out.println("graph G { \n rankdir = LR; \n");

        System.out.println("node [shape= circle style=filled fillcolor=\"#FFFFFF\"]; ");

        /*for (int node : graph.getLongestPath().get(0))
*/


        System.out.println(graphVizString);

        System.out.println("}\n");
    }

    private static void toGraphViz(String s1, String s2) {
        if(graphVizString == null){
            graphVizString = " " +  s1 + " -- " + s2 + "\n";
        }else{
            graphVizString = graphVizString + " " +  s1 + " -- " + s2 + "\n";
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