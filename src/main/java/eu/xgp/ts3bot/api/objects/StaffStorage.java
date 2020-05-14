package eu.xgp.ts3bot.api.objects;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import eu.xgp.ts3bot.api.StaffApi;

public class StaffStorage {
    private long time;
    private String uuid;
    private String name;
    private String date;
    private boolean ok;
    private StaffApi staffApi;

    public StaffStorage(String uuid, String name, String date, long time, boolean ok) {
        this.time = time;
        this.uuid = uuid;
        this.name = name;
        this.date = date;
        this.ok = ok;
        staffApi = new StaffApi(uuid.endsWith("=") ? "ts" : "mc");
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
        staffApi.updateStaffStorage(getDate(), uuid, "time", new JsonParser().parse("{\"value\": " + time + "}").getAsJsonObject());
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;staffApi.updateStaffStorage(getDate(), uuid, "uuid", new JsonParser().parse("{\"value\": \"" + uuid + "\"}").getAsJsonObject());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
         staffApi.updateStaffStorage(getDate(), uuid, "name", new JsonParser().parse("{\"value\": \"" + name + "\"}").getAsJsonObject());
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
         staffApi.updateStaffStorage(getDate(), uuid, "date", new JsonParser().parse("{\"value\": \"" + date + "\"}").getAsJsonObject());
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
         staffApi.updateStaffStorage(getDate(), uuid, "ok", new JsonParser().parse("{\"value\": " + (ok ? 1 : 0) + "}").getAsJsonObject());
    }

    @Override
    public String toString() {
        return "StaffStorage{" +
                "time=" + time +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", ok=" + ok +
                '}';
    }

    public JsonObject getJsonObject() {
        JsonObject object = new JsonObject();
        object.addProperty("uuid", uuid);
        object.addProperty("name", name);
        object.addProperty("date", date);
        object.addProperty("time", time);
        object.addProperty("ok", ok);
        return object;
    }
}
