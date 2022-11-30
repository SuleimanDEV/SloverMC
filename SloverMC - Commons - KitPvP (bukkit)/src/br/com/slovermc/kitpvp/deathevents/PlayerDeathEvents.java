package br.com.slovermc.kitpvp.deathevents;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.account.AccountAPI;
import br.com.slovermc.kitpvp.account.DeadsAPI;
import br.com.slovermc.kitpvp.account.KillsAPI;
import br.com.slovermc.kitpvp.api.CooldownAPI;
import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.WarpAPI;
import br.com.slovermc.kitpvp.combat.CombatSystem;
import br.com.slovermc.kitpvp.command.essentials.BuildCommand;
import br.com.slovermc.kitpvp.coords.LocationsConstructor;
import br.com.slovermc.kitpvp.kits.Gladiator;
import br.com.slovermc.kitpvp.kits.KitResetor;
import br.com.slovermc.kitpvp.mysql.SkyAPI;
import br.com.slovermc.kitpvp.warps.WarpsAPI;
import br.com.slovermc.kitpvp.warps.Capiroto.CapirotoWarpListener;
import br.com.slovermc.kitpvp.warps.Challenge.ChallengeWarpListener;
import br.com.slovermc.kitpvp.warps.Evento.EventoAPI;
import br.com.slovermc.kitpvp.warps.Fps.FpsWarpListener;
import br.com.slovermc.kitpvp.warps.OneVsOne.X1WarpListener;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnWarpListener;

public final class PlayerDeathEvents implements Listener {

