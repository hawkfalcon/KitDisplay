package io.snw.kitdisplay;

import com.earth2me.essentials.MetaItemStack;
import com.earth2me.essentials.textreader.IText;
import com.earth2me.essentials.textreader.KeywordReplacer;
import com.earth2me.essentials.textreader.SimpleTextInput;
import net.ess3.api.IEssentials;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class KitUtils {
    KitDisplay plugin;

    public KitUtils(KitDisplay plugin) {
        this.plugin = plugin;
    }

    public List<ItemStack> getExpandedItems(final IEssentials ess, final List<String> items) throws Exception
    {
        List<ItemStack> itemList = new ArrayList<ItemStack>();
        IText input = new SimpleTextInput(items);
        IText output = new KeywordReplacer(input, null, ess);
        final boolean allowUnsafe = ess.getSettings().allowUnsafeEnchantments();
        for (String kitItem : output.getLines())
        {
            final String[] parts = kitItem.split(" +");
            final ItemStack parseStack = ess.getItemDb().get(parts[0], parts.length > 1 ? Integer.parseInt(parts[1]) : 1);

            if (parseStack.getType() == Material.AIR)
            {
                continue;
            }

            final MetaItemStack metaStack = new MetaItemStack(parseStack);

            if (parts.length > 2)
            {
                // We pass a null sender here because kits should not do perm checks
                metaStack.parseStringMeta(null, allowUnsafe, parts, 2, ess);
            }
            itemList.add(metaStack.getItemStack());
        }
        return itemList;
    }
}
