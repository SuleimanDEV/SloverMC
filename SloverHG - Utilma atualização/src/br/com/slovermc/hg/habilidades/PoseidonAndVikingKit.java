package br.com.slovermc.hg.habilidades;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import br.com.slovermc.hg.api.KitAPI;

public class PoseidonAndVikingKit implements Listener {

	@EventHandler
	private void onEntityDamageByEntityViking(EntityDamageByEntityEvent e) {
		if (!(e.getDamager() instanceof Player))
			return;
		Player player = (Player) e.getDamager();
		if (KitAPI.getInstance().hasKit(player, "Viking") || KitAPI.getInstance().hasKit2(player, "Viking")) {
			if (player.getItemInHand().getType().toString().contains("AXE")) {
				e.setDamage(e.getDamage() + 2.0D);
			}
		}
	}

	@EventHandler
	private void onPlayerMoveUnderWaterPoseidon(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if (KitAPI.getInstance().hasKit(player, "Poseidon") || KitAPI.getInstance().hasKit2(player, "Poseidon")) {
			if (player.getLocation().getBlock().getType() == Material.STATIONARY_WATER
					|| player.getLocation().getBlock().getType() == Material.WATER) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20 * 5, 1));
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 5, 1));
			}
		}
	}
}
