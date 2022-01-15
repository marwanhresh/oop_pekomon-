package api;

import java.util.Objects;

public class Node_Data_DS implements node_data {

    /**
     * geoLocation:
     * * This interface represents a geo location (x,y,z), aka Point3D
     */
    public static class geoLocation implements geo_location {
        private double x;
        private double y;
        private double z;

        /**
         * geoLocation constructor
         *
         * @param x,y,z
         */
        public geoLocation(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        /**
         * geoLocation copy constructor
         *
         * @param p
         */
        private geoLocation(geo_location p) {
            this.x = p.x();
            this.y = p.y();
            this.z = p.z();
        }

        /**
         * @return x
         */
        @Override
        public double x() {
            return this.x;
        }

        /**
         * @return y
         */
        @Override
        public double y() {
            return this.y;
        }

        /**
         * @return z
         */
        @Override
        public double z() {
            return this.z;
        }

        /**
         * get a geo_location and return the distance
         *
         * @param g
         * @return distance
         */
        @Override
        public double distance(geo_location g) {
            double distance, x, y, z;
            x = Math.pow(g.x() - this.x, 2);
            y = Math.pow(g.x() - this.y, 2);
            z = Math.pow(g.x() - this.z, 2);
            distance = Math.sqrt(x + y + z);
            return distance;
        }

        /**
         * @return string of x,y,z
         */
        @Override
        public String toString() {
            String s = x + "," + y + "," + z;
            return s;
        }

        /**
         * check if two geo_location are equals
         *
         * @param o
         * @return true if equals, else- false
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            geoLocation that = (geoLocation) o;
            return Double.compare(that.x, x) == 0 &&
                    Double.compare(that.y, y) == 0 &&
                    Double.compare(that.z, z) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }
    }

    /**
     * Node_Data_DS:
     * <p>
     * This class represents the set of operations applicable on a
     * node in a directional weighted graph.
     */
    private int key;
    private String info;
    private geo_location location;
    private double nodeWeight;
    private int tag;

    /**
     * Node_Data_DS constructor creates a node with specific key
     *
     * @param key (key of the node)
     */
    public Node_Data_DS(int key) {
        this.key = key;
        this.info = null;
        this.location = null;
        this.nodeWeight = 0;
        this.tag = 0;
    }

    /**
     * Node_Data_DS copy constructor.
     *
     * @param node
     */
    public Node_Data_DS(node_data node) {
        this.key = node.getKey();
        this.info = node.getInfo();
        this.location = node.getLocation();
        this.nodeWeight = node.getWeight();
        this.tag = node.getTag();
    }

    /**
     * Node_Data_DS copy constructor.
     *
     * @param key
     * @param info
     * @param location
     * @param nodeWeight
     * @param tag
     */
    public Node_Data_DS(int key, String info, geo_location location, double nodeWeight, int tag) {
        this.key = key;
        this.info = info;
        this.location = location;
        this.nodeWeight = nodeWeight;
        this.tag = tag;
    }

    /**
     * Node_Data_DS copy constructor.
     *
     * @param key
     *  @param location
     */
    public Node_Data_DS(int key, geo_location location) {
        this.key = key;
        this.location = location;
        this.nodeWeight = 0;
        this.tag = 0;
        this.info = "";
    }

    /**
     * This method return the key associated with this node.
     * For each node_data should have a unique key.
     *
     * @return this.key
     */
    @Override
    public int getKey() {
        return this.key;
    }

    /**
     * Returns the location of this node, if none return null.
     *
     * @return this.location
     */
    @Override
    public geo_location getLocation() {
        return this.location;
    }

    /**
     * Allows changing this node's location.
     *
     * @param p - new location of this node.
     */
    @Override
    public void setLocation(geo_location p) {
        this.location = new geoLocation(p);
    }

    /**
     * Returns the weight associated with this node.
     *
     * @return this.nodeWeight
     */
    @Override
    public double getWeight() {
        return this.nodeWeight;
    }

    /**
     * Allows changing this node's weight.
     *
     * @param w - the new weight
     */
    @Override
    public void setWeight(double w) {
        this.nodeWeight = w;
    }

    /**
     * Returns the remark (meta data) associated with this node.
     *
     * @return this.info
     */
    @Override
    public String getInfo() {
        return this.info;
    }

    /**
     * Allows changing the remark (meta data) associated with this node.
     *
     * @param s
     */
    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    /**
     * return the tag of the node
     *
     * @return this.tag
     */
    @Override
    public int getTag() {
        return this.tag;
    }

    /**
     * allows setting the "tag" value for temporal marking an node - common practice for marking by algorithms.
     *
     * @param t - the new value of the tag
     */
    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    /**
     * get node's data.
     * return String
     */
    public String toString() {
        String s = "Node_Data_DS key:" + key + ": tag=" + tag + ", info=" + info +
                ", location=" + location + ", nodeWeight=" + nodeWeight + "}\n";
        return s;
    }

    /**
     * check if the 2 nodes are equals
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node_Data_DS that = (Node_Data_DS) o;
        return key == that.key &&
                Double.compare(that.nodeWeight, nodeWeight) == 0 &&
                tag == that.tag &&
                Objects.equals(info, that.info) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, info, location, nodeWeight, tag);
    }
}



