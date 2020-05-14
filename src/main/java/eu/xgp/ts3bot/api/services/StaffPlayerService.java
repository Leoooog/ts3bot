package eu.xgp.ts3bot.api.services;

import com.google.gson.JsonObject;
import eu.xgp.ts3bot.api.dao.StaffPlayerDataAccessService;
import eu.xgp.ts3bot.api.objects.StaffPlayer;

import java.util.List;

public class StaffPlayerService {
    private StaffPlayerDataAccessService staffPlayerDataAccessService;

    public StaffPlayerService() {
        this.staffPlayerDataAccessService = new StaffPlayerDataAccessService();
    }

    public List<StaffPlayer> getAllStaffPlayer() {
        return staffPlayerDataAccessService.getAllStaffPlayer();
    }

    public StaffPlayer getStaffPlayer(String uuid) {
        return uuid.endsWith("=") ? staffPlayerDataAccessService.getStaffPlayerFromTsUUID(uuid).orElse(null) : staffPlayerDataAccessService.getStaffPlayerFromMcUUID(uuid).orElse(null);
    }

    public void addStaffPlayer(StaffPlayer staffPlayer) {
        staffPlayerDataAccessService.addStaffPlayer(staffPlayer);
    }

    public void updateValue(String uuid, String toUpdate, JsonObject json) {
        staffPlayerDataAccessService.updateValue(uuid, toUpdate, json);
    }

    public void deleteStaffPlayer(String uuid) {
        staffPlayerDataAccessService.deleteStaffPlayer(uuid);
    }
}
