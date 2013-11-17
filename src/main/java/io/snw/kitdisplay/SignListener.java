package io.snw.kitdisplay;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignListener implements Listener {

    KitDisplay plugin;

    public SignListener(KitDisplay plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN) {
                Sign sign = (Sign) block.getState();
                String signname = ChatColor.stripColor(tACC(plugin.getConfig().getString("names.prefix", "&7[KitDisplay]")));
                if (!sign.getLine(0).contains(ChatColor.stripColor(signname)) || !player.hasPermission("kitdisplay.use") || sign.getLine(1) == null)
                    return;
                String kit = sign.getLine(1).toLowerCase();
                plugin.getKitInventory().createInventory(player, kit);
            }
        }
    }

    public String tACC(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
