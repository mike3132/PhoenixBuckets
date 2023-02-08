package net.resolutemc.phoenixbuckets.EventManager;

import net.resolutemc.phoenixbuckets.ChatManager.ChatMessages;
import net.resolutemc.phoenixbuckets.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Levelled;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;

import java.util.logging.Level;

public class WaterBucketEvent implements Listener {

    NamespacedKey key = new NamespacedKey(Main.plugin, "Water-Bucket-Key");
    @EventHandler
    public void onWaterPlace(PlayerBucketEmptyEvent peb) {
        Player player = peb.getPlayer();
        Block block = peb.getBlock();
        int blockX = block.getX();
        int blockY = block.getY();
        int blockZ = block.getZ();
        World world = player.getWorld();

        if (player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
            if (world.getName().equals("world_nether")) {
                ChatMessages.sendPlayerMessage(player, "Player-Use-Blacklisted-World-Placeholder");
                peb.setCancelled(true);
                return;
            }
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

    @EventHandler
    public void onInteract(PlayerInteractEvent pie) {
        Player player = pie.getPlayer();
        Block block = pie.getClickedBlock();

        if (!player.getInventory().getItemInMainHand().getType().equals(Material.WATER_BUCKET)) return;
        if (player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
            if (block == null) return;
            if (block.getType().equals(Material.CAULDRON) || block.getType().equals(Material.WATER_CAULDRON) ||
            block.getType().equals(Material.LAVA_CAULDRON)) {
                block.setType(Material.WATER_CAULDRON);
                Levelled cauldron = (Levelled) block.getBlockData();
                cauldron.setLevel(3);
                block.setBlockData(cauldron);
                pie.setCancelled(true);
                player.playSound(player.getLocation(), Sound.ITEM_BUCKET_EMPTY, 1, 1);
            }
        }
    }
}
