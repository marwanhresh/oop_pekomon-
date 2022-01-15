package api;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Map.Entry;

public class GraphJson implements JsonDeserializer<directed_weighted_graph> {
    /**
     * we add this class in order to save and load the graph in a json format.
     * @param jsonElement
     * @param type
     * @param jsonDeserializationContext
     * @return g
     * @throws JsonParseException
     */
    @Override
    public directed_weighted_graph deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext
            jsonDeserializationContext) throws JsonParseException {

        JsonObject graphJson = jsonElement.getAsJsonObject();
        directed_weighted_graph g = new DWGraph_DS();

        JsonObject nodesJson = graphJson.get("graphMap").getAsJsonObject();
        JsonObject edgesJson = graphJson.get("edgesMap").getAsJsonObject();

        for (Entry<String, JsonElement> set : nodesJson.entrySet()) {
            JsonElement node = set.getValue();
            int key = node.getAsJsonObject().get("key").getAsInt();
            String info = node.getAsJsonObject().get("info").getAsString();
            int tag = node.getAsJsonObject().get("tag").getAsInt();
            double node_Weight = node.getAsJsonObject().get("nodeWeight").getAsDouble();
            JsonObject loc = node.getAsJsonObject().get("location").getAsJsonObject();
            double x = loc.get("x").getAsDouble();
            double y = loc.get("y").getAsDouble();
            double z = loc.get("z").getAsDouble();
            geo_location h = new Node_Data_DS.geoLocation(x, y, z);
            node_data n = new Node_Data_DS(key, info, h, node_Weight, tag);
            g.addNode(n);
        }

        for (Entry<String, JsonElement> set : edgesJson.entrySet()) {
            JsonObject neighbor = set.getValue().getAsJsonObject();
            JsonObject nai=neighbor.get("neighbors").getAsJsonObject();
            for (Entry<String, JsonElement> s : nai.entrySet()) {
                JsonElement e = s.getValue();
                int src = e.getAsJsonObject().get("src").getAsInt();
                int dest = e.getAsJsonObject().get("dest").getAsInt();
                double w = e.getAsJsonObject().get("edgeWeight").getAsDouble();
                g.connect(src, dest, w);
            }
        }
        return g;
    }
}

