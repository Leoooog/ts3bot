package eu.xgp.ts3bot.api.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import eu.xgp.ts3bot.api.objects.StaffPlayer;
import eu.xgp.ts3bot.api.request.HttpConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StaffPlayerDataAccessService {

    public List<StaffPlayer> getAllStaffPlayer() {

        HttpConnection conn = new HttpConnection("staff", "GET", "");
        JsonArray jsonArray = conn.getJsonArray();
        List<StaffPlayer> staffPlayerList = new ArrayList<>();
        for (JsonElement e : jsonArray) {
            JsonObject obj = e.getAsJsonObject();
            String uuid = obj.get("mc_uuid").getAsString();
            String name = obj.get("name").getAsString();
            String tsUUID = obj.get("ts_uuid").getAsString();
            String role = obj.get("role").getAsString();

            staffPlayerList.add(new StaffPlayer(uuid, name, tsUUID, role));
        }
        return staffPlayerList;
    }

    public Optional<StaffPlayer> getStaffPlayerFromMcUUID(String uuid) {
        return getAllStaffPlayer().stream().filter(pi -> pi.getUuid().equalsIgnoreCase(uuid)).findFirst();
    }

    public Optional<StaffPlayer> getStaffPlayerFromTsUUID(String uuid) {
        return getAllStaffPlayer().stream().filter(pi -> pi.getTsUUID().equalsIgnoreCase(uuid)).findFirst();
    }

    public void updateValue(String uuid, String toUpdate, JsonObject json) {
        HttpConnection conn = new HttpConnection("staff/" + uuid + "/" + toUpdate, "PUT", json.toString());
        conn.runRequest();
    }

    public void addStaffPlayer(StaffPlayer staffPlayer) {
        HttpConnection conn = new HttpConnection("staff/", "POST", staffPlayer.getJsonObject().toString());
        conn.runRequest();
    }

    public void deleteStaffPlayer(String uuid) {
        HttpConnection conn = new HttpConnection("staff/" + uuid, "DELETE", "");
        conn.runRequest();
    }
}
