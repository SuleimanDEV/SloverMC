package br.com.slovermc.kitpvp.deathevents;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import br.com.slovermc.kitpvp.account.AccountAPI;

public final class Status {

	public static final int getXpByXp(final Player bp, final Player killed, final int kills, final int deaths,
			final int streak, final int xp) {
		int result = 0;
		if (xp >= 0 && xp < 100) {
			result = result + 1;
		}
		if (xp >= 100 && xp < 500) {
			result = result + 2;
		}
		if (xp >= 500 && xp < 1000) {
			result = result + 5;
		}
		if (xp >= 1000 && xp < 2000) {
			result = result + 7;
		}
		if (xp >= 2000 && xp < 3000) {
			result = result + 9;
		}
		if (xp >= 3000 && xp < 4000) {
			result = result + 11;
		}
		if (xp >= 5000 && xp < 6000) {
			result = result + 13;
		}
		if (xp >= 6000 && xp < 7000) {
			result = result + 15;
		}
		if (xp >= 7000 && xp < 8000) {
			result = result + 17;
		}
		if (xp >= 8000 && xp < 9000) {
			result = result + 19;
		}
		if (xp >= 10000 && xp < 20000) {
			result = result + 21;
		}
		if (xp >= 20000 && xp < 30000) {
			result = result + 25;
		}
		if (xp >= 30000 && xp < 40000) {
			result = result + 35;
		}
		if (xp >= 40000 && xp < 50000) {
			result = result + 45;
		}
		if (xp >= 50000 && xp < 60000) {
			result = result + 55;
		}
		if (xp >= 60000 && xp < 70000) {
			result = result + 65;
		}
		if (xp >= 70000 && xp < 80000) {
			result = result + 75;
		}
		if (xp >= 80000 && xp < 90000) {
			result = result + 85;
		}
		if (xp >= 90000 && xp < 100000) {
			result = result + 95;
		}
		if (xp >= 100000) {
			result = result + 110;
		}
		if (kills > deaths) {
			if (kills >= 0 && kills < 20) {
				result = result + 1;
			}
			if (kills >= 20 && kills < 30) {
				result = result + 2;
			}
			if (kills >= 30 && kills < 40) {
				result = result + 3;
			}
			if (kills >= 40 && kills < 50) {
				result = result + 4;
			}
			if (kills >= 50 && kills < 60) {
				result = result + 5;
			}
			if (kills >= 60 && kills < 70) {
				result = result + 6;
			}
			if (kills >= 70 && kills < 80) {
				result = result + 7;
			}
			if (kills >= 80 && kills < 90) {
				result = result + 8;
			}
			if (kills >= 90 && kills < 100) {
				result = result + 9;
			}
			if (kills >= 100 && kills < 200) {
				result = result + 10;
			}
			if (kills >= 200 && kills < 300) {
				result = result + 11;
			}
			if (kills >= 300 && kills < 400) {
				result = result + 12;
			}
			if (kills >= 400 && kills < 500) {
				result = result + 13;
			}
			if (kills >= 500 && kills < 600) {
				result = result + 14;
			}
			if (kills >= 600 && kills < 700) {
				result = result + 15;
			}
			if (kills >= 700 && kills < 800) {
				result = result + 16;
			}
			if (kills >= 800 && kills < 900) {
				result = result + 17;
			}
			if (kills >= 900 && kills < 1100) {
				result = result + 18;
			}
			if (kills >= 1100 && kills < 1200) {
				result = result + 19;
			}
			if (kills >= 1200 && kills < 1300) {
				result = result + 20;
			}
			if (kills >= 1300 && kills < 1400) {
				result = result + 21;
			}
			if (kills >= 1400 && kills < 1500) {
				result = result + 22;
			}
			if (kills >= 1500 && kills < 1600) {
				result = result + 23;
			}
			if (kills >= 1600 && kills < 1700) {
				result = result + 24;
			}
			if (kills >= 1700 && kills < 1800) {
				result = result + 25;
			}
			if (kills >= 1800 && kills < 1900) {
				result = result + 26;
			}
			if (kills >= 1900 && kills < 2000) {
				result = result + 27;
			}
			if (kills >= 2000 && kills < 3000) {
				result = result + 28;
			}
			if (kills >= 3000) {
				result = result + 30;
			}
		}
		if (deaths > kills) {
			if (kills >= 0 && kills < 20) {
				result = result + 1;
			}
			if (kills >= 20 && kills < 30) {
				result = result + 1;
			}
			if (kills >= 30 && kills < 40) {
				result = result + 2;
			}
			if (kills >= 40 && kills < 50) {
				result = result + 2;
			}
			if (kills >= 50 && kills < 60) {
				result = result + 3;
			}
			if (kills >= 60 && kills < 70) {
				result = result + 3;
			}
			if (kills >= 70 && kills < 80) {
				result = result + 4;
			}
			if (kills >= 80 && kills < 90) {
				result = result + 4;
			}
			if (kills >= 90 && kills < 100) {
				result = result + 5;
			}
			if (kills >= 100 && kills < 200) {
				result = result + 5;
			}
			if (kills >= 200 && kills < 300) {
				result = result + 6;
			}
			if (kills >= 300 && kills < 400) {
				result = result + 6;
			}
			if (kills >= 400 && kills < 500) {
				result = result + 7;
			}
			if (kills >= 500 && kills < 600) {
				result = result + 7;
			}
			if (kills >= 600 && kills < 700) {
				result = result + 8;
			}
			if (kills >= 700 && kills < 800) {
				result = result + 8;
			}
			if (kills >= 800 && kills < 900) {
				result = result + 9;
			}
			if (kills >= 900 && kills < 1100) {
				result = result + 9;
			}
			if (kills >= 1100 && kills < 1200) {
				result = result + 10;
			}
			if (kills >= 1200 && kills < 1300) {
				result = result + 10;
			}
			if (kills >= 1300 && kills < 1400) {
				result = result + 11;
			}
			if (kills >= 1400 && kills < 1500) {
				result = result + 11;
			}
			if (kills >= 1500 && kills < 1600) {
				result = result + 12;
			}
			if (kills >= 1600 && kills < 1700) {
				result = result + 12;
			}
			if (kills >= 1700 && kills < 1800) {
				result = result + 13;
			}
			if (kills >= 1800 && kills < 1900) {
				result = result + 13;
			}
			if (kills >= 1900 && kills < 2000) {
				result = result + 14;
			}
			if (kills >= 2000 && kills < 3000) {
				result = result + 14;
			}
			if (kills >= 3000) {
				result = result + 15;
			}
		}
		if (deaths == kills) {
			if (kills >= 0 && kills < 20) {
				result = result + 1;
			}
			if (kills >= 20 && kills < 30) {
				result = result + 1;
			}
			if (kills >= 30 && kills < 40) {
				result = result + 2;
			}
			if (kills >= 40 && kills < 50) {
				result = result + 2;
			}
			if (kills >= 50 && kills < 60) {
				result = result + 3;
			}
			if (kills >= 60 && kills < 70) {
				result = result + 3;
			}
			if (kills >= 70 && kills < 80) {
				result = result + 4;
			}
			if (kills >= 80 && kills < 90) {
				result = result + 4;
			}
			if (kills >= 90 && kills < 100) {
				result = result + 5;
			}
			if (kills >= 100 && kills < 200) {
				result = result + 5;
			}
			if (kills >= 200 && kills < 300) {
				result = result + 6;
			}
			if (kills >= 300 && kills < 400) {
				result = result + 6;
			}
			if (kills >= 400 && kills < 500) {
				result = result + 7;
			}
			if (kills >= 500 && kills < 600) {
				result = result + 7;
			}
			if (kills >= 600 && kills < 700) {
				result = result + 8;
			}
			if (kills >= 700 && kills < 800) {
				result = result + 8;
			}
			if (kills >= 800 && kills < 900) {
				result = result + 9;
			}
			if (kills >= 900 && kills < 1100) {
				result = result + 9;
			}
			if (kills >= 1100 && kills < 1200) {
				result = result + 10;
			}
			if (kills >= 1200 && kills < 1300) {
				result = result + 10;
			}
			if (kills >= 1300 && kills < 1400) {
				result = result + 11;
			}
			if (kills >= 1400 && kills < 1500) {
				result = result + 11;
			}
			if (kills >= 1500 && kills < 1600) {
				result = result + 12;
			}
			if (kills >= 1600 && kills < 1700) {
				result = result + 12;
			}
			if (kills >= 1700 && kills < 1800) {
				result = result + 13;
			}
			if (kills >= 1800 && kills < 1900) {
				result = result + 13;
			}
			if (kills >= 1900 && kills < 2000) {
				result = result + 14;
			}
			if (kills >= 2000 && kills < 3000) {
				result = result + 14;
			}
			if (kills >= 3000) {
				result = result + 15;
			}
		}
		if (streak > 9) {
			if (streak >= 10 && streak < 20) {
				result = result + 1;
			}
			if (streak >= 20 && streak < 30) {
				result = result + 2;
			}
			if (streak >= 30 && streak < 40) {
				result = result + 3;
			}
			if (streak >= 40 && streak < 50) {
				result = result + 4;
			}
			if (streak >= 50 && streak < 60) {
				result = result + 5;
			}
			if (streak >= 60 && streak < 70) {
				result = result + 6;
			}
			if (streak >= 70 && streak < 80) {
				result = result + 7;
			}
			if (streak >= 80 && streak < 90) {
				result = result + 8;
			}
			if (streak >= 90 && streak < 100) {
				result = result + 9;
			}
			if (streak >= 100) {
				result = result + 10;
			}
		}
		if (DoubleXp.hasDoubleXp.contains(bp.getName())) {
			result = result * 2;
		}
		return result;
	}
	
