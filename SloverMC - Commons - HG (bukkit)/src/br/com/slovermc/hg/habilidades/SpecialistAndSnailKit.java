package br.com.slovermc.hg.habilidades;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import br.com.slovermc.hg.api.KitAPI;

public class SpecialistAndSnailKit implements Listener {
	
	@EventHandler
	private void onEntityDamageByEntitySnail(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		if (!(e.getDamager() instanceof Player)) return;
		Player player = (Player) e.getDamager();
		if (KitAPI.getInstance().hasKit(player, "Snail") || KitAPI.getInstance().hasKit2(player, "Snail")) {
			if (new Random().nextInt(100) < 33) {
				((Player)e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0));
			}
		}
	}
	
	@EventHandler
	private void onPlayerDeathSpecialist(PlayerDeathEvent e) {
		if (!(e.getEntity().getKiller() instanceof Player)) return;
		Player player = e.getEntity().getKiller();	
		if (KitAPI.getInstance().hasKit(player, "Specialist") || KitAPI.getInstance().hasKit2(player, "Specialist")) {
			player.setLevel(player.getLevel() + 1);
		}
	}
	
	@EventHandler
	private void onPlayerInteractSpecialist(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if (KitAPI.getInstance().hasKit(player, "Specialist") || KitAPI.getInstance().hasKit2(player, "Specialist")) {
			if (player.getItemInHand().getType() == Material.BOOK) {
				e.setCancelled(true);
				player.openEnchanting(player.getLocation(), true);
			}
		}
	}
}
