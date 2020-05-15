package eu.xgp.ts3bot.api.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import eu.xgp.ts3bot.api.objects.VipPlayer;
import eu.xgp.ts3bot.api.request.HttpConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VipDataAccessService {
    private final String server;

    public VipDataAccessService(String server) {
        this.server = server;
    }

    public List<VipPlayer> getAllVipPlayers() {
        HttpConnection conn = new HttpConnection(server + "/vip", "GET", "");
        JsonArray jsonArray = conn.getJsonArray();
        List<VipPlayer> playerInfoList = new ArrayList<>();
        for (JsonElement e : jsonArray) {
            JsonObject obj = e.getAsJsonObject();
            String mcUuid = obj.get("mc_uuid").getAsString();
            String tsUuid = obj.get("ts_uuid").getAsString();
            String vip = obj.get("vip").getAsString();
            playerInfoList.add(new VipPlayer(mcUuid, tsUuid, vip));
        }
        return playerInfoList;
    }

    public Optional<VipPlayer> getVipPlayerFromTsUuid(String uuid) {
        return getAllVipPlayers().stream().filter(v -> v.getTsUuid().equalsIgnoreCase(uuid)).findFirst();
    }

    public Optional<VipPlayer> getVipPlayerFromMcUuid(String uuid) {
        return getAllVipPlayers().stream().filter(v -> v.getMcUuid().equalsIgnoreCase(uuid)).findFirst();
    }

    public void updateValue(String uuid, String toUpdate, JsonObject json) {
        HttpConnection conn = new HttpConnection(server + "/vip/" + uuid + "/" + toUpdate, "PUT", json.toString());
        conn.runRequest();
    }

    public void addVipPlayer(VipPlayer player) {
        HttpConnection conn = new HttpConnection(server + "/vip", "POST", player.getJsonObject().toString());
        conn.runRequest();
    }

    public void deleteVipPlayer(String uuid) {
        HttpConnection conn = new HttpConnection(server + "/vip/" + uuid, "DELETE", "");
        conn.runRequest();
    }
}
