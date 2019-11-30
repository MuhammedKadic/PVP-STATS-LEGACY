package me.kerb.pvpstats.listener;

import me.kerb.pvpstats.PvPStats;
import me.kerb.pvpstats.PvPStatsConfiguration;
import me.kerb.pvpstats.connection.MySQLManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JoinAndQuitListener implements Listener{

    private final PvPStats plugin;

    public JoinAndQuitListener(PvPStats plugin){
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent e){
        try {
            ResultSet res = MySQLManager.getResult("SELECT * FROM `stats` WHERE player_name = '" + e.getPlayer().getName() + "';");
            if (res.next()){
                MySQLManager.update("UPDATE `stats` SET `player_name`='" + e.getPlayer().getName() +"',`uuid`='" + e.getPlayer().getUniqueId() +"',`player_ip`='"+e.getPlayer().getAddress() +"' WHERE `player_name`= '"+ e.getPlayer().getName() +"'");
            }else{
                MySQLManager.update("INSERT INTO `stats`(`player_name`, `uuid`, `player_ip`, `deaths`, `kills`) VALUES ('" + e.getPlayer().getName() +"','" + e.getPlayer().getUniqueId() +"','" + e.getPlayer().getAddress()+"',`deaths`='0',`kills`='0')");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }


    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void OnQuit(PlayerQuitEvent e) {
        new BukkitRunnable() {

            @Override
            public void run() {
                try {
                    ResultSet res =  MySQLManager.getResult("SELECT * FROM stats WHERE player_name = '" + e.getPlayer().getName() + "';");
                    if (res.next()){
                        MySQLManager.update("UPDATE `stats` SET `player_name`='"+ e.getPlayer().getName()+"',`uuid`='"+ e.getPlayer().getUniqueId()+"',`player_ip`='" + e.getPlayer().getAddress()+"' WHERE `player_name`= '"+ e.getPlayer().getName() +"'");
                    }else{
                        MySQLManager.update("INSERT INTO `stats`(`player_name`, `uuid`, `player_ip`) VALUES ('" + e.getPlayer().getName() +"','" + e.getPlayer().getUniqueId() +"','" + e.getPlayer().getAddress()+"')");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }


        }.runTaskLater(this.plugin, 20);

    }
}
