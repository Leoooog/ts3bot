package eu.xgp.ts3bot.bot;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import eu.xgp.ts3bot.Ts3BotPlugin;
import eu.xgp.ts3bot.bot.runnables.AFKRunnable;
import eu.xgp.ts3bot.enums.Permission;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Bot {
    public static final TS3Config config = new TS3Config();
    public static final TS3Query query = new TS3Query(config);
    public static final TS3Api api = new TS3Api(query);
    private Ts3BotPlugin main;

    public Bot(Ts3BotPlugin ts3BotPlugin) {
        main = ts3BotPlugin;
    }

    public static TS3Api getApi() {
        return api;
    }

    public void start() {
        config.setHost("ts.xgamesplanet.eu");
        config.setFloodRate(TS3Query.FloodRate.DEFAULT);
        config.setDebugLevel(Level.ALL);
        query.connect();
        api.selectVirtualServerById(1);
        api.setNickname("XGP BOT");
        api.login("xgpbot", "x3U0OqC5");
        Ts3Events.loadEvents();
        api.moveClient(api.whoAmI().getId(), 294);
        api.addClientToServerGroup(38, api.whoAmI().getDatabaseId());
        System.out.println("connesso");
        main.getProxy().getScheduler().schedule(main,new AFKRunnable(), 1,1, TimeUnit.SECONDS);
    }

    public void stop() {
        query.exit();
    }

    public void addPermission(Permission pex, String id) {
        Client c = api.getClientByUId(id);
        api.addClientToServerGroup(pex.getId(), c.getDatabaseId());
    }

    public void removePermission(Permission pex, String id) {
        Client c = api.getClientByUId(id);
        api.removeClientFromServerGroup(pex.getId(), c.getDatabaseId());
    }

    public void sendMessage(String id, String message) {
        Client c = api.getClientByUId(id);
        api.sendPrivateMessage(c.getId(), message);
    }
}