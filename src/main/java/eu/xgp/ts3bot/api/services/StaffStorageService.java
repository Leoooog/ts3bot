package eu.xgp.ts3bot.api.services;

import com.google.gson.JsonObject;
import eu.xgp.ts3bot.api.dao.StaffStorageDataAccessService;
import eu.xgp.ts3bot.api.objects.StaffStorage;

import java.util.List;

public class StaffStorageService {
    private StaffStorageDataAccessService staffStorageDataAccessService;

    public StaffStorageService(String platform) {
        this.staffStorageDataAccessService = new StaffStorageDataAccessService(platform);
    }

    public List<StaffStorage> getAllStaffStorage() {
        return staffStorageDataAccessService.getAllStaffStorage();
    }

    public StaffStorage getStaffStorage(String date, String uuid) {
        return staffStorageDataAccessService.getStaffStorage(date, uuid).orElse(null);
    }

    public void addStaffStorage(StaffStorage staffStorage) {
        staffStorageDataAccessService.addStaffStorage(staffStorage);
    }

    public void updateValue(String date, String uuid, String toUpdate, JsonObject json) {
        staffStorageDataAccessService.updateValue(date, uuid, toUpdate, json);
    }

    public void deleteStaffStorage(String date, String uuid) {
        staffStorageDataAccessService.deleteStaffStorage(date, uuid);
    }
}
