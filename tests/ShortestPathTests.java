import api.EdgeData;
import api.NodeData;
import concrete.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ShortestPathTests {

    @Test
    public void TestShortestPathDist1()
    {
        var nodes = new ArrayList<NodeData>();

        for (int i = 0; i < 3; i++) {
            nodes.add(new Node(i, new Coordinates(i, i, i), i + 1, "", 0));
        }

        var edges = new ArrayList<EdgeData>();

        edges.add(new Edge(0, 1, 1, "", 0));
        edges.add(new Edge(1, 2, 1, "", 0));

        edges.add(new Edge(0, 2, 3, "", 0));


        var graph = new DWGraph(nodes, edges);

        var algo = new DWGAlgo();
        algo.init(graph);

        Assert.assertEquals(2, algo.shortestPathDist(0, 2), 0);
    }

    @Test
    public void TestShortestPathDist2()
    {
        var nodes = new ArrayList<NodeData>();

        for (int i = 0; i < 3; i++) {
            nodes.add(new Node(i, new Coordinates(i, i, i), i + 1, "", 0));
        }

        var edges = new ArrayList<EdgeData>();

        edges.add(new Edge(0, 1, 5, "", 0));
        edges.add(new Edge(1, 2, 1, "", 0));

        edges.add(new Edge(0, 2, 3, "", 0));


        var graph = new DWGraph(nodes, edges);

        var algo = new DWGAlgo();
        algo.init(graph);

        Assert.assertEquals(3, algo.shortestPathDist(0, 2), 0);
    }

    @Test
    public void TestShortestPath1() {
        var nodes = new ArrayList<NodeData>();

        for (int i = 0; i < 3; i++) {
            nodes.add(new Node(i, new Coordinates(i, i, i), i + 1, "", 0));
        }

        var edges = new ArrayList<EdgeData>();

        edges.add(new Edge(0, 1, 1, "", 0));
        edges.add(new Edge(1, 2, 1, "", 0));

        edges.add(new Edge(0, 2, 3, "", 0));


        var graph = new DWGraph(nodes, edges);

        var algo = new DWGAlgo();
        algo.init(graph);

        var p1 = new ArrayList<NodeData>();
        p1.add(graph.getNode(0));
        p1.add(graph.getNode(1));
        p1.add(graph.getNode(2));

        var p2 = algo.shortestPath(0, 2);

        Assert.assertEquals(p1.size(), p2.size());

        for (int i = 0; i < p1.size(); i++) {
            Assert.assertEquals(p1.get(i).getKey(), p2.get(i).getKey());
        }
    }

    @Test
    public void TestShortestPath2() {
        var nodes = new ArrayList<NodeData>();

        for (int i = 0; i < 3; i++) {
            nodes.add(new Node(i, new Coordinates(i, i, i), i + 1, "", 0));
        }

        var edges = new ArrayList<EdgeData>();

        edges.add(new Edge(0, 1, 5, "", 0));
        edges.add(new Edge(1, 2, 1, "", 0));

        edges.add(new Edge(0, 2, 3, "", 0));


        var graph = new DWGraph(nodes, edges);

        var algo = new DWGAlgo();
        algo.init(graph);

        var p1 = new ArrayList<NodeData>();
        p1.add(graph.getNode(0));
        p1.add(graph.getNode(2));

        var p2 = algo.shortestPath(0, 2);

        Assert.assertEquals(p1.size(), p2.size());

        for (int i = 0; i < p1.size(); i++) {
            Assert.assertEquals(p1.get(i).getKey(), p2.get(i).getKey());
        }
    }

}
