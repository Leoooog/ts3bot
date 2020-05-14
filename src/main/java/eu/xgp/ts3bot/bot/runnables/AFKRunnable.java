package eu.xgp.ts3bot.bot.runnables;

import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import eu.xgp.ts3bot.bot.Bot;

import java.util.HashMap;

public class AFKRunnable implements Runnable {
    HashMap<String, Long> afk = new HashMap<>();
    HashMap<String, Boolean> moved = new HashMap<>();
    HashMap<String, Integer> channel = new HashMap<>();
    int seconds = 60;

    @Override
    public void run() {
        if(Bot.api.getClients()==null) return;
        for (Client c : Bot.api.getClients()) {
            if (!c.isServerQueryClient()) {
                if (c.isAway() || c.isOutputMuted()) {
                    if (!moved.containsKey(c.getUniqueIdentifier())) {
                        if (!afk.containsKey(c.getUniqueIdentifier())) {
                            afk.put(c.getUniqueIdentifier(), System.currentTimeMillis());
                        } else {
                            long now = System.currentTimeMillis();
                            if (now - afk.get(c.getUniqueIdentifier()) >= seconds * 1000 && c.getChannelId() != 260) {
                                moved.put(c.getUniqueIdentifier(), true);
                                channel.put(c.getUniqueIdentifier(), c.getChannelId());
                                afk.remove(c.getUniqueIdentifier());
                                Bot.api.moveClient(c.getId(), 260);
                                Bot.api.sendPrivateMessage(c.getId(), "You have been moved to [B]AFK ROOM[/B]");
                            }
                        }
                    }
                } else {
                    if (afk.containsKey(c.getUniqueIdentifier())) {
                        afk.remove(c.getUniqueIdentifier());
                    }
                    if (moved.containsKey(c.getUniqueIdentifier())) {
                        if (moved.get(c.getUniqueIdentifier())) {
                            Bot.api.moveClient(c.getId(), channel.get(c.getUniqueIdentifier()));
                            moved.remove(c.getUniqueIdentifier());
                            afk.remove(c.getUniqueIdentifier());
                        }
                    }
                }
            }
        }
    }
}