	public static final String convert(final Player bp) {
		return (DoubleXp.hasDoubleXp.contains(bp.getName()) ? " §7(doublexp)" : "");
	}
	
	public static final void addMoneyToBattlePlayer(final Player bp, final int money) {
		AccountAPI.addBattlePlayerMoney(bp, money);
		bp.sendMessage("§6§lMONEY§f Você recebeu §6" + money + " §fmoedas§f!");
	}
	
	public static final void removeMoneyFromBattlePlayer(final Player bp, final int money) {
		if (money > AccountAPI.getBattlePlayerMoney(bp)) {
			return;
		}
		AccountAPI.removeBattlePlayerMoney(bp, money);
		bp.sendMessage("§6§lMONEY§f Você perdeu §6" + money + " moedas§f!");
	}
	
	public static final void removeStreakFromBattlePlayer(final Player death, final Player killer) {
		if (AccountAPI.getBattlePlayerKillStreak(death) <= 0) {
			return;
		}
		AnunciarPerdaKs(death, killer);
		AccountAPI.removeBattlePlayerKillStreak(death);
	}
	
	public static final void addStreakToBattlePlayer(final Player bp) {
		AccountAPI.addBattlePlayerKillStreak(bp, 1);
		AnunciarKs(bp);
	}
	
	public static final void AnunciarKs(final Player p) {
		int streak = AccountAPI.getBattlePlayerKillStreak(p);
		int[] values = {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100, 105, 110, 115, 120, 125, 130, 
				135, 140, 145, 150, 155, 160, 165, 170, 175, 180, 185, 190, 195, 200, 205, 210, 215, 220, 225, 230, 235, 240, 245, 250,
				255, 260, 265, 270, 275, 280, 285, 290, 295, 300, 305, 310, 315, 320, 325, 330, 335, 340, 345, 350, 355, 360, 365, 370,
				375, 380, 385, 390, 395, 400, 405, 410, 415, 420, 425, 430, 435, 440, 445, 450, 455, 460, 465, 470, 475, 480, 485, 490,
				495, 500, 505, 510, 515, 520, 525, 530, 535, 540, 545, 550, 555, 560, 565, 570, 575, 580, 585, 590, 595, 600, 605, 610,
				615, 620, 625, 630, 635, 640, 645, 650, 655, 660, 665, 670, 675, 680, 685, 690, 695, 700, 705, 710, 715, 720, 725, 730,
				735, 740, 745, 750, 755, 760, 765, 770, 775, 780, 785, 790, 795, 800, 805, 810, 815, 820, 825, 830, 835, 840, 845, 850,
				855, 860, 865, 870, 875, 880, 885, 890, 895, 900, 905, 910, 915, 920, 925, 930, 935, 940, 945, 950, 955, 960, 965, 970,
				975, 980, 985, 990, 995, 1000};
		for (int anuncio : values) {
			if (streak == anuncio) {
				Bukkit.getServer().broadcastMessage("§4§lKILLSTREAK §e" + p.getName() + "§f conseguiu um killstreak §fde§6 " + streak + "§f.");
				return;
			}
		}
	}
	
	public static final void AnunciarPerdaKs(final Player p1, final Player p2) {
		int streak = AccountAPI.getBattlePlayerKillStreak(p1);
		if (streak >= 5) {
			Bukkit.getServer().broadcastMessage("§4§lKILLSTREAK §e" + p1.getName() + "§fperdeu seu killstreak§f de " + streak + " para §e" + p2.getName() + "§f.");
			return;
		}
	}
}
