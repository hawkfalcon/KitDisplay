package io.snw.kitdisplay;

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
                String signname = plugin.getConfig().getString("names.sign");
                if (!sign.getLine(0).contains(signname) || !player.hasPermission("kitdisplay.use") || sign.getLine(1) == null)
                    return;
                plugin.getKitInventory().createInventory(player, sign.getLine(1));
            }
        }
    }

}
