package eu.baumistlustig.levelsystem;

import eu.baumistlustig.levelsystem.events.messageEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Levelsystem extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new messageEvents(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
