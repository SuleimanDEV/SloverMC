package br.com.slovermc.kitpvp.account;

import java.io.File;
import java.io.IOException;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import br.com.slovermc.kitpvp.BukkitMain;

public class KillsAPI {
	
	/**
	 * API old (remade v1)
	 */

	public static File Killss;
	public static FileConfiguration Kills;

	public static void createFile() {
		Killss = new File("plugins/" + BukkitMain.getPlugin().getDataFolder().getName() + "/Status/Kills.yml");
		Kills = YamlConfiguration.loadConfiguration(Killss);
		saveKills();

	}

	public static void saveKills() {
		try {
			Kills.save(Killss);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void newKills(Player p) {
		if (!Kills.contains(p.getName())) {
			Kills.set(p.getName(), 0);
			saveKills();
		}
	}

	public static int getKills(Player p) {
		return Kills.getInt(p.getName());
	}

	public static void addKills(Player p, int Qntd) {
		Kills.set(p.getName(), getKills(p) + Qntd);
		saveKills();
	}

	public static void removeKills(Player p, int Qntd) {
		Kills.set(p.getName(), getKills(p) - Qntd);
		saveKills();
	}
	
	public static int getKills(OfflinePlayer p) {
		return Kills.getInt(p.getName());
	}

	public static void addKills(OfflinePlayer p, int Qntd) {
		Kills.set(p.getName(), getKills(p) + Qntd);
		saveKills();
	}

	public static void removeKills(OfflinePlayer p, int Qntd) {
		Kills.set(p.getName(), getKills(p) - Qntd);
		saveKills();
	}
}