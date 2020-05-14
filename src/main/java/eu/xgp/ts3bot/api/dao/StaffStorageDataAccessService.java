package eu.xgp.ts3bot.api.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import eu.xgp.ts3bot.api.objects.StaffStorage;
import eu.xgp.ts3bot.api.request.HttpConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StaffStorageDataAccessService {
    private String platform;

    public StaffStorageDataAccessService(String platform) {
        this.platform = platform;
    }

    public List<StaffStorage> getAllStaffStorage() {

        HttpConnection conn = new HttpConnection("staff/" + platform, "GET", "");
        JsonArray jsonArray = conn.getJsonArray();
        List<StaffStorage> staffStorageList = new ArrayList<>();

        for (JsonElement e : jsonArray) {
            JsonObject obj = e.getAsJsonObject();
            String uuid = obj.get("uuid").getAsString();
            String name = obj.get("name").getAsString();
            String date = obj.get("date").getAsString();
            long time = obj.get("time").getAsLong();
            boolean ok = obj.get("ok").getAsBoolean();

            staffStorageList.add(new StaffStorage(uuid, name, date, time, ok));
        }
        return staffStorageList;
    }

    public Optional<StaffStorage> getStaffStorage(String date, String uuid) {
        return getAllStaffStorage().stream().filter(pi -> pi.getUuid().equalsIgnoreCase(uuid) && pi.getDate().equalsIgnoreCase(date)).findFirst();
    }

    public void updateValue(String date, String uuid, String toUpdate, JsonObject json) {
        HttpConnection conn = new HttpConnection("staff/storage/" + platform + "/" + date + "/" + uuid + "/" + toUpdate, "PUT", json.toString());
        conn.runRequest();
    }

    public void addStaffStorage(StaffStorage staffStorage) {
        HttpConnection conn = new HttpConnection("staff/storage/", "POST", staffStorage.getJsonObject().toString());
        conn.runRequest();
    }

    public void deleteStaffStorage(String date, String uuid) {
        HttpConnection conn = new HttpConnection("staff/storage/" + platform + "/" + date + "/" + uuid, "DELETE", "");
        conn.runRequest();
    }
}
