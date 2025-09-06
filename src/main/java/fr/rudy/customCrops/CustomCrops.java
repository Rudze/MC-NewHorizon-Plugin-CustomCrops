package fr.rudy.customCrops;

import fr.rudy.customCrops.listener.CropListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomCrops extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new CropListener(), this);
        getLogger().info("CustomCrops activé !");
    }

    @Override
    public void onDisable() {
        getLogger().info("CustomCrops désactivé !");
    }
}
