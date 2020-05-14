package eu.xgp.ts3bot.api.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import eu.xgp.ts3bot.api.objects.StaffTime;
import eu.xgp.ts3bot.api.request.HttpConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StaffTimeDataAccessService {
    private String platform;

    public StaffTimeDataAccessService(String platform) {
        this.platform = platform;
    }

    public List<StaffTime> getAllStaffTime() {

        HttpConnection conn = new HttpConnection("staff/" + platform, "GET", "");
        JsonArray jsonArray = conn.getJsonArray();
        List<StaffTime> staffTimeList = new ArrayList<>();

        for (JsonElement e : jsonArray) {
            JsonObject obj = e.getAsJsonObject();
            String uuid = obj.get("uuid").getAsString();
            String name = obj.get("name").getAsString();
            long spent = obj.get("spent").getAsLong();

            staffTimeList.add(new StaffTime(uuid, name, spent));
        }
        return staffTimeList;
    }

    public Optional<StaffTime> getStaffTime(String uuid) {
        return getAllStaffTime().stream().filter(pi -> pi.getUuid().equalsIgnoreCase(uuid)).findFirst();
    }

    public void updateValue(String uuid, String toUpdate, JsonObject json) {
        HttpConnection conn = new HttpConnection("staff/time/" + platform + "/" + uuid + "/" + toUpdate, "PUT", json.toString());
        conn.runRequest();
    }

    public void addStaffTime(StaffTime staffTime) {
        HttpConnection conn = new HttpConnection("staff/time/", "POST", staffTime.getJsonObject().toString());
        conn.runRequest();
    }

    public void deleteStaffTime(String uuid) {
        HttpConnection conn = new HttpConnection("staff/time/" + platform + "/" + uuid, "DELETE", "");
        conn.runRequest();
    }
}
