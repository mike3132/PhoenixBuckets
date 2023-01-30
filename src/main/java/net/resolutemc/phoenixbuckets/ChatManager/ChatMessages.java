package net.resolutemc.phoenixbuckets.ChatManager;

import net.resolutemc.phoenixbuckets.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class ChatMessages {

    public static void sendConsoleMessage(CommandSender commandSender, String key) {
        File messagesConfig = new File(Main.plugin.getDataFolder(), "messages.yml");
        YamlConfiguration configMessages = YamlConfiguration.loadConfiguration(messagesConfig);
        String message = configMessages.getString("Messages.Prefix") + configMessages.getString("Messages." + key);
        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
    public static void sendPlayerMessage(Player player, String key) {
        File messagesConfig = new File(Main.plugin.getDataFolder(), "messages.yml");
        YamlConfiguration configMessages = YamlConfiguration.loadConfiguration(messagesConfig);
        String message = configMessages.getString("Messages.Prefix") + configMessages.getString("Messages." + key);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}
