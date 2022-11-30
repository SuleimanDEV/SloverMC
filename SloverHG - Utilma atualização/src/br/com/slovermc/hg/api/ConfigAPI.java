package br.com.slovermc.hg.api;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import br.com.slovermc.hg.BukkitMain;

public class ConfigAPI {

	private static ConfigAPI instance = new ConfigAPI();
	public FileConfiguration status;
	public FileConfiguration bugs;
	
	public static ConfigAPI getInstance() {
		return instance;
	}

	public void setup() {
		File a = new File(BukkitMain.getPlugin().getDataFolder(), "status.yml");
		if (!a.exists()) {
			try {
				a.createNewFile();
			} catch (Exception e) {
			}
		}
		File b = new File(BukkitMain.getPlugin().getDataFolder(), "bugs.yml");
		if (!b.exists()) {
			try {
				b.createNewFile();
			} catch (Exception e) {
			}
		}
		status = YamlConfiguration.loadConfiguration(a);
		bugs = YamlConfiguration.loadConfiguration(b);
	}

	public void reload(String file, FileConfiguration fileconfiguration) {
		File sfile = new File(BukkitMain.getPlugin().getDataFolder(), file + ".yml");
		fileconfiguration = YamlConfiguration.loadConfiguration(sfile);
	}

	public void save(String file, FileConfiguration fileconfiguration) {
		File sfile = new File(BukkitMain.getPlugin().getDataFolder(), file + ".yml");
		try {
			fileconfiguration.save(sfile);
		} catch (IOException e) {
		}
	}

	public FileConfiguration getStats() {
		return status;
	}
	public FileConfiguration getBugs() {
		return bugs;
	}
}
