package ru.nukkit.dblib.nukkit;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import ru.nukkit.dblib.DbLib;
import ru.nukkit.dblib.core.M;

public class DbLibPlugin extends JavaPlugin {


    private static DbLibPlugin plugin;

    public static DbLibPlugin getPlugin() {
        return plugin;
    }

    private ConfigNukkit cfg;

    private boolean debugLog;

    @Override
    public void onLoad() {
        plugin = this;
        this.saveDefaultConfig();
        this.cfg = new ConfigNukkit(this);
//        this.cfg.load();
        M.init("DbLib", cfg.language, cfg.debugMode, cfg.saveLanguage);
        DbLib.init(cfg, this.getDataFolder());
        getLogger().info(ChatColor.translateAlternateColorCodes('&', "&eDbLib " + this.getDescription().getVersion() + " created by fromgate for nukkit.ru"));
    }


}
