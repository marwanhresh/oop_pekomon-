package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.*;
import java.util.*;

public class DWGraph_Algo implements dw_graph_algorithms {

    /**
     * Nodes_GA:
     * this class represents the set of operations applicable on a
     * node in a directional weighted graph, and presents his father.
     */
    public class Nodes_GA implements Comparable<Nodes_GA> {
        private int key;
        private String info;
        private double weight;
        private node_data father;

        /**
         * Nodes_GA constructor
         *
         * @param keyNode
         */
        private Nodes_GA(int keyNode) {
            this.key = keyNode;
            this.info = "";
            this.weight = 0;
            this.father = null;
        }

        /**
         * return the key of this Nodes_GA
         *
         * @return this.key
         */
        public int getKey2() {
            return this.key;
        }

        /**
         * return the info of this Nodes_GA
         *
         * @return this.info
         */
        public String getInfo2() {
            return this.info;
        }

        /**
         * return the weight of this Nodes_GA
         *
         * @return this.weight
         */
        public double getWeight2() {
            return this.weight;
        }

        /**
         * return the father of this Nodes_GA
         *
         * @return this.father
         */
        public node_data getFather2() {
            return this.father;
        }

        /**
         * set the node weight
         *
         * @param w
         */
        public double setWeight2(double w) {
            return this.weight = w;
        }

        /**
         * set the node info
         *
         * @param i
         */
        public String setInfo2(String i) {
            return this.info = i;
        }

        /**
         * set the node father
         *
         * @param f
         */
        public node_data setFather2(node_data f) {
            return this.father = f;
        }

        /**
         * comper between two Nodes_GA by their weight
         *
         * @param x
         */
        @Override
        public int compareTo(Nodes_GA x) {
            int ans = 0;
            if (this.getWeight2() > x.getWeight2())
                ans = 1;
            else if (this.getWeight2() < x.getWeight2())
                ans = -1;
            return ans;
        }

        /**
         * @return string of Nodes_GA
         */
        @Override
        public String toString() {
            return "{" +
                    "key=" + key +
                    ", info='" + info + '\'' +
                    ", weight=" + weight +
                    ", father=" + father +
                    '}';
        }
    }

    /**
     * DWGraph_Algo:
     * This class do algorithmic operations on an directional weighted graph that created by "DWGraph_DS".
     */
    private directed_weighted_graph g;

    /**
     * This constructor creates a new empty graph.
     */
    public DWGraph_Algo() {
        directed_weighted_graph g = new DWGraph_DS();
    }

    /**
     * copy constructor for DWGraph_Algo to create a new graph algo
     *
     * @param newG graph
     */
    public DWGraph_Algo(directed_weighted_graph newG) {
        this.g = newG;
    }

    /**
     * Init the graph on which this set of algorithms operates on.
     * change the value of the graph inside the object.
     *
     * @param g
     */
    @Override
    public void init(directed_weighted_graph g) {
        this.g = g;
    }

    /**
     * Return the underlying graph of which this class works.
     *
     * @return this.g
     */
    @Override
    public directed_weighted_graph getGraph() {
        return this.g;
    }

    /**
     * Compute a deep copy of this weighted graph.
     * run first to add the nodes from the graph.
     * after run by the node to add all his Neighbors.
     *
     * @return newG
     */
    @Override
    public directed_weighted_graph copy() {
        directed_weighted_graph newG = new DWGraph_DS();
        for (node_data node : g.getV()) {
            newG.addNode(node);
            newG.getNode(node.getKey()).setInfo(node.getInfo());
            newG.getNode(node.getKey()).setTag(node.getTag());
            newG.getNode(node.getKey()).setWeight(node.getWeight());
            newG.getNode(node.getKey()).setLocation(node.getLocation());
        }
        for (node_data node1 : g.getV()) {
            for (edge_data edge : g.getE(node1.getKey())) {
                newG.connect(node1.getKey(), edge.getDest(),
                        g.getEdge(node1.getKey(), edge.getDest()).getWeight());
            }
        }
        return newG;
    }

