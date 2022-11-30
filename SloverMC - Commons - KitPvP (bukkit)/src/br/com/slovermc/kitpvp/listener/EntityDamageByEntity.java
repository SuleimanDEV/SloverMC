package br.com.slovermc.kitpvp.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import br.com.slovermc.kitpvp.command.essentials.DanoCommand;
import br.com.slovermc.kitpvp.command.essentials.PvPCommand;
import br.com.slovermc.kitpvp.deathevents.PlayerDeathEvents;
import br.com.slovermc.kitpvp.warps.WarpsAPI;
import br.com.slovermc.kitpvp.warps.OneVsOne.X1WarpListener;
import net.minecraft.server.v1_7_R4.EntityPlayer;

public final class EntityDamageByEntity implements Listener {

	public static final double knockbackX = 0.45;
	public static final double knockbackY = 0.45;
	public static final double knockbackZ = 0.45;

	public final void get(EntityPlayer e, double d0, double d1, double d2) {
		e.motX += d0;

		e.motY += d1;
		e.motZ += d2;
		e.al = true;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
		if (!PvPCommand.onPvP || !DanoCommand.onDamage) {
			event.setCancelled(true);
		}
		if (event.getEntity() instanceof Player && (event.getDamager() instanceof Player)) {

			final Player damaged = (Player) event.getEntity();
			final Player damager = (Player) event.getDamager();

			if (WarpsAPI.battlePlayerWarp.get(damaged) == WarpsAPI.Warps.CAPIROTO) {
				PlayerDeathEvents.repairArmor(damaged);
			}

			if (WarpsAPI.battlePlayerWarp.get(damager) == WarpsAPI.Warps.ONEVSONE && !X1WarpListener.playerfigh.containsKey(damager)) {
				event.setCancelled(true);
			}

			if (WarpsAPI.battlePlayerWarp.get(damager) == WarpsAPI.Warps.ONEVSONE && X1WarpListener.playerfigh.containsKey(damager)
					&& X1WarpListener.playerfigh.get(damager) != damaged.getName()) {
				event.setCancelled(true);
			}

			if (WarpsAPI.battlePlayerWarp.get(damaged) == WarpsAPI.Warps.LAVA_CHALLENGE) {
				event.setCancelled(true);
			}
		}
	}
}