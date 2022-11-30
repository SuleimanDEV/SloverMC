package br.com.slovermc.ycommons.api.account;

import java.util.ArrayList;
import java.util.List;

public class League {

	private String leagueName;
	private String leagueSymbol;
	private int xp;

	public League(int xp) {
		this.xp = xp;
		this.leagueName = Name();
		this.leagueSymbol = Symbol();
	}

	public int getXp() {
		return this.xp;
	}

	public String getName() {
		return this.leagueName;
	}

	public String getSymbol() {
		return this.leagueSymbol;
	}

	public List<String> getAllLeagues() {
		List<String> list = new ArrayList<>();
		list.add(" §f" + "-" + " " + "UNRANKED");
		list.add(" §a" + "☰" + " " + "PRIMARY");
		list.add(" §e" + "☲" + " " + "ADVANCED");
		list.add(" §1" + "☷" + " " + "EXPERT");
		list.add(" §7" + "✶" + " " + "SILVER");
		list.add(" §6" + "✷" + " " + "GOLD");
		list.add(" §b" + "✦" + " " + "DIAMOND");
		list.add(" §2" + "�?�" + " " + "EMERALD");
		list.add(" §5" + "✯" + " " + "CRYSTAL");
		list.add(" §3" + "�?�" + " " + "SAPPHIRE");
		list.add(" §c" + "✫" + " " + "MASTER");
		list.add(" §4" + "✪" + " " + "LEGENDARY");
		return list;
	}

	public int remainingXp() {
		int pxp = this.xp;
		if (pxp < 1000) {
			return (1000 - pxp);
		} else if (pxp < 2000) {
			return (1000 - pxp);
		} else if (pxp < 3000) {
			return (1000 - pxp);
		} else if (pxp < 4000) {
			return (1000 - pxp);
		} else if (pxp < 5000) {
			return (1000 - pxp);
		} else if (pxp < 6000) {
			return (1000 - pxp);
		} else if (pxp < 7000) {
			return (1000 - pxp);
		} else if (pxp < 8000) {
			return (1000 - pxp);
		} else if (pxp < 9000) {
			return (1000 - pxp);
		} else if (pxp < 10000) {
			return (1000 - pxp);
		} else if (pxp < 11000) {
			return (1000 - pxp);
		} else {
			return 0;
		}
	}

	public String next() {
		if (this.xp < 1000) {
			return "§aPRIMARY";
		} else if (this.xp >= 1000 && this.xp < 2000) {
			return "§eADVANCED";
		} else if (this.xp >= 2000 && this.xp < 3000) {
			return "§1EXPERT";
		} else if (this.xp >= 3000 && this.xp < 4000) {
			return "§7SILVER";
		} else if (this.xp >= 4000 && this.xp < 5000) {
			return "§6GOLD";
		} else if (this.xp >= 5000 && this.xp < 6000) {
			return "§bDIAMOND";
		} else if (this.xp >= 6000 && this.xp < 7000) {
			return "§2EMERALD";
		} else if (this.xp >= 7000 && this.xp < 8000) {
			return "§5CRYSTAL";
		} else if (this.xp >= 8000 && this.xp < 9000) {
			return "§3SAPPHIRE";
		} else if (this.xp >= 9000 && this.xp < 10000) {
			return "§cMASTER";
		} else if (this.xp >= 10000 && this.xp < 11000) {
			return "§4LEGENDARY";
		} else if (this.xp >= 11000) {
			return "§9§lNENHUMA!";
		} else {
			return "NONE";
		}
	}

	private String Symbol() {
		if (this.xp < 1000) {
			return "§f" + "-";
		} else if (this.xp >= 1000 && this.xp < 2000) {
			return "§a" + "☰";
		} else if (this.xp >= 2000 && this.xp < 3000) {
			return "§e" + "☲";
		} else if (this.xp >= 3000 && this.xp < 4000) {
			return "§1" + "☷";
		} else if (this.xp >= 4000 && this.xp < 5000) {
			return "§7" + "✶";
		} else if (this.xp >= 5000 && this.xp < 6000) {
			return "§6" + "✷";
		} else if (this.xp >= 6000 && this.xp < 7000) {
			return "§b" + "✦";
		} else if (this.xp >= 7000 && this.xp < 8000) {
			return "§2" + "�?�";
		} else if (this.xp >= 8000 && this.xp < 9000) {
			return "§5" + "✯";
		} else if (this.xp >= 9000 && this.xp < 10000) {
			return "§3" + "�?�";
		} else if (this.xp >= 10000 && this.xp < 11000) {
			return "§c" + "✫";
		} else if (this.xp >= 11000) {
			return "§4" + "✪";
		} else {
			return "NONE";
		}
	}

	private String Name() {
		if (this.xp < 1000) {
			return "§fUNRANKED";
		} else if (this.xp >= 1000 && this.xp < 2000) {
			return "§aPRIMARY";
		} else if (this.xp >= 2000 && this.xp < 3000) {
			return "§eADVANCED";
		} else if (this.xp >= 3000 && this.xp < 4000) {
			return "§1EXPERT";
		} else if (this.xp >= 4000 && this.xp < 5000) {
			return "§7SILVER";
		} else if (this.xp >= 5000 && this.xp < 6000) {
			return "§6GOLD";
		} else if (this.xp >= 6000 && this.xp < 7000) {
			return "§bDIAMOND";
		} else if (this.xp >= 7000 && this.xp < 8000) {
			return "§2EMERALD";
		} else if (this.xp >= 8000 && this.xp < 9000) {
			return "§5CRYSTAL";
		} else if (this.xp >= 9000 && this.xp < 10000) {
			return "§3SAPPHIRE";
		} else if (this.xp >= 10000 && this.xp < 11000) {
			return "§cMASTER";
		} else if (this.xp >= 11000) {
			return "§4LEGENDARY";
		} else {
			return "NONE";
		}
	}
}
