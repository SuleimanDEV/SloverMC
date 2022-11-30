package br.com.slovermc.hg.habilidades;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerFishEvent;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.KitAPI;

public class FiremanAndFisherman implements Listener {
	
	@EventHandler
	private void onPlayerFireman(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		Player player = (Player)e.getEntity();
		if (KitAPI.getInstance().hasKit(player, "Fireman") || KitAPI.getInstance().hasKit2(player, "Fireman")) {
			if (e.getCause() == DamageCause.FIRE || e.getCause() == DamageCause.FIRE_TICK || e.getCause() == DamageCause.LAVA) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	private void onPlayerFisherman(PlayerFishEvent e) {
		Player player = e.getPlayer();
		if (KitAPI.getInstance().hasKit(player, "Fisherman") || KitAPI.getInstance().hasKit2(player, "Fisherman")) {
			if (BukkitMain.state != StateEnum.GAME) return;
			if (e.getCaught() instanceof Player) {
				Player player_teleported = (Player)e.getCaught();
				player_teleported.teleport(player);
			}
		}
	}
}
