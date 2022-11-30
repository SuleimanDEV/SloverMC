package br.com.slovermc.hg.habilidades;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import com.google.common.collect.Maps;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.api.PlayerAPI;
import br.com.slovermc.hg.utils.SecondsEvent;

public class MadmanAndNinjaKit implements Listener {

	private HashMap<Player, String> ninja = Maps.newHashMap();
	private HashMap<Player, Double> madman = Maps.newHashMap();

	@SuppressWarnings("deprecation")
	@EventHandler
	private void onMoveMadman(SecondsEvent e) {
		if (BukkitMain.state == StateEnum.GAME) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (KitAPI.getInstance().hasKit(player, "Madman") || KitAPI.getInstance().hasKit2(player, "Madman")) {
					for (Entity entitys : player.getNearbyEntities(20.0D, 20.0D, 20.0D)) {
						if (entitys instanceof Player) {
							Player players = (Player)entitys;
							if (PlayerAPI.getInstance().hasPlayer(player)) {
								if (madman.containsKey(players) && madman.get(players) < 3.0D) {
									madman.put(players, madman.get(players) + 0.03D);
								} else {
									if (!madman.containsKey(players)) {
										players.sendMessage("§b§lMADMAN §fVocê está se sentindo tonto.");
									}
									madman.put(players, 0.03D);
								}
							}
						}
					}
				}
				if (madman.containsKey(player)) {
					boolean aremadman = false;
					for (Entity entitys : player.getNearbyEntities(20.0D, 20.0D, 20.0D)) {
						if (entitys instanceof Player) {
							Player madman = (Player)entitys;
							if (PlayerAPI.getInstance().hasPlayer(madman)) {
								if (KitAPI.getInstance().hasKit(madman, "Madman") || KitAPI.getInstance().hasKit2(madman, "Madman")) {
									aremadman = true;
									break;
								}
							}
						}
					}
					if (!aremadman) {
						if (madman.get(player) - 0.8D <= 0.0D) {
							madman.remove(player);
							player.sendMessage("§b§lMADMAN §fVocê está bem agora!");
						} else {
							madman.put(player, madman.get(player) - 0.8D);
						}
					}
				}
			}
		}
	}

	@EventHandler
	private void onEntityDamageMadman(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) e.getEntity();
		if (madman.containsKey(player) && madman.get(player) > 0.05D) {
			e.setDamage(e.getDamage() + e.getDamage() * madman.get(player));
		}
	}

	@EventHandler
	private void onEntityDamageByEntityNinja(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		if (!(e.getDamager() instanceof Player)) return;

		Player player = (Player)e.getEntity();
		Player damager = (Player)e.getDamager();

		if (KitAPI.getInstance().hasKit(damager, "Ninja") || KitAPI.getInstance().hasKit2(damager, "Ninja")) {
			ninja.put(damager, player.getName());
		}
	}

	@EventHandler
	private void onPlayerToggleSneakNinja(PlayerToggleSneakEvent e) {
		Player player = e.getPlayer();

		if (!e.isSneaking()) return;

		if (KitAPI.getInstance().hasKit(player, "Ninja")) {
			if (!KitAPI.getInstance().hasCooldown(player)) {
				if (ninja.containsKey(player)) {
					Player teleport = Bukkit.getPlayerExact(ninja.get(player));

					if (teleport != null) {
						if (teleport.getLocation().distance(player.getLocation()) < 50) {
							player.teleport(teleport);
							player.sendMessage("§b§lNINJA §fTeleportado!");
							KitAPI.getInstance().addCooldown(player, 6);
							ninja.remove(player);
						} else {
							player.sendMessage("§b§lNINJA §fO último jogador está muito longe!");
							ninja.remove(player);
						}
					} else {
						player.sendMessage("§b§lNINJA §fO jogador não foi encontrado!");
						ninja.remove(player);
					}
				}
			} else {
				KitAPI.getInstance().messageCooldown(player);
			}
			return;
		}

		if (BukkitMain.getPlugin().duoKit()) {
			if (KitAPI.getInstance().hasKit2(player, "Ninja")) {
				if (!KitAPI.getInstance().hasCooldown2(player)) {
					if (ninja.containsKey(player)) {
						Player teleport = Bukkit.getPlayerExact(ninja.get(player));
						if (teleport != null) {
							if (teleport.getLocation().distance(player.getLocation()) < 50) {
								player.teleport(teleport);
								player.sendMessage("§b§lNINJA §fTeleportado!");
								KitAPI.getInstance().addCooldown2(player, 6);
								ninja.remove(player);
							} else {
								player.sendMessage("§b§lNINJA §fO último jogador está muito longe!");
								ninja.remove(player);
							}
						} else {
							player.sendMessage("§b§lNINJA §fO jogador não foi encontrado!");
							ninja.remove(player);
						}
					}
				} else {
					KitAPI.getInstance().messageCooldown2(player);
				}
			}
		}
	}
}
