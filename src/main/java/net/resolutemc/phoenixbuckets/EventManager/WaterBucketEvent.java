package net.resolutemc.phoenixbuckets.EventManager;

import net.resolutemc.phoenixbuckets.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.persistence.PersistentDataType;

public class WaterBucketEvent implements Listener {

    NamespacedKey key = new NamespacedKey(Main.plugin, "Water-Bucket-Key");
    @EventHandler
    public void onWaterPlace(PlayerBucketEmptyEvent peb) {
        Player player = peb.getPlayer();
        Block block = peb.getBlock();
        int blockX = block.getX();
        int blockY = block.getY();
        int blockZ = block.getZ();
        if (!player.getInventory().getItemInMainHand().equals(Material.WATER_BUCKET)) return;
        if (player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
            if (block.getBlockData() instanceof Waterlogged) {
                Waterlogged waterlogged = (Waterlogged) block.getBlockData();
                waterlogged.setWaterlogged(true);
                block.setBlockData((BlockData)waterlogged);
            } else {
                player.getWorld().getBlockAt(blockX, blockY, blockZ).setType(Material.WATER);
            }
            peb.setCancelled(true);
        }
    }
}
