package br.com.slovermc.gladiator.api;

import java.io.File;
import java.io.IOException;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import br.com.slovermc.gladiator.BukkitMain;

public class DeathsAPI {
	
	/**
	 * API old (remade v1)
	 */

	public static File Deathss;
	public static FileConfiguration Deaths;

	public static void createFile() {
		Deathss = new File("plugins/" + BukkitMain.getPlugin().getDataFolder().getName() + "/Status/Deaths.yml");
		Deaths = YamlConfiguration.loadConfiguration(Deathss);
		saveDeaths();

	}

	public static void saveDeaths() {
		try {
			Deaths.save(Deathss);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void newDeaths(Player p) {
		if (!Deaths.contains(p.getName())) {
			Deaths.set(p.getName(), 0);
			saveDeaths();
		}
	}

	public static int getDeaths(Player p) {
		return (int) Deaths.getInt(p.getName());
	}

	public static void addDeaths(Player p, int Qntd) {
		Deaths.set(p.getName(), getDeaths(p) + Qntd);
		saveDeaths();
	}

	public static int getDeaths(OfflinePlayer p) {
		return (int) Deaths.getInt(p.getName());
	}

	public static void addDeaths(OfflinePlayer p, int Qntd) {
		Deaths.set(p.getName(), getDeaths(p) + Qntd);
		saveDeaths();
	}
}