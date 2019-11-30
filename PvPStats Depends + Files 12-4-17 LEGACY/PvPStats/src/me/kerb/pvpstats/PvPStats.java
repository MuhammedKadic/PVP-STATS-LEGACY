package me.kerb.pvpstats;

import me.kerb.pvpstats.cmds.StatsCommand;
import me.kerb.pvpstats.configuration.FileConfig;
import me.kerb.pvpstats.connection.MySQLManager;
import me.kerb.pvpstats.listener.JoinAndQuitListener;
import me.kerb.pvpstats.listener.StatsDeathKillListener;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

public class PvPStats extends JavaPlugin {
    public static String mysqlHost;
    public static Integer mysqlPort;
    public static String mysqlUser;
    public static String mysqlDatabase;
    public static String mysqlPassword;
    private static Connection con;
    private PvPStatsConfiguration configuration;
    private MySQLManager mySQL;

    public PvPStats instance;


    public File file;

    public void onEnable() {
        instance = this;
        configuration = new PvPStatsConfiguration(this, new FileConfig(this, "config.yml"));
        Bukkit.getLogger().log(Level.WARNING, ChatColor.YELLOW + "PvPStats has been" + ChatColor.GREEN + "Enabled.");
        mysqlHost = getConfig().getString("mysql.MySQL_Host");
        mysqlPort = 3306;
        mysqlUser = getConfig().getString("mysql.MySQL_User");
        mysqlDatabase = getConfig().getString("mysql.MySQL_Database");
        mysqlPassword = getConfig().getString("mysql.MySQL_Password");
        getCommand("stats").setExecutor(new StatsCommand());
        getServer().getPluginManager().registerEvents(new StatsDeathKillListener(), this);
        getServer().getPluginManager().registerEvents(new JoinAndQuitListener(this), this);
        getServer().getPluginManager().registerEvents(new StatsCommand(), this);



        MySQLManager.connect();
    }

    public void onDisable() {
        MySQLManager.close();
        instance = null;
        Bukkit.getLogger().log(Level.WARNING, ChatColor.YELLOW + "PvPStats has been" + ChatColor.RED + "Disable.");
    }


    public PvPStats getInstance() {
        return instance;
    }

    public PvPStatsConfiguration getConfiguration() {
        return this.configuration;
    }

    public MySQLManager getMySQL() {
        return this.mySQL;
    }
    public static void setCon(Connection connection) {
        try {
            con = connection;
            connection.isValid(5);
        } catch (SQLException ex) {
            PvPStatsConfiguration
                    .CWarning(ex.getSQLState() + " |||| " + ex.getMessage() + " |||| " + ex.getErrorCode());
            PvPStatsConfiguration
                    .CWarning(PvPStatsConfiguration.ConsolePrefix + " Could not connect to MySQL! Disabling plugin");
            Bukkit.getPluginManager().disablePlugin(PvPStatsConfiguration.getPlugin);
        }
    }

    public static Connection getCon() {
        return con;
    }

}
