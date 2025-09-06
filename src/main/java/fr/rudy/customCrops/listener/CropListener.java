package fr.rudy.customCrops.listener;

import fr.rudy.customCrops.util.ItemFactory;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Location;

public class CropListener implements Listener {

    private static final double GOLDEN_DROP_CHANCE = 0.01;

    @EventHandler
    public void onCropBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (!(block.getBlockData() instanceof Ageable ageable)) return;
        if (ageable.getAge() != ageable.getMaximumAge()) return;

        Location dropLoc = block.getLocation().add(0.5, 0.5, 0.5);
        Material type = block.getType();

        switch (type) {
            case WHEAT -> {
                if (Math.random() <= GOLDEN_DROP_CHANCE) {
                    // ▶ Proc gold : on remplace les drops vanilla
                    event.setDropItems(false);
                    // Golden Wheat (custom) + graines (uniquement pour le blé)
                    block.getWorld().dropItem(dropLoc, ItemFactory.createGoldenWheat());
                    block.getWorld().dropItem(dropLoc, new ItemStack(Material.WHEAT_SEEDS, getRandom1to3()));
                }
                // sinon, vanilla
            }

            case POTATOES -> {
                if (Math.random() <= GOLDEN_DROP_CHANCE) {
                    event.setDropItems(false); // remplace vanilla
                    // ▶ Seulement la version gold (pas de patates normales)
                    block.getWorld().dropItem(dropLoc, ItemFactory.createGoldenPotato());
                }
            }

            case CARROTS -> {
                if (Math.random() <= GOLDEN_DROP_CHANCE) {
                    event.setDropItems(false); // remplace vanilla
                    // Golden Carrot VANILLA renommée (color §5)
                    block.getWorld().dropItem(dropLoc, ItemFactory.createGoldenCarrotVanilla());
                }
            }

            case BEETROOTS -> {
                if (Math.random() <= GOLDEN_DROP_CHANCE) {
                    event.setDropItems(false); // remplace vanilla
                    // ▶ Seulement la version gold (pas de graines)
                    block.getWorld().dropItem(dropLoc, ItemFactory.createGoldenBeetroot());
                }
            }

            default -> { /* rien */ }
        }
    }

    // Sweet berries : récolte au clic droit sur buisson mature
    @EventHandler
    public void onBerryHarvest(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Block block = event.getClickedBlock();
        if (block == null || block.getType() != Material.SWEET_BERRY_BUSH) return;
        if (!(block.getBlockData() instanceof Ageable ageable)) return;
        if (ageable.getAge() != ageable.getMaximumAge()) return;

        Location dropLoc = block.getLocation().add(0.5, 0.5, 0.5);

        if (Math.random() <= GOLDEN_DROP_CHANCE) {
            // ▶ Proc gold : on remplace la récolte vanilla
            event.setCancelled(true);
            // comportement vanilla : on réduit l'âge du buisson pour laisser quelques baies
            ageable.setAge(Math.max(1, ageable.getMaximumAge() - 1));
            block.setBlockData(ageable, true);

            // ▶ Seulement la version gold (pas de baies normales)
            block.getWorld().dropItem(dropLoc, ItemFactory.createGoldenBerry());
        }
        // sinon: laisser vanilla
    }

    private int getRandom1to3() {
        return 1 + (int) (Math.random() * 3);
    }
}
