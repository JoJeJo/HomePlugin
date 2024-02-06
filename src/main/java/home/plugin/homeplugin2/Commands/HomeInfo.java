package home.plugin.homeplugin2.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeInfo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        if (command.getName().equalsIgnoreCase("homeinfo")) {
            Player player = (Player) sender;
            sender.sendMessage("§ePlugin created by JoJeJo for Minecraft 1.20.X. Version 1.1");
        }
        return true;
    }
}
