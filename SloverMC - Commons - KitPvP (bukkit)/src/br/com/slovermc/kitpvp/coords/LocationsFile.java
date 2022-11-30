package br.com.slovermc.kitpvp.coords;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public final class LocationsFile {

	private static FileConfiguration player_accounts;
	private static File player_account;

	public static final String fileName = "pvp_warps.yml";

	public static void createLocationsFile(Plugin plugin) {

		if (!plugin.getDataFolder().exists()) {
			plugin.getDataFolder().mkdir();
		}
		player_account = new File(plugin.getDataFolder(), fileName);
		if (player_account.exists()) {
			try {
				player_account.createNewFile();
			} catch (IOException e) {
				Bukkit.getConsoleSender().sendMessage("§c§lERRO§f Erro ao criar o arquivo " + fileName);
				e.printStackTrace();
			}
		}
		player_accounts = YamlConfiguration.loadConfiguration(player_account);
	}

	public static FileConfiguration getWarpsFile() {
		return player_accounts;
	}

	public static void saveWarpsFile() {
		try {
			player_accounts.save(player_account);
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage("§c§lERRO§f Erro ao salvar o arquivo " + fileName);
			e.printStackTrace();
		}
	}
}