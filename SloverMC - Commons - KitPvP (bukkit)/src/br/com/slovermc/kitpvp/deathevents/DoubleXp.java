package br.com.slovermc.kitpvp.deathevents;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.account.AccountAPI;

public final class DoubleXp {

	public static final ArrayList<String> hasDoubleXp = new ArrayList<>();
	
	public static final void addPlayerDoubleXp(final Player bp) {
		if (AccountAPI.getBattlePlayerDoubleXp(bp) <= 0) {
			bp.sendMessage("§3§lDOUBLEXP§f Voce não possui doublexp!");
			return;
		}
		if (hasDoubleXp.contains(bp.getName())) {
			bp.sendMessage("§3§lDOUBLEXP§f Você já está usando um doublexp!");
			return;
		}
		AccountAPI.removeBattlePlayerDoubleXp(bp, 1);
		hasDoubleXp.add(bp.getName());
		bp.sendMessage("§3§lDOUBLEXP§f Você agora está usando um doublexp por 30 minutos.");
		Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitMain.getPlugin(), new Runnable() {
			@Override
			public void run() {
				if (hasDoubleXp.contains(bp.getName())) {
					hasDoubleXp.remove(bp.getName());
					if (bp.isOnline()) {
						bp.sendMessage("§3§lDOUBLEXP§f Seu tempo de doublexp se esgotou! Use outro para ativar novamente.");
					}
				}
			}
		}, 36000L);
	}
}
