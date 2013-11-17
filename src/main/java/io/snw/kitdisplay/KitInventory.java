package io.snw.kitdisplay;

import com.earth2me.essentials.Kit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class KitInventory {
    KitDisplay plugin;
    private HashMap<String, String> viewing = new HashMap<String, String>();

    public KitInventory(KitDisplay plugin) {
        this.plugin = plugin;
    }

    private void setViewing(String name, String kit) {
        this.viewing.put(name, kit);
    }

    private String getViewing(String name) {
        return this.viewing.get(name);
    }

    public void createInventory(Player player, String kit) {
        this.setViewing(player.getName(), kit);
        Bukkit.getLogger().info(kit);
        List<String> items = null;
        try {
            items = Kit.getItems(plugin.getIess(), plugin.getIess().getUser(player.getName()), kit, plugin.getIess().getSettings().getKit(kit));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String item : items) {
            Bukkit.getLogger().info(item);
        }
            Inventory inventory = Bukkit.createInventory(player, items.size() + (9 - (items.size() % 9)), kit);
        int slot = 0;
        List<ItemStack> itemstacks = null;
        try {
            plugin.getKitUtils().getExpandedItems(plugin.getIess(), items);
        } catch(Exception e) {
            e.printStackTrace();
        }
        for (ItemStack item : itemstacks) {
            inventory.setItem(slot, item);
        }
        player.openInventory(inventory);
    }


    @EventHandler(priority = EventPriority.MONITOR)
    void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equals(getInvName(this.getViewing(event.getWhoClicked().getName())))) {
            event.setCancelled(true);
        }
    }

    private String getInvName(String kit) {
        return plugin.getConfig().getString("names.inventory").replace("{kit}", kit);
    }

    public String tACC(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
