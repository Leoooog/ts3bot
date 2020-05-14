package eu.xgp.ts3bot.commands;

import eu.xgp.ts3bot.Ts3BotPlugin;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class PexCommand extends Command {
    private Ts3BotPlugin main;

    public PexCommand(Ts3BotPlugin main, String name, String permission, String... aliases) {
        super(name, permission, aliases);
        this.main = main;
    }

    @Override
    public void execute(CommandSender commandSender, String[] strings) {

    }
}
