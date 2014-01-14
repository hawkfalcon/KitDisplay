package io.snw.kitdisplay;

import net.ess3.api.IEssentials;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class KitDisplay extends JavaPlugin {

    private KitInventory kitInventory;
    private KitUtils kitUtils;
    private transient IEssentials ess = null;

    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new SignListener(this), this);
        this.getServer().getPluginManager().registerEvents(new KitInventory(this), this);
        getCommand("kitdisplay").setExecutor(new KitCommand(this));
        this.saveDefaultConfig();
        hookEss();

        kitInventory = new KitInventory(this);
        kitUtils = new KitUtils(this);
    }

    public KitInventory getKitInventory() {
        return kitInventory;
    }

    public void hookEss() {
        final PluginManager pm = this.getServer().getPluginManager();
        final Plugin essPlugin = pm.getPlugin("Essentials");
        if (essPlugin == null || !essPlugin.isEnabled()) {
            this.setEnabled(false);
            return;
        }
        ess = (IEssentials) essPlugin;
    }

    public IEssentials getIess() {
        if (ess == null) {
            hookEss();
        }
        return ess;
    }

    public KitUtils getKitUtils() {
        return kitUtils;
    }
}
