package eu.xgp.ts3bot.api.request;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {
    private final String STRING_URL = "http://45.91.250.147:8082/api/";
    private String args;
    private String method;
    private String json;

    public HttpConnection(String args, String method, String json) {
        this.args = args;
        this.method = method;
        this.json = json;
    }

    private String read() {
        String returned = "";
        try {
            URL url = new URL(STRING_URL + args);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            if (json != "") {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
                outputStreamWriter.write(json);
                outputStreamWriter.flush();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            if (connection.getResponseCode() != 200)
                throw new IOException("Cannot connect to server. Response code: " + connection.getResponseCode());
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
            returned = builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returned;
    }

    public JsonArray getJsonArray() {
        JsonReader reader = new JsonReader(new StringReader(read()));
        reader.setLenient(true);
        return (new JsonParser().parse(reader)).getAsJsonArray();
    }

    public JsonObject getJsonObject() {
        JsonReader reader = new JsonReader(new StringReader(read()));
        reader.setLenient(true);
        return (new JsonParser().parse(reader)).getAsJsonObject();
    }

    public void runRequest() {
        read();
    }
}
