package home.plugin.homeplugin2.background;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import home.plugin.homeplugin2.HomePlugin2;

public class PlayerEventHandler implements Listener {
    public PlayerEventHandler(HomePlugin2 plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

}