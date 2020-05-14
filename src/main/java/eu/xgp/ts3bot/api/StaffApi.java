package eu.xgp.ts3bot.api;

import com.google.gson.JsonObject;
import eu.xgp.ts3bot.api.objects.StaffPlayer;
import eu.xgp.ts3bot.api.objects.StaffStorage;
import eu.xgp.ts3bot.api.objects.StaffTime;
import eu.xgp.ts3bot.api.services.StaffPlayerService;
import eu.xgp.ts3bot.api.services.StaffStorageService;
import eu.xgp.ts3bot.api.services.StaffTimeService;

import java.util.List;

public class StaffApi {
    private StaffTimeService staffTimeService;
    private StaffStorageService staffStorageService;
    private StaffPlayerService staffPlayerService;

    public StaffApi(String platform) {
        staffTimeService = new StaffTimeService(platform);
        staffStorageService = new StaffStorageService(platform);
        staffPlayerService = new StaffPlayerService();
    }

    public StaffApi() {
        staffPlayerService = new StaffPlayerService();
    }

    public List<StaffPlayer> getAllStaffPlayer() {
        return staffPlayerService.getAllStaffPlayer();
    }

    public StaffPlayer getStaffPlayer(String uuid) {
        return staffPlayerService.getStaffPlayer(uuid);
    }

    public void addStaffPlayer(StaffPlayer staffPlayer) {
        staffPlayerService.addStaffPlayer(staffPlayer);
    }

    public void updateStaffPlayer(String uuid, String toUpdate, JsonObject json) {
        staffPlayerService.updateValue(uuid, toUpdate, json);
    }

    public void deleteStaffPlayer(String uuid) {
        staffPlayerService.deleteStaffPlayer(uuid);
    }

    public List<StaffStorage> getAllStaffStorage() {
        return staffStorageService.getAllStaffStorage();
    }

    public StaffStorage getStaffStorage(String date, String uuid) {
        return staffStorageService.getStaffStorage(date, uuid);
    }

    public void addStaffStorage(StaffStorage staffStorage) {
        staffStorageService.addStaffStorage(staffStorage);
    }

    public void updateStaffStorage(String date, String uuid, String toUpdate, JsonObject json) {
        staffStorageService.updateValue(date, uuid, toUpdate, json);
    }

    public void deleteStaffStorage(String date, String uuid) {
        staffStorageService.deleteStaffStorage(date, uuid);
    }

    public List<StaffTime> getAllStaffTime() {
        return staffTimeService.getAllStaffTime();
    }

    public StaffTime getStaffTime(String uuid) {
        return staffTimeService.getStaffTime(uuid);
    }

    public void addStaffTime(StaffTime staffTime) {
        staffTimeService.addStaffTime(staffTime);
    }

    public void updateStaffTime(String uuid, String toUpdate, JsonObject json) {
        staffTimeService.updateValue(uuid, toUpdate, json);
    }

    public void deleteStaffTime(String uuid) {
        staffTimeService.deleteStaffTime(uuid);
    }
}
