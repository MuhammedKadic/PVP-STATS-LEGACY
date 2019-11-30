package me.kerb.pvpstats.connection;

import me.kerb.pvpstats.PvPStats;
import me.kerb.pvpstats.PvPStatsConfiguration;
import org.bukkit.Bukkit;

import java.sql.*;

public class MySQLManager {

	/**
	 * @author xboxfly15 Class file created on: 29 Jul 2017 13:21:51
	 **/

	public static boolean connect() {
		if (isConnected()) {
			close();
		}
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://" + PvPStats.mysqlHost + ":"
					+ PvPStats.mysqlPort + "/" + PvPStats.mysqlDatabase, PvPStats.mysqlUser,
					PvPStats.mysqlPassword);
			PvPStats.setCon(con);
			PvPStatsConfiguration.CInfo(PvPStatsConfiguration.ConsolePrefix + " MySQL successfully connected to the database");
			return true;
		} catch (Exception ex) {
			PvPStatsConfiguration.CWarning(ex.getMessage());
			PvPStatsConfiguration.CWarning("-----[ Stacktrace ]----");
			ex.printStackTrace();
			PvPStatsConfiguration.CWarning("-----------------------");
			PvPStatsConfiguration.CWarning("-----[ MySQL Information ]----");
			PvPStatsConfiguration.CWarning("Host: " + PvPStatsConfiguration.getConfig.getString("mysql.MySQL_Host"));
			PvPStatsConfiguration.CWarning("Port: " + Integer.valueOf(PvPStatsConfiguration.getConfig.getInt("mysql.MySQL_Port")));
			PvPStatsConfiguration.CWarning("User: " + PvPStatsConfiguration.getConfig.getString("mysql.MySQL_User"));
			PvPStatsConfiguration.CWarning("Database: " + PvPStatsConfiguration.getConfig.getString("mysql.MySQL_Database"));
			PvPStatsConfiguration.CWarning("------------------------------");
			PvPStatsConfiguration
					.CWarning(PvPStatsConfiguration.ConsolePrefix + " Could not connect to MySQL! Disabling plugin");
			Bukkit.getPluginManager().disablePlugin(PvPStatsConfiguration.getPlugin);
			return false;
		}
	}

	public static void close() {
		if (isConnected()) {
			try {
				PvPStats.getCon().close();
				PvPStatsConfiguration
				.CInfo(PvPStatsConfiguration.ConsolePrefix + " MySQL successfully disconnected");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}


	public static boolean isConnected() {
		if (PvPStats.getCon() != null) {
			return true;
		}
		return false;
	}

	public static boolean update(String qry) {
		if (isConnected()) {
			try {
				PreparedStatement ps = PvPStats.getCon().prepareStatement(qry);
				ps.executeUpdate();
				return true;
			} catch (SQLException e) {
				return false;
			}
		} else {
			close();
			connect();
			try {
				PreparedStatement ps = PvPStats.getCon().prepareStatement(qry);
				ps.executeUpdate();
				return true;
			} catch (SQLException e) {
				return false;
			}
		}
	}

	public static ResultSet getResult(String qry) {
		if (isConnected()) {
			try {
				PreparedStatement ps = PvPStats.getCon().prepareStatement(qry);
				return ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			close();
			connect();
			try {
				PreparedStatement ps = PvPStats.getCon().prepareStatement(qry);
				return ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