	public static final void repairArmor(final Player bp) {
		if (WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.FPS
				|| WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.SPAWN
				|| WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.CAPIROTO) {
			if (bp.getInventory().getHelmet() != null) {
				bp.getInventory().getHelmet().setDurability((short) 0);
			}
			if (bp.getInventory().getChestplate() != null) {
				bp.getInventory().getChestplate().setDurability((short) 0);
			}
			if (bp.getInventory().getLeggings() != null) {
				bp.getInventory().getLeggings().setDurability((short) 0);
			}
			if (bp.getInventory().getBoots() != null) {
				bp.getInventory().getBoots().setDurability((short) 0);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public final void onPlayerDeathEvent(final PlayerDeathEvent e) {
		e.setDeathMessage(null);
		if (e.getEntity() instanceof Player) {

			final Player killed = (Player) e.getEntity();
			if (WarpsAPI.teleporting.contains(killed)) {
				WarpsAPI.teleporting.remove(killed);
			}
			if (e.getEntity().getKiller() instanceof Player
					&& WarpsAPI.battlePlayerWarp.get(killed) != WarpsAPI.Warps.ONEVSONE) {
				final Player killer = (Player) e.getEntity().getKiller();

				repairArmor(killer);
				if (WarpsAPI.battlePlayerWarp.get(killer) == WarpsAPI.Warps.CAPIROTO) {
					killer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 1, (byte) 1));
				}
				if (Gladiator.inFight.containsKey(killed.getName())) {
					final Player glader = Bukkit.getPlayerExact(Gladiator.inFight.get(killed.getName()));
					Gladiator.resetGladiatorByKill(glader, killed);
				}
				killer.sendMessage("§e§lKILL§f Você matou §e" + killed.getName() + "§f.");
				killer.playSound(killer.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0F, 1.0F);
				KillsAPI.addKills(killer, 1);
				Status.addMoneyToBattlePlayer(killer, 100);
				SkyAPI.addXp(killer, 25);
				Status.removeStreakFromBattlePlayer(killed, killer);
				Status.addStreakToBattlePlayer(killer);
				SkyAPI.removeXp(killed, 10);

				killed.sendMessage("§c§lDEATH§f Você morreu para §e" + killer.getName() + "§f.");
				killed.playSound(killed.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0F, 1.0F);
				DeadsAPI.addDead(killed, 1);
				Status.removeMoneyFromBattlePlayer(killed, 10);
			} else if (WarpsAPI.battlePlayerWarp.get(killed) == WarpsAPI.Warps.ONEVSONE
					&& X1WarpListener.playerfigh.containsKey(killed)) {
				e.getDrops().clear();
				Player k = Bukkit.getPlayerExact(X1WarpListener.playerfigh.get(killed));
				X1WarpListener.fighting.remove(k);
				X1WarpListener.fighting.remove(killed);
				X1WarpListener.playerfigh.remove(killed);
				X1WarpListener.playerfigh.remove(k);
				int sopsK = X1WarpListener.itemsInInventory(k.getInventory(),
						new Material[] { Material.MUSHROOM_SOUP });
				X1WarpListener.defaultItens(k);
				LocationsConstructor.teleportToBattleWarpLocation(k, "1v1");
				for (int i = 6; i > 0; i--) {
				}

				if (X1WarpListener.batalhando.containsKey(killed)) {
					X1WarpListener.batalhando.remove(killed);
				}
				if (X1WarpListener.batalhando.containsKey(k)) {
					X1WarpListener.batalhando.remove(k);
				}
				k.sendMessage("§6§l1v1 §fVocê venceu o 1v1 contra§e " + killed.getName() + " §fcom §c" + X1WarpListener.cora(k)
						+ " §fcorações e §a" + sopsK + " §fsopas restantes.");
				k.playSound(k.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0F, 1.0F);

				killed.sendMessage("§6§l1v1 §e" + k.getName() + " §fvenceu o 1v1 com §c" + X1WarpListener.cora(k) + " §fcorações e §a"
						+ sopsK + " §fsopas restantes.");
				killed.playSound(killed.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0F, 1.0F);

				k.sendMessage("§e§lKILL§f Você matou §e" + killed.getName() + "§f.");
				KillsAPI.addKills(k, 1);
				Status.addMoneyToBattlePlayer(k, 100);
				SkyAPI.addMoney(k, 25);
				SkyAPI.removeMoney(killed, 10);
				Status.removeStreakFromBattlePlayer(killed, k);
				Status.addStreakToBattlePlayer(k);

				killed.sendMessage("§c§lDEATH§f Você morreu para §e" + k.getName() + "§f.");
				DeadsAPI.addDead(killed, 1);;
				Status.removeMoneyFromBattlePlayer(killed, 10);

				k.setHealth(20);
			} else {
				if (WarpAPI.getWarp(killed) != "CHALLENGE") {
					e.setDeathMessage(null);
					if (Gladiator.inFight.containsKey(killed.getName())) {
						final Player glader = Bukkit.getPlayerExact(Gladiator.inFight.get(killed.getName()));
						Gladiator.resetGladiatorByKill(glader, killed);
					}
					if (CombatSystem.combat.containsKey(killed.getName())) {
						final Player killer = Bukkit.getPlayer(CombatSystem.combat.get(killed.getName()));

						killer.sendMessage("§e§lKILL§f Você matou §e" + killed.getName() + "§f.");
						killer.playSound(killer.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0F, 1.0F);
						KillsAPI.addKills(killer, 1);
						Status.addMoneyToBattlePlayer(killer, 100);
						SkyAPI.addXp(killer, 25);
						SkyAPI.removeMoney(killed, 10);
						Status.addStreakToBattlePlayer(killer);
					}
					killed.sendMessage("§c§lMORTE§f Você morreu.");
					killed.playSound(killed.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0F, 1.0F);
					DeadsAPI.addDead(killed, 1);
					AccountAPI.removeBattlePlayerKillStreak(killed);
				}
				Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitMain.getPlugin(), new Runnable() {
					@Override
					public void run() {
						if (!e.getDrops().isEmpty()) {
							e.getDrops().clear();
						}
					}
				}, 100L);
			}
			new BukkitRunnable() {
				public void run() {
					e.getEntity().spigot().respawn();
				}
			}.runTask(BukkitMain.getPlugin());
		}
	}

