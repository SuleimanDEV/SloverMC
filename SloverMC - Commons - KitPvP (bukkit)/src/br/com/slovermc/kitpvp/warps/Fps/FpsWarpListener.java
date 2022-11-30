package br.com.slovermc.kitpvp.warps.Fps;

import java.util.ArrayList;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.api.WarpAPI;
import br.com.slovermc.kitpvp.command.essentials.DanoCommand;
import br.com.slovermc.kitpvp.coords.LocationsConstructor;
import br.com.slovermc.kitpvp.coords.LocationsFile;
import br.com.slovermc.kitpvp.scoreboard.Score;
import br.com.slovermc.kitpvp.warps.WarpsAPI;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnWarpListener;

public class FpsWarpListener implements Listener {

	public static final int MAX_DISTANCE_FPS_SPAWN = 4;

	public static final ArrayList<Player> onFpsSpawnProtection = new ArrayList<>();

	public static final int getMaxDistanceFpsSpawn() {
		return (LocationsFile.getWarpsFile().get("SpawnProtections." + "FPS" + ".Distance") != null
				? Integer.valueOf(LocationsFile.getWarpsFile().getInt("SpawnProtections." + "FPS" + ".Distance"))
				: MAX_DISTANCE_FPS_SPAWN);
	}
	
	public static final void loadFpsWarpMethods(final Player bp) {
		WarpAPI.setWarp(bp, "FPS");
		if (SpawnWarpListener.onWarpSpawnProtection.contains(bp)) {
			SpawnWarpListener.onWarpSpawnProtection.remove(bp);
		}
		FpsItens.setWarpFpsItensToBattlePlayer(bp);
		FpsItens.newDiamondSwordSharpness();
		WarpsAPI.battlePlayerWarp.put(bp, WarpsAPI.Warps.FPS);
		Score.createScoreFps(bp);
		if (onFpsSpawnProtection.contains(bp)) {
			onFpsSpawnProtection.remove(bp);
		}
		onFpsSpawnProtection.add(bp);
		bp.sendMessage(BukkitMain.getReceivedSpawnProtectionMessage());
		bp.playSound(bp.getLocation(), Sound.ANVIL_LAND, 2.0F, 1.0F);
	}

	@EventHandler
	public final void onDamageInSpawnFps(final EntityDamageEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (!DanoCommand.onDamage) {
			e.setCancelled(true);
		}
		if (e.getEntity() instanceof Player) {
			final Player bp = (Player) e.getEntity();
			if (onFpsSpawnProtection.contains(bp)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public final void DamageCancelledToFpsSpawn(final EntityDamageByEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			final Player battleplayer = (Player) e.getEntity();
			if (onFpsSpawnProtection.contains(battleplayer)) {
				e.setCancelled(true);
			}
		}
		if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			final Player battleplayer = (Player) e.getDamager();
			final Player camper = (Player) e.getEntity();
			if (onFpsSpawnProtection.contains(battleplayer) && !onFpsSpawnProtection.contains(camper)) {
				onFpsSpawnProtection.remove(battleplayer);
				e.setCancelled(false);
				battleplayer.sendMessage(BukkitMain.getLostedSpawnProtectionMessage());
			}
		}
	}

	@EventHandler
	public final void BattlePlayerDistanceFromFpsSpawn(final PlayerMoveEvent e) {
		final Player battleplayer = e.getPlayer();
		if (WarpsAPI.battlePlayerWarp.get(battleplayer) == WarpsAPI.Warps.FPS
				&& onFpsSpawnProtection.contains(battleplayer)) {
			if (battleplayer.getLocation()
					.distance(LocationsConstructor.getWarpLocation(battleplayer, "Fps")) > getMaxDistanceFpsSpawn()) {
				onFpsSpawnProtection.remove(battleplayer);
				battleplayer.sendMessage(BukkitMain.getLostedSpawnProtectionMessage());
			}
		}
	}
}
