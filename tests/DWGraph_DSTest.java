
import api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;


class DWGraph_DSTest {
    directed_weighted_graph graph;

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
        for (int i = 0; i <= size; i++) {
            myG.addNode(new Node_Data_DS(i));
        }
        return myG;
    }

    @BeforeEach
    public void resetGraph() {
        graph = new DWGraph_DS();
    }

    @Test
    void getNode() {
        directed_weighted_graph g=createMyGraph();
        assertEquals(4, g.getNode(4).getKey());
        assertNotNull(g.getNode(3)); //node is exist
        assertNull(g.getNode(70), "the node is not exist");
    }

    @Test
    void getEdge() {
        directed_weighted_graph g=createMyGraph();
        assertNull(g.getEdge(3, 2));
        assertNotNull(g.getEdge(0, 3));
        assertNull( g.getEdge(1, 3), "the edge is not exist");
        assertEquals(16, g.getEdge(2, 3).getWeight(), "the edge is not exist");
        assertNotEquals(5, g.getEdge(0, 3));
    }


    @Test
    void addNode() {
        directed_weighted_graph g=createMyGraph();
        g.addNode(new Node_Data_DS(5)); // add positive node
        assertNotNull(g.getNode(5));
        g.addNode(new Node_Data_DS(-5)); // add negative node
    }

    @Test
    void connect() {
        directed_weighted_graph g=createMyGraph();
        g.connect(3,0,6);
        assertNotNull(g.getEdge(3,0));
        assertEquals(6,g.getEdge(3,0).getWeight());
    }

    @Test
    void getV() {
        directed_weighted_graph g=createMyGraph();
        assertNotNull(g.getV());
        Collection<node_data> c = g.getV();
        int[] nodes = {0, 1, 2, 3, 4};
        int i=0;
        for( node_data n: c){
            assertEquals(n.getKey(), nodes[i]);
            i++;
        }
        assertTrue(g.getV().contains(g.getNode(0)));
        g.removeNode(0);
        assertFalse(g.getV().contains(g.getNode(0)));
    }

    @Test
    void getE() {
        directed_weighted_graph g=createMyGraph();
        assertNotNull(g.getE(2));
        Collection<edge_data> c = g.getE(0);
        int[] nodes = {2, 4};
        int i=0;
        for(edge_data  e: c){
            assertEquals(e.getWeight(), nodes[i]);
            i++;
        }
        assertTrue(g.getE(2).contains(g.getEdge(2,3)));
        g.removeNode(3);
        assertFalse(g.getE(2).contains(g.getEdge(2,3)));
    }

    @Test
    void removeNode() {
        directed_weighted_graph g=createMyGraph();
        g.removeNode(0);
        assertEquals(4,g.nodeSize());
        assertNull(g.getNode(0));

    }

    @Test
    void removeEdge() {
        directed_weighted_graph g=createMyGraph();
        assertEquals(2,g.getEdge(0,1).getWeight());
        g.removeEdge(0, 1);
        assertNull(g.getEdge(0,1));
        assertEquals(5,g.edgeSize());
    }

    @Test
    void nodeSize() {
        directed_weighted_graph g=createMyGraph();
        assertEquals(5,g.nodeSize());
    }

    @Test
    void edgeSize() {
        directed_weighted_graph g=createMyGraph();
        assertEquals(6,g.edgeSize());
    }

    @Test
    void getMC() {
        directed_weighted_graph g=createMyGraph();
        g.addNode(new Node_Data_DS(5));
        g.addNode(new Node_Data_DS(6));
        g.removeNode(3);
        g.removeEdge(1,4);
        assertEquals(15,g.getMC());
    }

    @Test
    void testEquals() {
        directed_weighted_graph g= createMyGraph();
        assertEquals(g,g);
        directed_weighted_graph g2=createMyGraph2(100);
        assertNotEquals(g,g2);
        directed_weighted_graph g3= createMyGraph();
        g.connect(1,4,2);
        assertNotEquals(g,g2);
    }

    @Test
    void testToString() {
        directed_weighted_graph g=createMyGraph();
        System.out.println(g);
    }
}