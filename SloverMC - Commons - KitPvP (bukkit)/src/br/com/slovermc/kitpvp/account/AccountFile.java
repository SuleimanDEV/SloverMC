package br.com.slovermc.kitpvp.account;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public final class AccountFile {

	private static FileConfiguration player_accounts;
	private static File player_account;

	public static final String fileName = "pvp_accounts.yml";

	public static void createAccountFile(Plugin plugin) {

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

	public static FileConfiguration getAccountsFile() {
		return player_accounts;
	}

	public static void saveAccountsFile() {
		try {
			player_accounts.save(player_account);
		} catch (IOException e) {
			Bukkit.getConsoleSender().sendMessage("§c§lERRO§f Erro ao salvar o arquivo " + fileName);
			e.printStackTrace();
		}
	}
}