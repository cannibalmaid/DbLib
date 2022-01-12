package ru.nukkit.dblib.nukkit;


import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class ConfigNukkit extends YamlConfiguration {
    public ConfigNukkit(Plugin plugin){
    }

    //@Path("general.language")
    public String language = "default";

    //@Path("general.save-translation")
    public boolean saveLanguage = false;

    //@Path("general.debug-mode")
    boolean debugMode = false;

    //@Path("DbLib.use-MySQL")
    public boolean dbUseMySQL = false;

    //@Path("SQLite.file-name")
    public String dbFileName = DbLibPlugin.getPlugin().getDataFolder().getParentFile().getParent() + File.separator + "nukkit.db";

    //@Path("MySQL.host")
    public String dbMySqlUrl = "localhost";

    //@Path("MySQL.port")
    public int dbMySqlPort = 3306;

    //@Path("MySQL.database")
    public String dbMySqlDatabase = "db";

    //@Path("MySQL.username")
    public String dbMySqlUsername = "nukkit";

    //@Path("MySQL.password")
    public String dbMySqlPassword = "tikkun";

    //@Path("DbLib.ORMLite.debug")
    public boolean debugLog = false;

    //@Path("DbLib.ORMLite.keep-alive-interval")
    public int ormLiteKeepAlive = 0;

    public String language() {
        return language;
    }

    public boolean saveLanguage() {
        return saveLanguage;
    }

    public boolean debugMode() {
        return debugMode;
    }

    public boolean dbUseMySQL() {
        return dbUseMySQL;
    }

    public String dbFileName() {
        return dbFileName;
    }

    public String dbMySqlUrl() {
        return dbMySqlUrl;
    }

    public int dbMySqlPort() {
        return dbMySqlPort;
    }

    public String dbMySqlDatabase() {
        return dbMySqlDatabase;
    }

    public String dbMySqlUsername() {
        return dbMySqlUsername;
    }

    public String dbMySqlPassword() {
        return dbMySqlPassword;
    }

    public boolean debugLog() {
        return debugLog;
    }

    public int ormLiteKeepAlive() {
        return ormLiteKeepAlive;
    }

    public String getDbUrl() {
        StringBuilder sb = new StringBuilder("jdbc:");
        if (dbUseMySQL()) {
            sb.append("mysql://")
                    .append(dbMySqlUrl())
                    .append(":").append(dbMySqlPort())
                    .append("/").append(dbMySqlDatabase())
                    .append("?useSSL=false");
        } else {
            sb.append("sqlite:")
                    .append(dbFileName());
        }
        return sb.toString();
    }
}
