package br.com.slovermc.kitpvp.account;

import java.io.File;
import java.io.IOException;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import br.com.slovermc.kitpvp.BukkitMain;

public final class KsAPI {

	/**
	 * API old (remade v1)
	 */

	public static File Kss;
	public static FileConfiguration Ks;

	public static void createFile() {
		Kss = new File("plugins/" + BukkitMain.getPlugin().getDataFolder().getName() + "/Status/KillStreaktreak.yml");
		Ks = YamlConfiguration.loadConfiguration(Kss);
		saveKillStreak();

	}

	public static void saveKillStreak() {
		try {
			Ks.save(Kss);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void newKillStreak(Player p) {
		if (!Ks.contains(p.getName())) {
			Ks.set(p.getName(), 0);
			saveKillStreak();
		}
	}

	public static int getKillStreak(Player p) {
		return (int) Ks.getInt(p.getName());
	}

	public static void addKillStreak(Player p, int Qntd) {
		Ks.set(p.getName(), getKillStreak(p) + Qntd);
		saveKillStreak();
	}

	public static void removeKillStreak(Player p, int Qntd) {
		Ks.set(p.getName(), getKillStreak(p) - Qntd);
		saveKillStreak();
	}
	
	public static int getKillStreak(OfflinePlayer p) {
		return (int) Ks.getInt(p.getName());
	}

	public static void addKillStreak(OfflinePlayer p, int Qntd) {
		Ks.set(p.getName(), getKillStreak(p) + Qntd);
		saveKillStreak();
	}
	
	public static void removeKillStreak(OfflinePlayer p) {
		Ks.set(p.getName(), 0);
		saveKillStreak();
	}
	
	public static void removeKillStreak(Player p) {
		Ks.set(p.getName(), 0);
		saveKillStreak();
	}

	public static void removeKillStreak(OfflinePlayer p, int Qntd) {
		Ks.set(p.getName(), getKillStreak(p) - Qntd);
		saveKillStreak();
	}
}
