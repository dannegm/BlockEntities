package im.dnn.BlockEntities.Models;

import im.dnn.BlockEntities.Constants.Keys;
import im.dnn.BlockEntities.Utils.Helpers;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlockItem {
    private final Material BLOCK_MATERIAL = Material.LEGACY_STONE;
    private String displayName;
    private List<String> lore;
    private int customModelData;


    public BlockItem (ItemStack itemStack) {
        this.displayName = itemStack.getItemMeta().getDisplayName();
        this.lore = itemStack.getItemMeta().getLore();
        this.customModelData = itemStack.getItemMeta().getCustomModelData();
    }

    public BlockItem (Map<?, ?> configObject) {
        String rawDisplayName = (String) configObject.get(Keys.BLOCK_DISPLAYNAME);
        List<String> rawLore = (List<String>) configObject.get(Keys.BLOCK_LORE);

        this.displayName = Helpers.format(rawDisplayName);
        this.customModelData = (int) configObject.get(Keys.BLOCK_CUSTOMMODELDATA);
        this.lore = new ArrayList<>();

        for (String loreItem : rawLore) {
            this.lore.add(Helpers.format(loreItem));
        }
    }

    public BlockItem (String displayName, List<String> lore, int customModelData) {
        this.displayName = displayName;
        this.lore = lore;
        this.customModelData = customModelData;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public ItemStack getItem(int amount) {
        ItemStack item = new ItemStack(BLOCK_MATERIAL, amount);
        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setDisplayName(this.displayName);
        itemMeta.setLore(this.lore);
        itemMeta.setCustomModelData(this.customModelData);

        item.setItemMeta(itemMeta);

        return item;
    }
}
