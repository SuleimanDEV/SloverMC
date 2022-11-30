package br.com.slovermc.kitpvp.kits;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.api.KitAPI;
import br.com.slovermc.kitpvp.api.TittleAPI;
import br.com.slovermc.kitpvp.warps.Spawn.SpawnWarpListener;

public final class Magma implements Listener {

	public static final void setMagmaKit(final Player bp) {
		PvP.defaultItens(bp);
		KitAPI.setKit(bp, "Magma");
		bp.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
		bp.sendMessage("§3§lKIT§f Você selecionou o kit §b§lMagma");
		TittleAPI.sendTittle(bp, "§bKit Magma");
		TittleAPI.sendSubTittle(bp, "§fescolhido com sucesso!");
		bp.playSound(bp.getLocation(), Sound.LEVEL_UP,  2.0F, 1.0F);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public final void onEntityDamage(final EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (KitAPI.getKit(p) == "Magma") {
				if (e.getCause() == DamageCause.LAVA || e.getCause() == DamageCause.FIRE
						|| e.getCause() == DamageCause.FIRE_TICK) {
					e.setCancelled(true);
				}
			}
		}
	}

	public static final void checkIfPlayerOnWater(final Player bp) {
		final Material water = bp.getLocation().getBlock().getType();
		if (water == Material.STATIONARY_WATER || water == Material.WATER) {
			if (KitAPI.getKit(bp) == "Magma") {
				if (!SpawnWarpListener.onWarpSpawnProtection.contains(bp)) {
					bp.damage(6.0);
				}
			}
		}
	}

	public static final void updateMagmaOnWater() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(BukkitMain.getPlugin(), new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				for (Player all : Bukkit.getOnlinePlayers()) {
					checkIfPlayerOnWater(all);
				}
			}
		}, 0, 20L);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onMagmaHit(final EntityDamageByEntityEvent e) {
		if ((!(e.getEntity() instanceof Player)) || (!(e.getDamager() instanceof Player))) {
			return;
		}

		final Player player = (Player) e.getDamager();
		final Player player1 = (Player) e.getEntity();

		if (KitAPI.getKit(player) == "Magma") {
			if (SpawnWarpListener.onWarpSpawnProtection.contains(player1)) {
				return;
			}
			if ((Math.random() > 0.5D) && (Math.random() < 0.4D))
				player1.setFireTicks(150);
		}
	}
}
