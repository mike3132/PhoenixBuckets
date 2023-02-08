package net.resolutemc.phoenixbuckets.EventManager;

import net.resolutemc.phoenixbuckets.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;

public class LavaBucketEvent implements Listener {

    NamespacedKey key = new NamespacedKey(Main.plugin, "Lava-Bucket-Key");
    @EventHandler
    public void onLavaPlace(PlayerBucketEmptyEvent peb) {
        Player player = peb.getPlayer();
        Block block = peb.getBlock();
        int blockX = block.getX();
        int blockY = block.getY();
        int blockZ = block.getZ();

        if (player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
            player.getWorld().getBlockAt(blockX, blockY, blockZ).setType(Material.LAVA);
            peb.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent pie) {
        Player player = pie.getPlayer();
        Block block = pie.getClickedBlock();

        if (!player.getInventory().getItemInMainHand().getType().equals(Material.LAVA_BUCKET)) return;
        if (player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
            if (block == null) return;
            if (block.getType().equals(Material.CAULDRON) || block.getType().equals(Material.LAVA_CAULDRON) ||
            block.getType().equals(Material.WATER_CAULDRON)) {
                block.setType(Material.LAVA_CAULDRON);
                pie.setCancelled(true);
                player.playSound(player.getLocation(), Sound.ITEM_BUCKET_EMPTY_LAVA, 1, 1);
            }
        }
    }
}
