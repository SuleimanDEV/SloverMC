package br.com.slovermc.kitpvp.warps.Spawn;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.WarpAPI;
import br.com.slovermc.kitpvp.command.essentials.DanoCommand;
import br.com.slovermc.kitpvp.command.essentials.FlyCommand;
import br.com.slovermc.kitpvp.coords.LocationsConstructor;
import br.com.slovermc.kitpvp.coords.LocationsFile;
import br.com.slovermc.kitpvp.scoreboard.Score;
import br.com.slovermc.kitpvp.warps.WarpsAPI;
import br.com.slovermc.kitpvp.warps.Fps.FpsWarpListener;

public final class SpawnWarpListener implements Listener {

	public static final int MAX_DISTANCE_WARP_SPAWN = 15;

	public static final ArrayList<Player> onWarpSpawnProtection = new ArrayList<>();

	public static final int getMaxDistanceWarpSpawn() {
		return (LocationsFile.getWarpsFile().get("SpawnProtections." + "SPAWN" + ".Distance") != null
				? Integer.valueOf(LocationsFile.getWarpsFile().getInt("SpawnProtections." + "SPAWN" + ".Distance"))
				: MAX_DISTANCE_WARP_SPAWN);
	}

	@SuppressWarnings("deprecation")
	public static final void loadWarpSpawnMethods(final Player bp) {
		if (FpsWarpListener.onFpsSpawnProtection.contains(bp)) {
			FpsWarpListener.onFpsSpawnProtection.remove(bp);
		}
		WarpAPI.setWarp(bp, "SPAWN");
		bp.setGameMode(GameMode.SURVIVAL);
		bp.setHealth(20);
		SpawnItens.setWarpSpawnItensToBattlePlayer(bp);
		WarpsAPI.battlePlayerWarp.put(bp, WarpsAPI.Warps.SPAWN);
		Score.createScoreSpawn(bp);
		if (onWarpSpawnProtection.contains(bp)) {
			onWarpSpawnProtection.remove(bp);
		}
		onWarpSpawnProtection.add(bp);
		bp.sendMessage(BukkitMain.getReceivedSpawnProtectionMessage());
	}

	/// ABRIR MENUS
	@EventHandler
	public final void onItemSpawnClick(final PlayerInteractEvent e) throws Exception {
		final Player bp = e.getPlayer();
		if (bp.getItemInHand().getType() == Material.SKULL_ITEM
				&& WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.SPAWN) {
			e.setCancelled(true);
			Stats.Status(bp);
		}
		if (bp.getItemInHand().getType() == Material.PAPER
				&& WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.SPAWN && KitAPI.getKit(bp) == "Nenhum") {
			e.setCancelled(true);
			MenusWarpSpawn.openWarpsMenuToBattlePlayer(bp);
		}
		if (bp.getItemInHand().getType() == Material.CHEST
				&& WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.SPAWN) {
			e.setCancelled(true);
			SpawnKitsMenu.oPenKitsMenu(bp);
		}
		if (bp.getItemInHand().getType() == Material.DIAMOND
				&& WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.SPAWN) {
			e.setCancelled(true);
			MenusWarpSpawn.openLojasMenu(bp);
		}
		if (bp.getItemInHand().getType() == Material.ENCHANTED_BOOK
				&& WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.SPAWN) {
			e.setCancelled(true);
			bp.chat("/warp evento");
		}
	}

	@EventHandler
	public final void onDamageInWarpSpawn(final EntityDamageEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (!DanoCommand.onDamage) {
			e.setCancelled(true);
		}
		if (e.getEntity() instanceof Player) {
			final Player bp = (Player) e.getEntity();
			if (onWarpSpawnProtection.contains(bp)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public final void DamageCancelledToWarpSpawn(final EntityDamageByEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			final Player battleplayer = (Player) e.getEntity();
			if (onWarpSpawnProtection.contains(battleplayer)) {
				e.setCancelled(true);
			}
		}
		if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
			final Player battleplayer = (Player) e.getDamager();
			final Player camper = (Player) e.getEntity();
			if (onWarpSpawnProtection.contains(battleplayer) && FlyCommand.hasFly.contains(battleplayer)) {
				e.setCancelled(true);
				return;
			}
			if (onWarpSpawnProtection.contains(battleplayer) && !onWarpSpawnProtection.contains(camper)) {
				onWarpSpawnProtection.remove(battleplayer);
				e.setCancelled(false);
				battleplayer.sendMessage(BukkitMain.getLostedSpawnProtectionMessage());
			}
		}
	}

	@EventHandler
	public final void BattlePlayerDistanceFromFpsSpawn(final PlayerMoveEvent e) {
		final Player battleplayer = e.getPlayer();
		if (WarpsAPI.battlePlayerWarp.get(battleplayer) == WarpsAPI.Warps.SPAWN
				&& onWarpSpawnProtection.contains(battleplayer)) {
			if (battleplayer.getLocation()
					.distance(LocationsConstructor.getBattleWarpLocation("Spawn")) > getMaxDistanceWarpSpawn()
					&& !FlyCommand.hasFly.contains(battleplayer)) {
				onWarpSpawnProtection.remove(battleplayer);
				battleplayer.sendMessage(BukkitMain.getLostedSpawnProtectionMessage());
			} else if (battleplayer.getLocation()
					.distance(LocationsConstructor.getBattleWarpLocation("Spawn")) > 250.0D
					&& FlyCommand.hasFly.contains(battleplayer)) {
				LocationsConstructor.teleportToBattleWarpLocation(battleplayer, "Spawn");
				battleplayer
						.sendMessage("§c§lERRO §fVocê não pode se afastar mais de 250 blocos do spawn no modo vôo§f!");
			}
		}
	}
}