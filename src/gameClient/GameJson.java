package gameClient;

import api.*;
import com.google.gson.*;
import gameClient.util.Point3D;

import java.lang.reflect.Type;

public class GameJson implements JsonDeserializer<directed_weighted_graph> {

    @Override
    public directed_weighted_graph deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext
            jsonDeserializationContext) throws JsonParseException {
        JsonObject graphJson = jsonElement.getAsJsonObject();
        directed_weighted_graph g = new DWGraph_DS();

        JsonArray nodesJson = graphJson.get("Nodes").getAsJsonArray();
        JsonArray edgesJson = graphJson.get("Edges").getAsJsonArray();

        for (JsonElement node : nodesJson) {

            int key = node.getAsJsonObject().get("id").getAsInt();

            String pos = node.getAsJsonObject().get("pos").getAsString();

            geo_location h = new Point3D(pos);
            node_data n = new Node_Data_DS(key,  h);
            g.addNode(n);
        }

        for (JsonElement e: edgesJson) {
                int src = e.getAsJsonObject().get("src").getAsInt();
                int dest = e.getAsJsonObject().get("dest").getAsInt();
                double w = e.getAsJsonObject().get("w").getAsDouble();
                g.connect(src, dest, w);
            }

        return g;
    }
}

