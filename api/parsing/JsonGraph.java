package parsing;

import java.util.List;

public class JsonGraph {
    List<JsonEdge> Edges;
    List<JsonNode> Nodes;

    public JsonGraph() {

    }

    public JsonGraph(List<JsonEdge> edges, List<JsonNode> nodes) {
        this.Edges = edges;
        this.Nodes = nodes;
    }
}