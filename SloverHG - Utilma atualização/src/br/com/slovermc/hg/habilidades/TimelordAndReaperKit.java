package br.com.slovermc.hg.habilidades;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.api.PlayerAPI;

public class TimelordAndReaperKit implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) { 
		Player player = e.getPlayer();
		if (KitAPI.getInstance().hasKit(player, "Timelord")) { 
			if (player.getItemInHand().getType() == Material.WATCH) { 
				if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) { 
					e.setCancelled(true);
					if (BukkitMain.state == StateEnum.INVINCIBILITY) return;
					if (!KitAPI.getInstance().hasCooldown(player)) { 
						KitAPI.getInstance().addCooldown(player, 25);
						for (Entity entity : e.getPlayer().getNearbyEntities(20.0D, 20.0D, 20.0D)) {
							if (!(entity instanceof Player)) return;
							if (PlayerAPI.getInstance().hasPlayer((Player)entity)) { 
					            ((Player)entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, 255), true);
					            ((Player)entity).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 120, 250), true);
							}
						}
					} else { 
						KitAPI.getInstance().messageCooldown(player);
					}
				}
			}
		}
		if (BukkitMain.getPlugin().duoKit()) { 
			if (KitAPI.getInstance().hasKit2(player, "Timelord")) { 
				if (player.getItemInHand().getType() == Material.WATCH) { 
					if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) { 
						e.setCancelled(true);
						if (BukkitMain.state == StateEnum.INVINCIBILITY) return;
						if (!KitAPI.getInstance().hasCooldown2(player)) { 
							KitAPI.getInstance().addCooldown2(player, 25);
							for (Entity entity : e.getPlayer().getNearbyEntities(20.0D, 20.0D, 20.0D)) {
								if (!(entity instanceof Player)) return;
								if (PlayerAPI.getInstance().hasPlayer((Player)entity)) { 
						            ((Player)entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 120, 255), true);
						            ((Player)entity).addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 120, 250), true);
								}
							}
						} else { 
							KitAPI.getInstance().messageCooldown2(player);
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	private void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		if (!(e.getDamager() instanceof Player)) return;
		Player player = (Player) e.getDamager();
		if (KitAPI.getInstance().hasKit(player, "Reaper") || KitAPI.getInstance().hasKit2(player, "Reaper")) {
			if (new Random().nextInt(100) < 33) {
				((Player)e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 0));
			}
		}
	}
}
