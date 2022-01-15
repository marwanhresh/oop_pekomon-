package api;

import java.util.*;

public class DWGraph_DS implements directed_weighted_graph {

    /**
     * EdgeData:
     * This class represents the set of operations applicable on a
     * directional edges in a directional weighted graph.
     * this class represents an edge between two nodes in a graph
     *
     */
    public class EdgeData implements edge_data {

        /**
         * EdgeLocation:
         * <p>
         * This class represents a position on the graph (a relative position
         * on an edge - between two consecutive nodes).
         */
        class EdgeLocation implements edge_location {
            private edge_data edge;
            private double ratio;

            /**
             * Returns the edge on which the location is.
             *
             * @return
             */
            @Override
            public edge_data getEdge() {
                return this.edge;
            }

            /**
             * Returns the relative ration [0,1] of the location between src and dest.
             *
             * @return
             */
            @Override
            public double getRatio() {
                return this.ratio;
            }
        }

        /**
         * EdgeData
         */
        private int src;
        private int dest;
        private double edgeWeight;
        private String info;
        private int tag;


        /**
         * EdgeData constructor
         *
         * @param src
         * @param dest
         * @param w
         */
        public EdgeData(int src, int dest, double w) {
            this.src = src;
            this.dest = dest;
            this.edgeWeight = w;
            info = "";
            tag = 0;
        }

        /**
         * The id of the source node of this edge.
         *
         * @return this.src
         */
        @Override
        public int getSrc() {
            return this.src;
        }

        /**
         * The id of the destination node of this edge
         *
         * @return this.dest
         */
        @Override
        public int getDest() {
            return this.dest;
        }

        /**
         * @return the weight of this edge (positive value).
         */
        @Override
        public double getWeight() {
            return this.edgeWeight;
        }

        /**
         * Returns the remark (meta data) associated with this edge.
         *
         * @return this.info
         */
        @Override
        public String getInfo() {
            return this.info;
        }

        /**
         * Allows changing the remark (meta data) associated with this edge.
         *
         * @param s
         */
        @Override
        public void setInfo(String s) {
            this.info = s;
        }

        /**
         * return the tag of the edge
         *
         * @return this.tag
         */
        @Override
        public int getTag() {
            return this.tag;
        }

        /**
         * This method allows setting the "tag" value for temporal marking an edge - common
         * practice for marking by algorithms.
         *
         * @param t - the new value of the tag
         */
        @Override
        public void setTag(int t) {
            this.tag = t;
        }

        public String toString() {
            String s = " {edge src=" + src + ", edge dest=" + dest +
                    ", edge Weight=" + edgeWeight + "}\n";
            return s;
        }

        /**
         * check if two edges are equals
         *
         * @param o
         * @return
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EdgeData edgeData = (EdgeData) o;
            return src == edgeData.src &&
                    dest == edgeData.dest &&
                    Double.compare(edgeData.edgeWeight, edgeWeight) == 0 &&
                    tag == edgeData.tag &&
                    Objects.equals(info, edgeData.info);
        }

        @Override
        public int hashCode() {
            return Objects.hash(src, dest, edgeWeight, info, tag);
        }
    }

    /**
     * neighbors:
     * This class represents a hashMap for the Neighbors.
     */
    private class neighbors {
        private HashMap<Integer, EdgeData> neighbors;

        /**
         * constructor that create a new empty hashMap for the Neighbors.
         */
        private neighbors() {
            neighbors = new HashMap<Integer, EdgeData>();
        }

        /**
         * checks if the Neighbors Hashmaps are equals.
         *
         * @return true- if they are equals, else- return false.
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            neighbors neighbors1 = (neighbors) o;
            return Objects.equals(neighbors, neighbors1.neighbors);
        }

        @Override
        public int hashCode() {
            return Objects.hash(neighbors);
        }
    }

    /**
     * DWGraph_DS:
     * <p>
     * This class represents all the nodes in the graph.
     * This graph is an directional weighted graph.
     * The Data Structures that used is "HashMap" because it is very effective.
     * The first HashMap is represent the node in the graph.
     * The second HashMap is Type of "HashMap in HashMap" for the edges between one node to his Neighbors.
     */
    private HashMap<Integer, node_data> graphMap;
    private HashMap<Integer, neighbors> edgesMap;
    private int Edges = 0;
    private int Mc = 0;

    /**
     * constructor for DWGraph_DS to create new empty hashMaps for the graph.
     */
    public DWGraph_DS() {
        int Edges = 0;
        int Mc = 0;
        graphMap = new HashMap<Integer, node_data>();
        edgesMap = new HashMap<Integer, neighbors>();
    }

    /**
     * returns the node_data by the node_id,
     *
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    @Override
    public node_data getNode(int key) {
        if (this.graphMap.containsKey(key))
            return this.graphMap.get(key);
        else
            return null;
    }

    /**
     * returns the data of the edge (src,dest), null if none.
     *
     * @param src
     * @param dest
     * @return edge_data , null if none.
     */
    @Override
    public edge_data getEdge(int src, int dest) {
        if (this.edgesMap.get(src).neighbors.containsKey(dest) == true)
            return this.edgesMap.get(src).neighbors.get(dest);
        else
            return null;
    }

