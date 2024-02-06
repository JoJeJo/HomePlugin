package home.plugin.homeplugin2.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import home.plugin.homeplugin2.HomePlugin2;

public class PlayerEventHandler implements Listener {
    public PlayerEventHandler(HomePlugin2 plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

}