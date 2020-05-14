package eu.xgp.ts3bot.api.objects;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import eu.xgp.ts3bot.api.StaffApi;

public class StaffPlayer {
    private String name;
    private String role;
    private String uuid;
    private String tsUUID;
    private String platform;
    private StaffApi staffApi;

    public StaffPlayer(String uuid, String name, String tsUUID, String role) {
        this.role = role;
        this.uuid = uuid;
        this.tsUUID = tsUUID;
        this.name = name;
        staffApi = new StaffApi("survival");
    }

    public String getPlatform() {
        if (platform == null)
            platform = "mc";
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    private String getPlatformUuid() {
        return getPlatform() == "mc" ? uuid : tsUUID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
         staffApi.updateStaffPlayer(getPlatformUuid(), "role", new JsonParser().parse("{\"value\": \"" + role + "\"}").getAsJsonObject());
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
         staffApi.updateStaffPlayer(getPlatformUuid(), "mc_uuid", new JsonParser().parse("{\"value\": \"" + uuid + "\"}").getAsJsonObject());
    }

    public String getTsUUID() {
        return tsUUID;
    }

    public void setTsUUID(String tsUUID) {
        this.tsUUID = tsUUID;
         staffApi.updateStaffPlayer(getPlatformUuid(), "ts_uuid", new JsonParser().parse("{\"value\": \"" + name + "\"}").getAsJsonObject());
    }

    @Override
    public String toString() {
        return "StaffPlayer{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", uuid='" + uuid + '\'' +
                ", tsUUID='" + tsUUID + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
         staffApi.updateStaffPlayer(getPlatformUuid(), "name", new JsonParser().parse("{\"value\": \"" + name + "\"}").getAsJsonObject());
    }

    public JsonObject getJsonObject() {
        JsonObject object = new JsonObject();
        object.addProperty("mc_uuid", uuid);
        object.addProperty("name", name);
        object.addProperty("ts_uuid", tsUUID);
        object.addProperty("role", role);
        return object;
    }

}