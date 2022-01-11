 ### _:fire:Welcome To Pokemon Game_:fire:
 
 ![](https://www.logolynx.com/images/logolynx/9f/9f21a433280ff4df4f143dad2fbe13b6.png)
 
 
 :round_pushpin: 
 _Part one_
----------------------------------------
### :world_map: Directed (positive) Weighted Graph


**In this project we built a directed graph which is also a weighted graph:**

**Here are the departments that build the graph:**

**Node:**_implements node_data and its contain:_

              getKey(), getLocation(),setLocation,getWeight(),setWeight(double w),getInfo,setInfo(String s),double getTag(),setTag(double t) 

**Edge:**_implements edge_data and its contain:_

              getSrc(),getDest(),getWeight(),getInfo(),setInfo(String s),getTag(),setTag(int t)
              
**:zap:WGraph_DS class:**

Each graph has the following properties:_
**there is 3 HashMaps :**

 - **"vertex"**
**"edges"**
 **"parents"**
 
- **numOfEdge:** number of edeges in the graph .
 
 - **mc:**
   count of the change

:pushpin:_**the fanctions:**_

- **WGraph_DS ():**
creating a new graph

- **getNode(int key):**
returns the node_data by the node_id


- **getEdge(int node1, int node2):**
returns the data of the edge (src,dest)

- **addNode(node_data n):**
add  node to the graph
- **connect(int src, int dest, double w):**
Connects an edge with weight w between node src to node dest

- **Collection<node_data> getV():**
returns all the vertex in the graph

- **Collection<edge_data> getE(int node_id):**
 returns a pointer (shallow copy) for the collection representing all the edges getting out ofthe given node (all the edges starting (source) at the given node)
 
- **removeNode(int key):**
remove vertex  from the graph

- **removeEdge(int src, int dest):**
remove the edge between node1 and node2.

- **nodeSize():**
return the number of all  vertex in the graph

- **edgeSize():**
return the number of all  edeges in the graph

- **getMC():**
it return how much  changes was in the graph. 
......................................................................................................................................................................................................................................................
                                                                                        
**:zap: WGraph_Algo:**
_The Graph Algo class contains all algorithms that can be run on a graph._

   :pushpin: _**the fanctions:**_

- **init(directed_weighted_graph g):** Init the graph on which this set of algorithms operates on.

- **getGraph():** Return the underlying graph of which this class works.

- **Copy():** The algorithm compute a deep copy of this graph.

- **isConnected():** Checks whether the graph is strongly related

- **shortestPathDist(int src, int dest):** The algorithm finds the shortest way between the node Src and the node dest, the shortest way is the way the least amount of weight .

- **List<node_info>shortestPathDist(int src, int dest):** return List of the shortest path.

- **save(String file):** Saves this weighted (directed) graph to the given -> file name - in JSON format

- **load(String file):** This method load a graph to this graph algorithm.
 -------------------------------------------------------------------------------------------------------------------------
  -------------------------------------------------------------------------------------------------------------------------
 ### _:round_pushpin:  Part two_
 ![](https://fount.in/wp-content/uploads/2016/10/pokemon-go-apk.png)

### Pekemon game

 - **Arena:**
  This class represents a multi Agents Arena which move on a graph - grabs Pokemons and avoid the Zombies.
  - **CL_Agent:**
 - **CL_Pokemon:**
 - **MyFrame:**
 This class represents a very simple GUI class to present a
  game on a graph - you are welcome to use this class - yet keep in mind
 that the code is not well written in order to force you improve the
 code and not to take it "as is".
 - **SimpleGameClient:**
 This class represents the simplest "Client-Game" main class
  which uses the "server for moving the "Agents".
  Note: this code is a very simple no algorithm no threaded mechanism - it is presented just to show the basic
  use of the "server".

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
---------------------------------------------------------------------------------------------------- 
_:books:_For more information to this project::point_down:
- **Weighted Directed Graph:**-
       
     1) https://www.softwaretestinghelp.com/java-graph-tutorial/

     2) https://wiki.c2.com/?WeightedDirectedGraph 
     
     3) https://youtu.be/XkeG0gYdytg

     

- **Gson:**- **https://en.wikipedia.org/wiki/Gson**
- **Check the jeson online:**- **https://jsonformatter.curiousconcept.com/**
- **HashMap:**- **https://www.youtube.com/watch?v=ceh8s-r53m0&ab_channel=JavaCodeGeeks**
- **Check if a graph is strongly connected:**- **https://www.geeksforgeeks.org/connectivity-in-a-directed-graph/**

![](https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/CPT-Graphs-directed-weighted-ex2.svg/175px-CPT-Graphs-directed-weighted-ex2.svg.png) 
            ![](https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/CPT-Graphs-directed-weighted-ex1.svg/175px-CPT-Graphs-directed-weighted-ex1.svg.png)


![](https://i.ytimg.com/vi/KrPKq4lk1NQ/maxresdefault.jpg)
  
 
 






