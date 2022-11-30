package br.com.slovermc.kitpvp.listener;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import br.com.slovermc.kitpvp.api.WarpAPI;
import br.com.slovermc.kitpvp.coords.LocationsConstructor;

public final class CompassSystemListener implements Listener {

	@EventHandler
	public void onPlayerInteractEvent(final PlayerInteractEvent e) {
		boolean nullpointer = false;
		if (e.getPlayer().getItemInHand().getType() == Material.COMPASS) {
			e.setCancelled(true);
			if (WarpAPI.getWarp(e.getPlayer()) == "PVP") {
				for (Entity players : e.getPlayer().getNearbyEntities(100, 150, 100)) {
					if ((players instanceof Player) && e.getPlayer().getLocation().distance(players.getLocation()) >= 10) {
						if (players.getLocation().getY() > 170) {
							return;
						}
						nullpointer = true;
						e.getPlayer().setCompassTarget(players.getLocation());
						e.getPlayer().sendMessage("§3§lBUSSOLA§f Apontando para §b" + ((Player) players).getName() + "§f.");
						return;
					}
				}
				if (!nullpointer) {
					e.getPlayer().sendMessage("§3§lBUSSOLA§f Nenhum jogador localizado, atualmente apontando para o spawn.");
					e.getPlayer().setCompassTarget(LocationsConstructor.getWarpLocation(e.getPlayer(), "Spawn"));
					return;
				}
			}
		}
	}
}