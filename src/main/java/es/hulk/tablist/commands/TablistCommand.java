package es.hulk.tablist.commands;

import es.hulk.tablist.utils.CC;
import es.hulk.tablist.utils.command.BaseCommand;
import es.hulk.tablist.utils.command.Command;
import es.hulk.tablist.utils.command.CommandArgs;
import org.bukkit.command.CommandSender;

public class TablistCommand extends BaseCommand {

    @Command(name = "tablist", aliases = {"tab", "tabplugin", "plugintab", "mytab", "hulktab"})

    @Override
    public void onCommand(CommandArgs command) {
        CommandSender sender = command.getSender();
        if (sender.hasPermission("tablist.command.reload")) {
            sender.sendMessage(CC.translate("&cUsage: /tablist reload"));
            return;
        }
        sender.sendMessage(CC.translate("&aTablist plugin made by Hulk#0226"));
    }
}
