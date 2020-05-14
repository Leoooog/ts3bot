package eu.xgp.ts3bot.commands;

import eu.xgp.ts3bot.Ts3BotPlugin;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class SendMessageCommand extends Command {
    private static Ts3BotPlugin main;

    public SendMessageCommand(Ts3BotPlugin main, String name) {
        super(name);
        this.main = main;
    }


    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(new TextComponent("Usage: /ts3message <player> <message>"));
            return;
        }
        //ProxiedPlayer player = main.getProxy().getPlayer(args[0]);
        StringBuilder message = new StringBuilder();
        for (int i = 1; i < args.length; i++)
            message.append(" ").append(args[i]);
        main.getBot().sendMessage(args[0], message.toString());
        //TODO: creare parte api dei vip e prendere uid di ts dal player
    }

    public static void sendMessage(String tsUUID, String message){
        main.getBot().sendMessage(tsUUID, message);
    }
}
