package br.com.slovermc.hg.habilidades;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.google.common.collect.Maps;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.api.KitAPI;

public class WeakhandAndAjninKit implements Listener {
	
	private HashMap<Player, String> ajnin = Maps.newHashMap();
	
	@EventHandler
	private void onEntityDamageByEntityViper(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player))
			return;
		if (!(e.getDamager() instanceof Player))
			return;
		Player player = (Player) e.getDamager();
		if (KitAPI.getInstance().hasKit(player, "Weakhand") || KitAPI.getInstance().hasKit2(player, "Weakhand")) {
			if (new Random().nextInt(100) < 33) {
				((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 0));
				((Player) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 0));
			}
		}
	}

	@EventHandler
	private void onEntityDamageByEntityNinja(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		if (!(e.getDamager() instanceof Player)) return;
		Player player = (Player) e.getEntity();
		Player damager = (Player) e.getDamager();
		if (KitAPI.getInstance().hasKit(damager, "Ajnin") || KitAPI.getInstance().hasKit2(damager, "Ajnin")) {
			ajnin.put(damager, player.getName());
		}
	}

	@EventHandler
	private void onPlayerToggleSneakAjnin(PlayerToggleSneakEvent e) {
		Player player = e.getPlayer();
		if (!e.isSneaking()) return;
		if (KitAPI.getInstance().hasKit(player, "Ajnin")) {
			if (!KitAPI.getInstance().hasCooldown(player)) {
				if (ajnin.containsKey(player)) {
					Player teleport = Bukkit.getPlayerExact(ajnin.get(player));
					if (teleport != null) {
						if (teleport.getLocation().distance(player.getLocation()) < 50) {
							teleport.teleport(player);
							player.sendMessage("§b§lAJNIN §fTeleportado!");
							KitAPI.getInstance().addCooldown(player, 9);
							ajnin.remove(player);
						} else {
							player.sendMessage("§b§lAJNIN §fO último jogador está muito longe!");
							ajnin.remove(player);
						}
					} else {
						player.sendMessage("§b§lAJNIN §fO jogador não foi encontrado!");
						ajnin.remove(player);
					}
				}
			} else {
				KitAPI.getInstance().messageCooldown(player);
			}
			return;
		}
		if (BukkitMain.getPlugin().duoKit()) {
			if (KitAPI.getInstance().hasKit2(player, "Ajnin")) {
				if (!KitAPI.getInstance().hasCooldown2(player)) {
					if (ajnin.containsKey(player)) {
						Player teleport = Bukkit.getPlayerExact(ajnin.get(player));
						if (teleport != null) {
							if (teleport.getLocation().distance(player.getLocation()) < 50) {
								teleport.teleport(player);
								player.sendMessage("§b§lAJNIN §fTeleportado!");
								KitAPI.getInstance().addCooldown2(player, 9);
								ajnin.remove(player);
							} else {
								player.sendMessage("§b§lAJNIN §fO último jogador está muito longe!");
								ajnin.remove(player);
							}
						} else {
							player.sendMessage("§b§lAJNIN §fO jogador não foi encontrado!");
							ajnin.remove(player);
						}
					}
				} else {
					KitAPI.getInstance().messageCooldown2(player);
				}
			}
		}
	}
}
