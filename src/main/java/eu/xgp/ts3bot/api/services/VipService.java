package eu.xgp.ts3bot.api.services;

import com.google.gson.JsonObject;
import eu.xgp.ts3bot.api.dao.VipDataAccessService;
import eu.xgp.ts3bot.api.objects.VipPlayer;

import java.util.List;

public class VipService {
    private VipDataAccessService vipDataAccessService;

    public VipService(String server) {
        this.vipDataAccessService = new VipDataAccessService(server);
    }

    public List<VipPlayer> getAllVipPlayers() {
        return vipDataAccessService.getAllVipPlayers();
    }

    public void addVipPlayer(VipPlayer p) {
        vipDataAccessService.addVipPlayer(p);
    }

    public void updateVipPlayer(String uuid, String toUpdate, JsonObject json) {
        vipDataAccessService.updateValue(uuid, toUpdate, json);
    }

    public VipPlayer getVipPlayer(String uuid) {
        return uuid.endsWith("=") ? getVipPlayerFromTsUUID(uuid) : getVipPlayerFromMcUUID(uuid);
    }

    private VipPlayer getVipPlayerFromTsUUID(String uuid) {
        return vipDataAccessService.getVipPlayerFromTsUuid(uuid).orElse(null);
    }

    private VipPlayer getVipPlayerFromMcUUID(String uuid) {
        return vipDataAccessService.getVipPlayerFromMcUuid(uuid).orElse(null);
    }

    public void deleteVipPlayer(String uuid) {
        vipDataAccessService.deleteVipPlayer(uuid);
    }
}