    /**
     * bfc:
     * this class is a help function to "is connected".
     * which check if the graph is connected from src to all the nodes.
     * The method uses BFS algorithm in order to check this.
     * This algorithm uses a queue in order to store the nodes and go over the graph.
     * The algorithm changes the nodes tags from 0 to 1, and in this way
     * check if there is a path from src to all nodes.
     * If there is a node with tag=0 the graph is not connect.
     *
     * @param src, graph
     * @return true if the graph connect, else return false.
     */
    private boolean bfs(int src, directed_weighted_graph graph) {
        //check if the node is in the graph.
        if (g.getNode(src) == null)
            return false;

        // zeroing all tags
        for (node_data n : graph.getV())
            graph.getNode(n.getKey()).setTag(0);

        Queue<node_data> q = new LinkedList<node_data>();
        node_data first = g.getNode(src);
        q.add(graph.getNode(src));
        graph.getNode(src).setTag(1);
        while (!q.isEmpty()) {
            first = q.poll();
            for (edge_data e : graph.getE(first.getKey())) { //check current neighbors
                if (graph.getNode(e.getDest()).getTag() == 0) {
                    q.add(graph.getNode(e.getDest()));
                    graph.getNode(e.getDest()).setTag(1);
                }
            }
        }

        //check if the graph is connected
        for (node_data n : graph.getV())
            if (n.getTag() == 0)
                return false;
        return true;
    }

    /**
     * this class is a help function to "is connected" class.
     * finds the transpose of graph
     *
     * @return reversG
     */
    private directed_weighted_graph Transpose() {
        directed_weighted_graph reversG = new DWGraph_DS();

        //add all the nodes to reversG
        Collection<node_data> I = g.getV();
        Iterator<node_data> temp = I.iterator();
        while (temp.hasNext()) {
            node_data n = temp.next();
            node_data newN = new Node_Data_DS(n);
            reversG.addNode(newN);
        }

        for (node_data n : g.getV())
            for (edge_data e : g.getE(n.getKey())) {
                reversG.connect(e.getDest(), e.getSrc(), e.getWeight());
            }
        return reversG;
    }

    /**
     * Returns true if and only if (iff) there is a valid path from EVERY node to each other node.
     * The method uses bfc,Transpose classes in order to check if the graph is linked.
     * from every node to each other node.
     * If the graph is empty - returns true.
     * if the graph is linked by straight Tracking and also by  back Tracking- the graph is connected.
     *
     * @return true if the graph connect, else return false.
     */
    @Override
    public boolean isConnected() {
        if (g.nodeSize() == 0 || g.nodeSize() == 1) return true;

        boolean straightTracking = false;
        boolean backTracking = false;

        node_data myNode = null;
        for (node_data start : g.getV()) {
            myNode = g.getNode(start.getKey());
            straightTracking = bfs(myNode.getKey(), this.g);
            backTracking = bfs(myNode.getKey(), Transpose());
            break;
        }

        if (straightTracking == true && backTracking == true)
            return true;
        else return false;
    }

