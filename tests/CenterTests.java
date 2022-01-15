import api.EdgeData;
import api.NodeData;
import concrete.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CenterTests {

    @Test
    public void TestCenter1() {
        var nodes = new ArrayList<NodeData>();

        for (int i = 0; i < 3; i++) {
            nodes.add(new Node(i, new Coordinates(i, i, i), i + 1, "", 0));
        }

        var edges = new ArrayList<EdgeData>();

        edges.add(new Edge(0, 1, 1, "", 0));
        edges.add(new Edge(0, 2, 1, "", 0));

        edges.add(new Edge(1, 0, 3, "", 0));
        edges.add(new Edge(2, 0, 3, "", 0));

        var graph = new DWGraph(nodes, edges);

        var algo = new DWGAlgo();
        algo.init(graph);

        Assert.assertEquals(graph.getNode(0), algo.center());
    }
}
