package es.hulk.tablist.utils.command;

import es.hulk.tablist.Tablist;

public abstract class BaseCommand {

    public BaseCommand() {
        Tablist.get().getCommandManager().registerCommands(this);
    }

    public abstract void onCommand(CommandArgs command);

}
