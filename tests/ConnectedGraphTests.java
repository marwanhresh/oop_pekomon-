import api.EdgeData;
import api.NodeData;
import concrete.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConnectedGraphTests {

    @Test
    public void TestConnectedTrueEmptyGraph() {

        var nodes = new ArrayList<NodeData>();
        var edges = new ArrayList<EdgeData>();

        var graph = new DWGraph(nodes, edges);

        var algo = new DWGAlgo();
        algo.init(graph);

        assertTrue(algo.isConnected());
    }

    @Test
    public void TestConnectedTrue() {

        var nodes = new ArrayList<NodeData>();

        for (int i = 0; i < 3; i++) {
            nodes.add(new Node(i, new Coordinates(i, i, i), i + 1, "", 0));
        }

        var edges = new ArrayList<EdgeData>();
        edges.add(new Edge(1, 0, 2, "", 0));
        edges.add(new Edge(1, 2, 2, "", 0));

        edges.add(new Edge(0, 1, 2, "", 0));
        edges.add(new Edge(2, 1, 2, "", 0));


        var graph = new DWGraph(nodes, edges);

        var algo = new DWGAlgo();
        algo.init(graph);

        assertTrue(algo.isConnected());
    }

    @Test
    public void TestConnectedFalse1() {

        var nodes = new ArrayList<NodeData>();

        for (int i = 0; i < 3; i++) {
            nodes.add(new Node(i, new Coordinates(i, i, i), i + 1, "", 0));
        }

        var edges = new ArrayList<EdgeData>();

        var graph = new DWGraph(nodes, edges);

        var algo = new DWGAlgo();
        algo.init(graph);

        assertFalse(algo.isConnected());
    }

    @Test
    public void TestConnectedFalse2() {

        var nodes = new ArrayList<NodeData>();
        for (int i = 0; i < 3; i++) {
            nodes.add(new Node(i, new Coordinates(i, i, i), i + 1, "", 0));
        }

        var edges = new ArrayList<EdgeData>();
        edges.add(new Edge(1, 0, 2, "", 0));
        edges.add(new Edge(1, 2, 2, "", 0));

        edges.add(new Edge(0, 1, 2, "", 0));


        var graph = new DWGraph(nodes, edges);

        var algo = new DWGAlgo();
        algo.init(graph);

        assertFalse(algo.isConnected());
    }
}
