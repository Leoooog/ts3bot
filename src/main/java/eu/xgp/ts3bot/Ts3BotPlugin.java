package eu.xgp.ts3bot;

import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.google.gson.JsonObject;
import eu.xgp.ts3bot.api.PlayerApi;
import eu.xgp.ts3bot.api.StaffApi;
import eu.xgp.ts3bot.api.objects.StaffPlayer;
import eu.xgp.ts3bot.bot.Bot;
import eu.xgp.ts3bot.commands.AddPermCommand;
import eu.xgp.ts3bot.enums.Permission;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.util.HashMap;
import java.util.UUID;

public class Ts3BotPlugin extends Plugin implements Listener {
    private Bot bot;
    private final HashMap<String, Permission> newPex = new HashMap<>();
    private static Ts3BotPlugin instance;

    public static Ts3BotPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        bot = new Bot(this);
        getLogger().info("TS3BOT PLUGIN ENABLED");
        bot.start();
        getProxy().getPluginManager().registerCommand(this, new AddPermCommand("addpex", "ts3bot.addpex", new String[]{}));
        getProxy().registerChannel("Core");
        getProxy().getPluginManager().registerListener(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("TS3BOT PLUGIN DISABLED");
        bot.stop();
    }

    public Bot getBot() {
        return bot;
    }

    @EventHandler
    public void onMessage(PluginMessageEvent e) {
        if (!e.getTag().equalsIgnoreCase("Core")) return;
        ByteArrayDataInput input = ByteStreams.newDataInput(e.getData());
        String method = input.readUTF();
        switch (method) {
            case "addPerm":
                String id = input.readUTF();
                Permission pex = Permission.valueOf(input.readUTF());
                bot.addPermission(pex, id);
                break;
            case "removePerm":
                String id1 = input.readUTF();
                Permission pex1 = Permission.valueOf(input.readUTF());
                bot.removePermission(pex1, id1);
                break;
            case "sendMessage":
                String id2 = input.readUTF();
                String message = input.readUTF();
                bot.sendMessage(id2, message);
                break;
            case "matchTS":
                String uuid = input.readUTF();
                Permission pex2 = Permission.valueOf(input.readUTF());
                ProxiedPlayer p = getProxy().getPlayer(UUID.fromString(uuid));
                matchTs(p, pex2);
        }
    }

    public void matchTs(ProxiedPlayer p, Permission perm) {
        String ip = p.getAddress().getAddress().toString().split("/")[1];
        for (Client c : Bot.api.getClients())
            if (c.getIp().equalsIgnoreCase(ip)) {
                PlayerApi playerApi = new PlayerApi();
                playerApi.getVipPlayer(p.getUniqueId().toString()).setVip(perm.toString());

                bot.addPermission(perm, c.getUniqueIdentifier());
            }
    }
}