package io.snw.kitdisplay;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCommand implements CommandExecutor {
    public KitDisplay plugin;

    public KitCommand(KitDisplay m) {
        this.plugin = m;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("kitdisplay")) {
                if (args.length == 1) {
                    if (sender.hasPermission("kitdisplay.use")) {
                        plugin.getKitInventory().createInventory((Player)sender, args[0]);
                    } else {
                        sender.sendMessage(ChatColor.RED + "You don't have permission ._.");
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Please use /kitdisplay <kitname>");
                }
            }
        }
        return true;
    }
}
