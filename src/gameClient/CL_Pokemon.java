package gameClient;

import api.DWGraph_Algo;
import api.edge_data;
import gameClient.util.Point3D;
import org.json.JSONObject;
/**
 * a pokemon in the game
 */
public class CL_Pokemon implements Comparable<CL_Pokemon> {
    private edge_data _edge;
    private double _value;
    private int _type;
    private Point3D _pos;
    private double min_dist;
    private int min_ro;


    public CL_Pokemon(Point3D p, int t, double v, double s, edge_data e) {
        _type = t;
        //	_speed = s;
        _value = v;
        set_edge(e);
        _pos = p;
        min_dist = -1;
        min_ro = -1;
    }

    public static CL_Pokemon init_from_json(String json) {
        CL_Pokemon ans = null;
        try {
            JSONObject p = new JSONObject(json);
            int id = p.getInt("id");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ans;
    }

    public String toString() {
        return "F:{v=" + _value + ", t=" + _type + "}";
    }

    /**
     * function that returns the edge that the Pokemon is on it.
     * @return _edge
     */
    public edge_data get_edge() {
        return _edge;
    }

    /**
     * function that gets an edge and update this._edge.
     * @param _edge
     */
    public void set_edge(edge_data _edge) {
        this._edge = _edge;
    }

    /**
     * return the pokemon location.
     * @return _pos
     */
    public Point3D getLocation() {
        return _pos;
    }

    /**
     * return the pokemon type
     * @return _type
     */
    public int getType() {
        return _type;
    }

//	public double getSpeed() {return _speed;}

    /**
     * return the pokemon value.
     * @return
     */
    public double getValue() {
        return _value;
    }

    public double getMin_dist() {
        return min_dist;
    }

    public void setMin_dist(double mid_dist) {
        this.min_dist = mid_dist;
    }

    public int getMin_ro() {
        return min_ro;
    }

    public void setMin_ro(int min_ro) {
        this.min_ro = min_ro;
    }

    /**
     *  sort Pokemons by their value from highest to lowest.
     * @param x
     * @return
     */
    public int compareTo(CL_Pokemon x) {
        int ans = 0;
        if (this.getValue() < x.getValue())
            ans = 1;
        else if (this.getValue() > x.getValue())
            ans = -1;
        return ans;
    }
}
