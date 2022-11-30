package br.com.slovermc.kitpvp.combat;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.warps.WarpsAPI;

public final class CombatSystem implements Listener {

	public static final HashMap<String, String> combat = new HashMap<>();
	public static final HashMap<String, Integer> tasker = new HashMap<>();

	public static final HashMap<String, BukkitTask> tasking = new HashMap<>();

	public static final void addBattlePlayerInCombat(final String hited, final String hiter) {
		combat.put(hited, hiter);
		tasking.put(hited, new BukkitRunnable() {
			@Override
			public void run() {
				if (combat.containsKey(hited)) {
					combat.remove(hited);
				}
			}
		}.runTaskLater(BukkitMain.getPlugin(), 220L));
	}

	public static final void addBattlePlayerInKangarooCombat(final String hited, final String hiter) {
		combat.put(hited, hiter);
		Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitMain.getPlugin(), new Runnable() {
			@Override
			public void run() {
				if (combat.containsKey(hited)) {
					combat.remove(hited);
				}
			}
		}, 100L);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public final void onTeleportMove(final PlayerMoveEvent e) {
		final Player bp = e.getPlayer();
		if (WarpsAPI.teleporting.contains(bp)) {
			WarpsAPI.teleporting.remove(bp);
			WarpsAPI.Queen.get(bp.getName()).cancel();
			WarpsAPI.Queen.remove(bp.getName());
			bp.sendMessage("§9§lTELEPORTE§f Você se moveu, seu teleporte foi cancelado.");
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public final void onCombatManager(final EntityDamageByEntityEvent e) {
		if (e.isCancelled()) {
			return;
		}
		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			final Player hited = (Player) e.getEntity();
			final Player hiter = (Player) e.getDamager();
			if (WarpsAPI.battlePlayerWarp.get(hited) == WarpsAPI.Warps.ONEVSONE) {
				return;
			} else if (combat.containsKey(hited.getName()) && KitAPI.getKit(hited) == "Kangaroo") {
				return;
			} else if (KitAPI.getKit(hited) == "Kangaroo") {
				addBattlePlayerInKangarooCombat(hited.getName(), hiter.getName());
				return;
			} else if (combat.containsKey(hited.getName()) && KitAPI.getKit(hited) != "Kangaroo") {
				if (tasking.containsKey(hited.getName())) {
					tasking.get(hited.getName()).cancel();
				}
			}
			addBattlePlayerInCombat(hited.getName(), hiter.getName());
		}
	}
}
