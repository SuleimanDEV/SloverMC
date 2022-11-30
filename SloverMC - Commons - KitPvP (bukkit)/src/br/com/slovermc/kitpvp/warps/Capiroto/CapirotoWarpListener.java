package br.com.slovermc.kitpvp.warps.Capiroto;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import br.com.slovermc.kitpvp.warps.WarpsAPI;
import br.com.slovermc.kitpvp.warps.Fps.FpsWarpListener;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnWarpListener;

public final class CapirotoWarpListener {

	@SuppressWarnings("deprecation")
	public static final void loadCapirotoMethods(final Player bp) {
		if (FpsWarpListener.onFpsSpawnProtection.contains(bp)) {
			FpsWarpListener.onFpsSpawnProtection.remove(bp);
		}
		if (SpawnWarpListener.onWarpSpawnProtection.contains(bp)) {
			SpawnWarpListener.onWarpSpawnProtection.remove(bp);
		}
		bp.setHealth(20);
		WarpsAPI.battlePlayerWarp.put(bp, WarpsAPI.Warps.CAPIROTO);
		CapirotoItens.onInventoryCapiroto(bp);
		bp.playSound(bp.getLocation(), Sound.ANVIL_LAND, 2.0F, 1.0F);
	}
}
