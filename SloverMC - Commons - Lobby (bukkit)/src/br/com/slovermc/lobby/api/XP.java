package br.com.slovermc.lobby.api;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import br.com.slovermc.lobby.BukkitMain;

public class XP {

	/**
	 * API old (remade v1)
	 */

	public static File Xps;
	public static FileConfiguration Xp;

	public static void createFile() {
		Xps = new File("plugins/" + BukkitMain.getPlugin().getDataFolder().getName() + "/Status/Xp.yml");
		Xp = YamlConfiguration.loadConfiguration(Xps);
		saveXp();
	}

	public static void saveXp() {
		try {
			Xp.save(Xps);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static int getXP(Player player) {
		return Xp.getInt(player.getName());
	}

	public static void addXP(Player p) {
		Xp.set(p.getName(), SkyAPI.getXp(p));
		saveXp();
	}

	public static void addXP(Player p, int quantia) {
		Xp.set(p.getName(), SkyAPI.getXp(p) + quantia);
		saveXp();
	}
}