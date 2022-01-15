package parsing;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import concrete.Coordinates;
import concrete.DWGraph;
import concrete.Edge;
import concrete.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public static DirectedWeightedGraph getGraph(String json_file) {
        var jsonGraph = GetJsonGraph(json_file);
        if (jsonGraph != null) {

            List<NodeData> nodes = new ArrayList<>();
            List<EdgeData> edges = new ArrayList<>();

            for (var node : jsonGraph.Nodes) {

                var split = Arrays.stream(node.pos.split(",")).map(Double::parseDouble).toList();
                var geo = new Coordinates(split.get(0), split.get(1), split.get(2));

                nodes.add(new Node(node.id, geo, 0, "", 0));
            }

            for (var edge : jsonGraph.Edges) {
                edges.add(new Edge(edge.src, edge.dest, edge.w, "", 0));
            }

            return new DWGraph(nodes, edges);
        }
        return null;
    }

    public static boolean putGraph(DirectedWeightedGraph g, String json_file) {
        try {
            var gson = new GsonBuilder().setPrettyPrinting().create();

            List<JsonNode> nodes = new ArrayList<>();
            List<JsonEdge> edges = new ArrayList<>();

            var nodeIter = g.nodeIter();
            while(nodeIter.hasNext()) {
                var node = nodeIter.next();

                var pos = node.getLocation().x() + "," + node.getLocation().y() + "," + node.getLocation().z();

                nodes.add(new JsonNode(pos, node.getKey()));
            }

            var edgeIter = g.edgeIter();
            while (edgeIter.hasNext()) {
                var edge = edgeIter.next();

                edges.add(new JsonEdge(edge.getSrc(), edge.getWeight(), edge.getDest()));
            }


            var graphJson = new JsonGraph(edges, nodes);

            var writer = new FileWriter(json_file);

            gson.toJson(graphJson, graphJson.getClass(), writer);

            writer.close();

            return true;
        }
        catch (Exception ex) {
            return  false;
        }
    }

    public static JsonGraph GetJsonGraph(String json_file)  {
        try {
            var gson = new Gson();
            var fileContent = readFile(json_file);
            var jsonGraph = gson.fromJson(fileContent, JsonGraph.class);

            return jsonGraph;
        }
        catch (Exception ex) {
            return  null;
        }
    }

    private static String readFile(String file_name) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file_name));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
}
