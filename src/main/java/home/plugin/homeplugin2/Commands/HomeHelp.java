package home.plugin.homeplugin2.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeHelp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        if (command.getName().equalsIgnoreCase("homehelp")) {
            Player player = (Player) sender;
            sender.sendMessage("§eTo set home, right-click on a bed. To go home, use /home. There is a 10 second cooldown between uses of /home.");
        }
        return true;
    }
}
