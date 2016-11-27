public class Vikings {

    // Driver method
    public static void main(String args[]) {

        //@TODO: lê numberOfVertices do arquivo e valida >2&&<25
        //e numero de conexões >1&&<25

        Graph g1 = new Graph(15);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.addEdge(3, 4);
        g1.addEdge(3, 5);
        g1.addEdge(4, 6);
        g1.addEdge(5, 7);
        g1.addEdge(6, 8);
        g1.addEdge(7, 8);
        g1.addEdge(7, 9);
        g1.addEdge(8, 10);
        g1.addEdge(9, 11);
        g1.addEdge(10, 12);
        g1.addEdge(10, 13);
        g1.addEdge(11, 13);
        g1.addEdge(13, 14);

        g1.calculateLongestPath();

        System.out.println("The maximum distance is: "  + g1.getMaximumDistance());
        System.out.println("The path is " + g1.getLongestPath());
    }
}