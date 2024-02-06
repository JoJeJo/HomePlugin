package home.plugin.homeplugin2.Commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public class Home implements CommandExecutor {

    private final HashMap<UUID, Long> cooldown;

    public Home() {
        this.cooldown = new HashMap<UUID, Long>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§eWhat am I goind to send you to? The console?");
            return false;
        }
        Player player = null;
        if (command.getName().equalsIgnoreCase("home")) {
            player = (Player) sender;

            if (player.getBedSpawnLocation() == null) {
                sender.sendMessage("§eYou have no set spawn. Right-Click a bed to set your spawn.");
                cooldown.put(player.getUniqueId(), System.currentTimeMillis() - 10000);
                return false;
            }
            if (player.getWorld().getName().equals("world_nether")) {
                sender.sendMessage("§eYou can not use /home in the Nether.");
                cooldown.put(player.getUniqueId(), System.currentTimeMillis() - 10000);
                return false;
            }
            if (player.getWorld().getName().equals("world_the_end")) {
                sender.sendMessage("§eYou can not use /home in the End.");
                cooldown.put(player.getUniqueId(), System.currentTimeMillis() - 10000);
                return false;
            }
            if (!cooldown.containsKey(player.getUniqueId()) || System.currentTimeMillis() - cooldown.get(player.getUniqueId()) > 10000) {
                cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                try {
                    for (int i = 0; i < 5; i++) {
                        double health1 = player.getHealth();
                        @NotNull Location location1 = player.getLocation();
                        sender.sendMessage("Teleporting in " + (5 - i) + "!");
                        Thread.sleep(1000);
                        double health2 = player.getHealth();
                        @NotNull Location location2 = player.getLocation();
                        if (!((health1 == health2))) {
                            sender.sendMessage("You can not take damage when teleporting home.");
                            return false;
                        }
                        if (!((location1 == location2))) {
                            sender.sendMessage("You need to stand still when teleporting home.");
                            return false;
                        }
                    }
                    player.teleportAsync(player.getBedSpawnLocation());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            }
        } else
            sender.sendMessage("§eYou can't teleport home for another " + (10 - ((System.currentTimeMillis() - cooldown.get(player.getUniqueId())) / 1000)) + " seconds!");
        return true;
    }
}
