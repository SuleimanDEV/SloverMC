package br.com.slovermc.hg.scoreboard;

import org.bukkit.Bukkit;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.eventos.StatusHG;

public final class AnimatedTasks {

	public static int SPAWN = 1;

	public static String TittleSpawn = "HG-" + StatusHG.getIP();

	public static final void updateAllAnimations() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(BukkitMain.getPlugin(), new Runnable() {
			@Override
			public void run() {
				run_Spawn();
			}			
		}, 0, 2L);
	}

	//public static final void run_Spawn() {
	//	if (SPAWN == 1) {
		//	TittleSpawn = "§f§lHG-" + StatusHG.getIP();
	//	}
		//if (SPAWN == 2) {
		//	TittleSpawn = "§6§lH§f§lG-" + StatusHG.getIP();
		//}
	//	if (SPAWN == 3) {
		//	TittleSpawn = "§e§lH§6§lG§f§l-" + StatusHG.getIP();
		//}
	//	if (SPAWN == 4) {
	//		TittleSpawn = "§e§lHG§6§l-§f§l" + StatusHG.getIP();
	//	}
		//if (SPAWN == 5) {
		//	TittleSpawn = "§e§lHG-§6§l" + StatusHG.getIP();
		//}
	//	if (SPAWN == 6) {
		//	TittleSpawn = "§e§lHG-" + StatusHG.getIP();
	//	}
		//if (SPAWN == 7) {
	//		TittleSpawn = "§e§lHG-§6§l" + StatusHG.getIP();
		//}
	//	if (SPAWN == 8) {
		//	TittleSpawn = "§e§lHG§6§l-§f§l" + StatusHG.getIP();
	//	}
	//	if (SPAWN == 9) {
		//	TittleSpawn = "§e§lH§6§lG§f§l-" + StatusHG.getIP();
	//	}
	//	if (SPAWN == 10) {
	//		TittleSpawn = "§6§lH§f§lG-" + StatusHG.getIP();
	//		SPAWN = 0;
	//	}
	//	SPAWN++;
	//}
//}

public static final void run_Spawn() {
	if (SPAWN == 1) {
		TittleSpawn = "§f§lCOPA HG";
	}
	if (SPAWN == 2) {
		TittleSpawn = "§6§lC§f§lOPA HG";
	}
	if (SPAWN == 3) {
		TittleSpawn = "§e§lC§6§lO§f§lPA HG";
	}
	if (SPAWN == 4) {
		TittleSpawn = "§e§lCO§6§lP§f§lA HG";
	}
	if (SPAWN == 5) {
		TittleSpawn = "§e§lCOP§6§lA§f§l HG";
	}
	if (SPAWN == 6) {
		TittleSpawn = "§e§lCOPA §6§lH§f§lG";
	}
	if (SPAWN == 7) {
		TittleSpawn = "§e§lCOPA H§6§lG";
	}
	if (SPAWN == 8) {
		TittleSpawn = "§e§lCOPA HG";
	}
	if (SPAWN == 9) {
		TittleSpawn = "§e§lCOPA H§6§lG";
	}
	if (SPAWN == 10) {
		TittleSpawn = "§e§lCOPA §6§lH§f§lG";
	}
	if (SPAWN == 11) {
		TittleSpawn = "§e§lCOP§6§lA §f§lHG";
	}
	if (SPAWN == 12) {
		TittleSpawn = "§e§lCO§6§lP§f§lA HG";
	}
	if (SPAWN == 13) {
		TittleSpawn = "§e§lC§6§lO§f§lPA HG";
	}
	if (SPAWN == 14) {
		TittleSpawn = "§6§lC§f§lOPA HG";
		SPAWN = 0;
	}
	SPAWN++;
}
}
