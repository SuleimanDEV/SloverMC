package br.com.slovermc.hg.habilidades;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import br.com.slovermc.hg.api.KitAPI;

public class ArcherAndBoxerKit implements Listener {
	
	@EventHandler
	public void onDamageEntity(EntityDamageByEntityEvent e) { 
		if (!(e.getEntity() instanceof Player)) return;
		if (!(e.getDamager() instanceof Player)) return;
		Player player = (Player) e.getEntity();
		Player target = (Player) e.getDamager();
		if (KitAPI.getInstance().hasKit(target, "Boxer") || KitAPI.getInstance().hasKit2(target, "Boxer")) { 
			e.setDamage(e.getDamage() + 1.0);
		}
		if (KitAPI.getInstance().hasKit(player, "Boxer") || KitAPI.getInstance().hasKit2(player, "Boxer")) {
			if (target.getItemInHand().getType() == Material.AIR) { 
				e.setDamage(e.getDamage() - 1.0);
			}
		}
	}
}
