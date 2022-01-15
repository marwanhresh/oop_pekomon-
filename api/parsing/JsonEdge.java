package parsing;

public class JsonEdge {
    public int src;
    public double w;
    public int dest;

    public JsonEdge() {

    }

    public JsonEdge(int src, double w, int dest) {
        this.src = src;
        this.w = w;
        this.dest = dest;
    }
}