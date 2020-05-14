package eu.xgp.ts3bot.api.objects;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import eu.xgp.ts3bot.api.PlayerApi;

public class VipPlayer {
    private String mcUuid;
    private String tsUuid;
    private String vip;
    private final PlayerApi playerApi;

    public VipPlayer(String mcUuid, String tsUuid, String vip) {
        this.mcUuid = mcUuid;
        this.tsUuid = tsUuid;
        this.vip = vip;
        playerApi = new PlayerApi("survival");
    }

    public String getMcUuid() {
        return mcUuid;
    }

    public void setMcUuid(String mcUuid) {
        this.mcUuid = mcUuid;
        playerApi.updateVipPlayer(mcUuid, "mc_uuid", new JsonParser().parse("{\"value\": \"" + mcUuid + "\"}").getAsJsonObject());
    }

    public String getTsUuid() {
        return tsUuid;
    }

    public void setTsUuid(String tsUuid) {
        this.tsUuid = tsUuid;
        playerApi.updateVipPlayer(mcUuid, "ts_uuid", new JsonParser().parse("{\"value\": \"" + tsUuid + "\"}").getAsJsonObject());
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
        playerApi.updateVipPlayer(mcUuid, "vip", new JsonParser().parse("{\"value\": \"" + vip + "\"}").getAsJsonObject());
    }

    public JsonObject getJsonObject() {
        JsonObject object = new JsonObject();
        object.addProperty("mc_uuid", mcUuid);
        object.addProperty("ts_uuid", tsUuid);
        object.addProperty("vip", vip);
        return object;
    }

    @Override
    public String toString() {
        return "VipPlayer{" +
                "mcUuid='" + mcUuid + '\'' +
                ", tsUuid='" + tsUuid + '\'' +
                ", vip='" + vip + '\'' +
                '}';
    }
}
