package eu.xgp.ts3bot.api;

import com.google.gson.JsonObject;
import eu.xgp.ts3bot.api.objects.PlayerInfo;
import eu.xgp.ts3bot.api.objects.VipPlayer;
import eu.xgp.ts3bot.api.services.PlayerService;
import eu.xgp.ts3bot.api.services.VipService;

import java.util.List;

public class PlayerApi {
    private PlayerService playerService;
    private VipService vipService;

    public PlayerApi(String server) {
        playerService = new PlayerService(server);
        vipService = new VipService(server);
    }

    public PlayerApi() {
        playerService = new PlayerService("survival");
        vipService = new VipService("survival");
    }

    public List<PlayerInfo> getAllPlayerInfo() {
        return playerService.getAllPlayerInfo();
    }

    public PlayerInfo getPlayerInfo(String uuid) {
        return playerService.getPlayerInfo(uuid);
    }

    public void deletePlayerInfo(String uuid) {
        playerService.deletePlayerInfo(uuid);
    }

    public void addPlayerInfo(PlayerInfo playerInfo) {
        playerService.addPlayerInfo(playerInfo);
    }

    public void updatePlayerInfo(String uuid, String toUpdate, JsonObject value) {
        playerService.updateValue(uuid, toUpdate, value);
    }

    public List<VipPlayer> getAllVipPlayers() {
        return vipService.getAllVipPlayers();
    }

    public void deleteVipPlayer(String uuid) {
        vipService.deleteVipPlayer(uuid);
    }

    public void addVipPlayer(VipPlayer vipPlayer) {
        vipService.addVipPlayer(vipPlayer);
    }

    public VipPlayer getVipPlayer(String uuid){
        return vipService.getVipPlayer(uuid);
    }

    public void updateVipPlayer(String uuid, String toUpdate, JsonObject value) {
        vipService.updateVipPlayer(uuid, toUpdate, value);
    }

}
