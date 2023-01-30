package net.resolutemc.phoenixbuckets.EventManager;

import net.resolutemc.phoenixbuckets.ItemManager.LavaBucket;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;

public class LavaBucketEvent implements Listener {


    @EventHandler
    public void onWaterPlace(PlayerBucketEmptyEvent peb) {
        Player player = peb.getPlayer();
        Block block = peb.getBlock();
        int blockX = block.getX();
        int blockY = block.getY();
        int blockZ = block.getZ();
        LavaBucket lavaBucketItem = new LavaBucket();
        ItemStack lavaBucket = lavaBucketItem.getLavaBucket();
        if (player.getInventory().getItemInMainHand().isSimilar(lavaBucket)) {
            player.getWorld().getBlockAt(blockX, blockY, blockZ).setType(Material.LAVA);
            peb.setCancelled(true);
        }
    }
}
