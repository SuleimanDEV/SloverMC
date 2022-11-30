package br.com.slovermc.gladiator.api;

import java.io.File;
import java.io.IOException;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import br.com.slovermc.gladiator.BukkitMain;

public class WsAPI {
	
	/**
	 * API old (remade v1)
	 */

	public static File Wss;
	public static FileConfiguration Ws;

	public static void createFile() {
		Wss = new File("plugins/" + BukkitMain.getPlugin().getDataFolder().getName() + "/Status/Ws.yml");
		Ws = YamlConfiguration.loadConfiguration(Wss);
		saveWs();

	}

	public static void saveWs() {
		try {
			Ws.save(Wss);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void newWs(Player p) {
		if (!Ws.contains(p.getName())) {
			Ws.set(p.getName(), 0);
			saveWs();
		}
	}

	public static int getWs(Player p) {
		return (int) Ws.getInt(p.getName());
	}

	public static void addWs(Player p, int Qntd) {
		Ws.set(p.getName(), getWs(p) + Qntd);
		saveWs();
	}
	
	public static void setWs0(Player p, int Qntd) {
		Ws.set(p.getName(), getWs(p) == 0);
		saveWs();
	}

	public static int getWs(OfflinePlayer p) {
		return (int) Ws.getInt(p.getName());
	}

	public static void addWs(OfflinePlayer p, int Qntd) {
		Ws.set(p.getName(), getWs(p) + Qntd);
		saveWs();
	}
	
	public static void setWs(OfflinePlayer p, int Qntd) {
		Ws.set(p.getName(), getWs(p) == 0);
		saveWs();
	}
}