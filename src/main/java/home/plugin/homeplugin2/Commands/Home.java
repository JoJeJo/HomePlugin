package home.plugin.homeplugin2.Commands;

// import org.bukkit.Location;
import home.plugin.homeplugin2.HomePlugin2;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        // Check for cooldown
        if (cooldown.containsKey(playerUUID)) {
            long cooldownTimeLeft = (cooldown.get(playerUUID) - System.currentTimeMillis()) / 1000;
            player.sendMessage("§AYou need to wait " + cooldownTimeLeft + " seconds to use /home again.");
            return true;
        }

        // Get bed spawn location
        Location bedSpawn = player.getBedSpawnLocation();
        if (bedSpawn == null) {
            player.sendMessage("§AYou haven't slept in a bed yet!");
            return true;
        }
        if (!cooldown.containsKey(player.getUniqueId()) || System.currentTimeMillis() - cooldown.get(player.getUniqueId()) > 10000) {
            cooldown.put(player.getUniqueId(), System.currentTimeMillis());
            // Start 5 second countdown with task scheduler
            long startTime = System.currentTimeMillis();
            long delay = 5 * 2; // TIME OF DELAY
            long interval = 1000; // Check every second
            BukkitTask task = new BukkitRunnable() {
                @Override
                public void run() {
                    Bukkit.getLogger().info("run 5");
                    long timeLeft = (delay - (System.currentTimeMillis() - startTime)) / 1000;
                    if (timeLeft > 0) {
                        sender.sendMessage("§ATeleporting to home in " + timeLeft + " seconds...");
                        // Check for movement or damage during countdown
                        if (player.getLocation().distance(bedSpawn) > 0.1) {
                            sender.sendMessage("§ATeleport cancelled! You moved.");
                            return;
                        }
                        if (player.getHealth() < player.getMaxHealth()) {
                            sender.sendMessage("§ATeleport cancelled! You need to be at full health.");
                            return;
                        }
                    } else {
                        // Teleport and set cooldown
                        player.teleport(bedSpawn);
                        sender.sendMessage("§ATeleported to home!");
                        cooldown.put(playerUUID, System.currentTimeMillis() + 10 * 1000); // 10 seconds cooldown
                        return;
                    }
                }
            }.runTaskLater(HomePlugin2.getInstance(), interval);
        }
        return true;
    }
}