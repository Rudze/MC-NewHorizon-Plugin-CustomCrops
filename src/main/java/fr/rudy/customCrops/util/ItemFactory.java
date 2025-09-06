package fr.rudy.customCrops.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemFactory {

    public static ItemStack createGoldenWheat() {
        return makeGolden("§5Blé doré", 10025);
    }

    public static ItemStack createGoldenPotato() {
        return makeGolden("§5Pomme de terre dorée", 10026);
    }

    public static ItemStack createGoldenBeetroot() {
        return makeGolden("§5Betterave dorée", 10028);
    }

    public static ItemStack createGoldenBerry() {
        return makeGolden("§5Baies sucrées dorées", 10029);
    }

    // Pour la carotte dorée VANILLA (Material.GOLDEN_CARROT) avec rename
    public static ItemStack createGoldenCarrotVanilla() {
        ItemStack item = new ItemStack(Material.GOLDEN_CARROT);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§5Carotte dorée");
            item.setItemMeta(meta);
        }
        return item;
    }

    // ----- util -----
    private static ItemStack makeGolden(String name, int cmd) {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setCustomModelData(cmd);
            item.setItemMeta(meta);
        }
        return item;
    }
}