    /**
     * adds a new node to the graph with the given node_data.
     * check if the node is in the graph by his key.
     * if the node is not in the graph we add it to the graph.
     * else doing nothing.
     *
     * @param n node_data
     */
    @Override
    public void addNode(node_data n) {
        if (!this.graphMap.containsKey(n.getKey())) {
            this.graphMap.put(n.getKey(), n);
            this.edgesMap.put(n.getKey(), new neighbors());
            Mc++;
        }
    }

    /**
     * Connect an edge between node1 and node2, with an edge with weight >=0.
     * check if the nodes is in the graph by their keys.
     * check if the nodes are different, check if weight >=0 .
     * If this are true- check if the nodes are not neighbors and connect between them.
     * if they are already neighbors- update their wight.
     *
     * @param src  - the source of the edge.
     * @param dest - the destination of the edge.
     * @param w    - positive weight representing the cost (aka time, price, etc) between src-->dest.
     */

    @Override
    public void connect(int src, int dest, double w) {
        if (this.graphMap.get(src) != null && this.graphMap.get(dest) != null
                && src != dest && w >= 0) {
            if (!(this.edgesMap.get(src).neighbors.containsKey(dest))) {
                this.edgesMap.get(src).neighbors.put(dest, new EdgeData(src, dest, w));
                Edges++;
                Mc++;
            } else if (this.getEdge(src, dest).getWeight() != w) {
                this.edgesMap.get(src).neighbors.put(dest, new EdgeData(src, dest, w));
                Mc++;
            }
        }
    }

    /**
     * This method returns a pointer (shallow copy) for the
     * collection representing all the nodes in the graph.
     * return all Vertex in the graph.
     *
     * @return Collection<node_data>
     */
    @Override
    public Collection<node_data> getV() {
        return this.graphMap.values();
    }

    /**
     * This method returns a pointer (shallow copy) for the
     * collection representing all the edges getting out of
     * the given node (all the edges starting (source) at the given node).
     * return all edges in the graph.
     *
     * @param node_id
     * @return Collection<edge_data>
     */
    @Override
    public Collection<edge_data> getE(int node_id) {
        LinkedList<edge_data> cE = new LinkedList<>();
        if (this.graphMap.containsKey(node_id)) {
            Collection<Integer> I = this.edgesMap.get(node_id).neighbors.keySet();
            Iterator<Integer> temp = I.iterator();
            while (temp.hasNext()) {
                int key = temp.next();
                cE.add(this.getEdge(node_id, key));
                // cE.add(edgesMap.get(node_id).neighbors.get(key));
            }
        }
        return cE;
    }

    /**
     * Deletes the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * check if the node is in the graph by his key, if it is true-  delete the node from the graph.
     * and return the deleted node, else return null.
     *
     * @param key int
     * @return the data of the removed node (null if none).
     */
    @Override
    public node_data removeNode(int key) {
        Mc++;
        if (this.graphMap.containsKey(key)) {
            Collection<Integer> c = this.edgesMap.keySet();
            Iterator<Integer> i = c.iterator();
            while (i.hasNext()) {
                int node = i.next();
                if (this.edgesMap.get(node).neighbors.containsKey(key)) {
                    this.edgesMap.get(node).neighbors.remove(key);
                    Edges--;
                }
            }
            return this.graphMap.remove(key);
        } else
            return null;
    }

    /**
     * Delete the edge from the graph,
     * check if the two nodes is connect in the graph by their keys,
     * if it is true-  delete the edge between them.
     * @param src
     * @param dest
     * @return the data of the removed edge (null if none).
     */
    @Override
    public edge_data removeEdge(int src, int dest) {
        if (this.edgesMap.get(src).neighbors.containsKey(dest)) {
            edge_data edge = this.getEdge(src, dest);
            this.edgesMap.get(src).neighbors.remove(dest);
            Edges--;
            Mc++;
            return edge;
        }
        return null;
    }

    /** Returns the number of nodes in the graph.
     * @return this.graphMap.size()
     */
    @Override
    public int nodeSize() {
        return this.graphMap.size();
    }

    /**
     * Returns the number of edges (assume directional graph).
     * @return this.Edges
     */
    @Override
    public int edgeSize() {
        return this.Edges;
    }

    /**
     * Returns the Mode Count - for testing changes in the graph.
     * @return this.Mc
     */
    @Override
    public int getMC() {
        return this.Mc;
    }

    /**
     * checks if the graphs are equals.
     *
     * @param o
     * @return true- if they are equals, else- return false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DWGraph_DS that = (DWGraph_DS) o;
        return Edges == that.Edges &&
                Objects.equals(graphMap, that.graphMap) &&
                Objects.equals(edgesMap, that.edgesMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(graphMap, edgesMap, Edges, Mc);
    }

    /**
     * @return string of the graph
     */
    public String toString() {
        String s = new String("");
        for (node_data n : this.getV()) {
            s = s + n + edgesMap.get(n.getKey()).neighbors.keySet() + "\n";
        }
        return s;
    }
}
