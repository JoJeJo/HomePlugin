package home.plugin.homeplugin2.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.UUID;

public class Home implements CommandExecutor{

    private final HashMap<UUID, Long> cooldown;
    public Home(){
        this.cooldown = new HashMap<UUID, Long>();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        if (command.getName().equalsIgnoreCase("home")) {
            Player player = (Player) sender;

            if (player.getBedSpawnLocation() == null) {
                sender.sendMessage("§eYou have no set spawn. Right-Click a bed to set your spawn.");
                return false;
            }
            if (!cooldown.containsKey(player.getUniqueId()) || System.currentTimeMillis() - cooldown.get(player.getUniqueId()) > 10000) {
                cooldown.put(player.getUniqueId(), System.currentTimeMillis());

                if (player.getWorld().getName().equals(player.getBedSpawnLocation().getWorld().getName())) {
                    player.teleportAsync(player.getBedSpawnLocation());
                    return true;
                }
                if (player.getWorld().getName().equals("world_nether")) {
                    sender.sendMessage("§eYou can not use /home in the Nether.");
                    cooldown.put(player.getUniqueId(), System.currentTimeMillis()-10000);
                }
                if (player.getWorld().getName().equals("world_the_end")) {
                    sender.sendMessage("§eYou can not use /home in the End.");
                    cooldown.put(player.getUniqueId(), System.currentTimeMillis()-10000);
                }
            } else
                player.sendMessage("§eYou can't teleport home for another " + (10 - ((System.currentTimeMillis() - cooldown.get(player.getUniqueId())) / 1000)) + " seconds!");
        }
        return true;
    }
}
