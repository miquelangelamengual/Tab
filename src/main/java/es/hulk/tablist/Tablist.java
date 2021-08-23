package es.hulk.tablist;

import es.hulk.tablist.commands.ReloadCommand;
import es.hulk.tablist.commands.TablistCommand;
import es.hulk.tablist.listeners.TablistListener;
import es.hulk.tablist.provider.TablistProvider;
import es.hulk.tablist.utils.CC;
import es.hulk.tablist.utils.FileConfig;
import es.hulk.tablist.utils.command.CommandManager;
import io.github.nosequel.tab.shared.TabHandler;
import io.github.nosequel.tab.v1_10_r1.v1_10_R1TabAdapter;
import io.github.nosequel.tab.v1_12_r1.v1_12_R1TabAdapter;
import io.github.nosequel.tab.v1_14_r1.v1_14_R1TabAdapter;
import io.github.nosequel.tab.v1_15_r1.v1_15_R1TabAdapter;
import io.github.nosequel.tab.v1_16_r3.v1_16_R3TabAdapter;
import io.github.nosequel.tab.v1_7_r4.v1_7_R4TabAdapter;
import io.github.nosequel.tab.v1_8_r3.v1_8_R3TabAdapter;
import io.github.nosequel.tab.v1_9_r1.v1_9_R1TabAdapter;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public class Tablist extends JavaPlugin {

    private FileConfig tablistConfig;
    private CommandManager commandManager;

    private boolean isPlaceholderAPI = false;

    @Override
    public void onDisable() { }

    @Override
    public void onEnable() {

        CC.log("&8[&bTablist&8]");
        CC.log("&8[&bTablist&8] &aLoading the plugin...");
        CC.log("&8[&bTablist&8]");

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            this.isPlaceholderAPI = true;
            CC.log("&8[&bTablist&8] &aHooked into PlaceholderAPI");
        }

        loadConfig();
        loadListeners();
        loadProviders();
        loadCommands();

        CC.log("&8[&bTablist&8]");
    }

    public void loadConfig() {
        this.tablistConfig = new FileConfig(this, "tablist.yml");
        CC.log("&8[&bTablist&8] &aConfig Loaded");
    }

    public void loadProviders() {
        this.commandManager = new CommandManager(this);
        if (tablistConfig.getBoolean("TABLIST.ENABLE")) {
            if (Bukkit.getVersion().contains("1.7")) new TabHandler(new v1_7_R4TabAdapter(), new TablistProvider(), this, 20L);
            if (Bukkit.getVersion().contains("1.8")) new TabHandler(new v1_8_R3TabAdapter(), new TablistProvider(), this, 20L);
            if (Bukkit.getVersion().contains("1.9")) new TabHandler(new v1_9_R1TabAdapter(), new TablistProvider(), this, 20L);
            if (Bukkit.getVersion().contains("1.10")) new TabHandler(new v1_10_R1TabAdapter(), new TablistProvider(), this, 20L);
            if (Bukkit.getVersion().contains("1.12")) new TabHandler(new v1_12_R1TabAdapter(), new TablistProvider(), this, 20L);
            if (Bukkit.getVersion().contains("1.14")) new TabHandler(new v1_14_R1TabAdapter(), new TablistProvider(), this, 20L);
            if (Bukkit.getVersion().contains("1.15")) new TabHandler(new v1_15_R1TabAdapter(), new TablistProvider(), this, 20L);
            if (Bukkit.getVersion().contains("1.16")) new TabHandler(new v1_16_R3TabAdapter(), new TablistProvider(), this, 20L);
            CC.log("&8[&bTablist&8] &aTablist Enabled");
            return;
        }
        CC.log("&8[&bTablist&8] &cTablist Disabled, no version found");
    }

    public void loadListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new TablistListener(), this);
    }

    public void loadCommands() {
        new ReloadCommand();
        new TablistCommand();
        CC.log("&8[&bTablist&8] &aCommands loaded");
    }

    public static Tablist get() {
        return getPlugin(Tablist.class);
    }
}
