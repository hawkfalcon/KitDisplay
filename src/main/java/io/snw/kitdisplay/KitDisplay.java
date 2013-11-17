package io.snw.kitdisplay;

import net.ess3.api.IEssentials;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class KitDisplay extends JavaPlugin {

    private KitInventory kitInventory;
    private ItemDb itemDb;
    private IEssentials iess = null;


    public void onEnable() {
        iess = (IEssentials) Bukkit.getPluginManager().getPlugin("Essentials");
        kitInventory = new KitInventory(this);
        itemDb = new ItemDb(iess);
        this.getServer().getPluginManager().registerEvents(new SignListener(this), this);
        this.saveDefaultConfig();
    }

    public KitInventory getKitInventory() {
        return kitInventory;
    }

    public ItemDb getItemDb() {
        return itemDb;
    }

    public IEssentials getIess() {
        return iess;
    }
}
