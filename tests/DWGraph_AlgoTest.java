
import api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DWGraph_AlgoTest {
    directed_weighted_graph graph;
    dw_graph_algorithms ga;

    public directed_weighted_graph createMyGraph() {
        directed_weighted_graph myG = new DWGraph_DS();
        for (int i = 0; i <= 4; i++) {
            myG.addNode(new Node_Data_DS(i));
        }
        myG.connect(0, 1, 2);
        myG.connect(0, 3, 4);
        myG.connect(1, 2, 1);
        myG.connect(1, 4, 2);
        myG.connect(2, 3, 16);
        myG.connect(2, 4, 0);
        return myG;
    }

    public directed_weighted_graph createMyGraph2(int size) {
        directed_weighted_graph myG = new DWGraph_DS();
        for (int i = 0; i < size; i++) {
            myG.addNode(new Node_Data_DS(i));
        }
        return myG;
    }

    @BeforeEach
    public void resetGraph() {
        graph = new DWGraph_DS();
        ga = new DWGraph_Algo();
        ga.init(graph);
    }

    @Test
    void init() {
        directed_weighted_graph g = createMyGraph();
        dw_graph_algorithms ga = new DWGraph_Algo();
        ga.init(g);
        assertEquals(ga.getGraph(), g);
    }

    @Test
    void getGraph() {
        directed_weighted_graph g = createMyGraph();
        g = ga.getGraph();
        assertEquals(g, ga.getGraph());
    }

    @Test
    void copy() {
        directed_weighted_graph g1 = createMyGraph();
        dw_graph_algorithms g2 = new DWGraph_Algo();
        g2.init(g1);
        directed_weighted_graph g3 = g2.copy();
        System.out.println("g1 = " + g1);
        System.out.println("g3 = " + g3);
        System.out.println(g1.equals(g3));
        assertEquals(g1, g3);
        g1.connect(0, 2, 3);
        assertNull(g3.getEdge(0, 2));
        assertNotSame(g1, g3);//on memory
    }

    @Test
    void isConnected() {
        directed_weighted_graph g1 = createMyGraph();
        dw_graph_algorithms g2 = new DWGraph_Algo(g1);
        assertFalse(g2.isConnected());
        g1.connect(3, 0, 4);
        g1.connect(4, 3, 5);
        assertTrue(g2.isConnected());

        directed_weighted_graph g3 = new DWGraph_DS();
        g2.init(g3);
        g3.addNode(new Node_Data_DS(5));
        assertTrue(g2.isConnected()); // graph with 1 node is connected

        g3.addNode(new Node_Data_DS(2));
        assertFalse(g2.isConnected());
        g3.connect(5, 2, 1);
        assertFalse(g2.isConnected());
        g3.connect(2, 5, 1);
        assertTrue(g2.isConnected());
    }

    @Test
    void shortestPathDist() {
        directed_weighted_graph g1 = createMyGraph();
        dw_graph_algorithms g2 = new DWGraph_Algo(g1);
        assertEquals(1, g2.shortestPathDist(1, 4));
        assertEquals(17, g2.shortestPathDist(1, 3));
        assertEquals(3, g2.shortestPathDist(0, 4));
        assertEquals(1, g2.shortestPathDist(1, 4));
        assertEquals(0, g2.shortestPathDist(1, 1));
        assertEquals(-1, g2.shortestPathDist(9, 2));

        directed_weighted_graph g3 = createMyGraph2(5);
        g2.init(g3);
        g3.connect(2, 3, 5);
        assertEquals(-1, g2.shortestPathDist(0, 1));
        assertEquals(5, g2.shortestPathDist(2, 3));

        DWGraph_Algo g4 = new DWGraph_Algo();
        assertEquals(-1, g4.shortestPathDist(0, 1)); //empty graph

        g2.init(g1);
        g1.addNode(new Node_Data_DS(5));
        g1.addNode(new Node_Data_DS(6));

        g1.connect(4, 5, 3);
        g1.connect(5, 6, 4);
        assertEquals(8, g2.shortestPathDist(1, 6));
    }

    @Test
    void shortestPath() {
        directed_weighted_graph g1 = createMyGraph();
        dw_graph_algorithms g2 = new DWGraph_Algo(g1);
        List<node_data> List = g2.shortestPath(1, 3);
        int[] nodes = {1, 2, 3};
        int i = 0;
        for (node_data n : List) {
            assertEquals(n.getKey(), nodes[i]);
            i++;
        }
        List = g2.shortestPath(1, 1);
        for (node_data n : List) {
            assertEquals(n.getKey(), 1);
            i++;
        }
        g1.addNode(new Node_Data_DS(5));
        g1.addNode(new Node_Data_DS(6));
        g1.connect(4, 5, 3);
        g1.connect(5, 6, 4);
        assertEquals(4, g2.shortestPath(1, 5).size());
    }

    @Test
    void save() {
        directed_weighted_graph g1 = createMyGraph();
        dw_graph_algorithms g2 = new DWGraph_Algo(g1);
        assertTrue(g2.save("graph.json"));
    }

    @Test
    void load() {
        dw_graph_algorithms ga= new DWGraph_Algo();
        ga.load("A1");
        System.out.println(ga.getGraph());
        directed_weighted_graph g1 = createMyGraph();
        directed_weighted_graph g3 = createMyGraph();
        dw_graph_algorithms g2 = new DWGraph_Algo(g1);
       boolean b= g2.save("graph.json");
       assertTrue(g2.load("graph.json"));
       System.out.println(g3.toString());
        System.out.println(g2.getGraph().toString());
       assertEquals(g3,g2.getGraph());
    }
}