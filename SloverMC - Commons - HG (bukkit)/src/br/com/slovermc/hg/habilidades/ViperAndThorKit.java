package br.com.slovermc.hg.habilidades;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.KitAPI;

public class ViperAndThorKit implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	private void onPlayerInteractThor(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if (player.getItemInHand().getType() == Material.WOOD_AXE) { 
			if (KitAPI.getInstance().hasKit(player, "Thor")) { 
				if (BukkitMain.state != StateEnum.GAME) return;
				if (KitAPI.getInstance().hasCooldown(player) == false) { 
					KitAPI.getInstance().addCooldown(player, 10);
				    Location loc = player.getTargetBlock(null, 40).getLocation();
				    loc = loc.getWorld().getHighestBlockAt(loc).getLocation();
					Bukkit.getWorld("world").strikeLightning(loc);
					Location location = loc.getBlock().getRelative(BlockFace.UP).getLocation();
					location.getBlock().setType(Material.FIRE);
				} else { 
					KitAPI.getInstance().messageCooldown(player);
				}
			}
		}
		if (BukkitMain.getPlugin().duoKit()) { 
			if (player.getItemInHand().getType() == Material.WOOD_AXE) { 
				if (KitAPI.getInstance().hasKit(player, "Thor")) { 
					if (BukkitMain.state != StateEnum.GAME) return;
					if (KitAPI.getInstance().hasCooldown(player) == false) { 
						KitAPI.getInstance().addCooldown(player, 10);
					    Location loc = player.getTargetBlock(null, 40).getLocation();
					    loc = loc.getWorld().getHighestBlockAt(loc).getLocation();
						Bukkit.getWorld("world").strikeLightning(loc);
						Location location = loc.getBlock().getRelative(BlockFace.UP).getLocation();
						location.getBlock().setType(Material.FIRE);
					} else { 
						KitAPI.getInstance().messageCooldown(player);
					}
				}
			}
		}
 	}
	
	@EventHandler
	private void onEntityDamageThor(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		Player player = (Player) e.getEntity();
		if (e.getCause() == DamageCause.LIGHTNING) {
			if (KitAPI.getInstance().hasKit(player, "Thor") || KitAPI.getInstance().hasKit2(player, "Thor")) {
				e.setCancelled(true);
			} else {
				e.setDamage(4.0D);
			}
		}
	}
	
	@EventHandler
	private void onEntityDamageByEntityViper(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		if (!(e.getDamager() instanceof Player)) return;
		Player player = (Player) e.getDamager();
		if (KitAPI.getInstance().hasKit(player, "Viper") || KitAPI.getInstance().hasKit2(player, "Viper")) {
			if (new Random().nextInt(100) < 33) {
				((Player)e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0));
			}
		}
	}
}
