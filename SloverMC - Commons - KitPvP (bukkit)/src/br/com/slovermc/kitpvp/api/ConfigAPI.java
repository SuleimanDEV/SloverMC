package br.com.slovermc.kitpvp.api;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public final class ConfigAPI {

	private static FileConfiguration config_settings;
	private static File config_file;

	public static final String fileName = "config.yml";

	public ConfigAPI(Plugin plugin) {

		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		config_file = new File(plugin.getDataFolder(), fileName);
		if (config_file.exists()) {
			try {
				config_file.createNewFile();
			} catch (IOException e) {
				Bukkit.getConsoleSender().sendMessage("§c§lERRO§f Erro ao criar o arquivo " + fileName);
				e.printStackTrace();
			}
		}
		config_settings = YamlConfiguration.loadConfiguration(config_file);
	}

	public static FileConfiguration getAccountsFile() {
		return config_settings;
	}

	public static void saveDefaultConfig() {
		try {
			config_settings.save(config_file);
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage("§c§lERRO§f Erro ao salvar o arquivo " + fileName);
			e.printStackTrace();
		}
	}
}
