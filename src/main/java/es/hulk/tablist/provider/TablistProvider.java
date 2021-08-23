package es.hulk.tablist.provider;

import es.hulk.tablist.Tablist;
import es.hulk.tablist.utils.FileConfig;
import io.github.nosequel.tab.shared.entry.TabElement;
import io.github.nosequel.tab.shared.entry.TabElementHandler;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class TablistProvider implements TabElementHandler {

    private final FileConfig tablistConfig = Tablist.get().getTablistConfig();

    @Override
    public TabElement getElement(Player player) {
        TabElement element = new TabElement();

        if (Tablist.get().isPlaceholderAPI()) {
            element.setHeader(PlaceholderAPI.setPlaceholders(player, tablistConfig.getString("TABLIST.HEADER").replace("<line>", "\n").replaceAll("<player>", player.getDisplayName())));
            element.setFooter(PlaceholderAPI.setPlaceholders(player, tablistConfig.getString("TABLIST.FOOTER").replace("<line>", "\n").replaceAll("<player>", player.getDisplayName())));
        } else {
            element.setHeader(tablistConfig.getString("TABLIST.HEADER").replace("<line>", "\n"));
            element.setFooter(tablistConfig.getString("TABLIST.FOOTER").replace("<line>", "\n"));
        }

        List<String> tabType = Arrays.asList("LEFT", "MIDDLE", "RIGHT", "FAR-RIGHT");

        for (int j = 0; j < 4; j++) {
            String type = tabType.get(j);
            for (int i = 0; i < 20; i++) {
                if (Tablist.get().isPlaceholderAPI()) {
                    element.add(j, i, PlaceholderAPI.setPlaceholders(player, tablistConfig.getString("TABLIST." + type + "." + (i + 1)).replaceAll("<player>", player.getDisplayName())));
                } else {
                    element.add(j, i, tablistConfig.getString("TABLIST." + type + "." + (i + 1)).replaceAll("<player>", player.getDisplayName()));
                }
            }
        }

        return element;
    }
}
