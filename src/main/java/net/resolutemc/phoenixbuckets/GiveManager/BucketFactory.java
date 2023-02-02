package net.resolutemc.phoenixbuckets.GiveManager;

import net.resolutemc.phoenixbuckets.ItemManager.LavaBucket;
import net.resolutemc.phoenixbuckets.ItemManager.WaterBucket;
import org.bukkit.inventory.ItemStack;

import java.util.Locale;

public class BucketFactory {

    public static ItemStack getItem(String string) {
        switch (string.toUpperCase(Locale.ROOT)) {
            case "LAVA":
                return LavaBucket.getLavaBucket();
            case "WATER":
                return WaterBucket.getWaterBucket();
        }
        return null;
    }
}
