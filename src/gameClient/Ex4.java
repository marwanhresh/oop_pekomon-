package gameClient;

import Server.Game_Server_Ex4;
import api.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * this class includes function that starts the game.
 */
public class Ex4 implements Runnable {

    private static GameFrame _win;
    private static Arena arena;
    private static long time;
    private static double grade;
    private static int moves;
    public static int level_number;
    public static int ID;
    private static HashMap<Integer, CL_Pokemon> agentToPokemon = new HashMap<Integer, CL_Pokemon>();
    public static Thread client = new Thread(new Ex4());

    /**
     * Call for user to insert login ID number, and a level number.
     *
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            ID = Integer.parseInt(args[0]);
            level_number = Integer.parseInt(args[1]);
            client.start();
        } else new Label();
    }

    /**
     * start the game by loading the graph from the JSON file according to the chosen level.
     * then start the game by functions.
     */
    @Override
    public void run() {
        game_service game = Game_Server_Ex4.getServer(level_number); 
//      System.out.println(game.getGraph());
//      System.out.println(game.getPokemons());
//      System.out.println(game);

        game.login(ID);
        String g = game.getGraph();
        String pks = game.getPokemons();
        directed_weighted_graph gg = buildGraphFromJason(game.getGraph());
        init(game);

        game.startGame(); // starting the game
        int ind = 0;
        long dt = 100;
        while (game.isRunning()) {
            this.setTime(game.timeToEnd());
            moveAgents(game, gg);
            this.setMoves(game);
            this.setGrade(game);
            try {
                if (ind % 3 == 0) {
                    _win.repaint();
                }
                Thread.sleep(dt);
                ind++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String res = game.toString();
        System.out.println(res);
        System.exit(0);
    }

    /**
     * Moves the agents of the game to catch the pokemons.
     *
     * @param game
     * @param g
     */
    private static void moveAgents(game_service game, directed_weighted_graph g) {
        String gameMove = game.move();
        List<CL_Agent> AgentsList = Arena.getAgents(gameMove, g);
        arena.setAgents(AgentsList); // update agents
        //ArrayList<OOP_Point3D> rs = new ArrayList<OOP_Point3D>();
        String pokemonsString = game.getPokemons();
        List<CL_Pokemon> pokemonsList = Arena.json2Pokemons(pokemonsString);
        arena.setPokemons(pokemonsList); // update pokemons

        for (int i = 0; i < AgentsList.size(); i++) {
            CL_Agent agent = AgentsList.get(i);
            int id = agent.getID();
            int dest = agent.getNextNode();
            int src = agent.getSrcNode();
            double v = agent.getValue();
            CL_Pokemon p = agent.get_My_Pokemon();
            if (dest == -1) {
                dest = nextNode(g, src, agent);
                game.chooseNextEdge(agent.getID(), dest);
                System.out.println("Agent: " + id + ", val: " + v + " turned to node: " + dest);
                // System.out.println(agent.get_My_Pokemon().getValue());
                // System.out.println(agent.get_My_Pokemon().get_edge().getSrc());
            }
        }
    }

    /**
     * Find the next node to move the agent in the game by the method shrotestPath.
     *
     * @param g
     * @param src
     * @param agent
     * @return
     */

    private static int nextNode(directed_weighted_graph g, int src, CL_Agent agent) {
        dw_graph_algorithms gAlgo = new DWGraph_Algo(g);
        double minPath = Double.MAX_VALUE;
        double dist = -1;
        int ans = -1;
        boolean flag = true;
        // find closet pokemon
        for (CL_Pokemon p : arena.getPokemons()) {
            Arena.updateEdge(p, g); //set edge to this pokemon
            // System.out.println("pokV : " + p + "src" + p.get_edge().getSrc());
            for (int hashA : agentToPokemon.keySet()) {
                flag = true;
                if (agentToPokemon.get(hashA).get_edge().getSrc() == p.get_edge().getSrc() && agent.getID() != hashA) {
                    // System.out.println(agent.getID() + " " + hashA);
                    // System.out.println(agentToPokemon.get(hashA).get_edge().getSrc());
                    // System.out.println(p.get_edge().getSrc());
                    flag = false;
                    break;
                }
            }
            dist = gAlgo.shortestPathDist(src, p.get_edge().getSrc());
            if (flag == true && dist < minPath && dist != -1) {
                //              System.out.println("do");
                minPath = gAlgo.shortestPathDist(src, p.get_edge().getSrc());
                agent.set_My_Pokemon(p);
                agentToPokemon.put(agent.getID(), p);
                //System.out.println("agent "+agent.getID()+" add "+p.get_edge().getSrc());
            }
        }
        if (agent.get_My_Pokemon() != null) {
            if (src == agent.get_My_Pokemon().get_edge().getSrc())
                ans = agent.get_My_Pokemon().get_edge().getDest();
            else {
                List<node_data> shortestPath = gAlgo.shortestPath(src, agent.get_My_Pokemon().get_edge().getSrc());
                if (!shortestPath.isEmpty())
                    ans = shortestPath.get(1).getKey();
            }
        } else {
            ans = agentToPokemon.get(agent.getID()).get_edge().getDest();
            // System.out.println("going to debug");
        }
        // System.out.println(agentToPokemon.toString());
        // System.out.println("pokemon src: " + agent.get_My_Pokemon().get_edge().getSrc());
        return ans;
    }

    /**
     * added the agents to the game acorrding the pokemons value.
     * @param game
     * param g
     * param pL (pokemon list)
     * param aS (agents size)
     */
    public static void putAgents(game_service game, directed_weighted_graph g,
                                 List<CL_Pokemon> pL, int aS) {
        PriorityQueue<CL_Pokemon> maxP = new PriorityQueue<>();
        CL_Pokemon pokemonTemp;
        for (CL_Pokemon p : pL) {
            maxP.add(p);
        }
        int i = 0;
        while (i < aS) {
            if (!maxP.isEmpty()) {
// System.out.println("agent num: " + i + " pokemon- src: " + maxP.peek().get_edge().getSrc() + " value: " + maxP.peek().getValue());
                pokemonTemp = maxP.poll();
                game.addAgent(pokemonTemp.get_edge().getSrc());
                agentToPokemon.put(i, pokemonTemp);
            } else {
                Random rand = new Random();
                int randomNum = rand.nextInt(g.nodeSize());
                game.addAgent(randomNum);
                agentToPokemon.put(i, null);
            }
            i++;
        }
    }

    /**
     * Initializes the game from the JSON file.
     * @param game
     */
    private void init(game_service game) {
        String g = game.getGraph();
        String fs = game.getPokemons();
        directed_weighted_graph graph = buildGraphFromJason(game.getGraph());
        //gg.init(g);

        arena = new Arena();
        arena.setGraph(graph);
        arena.setPokemons(Arena.json2Pokemons(fs));
        _win = new GameFrame(arena);
//      _win.setSize(1000, 700);
//      _win.update(arena);

        _win.show();
        String info = game.toString();
        JSONObject line;
        try {
            line = new JSONObject(info);
            JSONObject ttt = line.getJSONObject("GameServer");
            int numAgents = ttt.getInt("agents");
            System.out.println(info);
            System.out.println(game.getPokemons());
            int src_node = 0;  // arbitrary node, you should start at one of the pokemon
            ArrayList<CL_Pokemon> pokemonsL = Arena.json2Pokemons(game.getPokemons());
            for (int a = 0; a < pokemonsL.size(); a++) {
                Arena.updateEdge(pokemonsL.get(a), graph);
            }
            putAgents(game, graph, pokemonsL, numAgents);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the JSON string.
     * builds a directed weighted graph.
     * @param g
     * @return graph
     */
    public directed_weighted_graph buildGraphFromJason(String g) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(DWGraph_DS.class, new GameJson());
        Gson json = builder.create();
        directed_weighted_graph graph = json.fromJson(g, DWGraph_DS.class);
        return graph;
    }

    /**
     * get the time of the game
     * @return time
     */
    public static long getTime() {
        return time;
    }

    /**
     * set the time of the game
     * @param time
     */

    public void setTime(long time) {
        this.time = time;
    }

    /**
     * get the level of the game
     * @return level_number
     */
    public static int getLevelNumber() {
        return level_number;
    }

    /**
     * set the level of the game
     * @param level_number
     */
    public void setLevelNumber(int level_number) {
        this.level_number = level_number;
    }

    /**
     * get the grade of the level
     * @return grade
     */
    public static double getGrade() {
        return grade;
    }

    /**
     * set the grade of the level
     * @param game
     */
    public void setGrade(game_service game) {
        String info = game.toString();
        JSONObject line;
        try {
            line = new JSONObject(info);
            JSONObject obj = line.getJSONObject("GameServer");
            grade = obj.getDouble("grade");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * get the moves of the level
     * @return moves
     */
    public static int getMoves() {
        return moves;
    }

    /**
     * set the moves of the level
     * @param game
     */
    public void setMoves(game_service game) {
        String info = game.toString();
        JSONObject line;
        try {
            line = new JSONObject(info);
            JSONObject obj = line.getJSONObject("GameServer");
            moves = obj.getInt("moves");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
