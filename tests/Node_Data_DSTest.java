import api.Node_Data_DS;
import api.geo_location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Node_Data_DSTest {

    @Test
    void getKey() {
        Node_Data_DS n=new Node_Data_DS(5);
        assertEquals(5,n.getKey());
    }

    @Test
    void getLocation() {
        Node_Data_DS n=new Node_Data_DS(0);
        assertNull(n.getLocation());
    }

    @Test
    void setLocation() {
        Node_Data_DS n=new Node_Data_DS(6);
        geo_location g=new Node_Data_DS.geoLocation(1,2,3);
        n.setLocation(g);
        assertEquals(g,n.getLocation());
    }

    @Test
    void getWeight() {
        Node_Data_DS n=new Node_Data_DS(3);
        assertEquals(0,n.getWeight());
    }

    @Test
    void setWeight() {
        Node_Data_DS n=new Node_Data_DS(0);
        n.setWeight(5.5);
        assertEquals(5.5,n.getWeight());
    }

    @Test
    void getInfo() {
        Node_Data_DS n=new Node_Data_DS(0);
        assertNull(n.getInfo());
    }

    @Test
    void setInfo() {
        Node_Data_DS n=new Node_Data_DS(0);
        n.setInfo("hallo world");
        assertEquals("hallo world",n.getInfo());
    }

    @Test
    void getTag() {
        Node_Data_DS n=new Node_Data_DS(0);
        assertEquals(0,n.getTag());
    }

    @Test
    void setTag() {
        Node_Data_DS n=new Node_Data_DS(0);
        assertEquals(0,n.getTag());
        n.setTag(1);
        assertEquals(1,n.getTag());
    }
}