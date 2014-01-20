package io.snw.kitdisplay;

import com.earth2me.essentials.Kit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class KitInventory implements Listener {
    KitDisplay plugin;

    public KitInventory(KitDisplay plugin) {
        this.plugin = plugin;
    }

    public void createInventory(Player player, String kit) {
        List<String> items = null;
        try {
            items = Kit.getItems(plugin.getIess(), plugin.getIess().getUser(player.getName()), kit, plugin.getIess().getSettings().getKit(kit));
        } catch (Exception e) {
            player.sendMessage("Invalid kit");
        }
        if (items == null) {
            return;
        }
        String prefix = plugin.getConfig().getString("names.prefix", "&7[Kit]");
        Inventory inventory = Bukkit.createInventory(player, (int) Math.ceil(items.size() / 9) * 9, tACC(prefix + "" + getInvName(kit)));
        int slot = 0;
        List<ItemStack> itemstacks = null;
        try {
            itemstacks = plugin.getKitUtils().getExpandedItems(plugin.getIess(), items);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (ItemStack item : itemstacks) {
            inventory.setItem(slot, item);
            slot++;
        }
        player.openInventory(inventory);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().contains(tACC(plugin.getConfig().getString("names.prefix")))) {
            event.setCancelled(true);
        }
    }

    private String getInvName(String kit) {
        return plugin.getConfig().getString("names.inventory", "&6 {kit}").replace("{kit}", kit.substring(0, 1).toUpperCase() + kit.substring(1));
    }

    public String tACC(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
