package eu.xgp.ts3bot.api.objects;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import eu.xgp.ts3bot.api.StaffApi;

public class StaffTime {
    private long spent;
    private String uuid;
    private String name;
    private StaffApi staffApi;

    public StaffTime(String uuid, String name, long spent) {
        this.spent = spent;
        this.uuid = uuid;
        this.name = name;
        staffApi = new StaffApi(uuid.endsWith("=") ? "ts" : "mc");
    }

    public long getSpent() {
        return spent;
    }

    public void setSpent(long spent) {
        this.spent = spent;
         staffApi.updateStaffTime(uuid, "spent", new JsonParser().parse("{\"value\": " + spent + "}").getAsJsonObject());
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
         staffApi.updateStaffTime(uuid, "uuid", new JsonParser().parse("{\"value\": \"" + uuid + "\"}").getAsJsonObject());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
         staffApi.updateStaffTime(uuid, "name", new JsonParser().parse("{\"value\": \"" + name + "\"}").getAsJsonObject());
    }

    @Override
    public String toString() {
        return "StaffTime{" +
                "spent=" + spent +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Object getJsonObject() {
        JsonObject object = new JsonObject();
        object.addProperty("uuid", uuid);
        object.addProperty("name", name);
        object.addProperty("spent", spent);
        return object;
    }
}
