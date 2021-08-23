package es.hulk.tablist.commands;

import es.hulk.tablist.Tablist;
import es.hulk.tablist.utils.CC;
import es.hulk.tablist.utils.command.BaseCommand;
import es.hulk.tablist.utils.command.Command;
import es.hulk.tablist.utils.command.CommandArgs;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends BaseCommand {

    private final Tablist plugin = Tablist.get();

    @Command(name = "tablist.reload", permission = "tablist.command.reload", aliases = "tab.reload")

    @Override
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        plugin.getTablistConfig().reload();
        CC.sender(sender, "&aTablist Config has been reloaded");
    }
}
