package eu.xgp.ts3bot.commands;

import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import eu.xgp.ts3bot.Ts3BotPlugin;
import eu.xgp.ts3bot.api.StaffApi;
import eu.xgp.ts3bot.api.objects.StaffPlayer;
import eu.xgp.ts3bot.bot.Bot;
import eu.xgp.ts3bot.enums.Permission;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AddPermCommand extends Command {

    StaffApi staffApi = new StaffApi();
    private Ts3BotPlugin main = Ts3BotPlugin.getInstance();

    public AddPermCommand(String name, String permission, String[] aliases) {
        super(name, permission, aliases);
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        if(args.length != 2){
            commandSender.sendMessage(new TextComponent("§cUsage: /addpex <player> <permission>\n§6Allowed permissions: HELPER, HEADHELPER, MOD, HEADMOD, Admin, BLACK, §6LEGEND, §6CRYSTAL, DEFINITIVE, XGPRIME"));
            return;
        }
        ProxiedPlayer p = main.getProxy().getPlayer(args[0]);
        if (p == null) {
            commandSender.sendMessage(new TextComponent("§c"+args[0] + " is not online"));
            return;
        }
        Permission perm;
        try {
            perm = Permission.valueOf(args[1].toUpperCase());
        }catch (IllegalArgumentException e){
            commandSender.sendMessage(new TextComponent("§4ERROR: §6Allowed permissions: HELPER, HEADHELPER, MOD, HEADMOD, Admin, BLACK, §6LEGEND, CRYSTAL, DEFINITIVE, XGPRIME"));
            return;
        }
        main.matchTs(p,perm);
        commandSender.sendMessage(new TextComponent("done"));
    }
}