	@EventHandler
	public final void onPlayerRespawnEvent(final PlayerRespawnEvent e) {
		final Player bp = e.getPlayer();
		if (WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.SS) {
			bp.sendMessage("§c§lSCREENSHARE§f Você morreu e renasceu no local §cSCREENSHARE§f.");
			new BukkitRunnable() {
				public void run() {
					LocationsConstructor.teleportToBattleWarpLocation(bp, "Screenshare");
				}
			}.runTask(BukkitMain.getPlugin());
		} else if (WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.SPAWN) {
			bp.sendMessage("§e§lRESPAWN§f Você morreu e renasceu na Warp §eSPAWN§f.");
			SpawnWarpListener.loadWarpSpawnMethods(bp);
			KitAPI.setKit(bp, "Nenhum");
			CooldownAPI.removeCooldown(bp);
			if (CombatSystem.combat.containsKey(bp.getName())) {
				CombatSystem.combat.remove(bp.getName());
			}
			new BukkitRunnable() {
				public void run() {
					LocationsConstructor.teleportToBattleWarpLocation(bp, "Spawn");
				}
			}.runTask(BukkitMain.getPlugin());
		} else if (WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.EVENTO || EventoAPI.Players.contains(bp.getName())) {
			bp.sendMessage("§3§lEVENTO§f Você morreu e foi enviado para o §eSPAWN§f.");
			if (CombatSystem.combat.containsKey(bp.getName())) {
				CombatSystem.combat.remove(bp.getName());
			}
			if (EventoAPI.Players.contains(bp.getName()) && BuildCommand.hasBuild(bp)) {
				BuildCommand.hasBuild.remove(bp);
			}
			for (PotionEffect effect : bp.getActivePotionEffects()) {
				bp.removePotionEffect(effect.getType());
			}
			if (EventoAPI.Players.contains(bp.getName())) {
				EventoAPI.Players.remove(bp.getName());
			}
			WarpsAPI.addPlayerInWarpByArgs(bp, "Spawn");
			KitResetor.resetPlayerKit(bp);
			KitAPI.setKit(bp, "Nenhum");
			CooldownAPI.removeCooldown(bp);
			LocationsConstructor.teleportToBattleWarpLocation(bp, "Spawn");
			WarpsAPI.loadWarpItensFromArgs(bp, "Spawn");
			new BukkitRunnable() {
				public void run() {
					LocationsConstructor.teleportToBattleWarpLocation(bp, "Spawn");
				}
			}.runTask(BukkitMain.getPlugin());
		} else if (WarpsAPI.inFps.contains(bp.getName())) {
			bp.sendMessage("§e§lRESPAWN§f Você morreu e renasceu na Warp §eFPS§f.");
			FpsWarpListener.loadFpsWarpMethods(bp);
			new BukkitRunnable() {
				public void run() {
					LocationsConstructor.teleportToBattleWarpLocation(bp, "Fps");
				}
			}.runTask(BukkitMain.getPlugin());
		} else if (WarpsAPI.inLavaChallenge.contains(bp.getName())) {
			bp.sendMessage("§e§lRESPAWN§f Você morreu e renasceu na Warp §eLava Challenge§f.");
			ChallengeWarpListener.loadWarpChallengeMethods(bp);
			new BukkitRunnable() {
				public void run() {
					LocationsConstructor.teleportToBattleWarpLocation(bp, "LavaChallenge");
				}
			}.runTask(BukkitMain.getPlugin());
		} else if (WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.ONEVSONE) {
			bp.sendMessage("§e§lRESPAWN§f Você morreu e renasceu na Warp §e1v1§f.");
			X1WarpListener.loadWarp1v1Methods(bp);
			new BukkitRunnable() {
				public void run() {
					LocationsConstructor.teleportToBattleWarpLocation(bp, "1v1");
				}
			}.runTask(BukkitMain.getPlugin());
		} else if (WarpsAPI.battlePlayerWarp.get(bp) == WarpsAPI.Warps.CAPIROTO) {
			bp.sendMessage("§e§lRESPAWN§f Você morreu e renasceu na Warp §eCapiroto§f.");
			CapirotoWarpListener.loadCapirotoMethods(bp);
			new BukkitRunnable() {
				public void run() {
					LocationsConstructor.teleportToBattleWarpLocation(bp, "Capiroto");
				}
			}.runTask(BukkitMain.getPlugin());
		}
	}
}
