package gameClient;

import api.*;
import gameClient.util.Point3D;
import gameClient.util.Range;
import gameClient.util.Range2D;
import gameClient.util.Range2Range;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

/**
 * This class represents a multi Agents Arena which move on a graph -
 * grabs Pokemons and avoid the Zombies.
 *
 */
public class Arena {
    public static final double EPS1 = 0.000001, EPS2 = EPS1 * EPS1, EPS = EPS2;
    private directed_weighted_graph _gg;
    private List<CL_Agent> _agents;
    private List<CL_Pokemon> _pokemons;
    private List<String> _info;
    private static Point3D MIN = new Point3D(0, 100, 0);
    private static Point3D MAX = new Point3D(0, 100, 0);

    public Arena() {
        _info = new ArrayList<String>();
    }

    private Arena(directed_weighted_graph g, List<CL_Agent> r, List<CL_Pokemon> p) {
        _gg = g;
        this.setAgents(r);
        this.setPokemons(p);
    }

    /**
     * get list of pokemos and update this._pokemons list.
     * @param f
     */
    public void setPokemons(List<CL_Pokemon> f) {
        this._pokemons = f;
    }

    /**
     *  gets list of agent and update this._agents list.
     * @param f
     */
    public void setAgents(List<CL_Agent> f) {
        this._agents = f;
    }

    /**
     *  gets a graph and update this._gg graph.
     * @param g
     */
    public void setGraph(directed_weighted_graph g) {
        this._gg = g;
    }//init();}

    private void init() {
        MIN = null;
        MAX = null;
        double x0 = 0, x1 = 0, y0 = 0, y1 = 0;
        Iterator<node_data> iter = _gg.getV().iterator();
        while (iter.hasNext()) {
            geo_location c = iter.next().getLocation();
            if (MIN == null) {
                x0 = c.x();
                y0 = c.y();
                x1 = x0;
                y1 = y0;
                MIN = new Point3D(x0, y0);
            }
            if (c.x() < x0) {
                x0 = c.x();
            }
            if (c.y() < y0) {
                y0 = c.y();
            }
            if (c.x() > x1) {
                x1 = c.x();
            }
            if (c.y() > y1) {
                y1 = c.y();
            }
        }
        double dx = x1 - x0, dy = y1 - y0;
        MIN = new Point3D(x0 - dx / 10, y0 - dy / 10);
        MAX = new Point3D(x1 + dx / 10, y1 + dy / 10);
    }

    /**
     * return a list of the agent that in the arena
     * @return  _agents
     */
    public List<CL_Agent> getAgents() {
        return _agents;
    }

    /**
     * return a list of the pokemons that in the arena
     * @return _pokemons
     */
    public List<CL_Pokemon> getPokemons() {
        return _pokemons;
    }

    /**
     * return a graph that in the arena
     * @return _gg
     */
    public directed_weighted_graph getGraph() {
        return _gg;
    }

    public List<String> get_info() {
        return _info;
    }

    public void set_info(List<String> _info) {
        this._info = _info;
    }

    ////////////////////////////////////////////////////

