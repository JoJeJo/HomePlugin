/*
package home.plugin.homeplugin2.Commands;

import home.plugin.homeplugin2.HomePlugin2;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.HashMap;
import java.util.UUID;

public class Home implements CommandExecutor {

    private final HashMap<UUID, Long> cooldown;

    public Home() {
        this.cooldown = new HashMap<UUID, Long>();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§AThis command can only be used by players!");
            return true;
        }

        Player player = (Player) sender;
        UUID playerUUID = player.getUniqueId();
        if (player.getWorld().getName().equals("world_nether")) {
            sender.sendMessage("§AYou can not use /home in the Nether.");
            cooldown.put(player.getUniqueId(), System.currentTimeMillis() - 10000);
            return false;
        }
        // Can not use command in End
        if (player.getWorld().getName().equals("world_the_end")) {
            sender.sendMessage("§AYou can not use /home in the End.");
            cooldown.put(player.getUniqueId(), System.currentTimeMillis() - 10000);
            return false;
        }
        if (player.getBedSpawnLocation().getWorld().getName().equals("world_nether")) {
            sender.sendMessage("§AYou can not teleport to a home in the nether.");
            cooldown.put(player.getUniqueId(), System.currentTimeMillis() - 10000);
            return true;
        }
        // Get bed spawn location
        Location bedSpawn = player.getBedSpawnLocation();
        if (bedSpawn == null) {
            player.sendMessage("§AYou haven't slept in a bed yet!");
            return true;
        }
        if (!cooldown.containsKey(player.getUniqueId()) || System.currentTimeMillis() - cooldown.get(player.getUniqueId()) > 10000) {
            double location1X = player.getLocation().getX();
            double location1Y = player.getLocation().getY();
            double location1Z = player.getLocation().getZ();
            long startTime = System.currentTimeMillis();
            long delay = 5000;
            long interval = 100;
            BukkitTask task = new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getLogger().info("run 1");
                    long timeLeft = (delay - (System.currentTimeMillis() - startTime)) / 1000;
                    sender.sendMessage("§ATeleporting to home in " + timeLeft + " seconds...");
                    if (timeLeft > 0) {
                        sender.sendMessage("§ATeleporting to home in " + timeLeft + " seconds...");
                        double location2X = player.getLocation().getX();
                        double location2Y = player.getLocation().getY();
                        double location2Z = player.getLocation().getZ();
                        if (!(location1X == location2X)) {
                            sender.sendMessage("§cYou moved! Teleport Canceled!");
                            Bukkit.getLogger().info("run 2");
                            cooldown.put(player.getUniqueId(), System.currentTimeMillis() - 10000);
                            cancel();
                        }
                        if (!(location1Y == location2Y)) {
                            sender.sendMessage("§cYou moved! Teleport Canceled!");
                            Bukkit.getLogger().info("run 3");
                            cooldown.put(player.getUniqueId(), System.currentTimeMillis() - 10000);
                            cancel();
                        }
                        if (!(location1Z == location2Z)) {
                            sender.sendMessage("§cYou moved! Teleport Canceled!");
                            Bukkit.getLogger().info("run 4");
                            cooldown.put(player.getUniqueId(), System.currentTimeMillis() - 10000);
                            cancel();
                        }
                    }
                    if (timeLeft <= 0) {
                        sender.sendMessage("§ATeleporting to home in " + timeLeft + " seconds...");
                        double location2X = player.getLocation().getX();
                        double location2Y = player.getLocation().getY();
                        double location2Z = player.getLocation().getZ();
                        if (!(location1X == location2X)) {
                            sender.sendMessage("§cYou moved! Teleport Canceled!");
                            Bukkit.getLogger().info("run 5");
                            cooldown.put(player.getUniqueId(), System.currentTimeMillis() - 10000);
                            cancel();
                        }
                        if (!(location1Y == location2Y)) {
                            sender.sendMessage("§cYou moved! Teleport Canceled!");
                            Bukkit.getLogger().info("run 6");
                            cooldown.put(player.getUniqueId(), System.currentTimeMillis() - 10000);
                            cancel();
                        }
                        if (!(location1Z == location2Z)) {
                            sender.sendMessage("§cYou moved! Teleport Canceled!");
                            Bukkit.getLogger().info("run 7");
                            cooldown.put(player.getUniqueId(), System.currentTimeMillis() - 10000);
                            cancel();
                        }
                        else {
                            // Teleport and set cooldown
                            player.teleport(bedSpawn);
                            sender.sendMessage("§ATeleported to home!");
                            cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                            return;
                        }
                    }
                }
            }.runTaskLater(HomePlugin2.getInstance(), interval);
        }
        else {
            sender.sendMessage("§cYou can't teleport home for another " + (10 - ((System.currentTimeMillis() - cooldown.get(player.getUniqueId())) / 1000)) + " seconds!");
            return false;
        }
        return true;
    }
}
*/