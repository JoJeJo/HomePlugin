package home.plugin.homeplugin2;

import home.plugin.homeplugin2.Commands.Home;
import org.bukkit.plugin.java.JavaPlugin;

public final class HomePlugin2 extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getCommand("home").setExecutor(new Home());
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    private static HomePlugin2 instance;

    public static HomePlugin2 getInstance() {
        return instance;
    }
}
