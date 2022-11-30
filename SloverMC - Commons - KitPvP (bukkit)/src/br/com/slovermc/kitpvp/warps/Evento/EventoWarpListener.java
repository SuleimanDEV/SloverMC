package br.com.slovermc.kitpvp.warps.Evento;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import br.com.slovermc.kitpvp.scoreboard.Score;
import br.com.slovermc.kitpvp.warps.WarpsAPI;
import br.com.slovermc.kitpvp.warps.Fps.FpsWarpListener;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnWarpListener;

public final class EventoWarpListener {

	@SuppressWarnings("deprecation")
	public static final void loadWarpEventoMethods(final Player bp) {
		bp.getInventory().clear();
		bp.getInventory().setArmorContents(null);
		if (FpsWarpListener.onFpsSpawnProtection.contains(bp)) {
			FpsWarpListener.onFpsSpawnProtection.remove(bp);
		}
		if (SpawnWarpListener.onWarpSpawnProtection.contains(bp)) {
			SpawnWarpListener.onWarpSpawnProtection.remove(bp);
		}
		bp.setHealth(20);
		WarpsAPI.battlePlayerWarp.put(bp, WarpsAPI.Warps.EVENTO);
		Score.createScoreEvento(bp);
		if (!EventoAPI.Players.contains(bp.getName())) {
			EventoAPI.Players.add(bp.getName());
		}
		bp.playSound(bp.getLocation(), Sound.ANVIL_LAND, 2.0F, 1.0F);
	}
}
