package net.resolutemc.phoenixbuckets.CommandManager;

import net.resolutemc.phoenixbuckets.ChatManager.ChatMessages;
import net.resolutemc.phoenixbuckets.GiveManager.BucketFactory;
import net.resolutemc.phoenixbuckets.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BucketCommand implements CommandExecutor {

    public BucketCommand() {
        Main.plugin.getCommand("Bucket").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Not enough command args");
            return false;
        }
        if (args[0].equalsIgnoreCase("Reload")) {
            if (!sender.hasPermission("phoenixBuckets.Reload")) {
                ChatMessages.sendConsoleMessage(sender, "No-Permission-Placeholder");
                return false;
            }
            sender.sendMessage(Main.chatColor("&4Phoenix &5Buckets &7> &aPlugin config reloaded in &2" + String.valueOf(System.currentTimeMillis() -1) + "&ams"));
            Main.plugin.reloadConfig();
            return false;
        }
        if (args[0].equalsIgnoreCase("List")) {
            if (!sender.hasPermission("phoenixTools.List")) {
                ChatMessages.sendConsoleMessage(sender, "No-Permission-Placeholder");
                return false;
            }
            ChatMessages.sendConsoleMessage(sender, "Bucket-List-Placeholder");
            return false;
        }
        if (!sender.hasPermission("phoenixTools.Give")) {
            ChatMessages.sendConsoleMessage(sender, "No-Permission-Placeholder");
            return false;
        }
        if (!args[0].equalsIgnoreCase("Give")) {
            ChatMessages.sendConsoleMessage(sender, "Not-Give-Placeholder");
            return false;
        }
        if (args.length < 2) {
            ChatMessages.sendConsoleMessage(sender, "Player-Not-Exist-Placeholder");
            return false;
        }
        Player target = Bukkit.getPlayer(args[1]);
        if (target == null) {
            ChatMessages.sendConsoleMessage(sender, "Player-not-found-message-placeholder");
            return false;
        }
        if (args.length < 3) {
            ChatMessages.sendConsoleMessage(sender, "Not-Valid-Bucket-Placeholder");
            return false;
        }
        ItemStack item = BucketFactory.getItem(args[2]);
        if (item == null) {
            ChatMessages.sendConsoleMessage(sender, "Not-Valid-Bucket-Placeholder");
            return false;
        }
        int amount = 1;
        if (args.length >= 4) {
            amount = Integer.parseInt(args[3]);
        }
        for (int i = 0; i < amount; i++ ) {
            if (target.getInventory().firstEmpty() == -1) {
                ChatMessages.sendConsoleMessageWithPlayerPlaceholder(sender, "Player-Give-Bucket-Placeholder", target.getName());
                ChatMessages.sendPlayerMessage(target, "Player-Inventory-Full-Placeholder");
                ChatMessages.sendPlayerMessage(target, "Player-Given-Bucket-Placeholder");
                target.getLocation().getWorld().dropItem(target.getLocation(), item);
                return false;
            }
            ChatMessages.sendConsoleMessageWithPlayerPlaceholder(sender, "Player-Give-Bucket-Placeholder", target.getName());
            ChatMessages.sendPlayerMessage(target, "Player-Given-Bucket-Placeholder");
            target.getInventory().addItem(item);
        }


        return true;
    }
}
