package eu.xgp.ts3bot.bot;

import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import com.github.theholywaffle.teamspeak3.api.event.*;
import com.github.theholywaffle.teamspeak3.api.wrapper.ChannelGroupClient;
import com.github.theholywaffle.teamspeak3.api.wrapper.ChannelInfo;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import eu.xgp.ts3bot.enums.Permission;

import java.util.HashMap;
import java.util.Map;

public class Ts3Events {
    public static void loadEvents() {
        Bot.getApi().registerAllEvents();
        Bot.getApi().addTS3Listeners(new TS3Listener() {
            public void onTextMessage(TextMessageEvent textMessageEvent) {

            }

            public void onClientJoin(ClientJoinEvent clientJoinEvent) {
                Client c = Bot.api.getClientInfo(clientJoinEvent.getClientId());
                Bot.api.sendPrivateMessage(c.getId(), "Welcome to XGP teamspeak!");
            }

            public void onClientLeave(ClientLeaveEvent clientLeaveEvent) {
            }

            public void onServerEdit(ServerEditedEvent serverEditedEvent) {

            }

            public void onChannelEdit(ChannelEditedEvent channelEditedEvent) {

            }

            public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent channelDescriptionEditedEvent) {

            }

            public void onClientMoved(ClientMovedEvent clientMovedEvent) {
                ChannelInfo channelInfo = Bot.api.getChannelInfo(clientMovedEvent.getTargetChannelId());
                Client client = Bot.api.getClientInfo(clientMovedEvent.getClientId());
                ChannelGroupClient cgc;
                Map<ChannelProperty, String> properties = new HashMap<>();
                int channelId;
                switch (channelInfo.getId()) {
                    case 285: //VIP join room
                        if (!isVIP(client)) {
                            Bot.api.kickClientFromChannel(client.getId());
                            break;
                        }
                        cgc = Bot.api.getChannelGroupClientsByClientDBId(client.getDatabaseId()).stream().filter(channelGroupClient -> channelGroupClient.getChannelGroupId() == 11).findFirst().orElse(null);
                        if (cgc != null) {
                            Bot.api.moveClient(client.getId(), cgc.getChannelId());
                            break;
                        }
                        properties.put(ChannelProperty.CHANNEL_FLAG_PERMANENT, "1");
                        properties.put(ChannelProperty.CPID, Integer.toString(channelInfo.getId()));
                        channelId = Bot.api.createChannel(client.getNickname() + "'s channel", properties);
                        Bot.api.moveClient(client.getId(), channelId);
                        Bot.api.moveClient(Bot.api.whoAmI().getId(), 294);
                        Bot.api.addChannelPermission(channelId, "i_channel_needed_modify_power", 10);
                        Bot.api.setClientChannelGroup(11, channelId, client.getDatabaseId());
                        break;
                    case 290: //Temporary join room
                        properties.put(ChannelProperty.CHANNEL_FLAG_TEMPORARY, "1");
                        properties.put(ChannelProperty.CPID, Integer.toString(channelInfo.getId()));
                        channelId = Bot.api.createChannel(client.getNickname() + "'s channel", properties);
                        Bot.api.moveClient(client.getId(), Bot.api.getChannelsByName(client.getNickname() + "'s channel").get(0).getId());
                        Bot.api.moveClient(Bot.api.whoAmI().getId(), 294);
                        Bot.api.addChannelPermission(channelId, "i_channel_needed_modify_power", 5);
                        Bot.api.setClientChannelGroup(12, channelId, client.getDatabaseId());
                        break;
                    case 315: //meeting room
                        if (isFounder(client)) {
                            for (Client c : Bot.api.getClients()) {
                                if (!isStaff(c) || c.getId() == client.getId())
                                    continue;
                                Bot.api.pokeClient(c.getId(), "Attention! [B]" + client.getNickname() + "[/B] requested a meeting! Please enter in the meeting room now!");
                            }
                        }
                        break;
                    case 281: //request support room
                        for (Client c : Bot.api.getClients()) {
                            if (!isStaff(c))
                                continue;
                            Bot.api.sendPrivateMessage(c.getId(), "Attention! [B]" + client.getNickname() + "[/B] is in the support room!");
                        }
                        break;
                }
            }

            public void onChannelCreate(ChannelCreateEvent channelCreateEvent) {

            }

            public void onChannelDeleted(ChannelDeletedEvent channelDeletedEvent) {

            }

            public void onChannelMoved(ChannelMovedEvent channelMovedEvent) {

            }

            public void onChannelPasswordChanged(ChannelPasswordChangedEvent channelPasswordChangedEvent) {

            }

            public void onPrivilegeKeyUsed(PrivilegeKeyUsedEvent privilegeKeyUsedEvent) {

            }
        });
    }

    private static boolean isVIP(Client client) {
        return hasPermission(client, Permission.BLACK, Permission.LEGEND, Permission.CRYSTAL, Permission.DEFINITIVE, Permission.XGPRIME);
    }

    private static boolean isFounder(Client client) {
        return hasPermission(client, Permission.Owner, Permission.Founder, Permission.CoFounder, Permission.StaffManager, Permission.Manager);
    }

    private static boolean isStaff(Client client) {
        return hasPermission(client, Permission.values()) && !isVIP(client);
    }

    private static boolean hasPermission(Client client, Permission... pex) {
        for (int id : client.getServerGroups()) {
            for (Permission p : pex) {
                if (p.getId() == id)
                    return true;
            }
        }
        return false;
    }
}
