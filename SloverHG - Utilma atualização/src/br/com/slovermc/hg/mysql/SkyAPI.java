package br.com.slovermc.hg.mysql;

import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class SkyAPI {
	
	public static MySQL_Settings mysql = new MySQL_Settings();
	
	/**
	 * Check if a UUID has registered on server
	 * @param uuid
	 * @return
	 */
	public static boolean checkRegister(UUID uuid) {
		if (mysql.verifyPlayerRegister(uuid)) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Add XP to an Online Player
	 * @param p
	 * @param xp
	 */
	public static void addXp(Player p, int xp) {
		mysql.addXP(p, xp);
	}
	
	/**
	 * Add XP to an Offline Player
	 * @param p
	 * @param xp
	 */
	public static void addXp(OfflinePlayer p, int xp) {
		mysql.addXP(p, xp);
	}
	
	/**
	 * Removes XP from an Online Player
	 * @param p
	 * @param xp
	 */
	public static void removeXp(Player p, int xp) {
		mysql.removeXP(p, xp);
	}
	
	/**
	 * Removes XP from an Offline Player
	 * @param p
	 * @param xp
	 */
	public static void removeXp(OfflinePlayer p, int xp) {
		mysql.removeXP(p, xp);
	}
	
	/**
	 * Add Money to an Online Player
	 * @param p
	 * @param money
	 */
	public static void addMoney(Player p, int money) {
		mysql.addMoney(p, money);
	}
	
	/**
	 * Add Money to an Offline Player
	 * @param p
	 * @param money
	 */
	public static void addMoney(OfflinePlayer p, int money) {
		mysql.addMoney(p, money);
	}
	
	/**
	 * Removes Money from an Player
	 * @param p
	 * @param money
	 */
	public static void removeMoney(Player p, int money) {
		mysql.removeMoney(p, money);
	}
	
	/**
	 * Removes Money from an Offline Player
	 * @param p
	 * @param money
	 */
	public static void removeMoney(OfflinePlayer p, int money) {
		mysql.removeMoney(p, money);
	}
	
	/**
	 * Get the XP INT from an Online Player
	 * @param p
	 * @return
	 */
	public static int getXp(Player p) {
		return mysql.getXp(p);
	}
	
	/**
	 * Get the XP INT from an Offline Player
	 * @param p
	 * @return
	 */
	public static int getXp(OfflinePlayer p) {
		return mysql.getXp(p);
	}
	
	/**
	 * Get the Money INT from an Online Player
	 * @param p
	 * @return
	 */
	public static int getMoney(Player p) {
		return mysql.getMoney(p);
	}
	
	/**
	 * Get the Money INT from an Offline Player
	 * @param p
	 * @return
	 */
	public static int getMoney(OfflinePlayer p) {
		return mysql.getMoney(p);
	}
}