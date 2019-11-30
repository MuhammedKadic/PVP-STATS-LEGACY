package me.kerb.pvpstats.configuration;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class FileConfig {

	private File file;
	private FileConfiguration config;

	public FileConfig(Plugin plugin, String fileName) {
		this.file = new File(plugin.getDataFolder(), fileName);

		if (!this.file.exists()) {
			this.file.getParentFile().mkdirs();

			if (plugin.getResource(fileName) == null) {
				try {
					this.file.createNewFile();
				} catch (IOException e) {
					plugin.getLogger().severe("Failed to create new file " + fileName);
				}
			} else {
				plugin.saveResource(fileName, false);
			}
		}

		this.config = YamlConfiguration.loadConfiguration(this.file);
	}

	public boolean contains(String path) {
		return this.config.contains(path);
	}

	public void set(String path, Object object) {
		this.config.set(path, object);
	}

	public String getString(String path) {
		if (this.config.contains(path)) {
			return ChatColor.translateAlternateColorCodes('&', this.config.getString(path));
		}

		return null;
	}

	public List<String> getStringList(String path) {
		if (this.contains(path)) {
			return this.config.getStringList(path);
		}

		return Collections.emptyList();
	}

	public boolean getBoolean(String path) {
		return this.config.getBoolean(path);
	}

	public long getLong(String path) {
		return this.config.getLong(path);
	}

	public void save() {
		try {
			this.config.save(this.file);
		} catch (IOException e) {
			Bukkit.getLogger().severe("Could not save config file " + this.file.toString());
			e.printStackTrace();
		}
	}

}