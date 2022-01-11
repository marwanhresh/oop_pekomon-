*pokemon game*




![](https://www.logolynx.com/images/logolynx/9f/9f21a433280ff4df4f143dad2fbe13b6.png)








**links:**


1)https://www.youtube.com/watch?v=gx_qorHxBpI

2)https://stackoverflow.com/questions/7942384/simple-java-pokemon-fight-simulator

3)https://kandi.openweaver.com/java/rizaozcelik/PokemonGame

For a final project in a computer science class, I developed many individual pieces of code that eventually came together as a playable game. Interfaces were developed for the many possible Pokemon types (e.g. grass, electric, etc.) in order to allow strengths and weaknesses to play a part in combat. Several dozen separate pokemon were developed, with unique names, types, and stats, along with unique growth rates once instantiated. A battle system was then developed to allow two players to compete. They would each choose a pokemon, name them, and then watch the action happen…in text form. In addition, a method for catching pokemon and storing them was created, including a “pokedex” which stored information about all pokemon caught even when traded away or released. Once the game was functional, I developed a GUI (Graphical User Interface) such that with a text input box and four buttons all of these actions could be undertaken by the player(s)

Weighted Graphs :

A weighted graph is a graph with edges labeled by numbers (called weights). In general, we only consider nonnegative edge weights. Sometimes, ∞ can also be allowed as a weight, which in optimization problems generally means we must (or may not) use that edge. In many applications, each edge of a graph has an associated numerical value, called a weight. Usually, the edge weights are nonnegative integers. Weighted graphs may be either directed or undirected. The weight of an edge is often referred to as the “cost” of the edge. In applications, the weight may be a measure of the length of a route, the capacity of a line, the energy required to move between locations along a route, etc.
SyntaxEditor Code Snippet
 
WGraph_DS class:** Each graph has the following properties:_
**there is 3 HashMaps :**

 - **"vertex"**
**"edges"**
 **"parents"**
 
- **numOfEdge:** number of edeges in the graph .
 
 - **mc:**
   count of the change


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


Diagram: 

file:///C:/Users/USER1/Desktop/uml.png


                                                                                        
** WGraph_Algo:**
_The Graph Algo class contains all algorithms that can be run on a graph._

   **the fanctions:**_

- **init(directed_weighted_graph g):** Init the graph on which this set of algorithms operates on.

- **getGraph():** Return the underlying graph of which this class works.

- **Copy():** The algorithm compute a deep copy of this graph.

- **isConnected():** Checks whether the graph is strongly related

- **shortestPathDist(int src, int dest):** The algorithm finds the shortest way between the node Src and the node dest, the shortest way is the way the least amount of weight .

- **List<node_info>shortestPathDist(int src, int dest):** return List of the shortest path.

- **save(String file):** Saves this weighted (directed) graph to the given -> file name - in JSON format

- **load(String file):** This method load a graph to this graph algorithm.



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

****class Client** :** 

* to start a new connection to the game server


* @param ip

* @param port

* @throws UnknownHostException

* @throws IOException


public void startConnection(String ip, int port) throws UnknownHostException,



}

private String sendMessage(String msg) throws IOException 

* use this function to signal end of comunication with the server.


* @throws IOException


public void stopConnection() throws IOException {

return json str of agents


public String getAgents() 


* @param jsonOfNode should be in this format:






* (replace 0 with the desired starting node for the agent.)

* return 'true' (as str) iff the agent has been added succesfuly


public String addAgent(String jsonOfNode) 

* use start to run the game


public void start() 


 <html>

 <h1>choosing the next destination for a specific agent.</h1>

* <h2>Note that if</h2>


* 1. the agent is still moving on some edge, (a.k. agent.dest != -1)

* or<br>

* 2. the "next_node_id" isn't an adjacent vertex of agent.src,

* then move() won't be affected by this invalid "next_node_id" choice.


* @param jsonAgentAndNode next_agent_node_json should be in format:

public void chooseNextEdge(String jsonAgentAndNode) 



* activate all valid choose_next_edge calls. returns: agents state with the

* same form as get_agents()


public void move() 


* use stop to end the game and upload results.

* Note: results will be uploaded only after login and scores > 0.


public void stop() 


* returns the graph as json str. for example:



* @return the graph as json str


public String getGraph() 


* enter your id as str to login and upload your score to the web server


public void login(String id) 



* returns the current pokemons state as json str.



* @return returns the current pokemons state as json str.

* for pokemon lying on edge (src,dest)


public String getPokemons() 

* @return time to end in mili-seconds str. for example: '29996'


public String timeToEnd() 




* @return 'true' (as str) if the game is still running, else: returns 'false'

* (also str)


public String isRunning() 








* returns the current game info. for example:




* @return the current game info.
 
public String getInfo()
