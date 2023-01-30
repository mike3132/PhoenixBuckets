package net.resolutemc.phoenixbuckets;

import net.resolutemc.phoenixbuckets.CommandManager.BucketCommand;
import net.resolutemc.phoenixbuckets.CommandManager.TabComplete;
import net.resolutemc.phoenixbuckets.EventManager.LavaBucketEvent;
import net.resolutemc.phoenixbuckets.EventManager.WaterBucketEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

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
        createFile();

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

    // File creation
    private File messages;
    private FileConfiguration config;

    private void createFile() {
        messages = new File(getDataFolder(), "messages.yml");
        if (!messages.exists()) {
            messages.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }
        config = new YamlConfiguration();
        try {
            config.load(messages);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
