package br.com.slovermc.kitpvp.account;

import java.io.File;
import java.io.IOException;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import br.com.slovermc.kitpvp.BukkitMain;

public class DeadsAPI {
	
	/**
	 * API old (remade v1)
	 */

	public static File Dead;
	public static FileConfiguration Deads;

	public static void createFile() {
		Dead = new File("plugins/" + BukkitMain.getPlugin().getDataFolder().getName() + "/Status/Deaths.yml");
		Deads = YamlConfiguration.loadConfiguration(Dead);
		saveDead();

	}

	public static void saveDead() {
		try {
			Deads.save(Dead);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void newDead(Player p) {
		if (!Deads.contains(p.getName())) {
			Deads.set(p.getName(), 0);
			saveDead();
		}
	}

	public static int getDead(Player p) {
		return (int) Deads.getInt(p.getName());
	}

	public static void addDead(Player p, int Qntd) {
		Deads.set(p.getName(), getDead(p) + Qntd);
		saveDead();
	}

	public static void removeDead(Player p, int Qntd) {
		Deads.set(p.getName(), getDead(p) - Qntd);
		saveDead();
	}
	
	public static int getDead(OfflinePlayer p) {
		return (int) Deads.getInt(p.getName());
	}

	public static void addDead(OfflinePlayer p, int Qntd) {
		Deads.set(p.getName(), getDead(p) + Qntd);
		saveDead();
	}

	public static void removeDead(OfflinePlayer p, int Qntd) {
		Deads.set(p.getName(), getDead(p) - Qntd);
		saveDead();
	}
}