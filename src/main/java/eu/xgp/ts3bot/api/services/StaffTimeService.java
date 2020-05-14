package eu.xgp.ts3bot.api.services;

import com.google.gson.JsonObject;
import eu.xgp.ts3bot.api.dao.StaffTimeDataAccessService;
import eu.xgp.ts3bot.api.objects.StaffTime;

import java.util.List;

public class StaffTimeService {
    private StaffTimeDataAccessService staffTimeDataAccessService;

    public StaffTimeService(String platform) {
        this.staffTimeDataAccessService = new StaffTimeDataAccessService(platform);
    }

    public List<StaffTime> getAllStaffTime() {
        return staffTimeDataAccessService.getAllStaffTime();
    }

    public StaffTime getStaffTime(String uuid) {
        return staffTimeDataAccessService.getStaffTime(uuid).orElse(null);
    }

    public void addStaffTime(StaffTime staffTime) {
        staffTimeDataAccessService.addStaffTime(staffTime);
    }

    public void updateValue(String uuid, String toUpdate, JsonObject json) {
        staffTimeDataAccessService.updateValue(uuid, toUpdate, json);
    }

    public void deleteStaffTime(String uuid) {
        staffTimeDataAccessService.deleteStaffTime(uuid);
    }
}
