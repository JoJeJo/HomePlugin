package home.plugin.homeplugin2.Commands;

import home.plugin.homeplugin2.HomePlugin2;
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
            sender.sendMessage("§c/home can only be used by players!");
            return true;
        }
        Player player = (Player) sender;
        UUID playerUUID = player.getUniqueId();
        Location bedSpawn = player.getBedSpawnLocation();
        if (player.getBedSpawnLocation() == null) {
            sender.sendMessage("§cYou have no set spawn. Right-Click a bed to set your spawn.");
            cooldown.put(player.getUniqueId(), System.currentTimeMillis() - 10000);
            return true;
        }
        if (player.getWorld().getName().equals("world_nether")) {
            sender.sendMessage("§cYou can not use /home in the Nether.");
            cooldown.put(player.getUniqueId(), System.currentTimeMillis() - 10000);
            return true;
        }
        // Can not use command in End
        if (player.getWorld().getName().equals("world_the_end")) {
            sender.sendMessage("§cYou can not use /home in the End.");
            cooldown.put(player.getUniqueId(), System.currentTimeMillis() - 10000);
            return true;
        }
        if (player.getBedSpawnLocation().getWorld().getName().equals("world_nether")) {
            sender.sendMessage("§cYou can not teleport to a home in the nether.");
            cooldown.put(player.getUniqueId(), System.currentTimeMillis() - 10000);
            return true;
        }
        if (!cooldown.containsKey(player.getUniqueId()) || System.currentTimeMillis() - cooldown.get(player.getUniqueId()) > 10000) {
            long interval = 1 * 20;
            BukkitTask task = new BukkitRunnable() {

                @Override
                public void run() {
                    double location1X = player.getLocation().getX();
                    double location1Y = player.getLocation().getY();
                    double location1Z = player.getLocation().getZ();
                    double PlayerHealth1 = player.getHealth();
                    for (int i=0; i<5; i++) {
                        double location2X = player.getLocation().getX();
                        double location2Y = player.getLocation().getY();
                        double location2Z = player.getLocation().getZ();
                        double PlayerHealth2 = player.getHealth();
                        sender.sendMessage("§eTeleporting in "+ (5-i) +"...");
                        if (PlayerHealth2 < PlayerHealth1) {
                            sender.sendMessage("§cYou can not use /home while taking damage! Teleport Canceled!!");
                            cooldown.put(player.getUniqueId(), System.currentTimeMillis() - 10000);
                            cancel();
                        }
                        if (location1X != location2X || location1Y != location2Y || location1Z != location2Z) {
                            sender.sendMessage("§cYou can not use /home while moving! Teleport Canceled!!");
                            cooldown.put(player.getUniqueId(), System.currentTimeMillis() - 10000);
                            cancel();
                        }
                    }
                    player.teleport(bedSpawn);
                    sender.sendMessage("§AWelcome Home!");
                    cooldown.put(player.getUniqueId(), System.currentTimeMillis());
                }

            }.runTaskLater(HomePlugin2.getInstance(), interval);
        }
        else{
                sender.sendMessage("§eYou can't teleport home for another " + (10 - ((System.currentTimeMillis() - cooldown.get(player.getUniqueId())) / 1000)) + " seconds!");
                return false;
            }
            return true;
        }
    }