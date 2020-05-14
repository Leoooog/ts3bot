package eu.xgp.ts3bot.api.services;

import com.google.gson.JsonObject;
import eu.xgp.ts3bot.api.dao.PlayerDataAccessService;
import eu.xgp.ts3bot.api.objects.PlayerInfo;

import java.util.List;

public class PlayerService {
    private PlayerDataAccessService playerDataAccessService;

    public PlayerService(String server) {
        this.playerDataAccessService = new PlayerDataAccessService(server);
    }

    public List<PlayerInfo> getAllPlayerInfo() {
        return playerDataAccessService.getAllPlayerInfo();
    }

    public PlayerInfo getPlayerInfo(String uuid) {
        return playerDataAccessService.getPlayerInfo(uuid).orElse(null);
    }

    public void addPlayerInfo(PlayerInfo playerInfo) {
        playerDataAccessService.addPlayerInfo(playerInfo);
    }

    public void updateValue(String uuid, String toUpdate, JsonObject json) {
        playerDataAccessService.updateValue(uuid, toUpdate, json);
    }

    public void deletePlayerInfo(String uuid) {
        playerDataAccessService.deletePlayerInfo(uuid);
    }
}
