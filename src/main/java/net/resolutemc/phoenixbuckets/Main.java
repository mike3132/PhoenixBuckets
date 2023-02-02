package net.resolutemc.phoenixbuckets;

import net.resolutemc.phoenixbuckets.CommandManager.BucketCommand;
import net.resolutemc.phoenixbuckets.CommandManager.TabComplete;
import net.resolutemc.phoenixbuckets.ConfigManager.ConfigCreator;
import net.resolutemc.phoenixbuckets.EventManager.LavaBucketEvent;
import net.resolutemc.phoenixbuckets.EventManager.WaterBucketEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main plugin;

    public static String chatColor(String chatColor) {
        return ChatColor.translateAlternateColorCodes('&', chatColor);
    }

    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(chatColor("&a[&cInfinite Buckets&a]" + "&2&lENABLED"));

        // Config loaders
        saveDefaultConfig();
        getConfig();
        ConfigCreator.MESSAGES.create();

        // Command loaders
        registerBucketCommand();

        // Register Tab complete
        registerTabComplete();

        // Event Registers
        Bukkit.getPluginManager().registerEvents(new WaterBucketEvent(),this);
        Bukkit.getPluginManager().registerEvents(new LavaBucketEvent(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(chatColor("&a[&cInfinite Buckets&a]" + "&4&lDISABLED"));
    }

    // Command Register
    public void registerBucketCommand() {
        new BucketCommand();
    }

    // Tab complete Register
    public void registerTabComplete() {
        new TabComplete();
    }

}
