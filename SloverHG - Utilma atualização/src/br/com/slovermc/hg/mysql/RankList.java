package br.com.slovermc.hg.mysql;

import org.bukkit.entity.Player;

import br.com.slovermc.hg.BukkitMain;

public class RankList {
	
	public static String getDisplayNameRank(Player p) {
		int xp = BukkitMain.mysql1.getXp(p);
		if (xp < 0) {
			// unranked //
			return " " + "§7(§f" + "-" + "§7)";
		}
		else if (xp >= 0 && xp <= 4999) {
			// unranked //
			return " " + "§7(§f" + "-" + "§7)";
		}
		else if (xp >= 5000 && xp <= 9999) {
			// primary //
			return " " + "§7(§a" + "☰" + "§7)";
		}
		else if (xp >= 10000 && xp <= 14999) {
			// advanced //
			return " " + "§7(§e" + "☴" + "§7)";
		}
		else if (xp >= 15000 && xp <= 19999) {
			// expert //
			return " " + "§7(§1" + "☷" + "§7)";
		}
		else if (xp >= 20000 && xp <= 29999) {
			// silver //
			return " " + "§7(§7" + "✶" + "§7)";
		}
		else if (xp >= 30000 && xp <= 39999) {
			// gold //
			return " " + "§7(§6" + "✹" + "§7)";
		}
		else if (xp >= 40000 && xp <= 49999) {
			// diamond //
			return " " + "§7(§b" + "✦" + "§7)";
		}
		else if (xp >= 50000 && xp <= 59999) {
			// sapphire //
			return " " + "§7(§2" + "�?�" + "§7)";
		}
		else if (xp >= 60000 && xp <= 69999) {
			// elite //
			return " " + "§7(§5" + "✹" + "§7)";
		}
		else if (xp >= 70000 && xp <= 79999) {
			// master //
			return  " " + "§7(§c" + "�?�" + "§7)";
		}
		else if (xp >= 80000 && xp <= 99999) {
			// legendary //
			return " " + "§7(§4" + "✪" + "§7)";
		}
		else if (xp >= 100000) {
			// xiter //
			return " " + "§7(§8" + "X" + "§7)";
		}
		// xiter //
		return " " + "§7(§8" + "X" + "§7)";
	}
	
	public static String getScoreRank(Player p) {
		int xp = BukkitMain.mysql1.getXp(p);
		if (xp < 0) {
			// unranked //
			return "§f" + "- UNRANKED";
		}
		else if (xp >= 0 && xp <= 4999) {
			// unranked //
			return "§f" + "- UNRANKED";
		}
		else if (xp >= 5000 && xp <= 9999) {
			// primary //
			return "§a" + "☰ PRIMARY";
		}
		else if (xp >= 10000 && xp <= 14999) {
			// advanced //
			return "§e" + "☴ ADVANCED";
		}
		else if (xp >= 15000 && xp <= 19999) {
			// expert //
			return "§1" + "☷ EXPERT";
		}
		else if (xp >= 20000 && xp <= 29999) {
			// silver //
			return "§7" + "✶ SILVER";
		}
		else if (xp >= 30000 && xp <= 39999) {
			// gold //
			return "§6" + "✹ GOLD";
		}
		else if (xp >= 40000 && xp <= 49999) {
			// diamond //
			return "§b" + "✦ DIAMOND";
		}
		else if (xp >= 50000 && xp <= 59999) {
			// sapphire //
			return "§2" + "�?� SAPPHIRE";
		}
		else if (xp >= 60000 && xp <= 69999) {
			// elite //
			return "§5" + "✹ ELITE";
		}
		else if (xp >= 70000 && xp <= 79999) {
			// master //
			return  "§c" + "�?� MASTER";
		}
		else if (xp >= 80000 && xp <= 99999) {
			// legendary //
			return "§4" + "✪ LEGENDARY";
		}
		else if (xp >= 100000) {
			// xiter //
			return "§8" + "X XITER";
		}
		// xiter //
		return "§8" + "X XITER";
	}
}