    /**
     *  function that gets graph and agent String and creates list
     * of the agents.
     * @param aa
     * @param gg
     * @return ans
     */
    public static List<CL_Agent> getAgents(String aa, directed_weighted_graph gg) {
        ArrayList<CL_Agent> ans = new ArrayList<CL_Agent>();
        try {
            JSONObject ttt = new JSONObject(aa);
            JSONArray ags = ttt.getJSONArray("Agents");
            for (int i = 0; i < ags.length(); i++) {
                CL_Agent c = new CL_Agent(gg, 0);
                c.update(ags.get(i).toString());
                ans.add(c);
            }
            //= getJSONArray("Agents");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ans;
    }

    /**
     * function that get string of pokemons and creates
     * list of the pokemons.
     * @param fs
     * @return ans
     */
    public static ArrayList<CL_Pokemon> json2Pokemons(String fs) {
        ArrayList<CL_Pokemon> ans = new ArrayList<CL_Pokemon>();
        try {
            JSONObject ttt = new JSONObject(fs);
            JSONArray ags = ttt.getJSONArray("Pokemons");
            for (int i = 0; i < ags.length(); i++) {
                JSONObject pp = ags.getJSONObject(i);
                JSONObject pk = pp.getJSONObject("Pokemon");
                int t = pk.getInt("type");
                double v = pk.getDouble("value");
                //double s = 0;//pk.getDouble("speed");
                String p = pk.getString("pos");
                CL_Pokemon f = new CL_Pokemon(new Point3D(p), t, v, 0, null);
                ans.add(f);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ans;
    }

    /**
     * function that get pokemon and graph, and update the pokemon edge according to geo location.
     * @param pok
     * @param g
     */
    public static void updateEdge(CL_Pokemon pok, directed_weighted_graph g) {
        //	oop_edge_data ans = null;
        Iterator<node_data> iter1 = g.getV().iterator();
        while (iter1.hasNext()) {
            node_data v = iter1.next();
            Iterator<edge_data> iter2 = g.getE(v.getKey()).iterator();
            while (iter2.hasNext()) {
                edge_data e = iter2.next();
                boolean f = isOnEdge(pok.getLocation(), e, pok.getType(), g);
                if (f) {
                    pok.set_edge(e);
                }
            }
        }
    }

    /**
     *  function that get geo location p, src, dest.
     *  if p is on the edge between src and dest- return true, else- return false
     * @param p
     * @param src
     * @param dest
     * @return true/false
     */
    private static boolean isOnEdge(geo_location p, geo_location src, geo_location dest) {
        boolean ans = false;
        double dist = src.distance(dest);
        double d1 = src.distance(p) + p.distance(dest);
        if (dist > d1 - EPS2) {
            ans = true;
        }
        return ans;
    }

    /**
     *  function that get geo location p,  2 keys of nodes, graph g.
     * if p is on the edge between s and d- return true, else- return false.
     * @param p
     * @param s
     * @param d
     * @param g
     * @return true/false
     */
    private static boolean isOnEdge(geo_location p, int s, int d, directed_weighted_graph g) {
        geo_location src = g.getNode(s).getLocation();
        geo_location dest = g.getNode(d).getLocation();
        return isOnEdge(p, src, dest);
    }

    /**
     * function that get geo location p, edge e, type, graph g.
     * check if the type of the pokemon is -1 dest is smaller then src.
     * if type is 1 dest is bigger then src.
     * if type is suitable, this function check if p is on the edge between src and dest- return true,
     * else- return false.
     * @param p
     * @param e
     * @param type
     * @param g
     * @return true/false
     */
    private static boolean isOnEdge(geo_location p, edge_data e, int type, directed_weighted_graph g) {
        int src = g.getNode(e.getSrc()).getKey();
        int dest = g.getNode(e.getDest()).getKey();
        if (type < 0 && dest > src) {
            return false;
        }
        if (type > 0 && src > dest) {
            return false;
        }
        return isOnEdge(p, src, dest, g);
    }

    private static Range2D GraphRange(directed_weighted_graph g) {
        Iterator<node_data> itr = g.getV().iterator();
        double x0 = 0, x1 = 0, y0 = 0, y1 = 0;
        boolean first = true;
        while (itr.hasNext()) {
            geo_location p = itr.next().getLocation();
            if (first) {
                x0 = p.x();
                x1 = x0;
                y0 = p.y();
                y1 = y0;
                first = false;
            } else {
                if (p.x() < x0) {
                    x0 = p.x();
                }
                if (p.x() > x1) {
                    x1 = p.x();
                }
                if (p.y() < y0) {
                    y0 = p.y();
                }
                if (p.y() > y1) {
                    y1 = p.y();
                }
            }
        }
        Range xr = new Range(x0, x1);
        Range yr = new Range(y0, y1);
        return new Range2D(xr, yr);
    }

    public static Range2Range w2f(directed_weighted_graph g, Range2D frame) {
        Range2D world = GraphRange(g);
        Range2Range ans = new Range2Range(world, frame);
        return ans;
    }
}
