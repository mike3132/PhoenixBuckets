package net.resolutemc.phoenixbuckets.ItemManager;

import net.resolutemc.phoenixbuckets.Main;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LavaBucket {

    private ItemStack lavaBucket;

    public LavaBucket() {
        this.createBucket();
    }

    public ItemStack getLavaBucket() {
        return this.lavaBucket;
    }

    private void createBucket() {
        ItemStack item = new ItemStack(Material.LAVA_BUCKET, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        for (String realLore : Main.plugin.getConfig().getStringList("Lava-Bucket-Lore")) {
            lore.add(Main.chatColor("" + realLore));
        }

        meta.setDisplayName(Main.chatColor(""+ Main.plugin.getConfig().getString("Lava-Bucket-Name")));
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 10, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        lavaBucket = item;
    }
}
