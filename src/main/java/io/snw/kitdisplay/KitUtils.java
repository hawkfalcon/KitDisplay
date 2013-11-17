package io.snw.kitdisplay;

import com.earth2me.essentials.CommandSource;
import com.earth2me.essentials.Console;
import com.earth2me.essentials.MetaItemStack;
import com.earth2me.essentials.textreader.IText;
import com.earth2me.essentials.textreader.KeywordReplacer;
import com.earth2me.essentials.textreader.SimpleTextInput;
import net.ess3.api.IEssentials;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class KitUtils {
    KitDisplay plugin;

    public KitUtils(KitDisplay plugin) {
        this.plugin = plugin;
    }

    public List<ItemStack> getExpandedItems(final IEssentials ess, final List<String> items) throws Exception {
        List<ItemStack> itemList = new ArrayList<ItemStack>();
        IText input = new SimpleTextInput(items);
        final CommandSource sender = new CommandSource(Console.getCommandSender(ess.getServer()));
        IText output = new KeywordReplacer(input, sender, ess);
        final boolean allowUnsafe = ess.getSettings().allowUnsafeEnchantments();
        for (String kitItem : output.getLines()) {
            final String[] parts = kitItem.split(" +");
            final ItemStack parseStack = ess.getItemDb().get(parts[0], parts.length > 1 ? Integer.parseInt(parts[1]) : 1);
            if (parseStack.getType() == Material.AIR) {
                continue;
            }
            final MetaItemStack metaStack = new MetaItemStack(parseStack);
            if (parts.length > 2) {
                metaStack.parseStringMeta(null, allowUnsafe, parts, 2, ess);
            }
            itemList.add(metaStack.getItemStack());
        }
        return itemList;
    }
}
