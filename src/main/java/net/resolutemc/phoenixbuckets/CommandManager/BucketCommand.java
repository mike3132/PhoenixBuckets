package net.resolutemc.phoenixbuckets.CommandManager;

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
                sender.sendMessage("No-Permission-Placeholder");
                return false;
            }
            sender.sendMessage(Main.chatColor("&4Phoenix &5Buckets &7> &aPlugin config reloaded in &2" + String.valueOf(System.currentTimeMillis() -1) + "&ams"));
            Main.plugin.reloadConfig();
            return false;
        }
        if (args[0].equalsIgnoreCase("List")) {
            if (!sender.hasPermission("phoenixTools.List")) {
                sender.sendMessage("No-Permission-Placeholder");
                return false;
            }
            sender.sendMessage("Bucket-List-Placeholder");
            return false;
        }
        if (!sender.hasPermission("phoenixTools.Give")) {
            sender.sendMessage("No-Permission-Placeholder");
            return false;
        }
        if (!args[0].equalsIgnoreCase("Give")) {
            sender.sendMessage("Not-Give-Placeholder");
            return false;
        }
        if (args.length < 2) {
            sender.sendMessage("Not-Player-Placeholder");
            return false;
        }
        Player target = Bukkit.getPlayer(args[1]);
        if (target == null) {
            sender.sendMessage("Player-Not-Found-Placeholder");
            return false;
        }
        if (args.length < 3) {
            sender.sendMessage("Not-Bucket-Placeholder");
            return false;
        }
        ItemStack item = BucketFactory.getItem(args[2]);
        if (item == null) {
            sender.sendMessage("Tool-Not-Found-Placeholder");
            return false;
        }
        int amount = 1;
        if (args.length >= 4) {
            amount = Integer.parseInt(args[3]);
        }
        for (int i = 0; i < amount; i++ ) {
            if (target.getInventory().firstEmpty() == -1) {
                sender.sendMessage("Player-Inventory-Full-Placeholder");
                target.getLocation().getWorld().dropItem(target.getLocation(), item);
                return false;
            }
            sender.sendMessage("Player-Give-Bucket-Placeholder");
            target.getInventory().addItem(item);
        }


        return true;
    }
}
