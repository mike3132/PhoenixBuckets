package net.resolutemc.phoenixbuckets.CommandManager;

import net.resolutemc.phoenixbuckets.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TabComplete implements TabCompleter {

    public TabComplete() {
        Main.plugin.getCommand("Bucket").setTabCompleter(this);
    }

    List<String> arguments = new ArrayList<>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (arguments.isEmpty()) {
            arguments.add("Reload");
            arguments.add("List");
            arguments.add("Give");
        }

        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase())) result.add(a);
            }
            return result;
        }
        arguments.clear();

        if (args.length > 1) {
            if (args[1].equalsIgnoreCase("Give")) {
                Player target = Bukkit.getPlayer(args[2]);
                if (target == null) return null;
                arguments.add(target.getName());
                for (String s : arguments) {
                    if (s.toLowerCase().startsWith(args[2].toLowerCase())) result.add(s);
                }
                return result;
            }
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) return null;
            if (args[1].equalsIgnoreCase(target.getName())) {
                arguments.add("Water");
                arguments.add("Lava");
                for (String s : arguments) {
                    if (s.toLowerCase().startsWith(args[2].toLowerCase())) result.add(s);
                }
                arguments.clear();
                return result;
            }
            arguments.clear();
        }
        return null;
    }
}
