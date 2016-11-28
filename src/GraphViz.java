import java.util.ArrayList;
import java.util.LinkedList;

public class GraphViz {
    ArrayList<String> vertexListToPrint;

    public GraphViz(){
        vertexListToPrint = new ArrayList<>();
    }

    public void addVertex(String v1, String v2){
        if(Integer.parseInt(v1) <= Integer.parseInt(v2)){
            vertexListToPrint.add(v1 + " -- " + v2);
        }else{
            vertexListToPrint.add(v2 + " -- " + v1);
        }
    }

    public String generateGraphViz(LinkedList<Integer> edges, int routeNumber){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("graph G_Route_" + routeNumber + "{ \nrankdir = LR; \n" +
                "node [shape= circle style=filled fillcolor=\"#FFFFFF\"]; \n");

        ArrayList<String> vertexToPrint = (ArrayList<String>)vertexListToPrint.clone();
        for(int i = 0; i < edges.size()-1; i++){
            if(edges.get(i) <= edges.get(i+1)){
                colorEdge(vertexToPrint, edges.get(i), edges.get(i+1));
            }
            else {
                colorEdge(vertexToPrint, edges.get(i+1), edges.get(i));
            }
        }
        for (String edge : vertexToPrint) {
            stringBuilder.append(" " +  edge + "\n");
        }
        int go = edges.getFirst();
        int to = edges.getLast();
        if(go != to){
            stringBuilder.append(" " +  go + " [fillcolor=\"yellow\"]\n");
            stringBuilder.append(" " +  to + " [fillcolor=\"cyan\"]\n");
        }
        else{
            stringBuilder.append(" " +  go + " [fillcolor=\"green\"]\n");
        }
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    private ArrayList<String> colorEdge(ArrayList<String> list, Integer v1, Integer v2){
        String newEdge = v1 + " -- " + v2;
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).equals(newEdge)){
                list.set(i, newEdge + " [color=\"red\"]");
            }
        }
        return list;
    }
}
