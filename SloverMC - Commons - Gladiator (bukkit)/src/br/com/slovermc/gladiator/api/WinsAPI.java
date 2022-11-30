package br.com.slovermc.gladiator.api;

import java.io.File;
import java.io.IOException;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import br.com.slovermc.gladiator.BukkitMain;

public class WinsAPI {
	
	/**
	 * API old (remade v1)
	 */

	public static File Winss;
	public static FileConfiguration Wins;

	public static void createFile() {
		Winss = new File("plugins/" + BukkitMain.getPlugin().getDataFolder().getName() + "/Status/Wins.yml");
		Wins = YamlConfiguration.loadConfiguration(Winss);
		saveWins();

	}

	public static void saveWins() {
		try {
			Wins.save(Winss);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void newWins(Player p) {
		if (!Wins.contains(p.getName())) {
			Wins.set(p.getName(), 0);
			saveWins();
		}
	}

	public static int getWins(Player p) {
		return (int) Wins.getInt(p.getName());
	}

	public static void addWins(Player p, int Qntd) {
		Wins.set(p.getName(), getWins(p) + Qntd);
		saveWins();
	}

	public static int getWins(OfflinePlayer p) {
		return (int) Wins.getInt(p.getName());
	}

	public static void addWins(OfflinePlayer p, int Qntd) {
		Wins.set(p.getName(), getWins(p) + Qntd);
		saveWins();
	}
}