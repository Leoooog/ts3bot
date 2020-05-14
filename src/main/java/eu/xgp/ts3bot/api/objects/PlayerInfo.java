package eu.xgp.ts3bot.api.objects;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import eu.xgp.ts3bot.api.PlayerApi;

public class PlayerInfo {
    private String uuid;
    private String name;
    private int kills;
    private int deaths;
    private int coins;
    private int tokens;
    private int money;
    private String ranking;
    private long firstLogin;
    private long lastLogin;
    private String ip;

    private PlayerApi playerApi;

    public PlayerInfo(String uuid, String name, int kills, int deaths, int coins, int tokens, int money, String ranking, long firstLogin, long lastLogin, String ip) {
        this.uuid = uuid;
        this.name = name;
        this.kills = kills;
        this.deaths = deaths;
        this.coins = coins;
        this.tokens = tokens;
        this.money = money;
        this.ranking = ranking;
        this.firstLogin = firstLogin;
        this.lastLogin = lastLogin;
        this.ip = ip;
        playerApi = new PlayerApi("survival");
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
         playerApi.updatePlayerInfo(uuid, "uuid", new JsonParser().parse("{\"value\": \"" + uuid + "\"}").getAsJsonObject());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
         playerApi.updatePlayerInfo(uuid, "name", new JsonParser().parse("{\"value\": \"" + name + "\"}").getAsJsonObject());
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
         playerApi.updatePlayerInfo(uuid, "kills", new JsonParser().parse("{\"value\": " + kills + "}").getAsJsonObject());
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
         playerApi.updatePlayerInfo(uuid, "deaths", new JsonParser().parse("{\"value\": " + deaths + "}").getAsJsonObject());
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
         playerApi.updatePlayerInfo(uuid, "coins", new JsonParser().parse("{\"value\": " + coins + "}").getAsJsonObject());
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
         playerApi.updatePlayerInfo(uuid, "tokens", new JsonParser().parse("{\"value\": " + tokens + "}").getAsJsonObject());
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
         playerApi.updatePlayerInfo(uuid, "money", new JsonParser().parse("{\"value\": " + money + "}").getAsJsonObject());
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
         playerApi.updatePlayerInfo(uuid, "ranking", new JsonParser().parse("{\"value\": \"" + ranking + "\"}").getAsJsonObject());
    }

    public long getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(long firstLogin) {
        this.firstLogin = firstLogin;
         playerApi.updatePlayerInfo(uuid, "first_login", new JsonParser().parse("{\"value\": " + firstLogin + "}").getAsJsonObject());
    }

    public long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(long lastLogin) {
        this.lastLogin = lastLogin;
         playerApi.updatePlayerInfo(uuid, "last_login", new JsonParser().parse("{\"value\": " + lastLogin + "}").getAsJsonObject());
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
         playerApi.updatePlayerInfo(uuid, "ip", new JsonParser().parse("{\"value\": \"" + ip + "\"}").getAsJsonObject());
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", kills=" + kills +
                ", deaths=" + deaths +
                ", coins=" + coins +
                ", tokens=" + tokens +
                ", money=" + money +
                ", ranking='" + ranking + '\'' +
                ", firstLogin=" + firstLogin +
                ", lastLogin=" + lastLogin +
                ", ip='" + ip + '\'';
    }

    public JsonObject getJsonObject() {
        JsonObject object = new JsonObject();
        object.addProperty("uuid", uuid);
        object.addProperty("name", name);
        object.addProperty("kills", kills);
        object.addProperty("deaths", deaths);
        object.addProperty("coins", coins);
        object.addProperty("tokens", tokens);
        object.addProperty("money", money);
        object.addProperty("uuid", uuid);
        object.addProperty("ranking", ranking);
        object.addProperty("firstLogin", firstLogin);
        object.addProperty("lastLogin", lastLogin);
        object.addProperty("ip", ip);
        return object;
    }


}
