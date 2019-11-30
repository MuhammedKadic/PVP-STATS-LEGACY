package me.kerb.pvpstats.listener;

import me.kerb.pvpstats.connection.MySQLManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatsDeathKillListener implements Listener {

	/**
	 * @author xboxfly15 Class file created on: 30 Jul 2017 20:18:20
	 **/

	// FUCK YOU
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		ResultSet rs = MySQLManager
				.getResult("SELECT * FROM stats WHERE player_name = '" + e.getEntity().getPlayer().getName() + "'");
		try {
			while (rs.next()) {
				int deaths = rs.getInt("deaths") + 1;
				MySQLManager.update("UPDATE `stats` SET deaths='" + deaths + "' WHERE player_name='"
						+ e.getEntity().getPlayer().getName() + "'");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (e.getEntity().getKiller() instanceof Player) {
			ResultSet rs2 = MySQLManager.getResult("SELECT * FROM stats WHERE player_name = '"
					+ e.getEntity().getKiller().getPlayer().getName() + "'");
			try {
				while (rs2.next()) {
					int kills = rs2.getInt("kills") + 1;
					MySQLManager.update("UPDATE `stats` SET kills='" + kills + "' WHERE player_name='"
							+ e.getEntity().getKiller().getName() + "'");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

}