    /**
     * Dijkstra:
     * this class is a help function to "shortestPathDist", "shortestPath" classes.
     * update the father of all the nodes.
     * return a hashMap with update fathers nodes.
     * we use a Priority Queue in order to find the father to each node.
     * @param src
     * @return parentGraph
     */
    private HashMap Dijkstra(int src) {

        if (g.getNode(src) == null)  //check if the node is in the graph.
            return null;

        PriorityQueue<Nodes_GA> PQ = new PriorityQueue<>();
        HashMap<Integer, Nodes_GA> parentGraph = new HashMap<>();

        Nodes_GA start = new Nodes_GA(src);
        start.setWeight2(0); //set the first tag node to- 0
        start.setInfo2("not visited");
        PQ.add(start);
        parentGraph.put(start.getKey2(), start);

        //set all the tags to- infinity, and the info to- "not visited".
        for (node_data node : g.getV())
            if (node.getKey() != start.getKey2()) {
                Nodes_GA nodeGa = new Nodes_GA(node.getKey());
                nodeGa.setInfo2("not visited");
                nodeGa.setWeight2(Double.MAX_VALUE);
                parentGraph.put(node.getKey(), nodeGa);
            }

        while (!PQ.isEmpty()) {
            Nodes_GA current = PQ.peek();
            for (edge_data e : g.getE(current.getKey2())) { //check current neighbors
                if (parentGraph.get(e.getDest()).getInfo2().equals("not visited")) {
                    double path = current.getWeight2() + e.getWeight();
                    double NodeWeight = parentGraph.get(e.getDest()).getWeight2();
                    if (path < NodeWeight) {
                        parentGraph.get(e.getDest()).setWeight2(path);
                        parentGraph.get(e.getDest()).setFather2(g.getNode(current.getKey2()));
                        PQ.add(parentGraph.get(e.getDest()));
                    }
                }
            }
            current.setInfo2("visited");
            PQ.poll();
        }
        return parentGraph;
    }

    /**
     * returns the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     * Uses Dijkstra algorithm to check if there is a path between src and dest.
     * if it is true- return the tag of the node. (the tag is the wight of the path).
     * @param src  - start node
     * @param dest - end (target) node
     * @return double of the weights by the path from src to dest,
     * if there is no path from src to dest return -1.
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        if (src == dest)
            return 0;

        if (g != null) {
            if (g.getNode(src) != null && g.getNode(dest) != null) {
                HashMap<Integer, Nodes_GA> parentHash = Dijkstra(src);
                if (parentHash.get(dest).getInfo2() == "visited")
                    return parentHash.get(dest).getWeight2();
            }
        }
        return -1;
    }

    /**
     * returns the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * Note if no such path --> returns null;
     * Uses Dijkstra algorithm to create the list of the path between src and dest.
     * @param src  - start node
     * @param dest - end (target) node
     * @return List of the node_data that present the path from src to dest,
     * if there is no path from src to dest return null.
     */
    @Override
    public List<node_data> shortestPath(int src, int dest) {
        if (g.getNode(src) == null || g.getNode(dest) == null)
            return null;
        if (shortestPathDist(src, dest) == -1)
            return null;

        Stack<node_data> pathList = new Stack<>();
        LinkedList<node_data> answer = new LinkedList<node_data>();
        HashMap<Integer, Nodes_GA> parentHash = Dijkstra(src);
        Nodes_GA node = parentHash.get(dest);

        if (src == dest)
            return pathList;

        while (node.getKey2() != src) {
            pathList.add(g.getNode(node.getKey2()));
            node = parentHash.get(node.getFather2().getKey());
        }
        pathList.add(g.getNode(src));

        while (!pathList.isEmpty()) {
            node_data p = pathList.pop();
            answer.add(p);
        }
        return answer;
    }

    /**
     * Saves this weighted (directed) graph to the given
     * file name - in JSON format
     * @param file - the file name (may include a relative path).
     * @return true - iff the file was successfully saved
     */
    @Override
    public boolean save(String file) {
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(g);

        //Write JSON to file
        try {
            PrintWriter pw = new PrintWriter(new File(file));
            pw.write(json);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * This method load a graph to this graph algorithm.
     * if the file was successfully loaded - the underlying graph
     * of this class will be changed (to the loaded one), in case the
     * graph was not loaded the original graph should remain "as is".
     * @param file - file name of JSON file
     * @return true - iff the graph was successfully loaded.
     */
    @Override
    public boolean load(String file) {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(DWGraph_DS.class, new GraphJson());
            Gson json = builder.create();
            FileReader reader = new FileReader(file);
            g = json.fromJson(reader, DWGraph_DS.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

