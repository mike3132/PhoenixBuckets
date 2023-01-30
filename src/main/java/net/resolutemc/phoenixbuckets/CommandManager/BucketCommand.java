package net.resolutemc.phoenixbuckets.CommandManager;

import net.resolutemc.phoenixbuckets.ChatManager.ChatMessages;
import net.resolutemc.phoenixbuckets.ItemManager.LavaBucket;
import net.resolutemc.phoenixbuckets.ItemManager.WaterBucket;
import net.resolutemc.phoenixbuckets.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BucketCommand implements CommandExecutor {

    public BucketCommand() {
        Main.plugin.getCommand("InfiniteBuckets").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length != 0) {
            if (!sender.hasPermission("InfiniteBuckets.Command")) {
                sender.sendMessage("No-permission-Placeholder");
                return true;
            }
            if (sender.hasPermission("InfiniteBuckets.Admin")) {
                switch (args[0].toUpperCase()) {
                    case "RELOAD":
                        if (!sender.hasPermission("InfiniteBuckets.Reload")) {
                            sender.sendMessage("No-reload-permission");
                        }
                        sender.sendMessage(Main.chatColor("&a[&cInfinite Buckets&a] " + "&6Config reloaded in &2" + String.valueOf(System.currentTimeMillis() - 1 + " &6ms")));
                        Main.plugin.reloadConfig();
                        break;
                    case "HELP":
                        ChatMessages.sendConsoleMessage(sender, "Help-Header");
                        ChatMessages.sendConsoleMessage(sender, "Help-A");
                        ChatMessages.sendConsoleMessage(sender, "Help-B");
                        ChatMessages.sendConsoleMessage(sender, "Help-C");
                        ChatMessages.sendConsoleMessage(sender, "Help-D");
                        ChatMessages.sendConsoleMessage(sender, "Help-E");
                        ChatMessages.sendConsoleMessage(sender, "Help-Footer");
                        break;
                    case "GIVE":
                        if (!sender.hasPermission("InfiniteBuckets.Give")) {
                            ChatMessages.sendConsoleMessage(sender, "No-give-permission");
                        }
                        if (args.length != 1) {
                            Player target = Bukkit.getPlayer(args[1]);
                            if (target != null) {
                                if (args.length > 2) {
                                    if (args[2].equalsIgnoreCase("Water")) {
                                        WaterBucket waterBucketItem = new WaterBucket();
                                        ItemStack waterBucket = waterBucketItem.getWaterBucket();
                                        if (target.getInventory().firstEmpty() == -1) {
                                            ChatMessages.sendPlayerMessage(target, "You-have-been-given-a-water-bucket");
                                            ChatMessages.sendPlayerMessage(target, "Your-inventory-is-full");
                                            Location location = target.getLocation();
                                            location.getWorld().dropItem(location.add(0, 1, 0), waterBucket);
                                            return true;
                                        }
                                        target.getInventory().addItem(waterBucket);
                                        ChatMessages.sendPlayerMessage(target, "You-have-been-given-a-water-bucket");
                                    } else {
                                        if (args[2].equalsIgnoreCase("Lava")) {
                                            LavaBucket lavaBucketItem = new LavaBucket();
                                            ItemStack lavaBucket = lavaBucketItem.getLavaBucket();
                                            if (target.getInventory().firstEmpty() == -1) {
                                                ChatMessages.sendPlayerMessage(target, "You-have-been-given-a-lava-bucket");
                                                ChatMessages.sendPlayerMessage(target, "Your-inventory-is-full");
                                                Location location = target.getLocation();
                                                location.getWorld().dropItem(location.add(0, 1, 0), lavaBucket);
                                                return true;
                                            }
                                            target.getInventory().addItem(lavaBucket);
                                            ChatMessages.sendPlayerMessage(target, "You-have-been-given-a-lava-bucket");
                                        }
                                    }
                                } else {
                                    ChatMessages.sendConsoleMessage(sender, "Please-select-either-water-or-lava");
                                }
                            } else {
                                ChatMessages.sendConsoleMessage(sender, "Player-not-found-message-placeholder");
                            }

                        } else {
                            ChatMessages.sendConsoleMessage(sender, "Please-select-a-player");
                        }
                        break;
                    default:
                        ChatMessages.sendConsoleMessage(sender, "Help-Trigger");
                        break;
                }
            }

        } else {
            ChatMessages.sendConsoleMessage(sender, "Help-Trigger");
        }

        return true;
    }
}
