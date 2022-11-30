package br.com.slovermc.gladiator.score;

import org.bukkit.Bukkit;

import br.com.slovermc.gladiator.BukkitMain;

public final class AnimatedTasks {

	public static int Glad = 1;

	public static String TittleGlad = "GLADIATOR";
	
	public static final void updateAllAnimations() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(BukkitMain.getPlugin(), new Runnable() {
			@Override
			public void run() {
				run_Glad();
			}			
		}, 0, 3L);
	}
	
	public static final void run_Glad() {
		if (Glad == 1) {
			TittleGlad = "§f§lGLADIATOR";
		}
		if (Glad == 2) {
			TittleGlad = "§6§lG§f§lLADIATOR";
		}
		if (Glad == 3) {
			TittleGlad = "§e§lG§6§lL§f§lADIATOR";
		}
		if (Glad == 4) {
			TittleGlad = "§e§lGL§6§lA§f§lDIATOR";
		}
		if (Glad == 5) {
			TittleGlad = "§e§lGLA§6§lD§f§lIATOR";
		}
		if (Glad == 6) {
			TittleGlad = "§e§lGLAD§6§lI§f§lATOR";
		}
		if (Glad == 7) {
			TittleGlad = "§e§lGLADI§6§lA§f§lTOR";
		}
		if (Glad == 8) {
			TittleGlad = "§e§lGLADIA§6§lT§f§lOR";
		}
		if (Glad == 9) {
			TittleGlad = "§e§lGLADIAT§6§lO§f§lR";
		}
		if (Glad == 10) {
		    TittleGlad = "§e§lGLADIATO§6§lR";
		}
		if (Glad == 11) {
			TittleGlad = "§e§lGLADIATOR";
		}
		if (Glad == 12) {
			TittleGlad = "§e§lGLADIATO§6§lR";
		}
		if (Glad == 13) {
			TittleGlad = "§e§lGLADIAT§6§lO§f§lR";
		}
		if (Glad == 14) {
			TittleGlad = "§e§lGLADIA§6§lT§f§lOR";
		}
		if (Glad == 15) {
			TittleGlad = "§e§lGLADI§6§lA§f§lTOR";
		}
		if (Glad == 16) {
			TittleGlad = "§e§lGLAD§6§lI§f§lATOR";
		}
		if (Glad == 17) {
			TittleGlad = "§e§lGLA§6§lD§f§lIATOR";
		}
		if (Glad == 18) {
			TittleGlad = "§e§lGL§6§lA§f§lDIATOR";
		}
		if (Glad == 19) {
			TittleGlad = "§e§lG§6§lL§f§lADIATOR";
		}
		if (Glad == 20) {
			TittleGlad = "§6§lG§f§lLADIATOR";
			Glad = 0;
		}
		Glad++;
	}
}