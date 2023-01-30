package net.resolutemc.phoenixbuckets.ItemManager;

import net.resolutemc.phoenixbuckets.Main;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class WaterBucket {

    private ItemStack waterBucket;

    public WaterBucket() {
        this.createBucket();
    }

    public ItemStack getWaterBucket() {
        return this.waterBucket;
    }

    private void createBucket() {
        ItemStack item = new ItemStack(Material.WATER_BUCKET, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        for (String realLore : Main.plugin.getConfig().getStringList("Water-Bucket-Lore")) {
            lore.add(Main.chatColor("" + realLore));
        }

        meta.setDisplayName(Main.chatColor(""+ Main.plugin.getConfig().getString("Water-Bucket-Name")));
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 10, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);

        waterBucket = item;
    }
}
