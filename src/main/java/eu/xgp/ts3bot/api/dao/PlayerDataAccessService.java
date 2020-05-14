package eu.xgp.ts3bot.api.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import eu.xgp.ts3bot.api.objects.PlayerInfo;
import eu.xgp.ts3bot.api.request.HttpConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerDataAccessService {
    private final String server;

    public PlayerDataAccessService(String server) {
        this.server = server;
    }

    public List<PlayerInfo> getAllPlayerInfo() {
        HttpConnection conn = new HttpConnection(server + "/player", "GET", "");
        JsonArray jsonArray = conn.getJsonArray();
        List<PlayerInfo> playerInfoList = new ArrayList<>();
        for (JsonElement e : jsonArray) {
            JsonObject obj = e.getAsJsonObject();
            String uuid = obj.get("uuid").getAsString();
            String name = obj.get("name").getAsString();
            int kills = obj.get("kills").getAsInt();
            int deaths = obj.get("deaths").getAsInt();
            int coins = obj.get("coins").getAsInt();
            int tokens = obj.get("tokens").getAsInt();
            int money = obj.get("money").getAsInt();
            String ranking = obj.get("ranking").getAsString();
            long firstLogin = obj.get("firstLogin").getAsLong();
            long lastLogin = obj.get("lastLogin").getAsLong();
            String ip = obj.get("ip").getAsString();
            playerInfoList.add(new PlayerInfo(uuid, name, kills, deaths, coins, tokens, money, ranking, firstLogin, lastLogin, ip));
        }
        return playerInfoList;
    }

    public Optional<PlayerInfo> getPlayerInfo(String uuid) {
        return getAllPlayerInfo().stream().filter(pi -> pi.getUuid().equalsIgnoreCase(uuid)).findFirst();
    }

    public void updateValue(String uuid, String toUpdate, JsonObject json) {
        HttpConnection conn = new HttpConnection(server + "/player/" + uuid + "/" + toUpdate, "PUT", json.toString());
        conn.runRequest();
    }

    public void addPlayerInfo(PlayerInfo playerInfo) {
        HttpConnection conn = new HttpConnection(server + "/player", "POST", playerInfo.getJsonObject().toString());
        conn.runRequest();
    }

    public void deletePlayerInfo(String uuid) {
        HttpConnection conn = new HttpConnection(server + "/player/" + uuid, "DELETE", "");
        conn.runRequest();
    }
}
