package home.plugin.homeplugin2.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Home implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("home")) {
            Player player = (Player) sender;
            if (player.getBedSpawnLocation() == null) {
                return false;
            }
            if (player.getWorld().getName().equals(player.getBedSpawnLocation().getWorld().getName())) {
                player.teleportAsync(player.getBedSpawnLocation());
                return true;
            }
            if (player.getWorld().getName().equals("world_nether")) {
                sender.sendMessage("§eYou can not use /home in the Nether");
            }
            if (player.getWorld().getName().equals("world_the_end")) {
                sender.sendMessage("§eYou can not use /home in the End");
            }
        }

        return false;
    }

}
