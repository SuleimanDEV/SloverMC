package br.com.slovermc.hg.habilidades;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.api.KitAPI;

public class VacuumAndTankKit implements Listener {

	@EventHandler
	private void onPlayerInteractVacuum(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if (KitAPI.getInstance().hasKit(player, "Vacuum")) {
			if (player.getItemInHand().getType() == Material.EYE_OF_ENDER) {
				if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					e.setCancelled(true);
					player.updateInventory();
				}
				if (BukkitMain.state != StateEnum.GAME) return;
				if (KitAPI.getInstance().hasCooldown(player) == false) {
					KitAPI.getInstance().addCooldown(player, 45);
					for (Entity en : player.getNearbyEntities(20.0D, 15.0D, 20.0D)) {
						if (en instanceof Player) {
							Player px = (Player) en;
							Location loc = new Location(player.getWorld(), player.getLocation().getX(),
									player.getLocation().getY(), player.getLocation().getZ());
							double g = -0.20D;
							double d = loc.distance(px.getLocation());
							double t = d;
							double v_x = -(g - (loc.getX() - px.getLocation().getX()) / t);
							double v_y = -(g - (loc.getY() - px.getLocation().getY()) / t);
							double v_z = -(g - (loc.getZ() - px.getLocation().getZ()) / t);
							Vector v = px.getVelocity();
							v.setX(v_x);
							v.setY(v_y);
							v.setZ(v_z);
							px.setVelocity(v);
						}
					}
				} else {
					KitAPI.getInstance().messageCooldown(player);
				}
			}
		}
		if (BukkitMain.getPlugin().duoKit()) {
			if (KitAPI.getInstance().hasKit2(player, "Vacuum")) {
				if (player.getItemInHand().getType() == Material.EYE_OF_ENDER) {
					if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
						e.setCancelled(true);
						player.updateInventory();
					}
					if (BukkitMain.state != StateEnum.GAME) return;
					if (KitAPI.getInstance().hasCooldown2(player) == false) {
						KitAPI.getInstance().addCooldown2(player, 45);
						for (Entity en : player.getNearbyEntities(20.0D, 15.0D, 20.0D)) {
							if (en instanceof Player) {
								Player px = (Player) en;
								Location loc = new Location(player.getWorld(), player.getLocation().getX(),
										player.getLocation().getY(), player.getLocation().getZ());
								double g = -0.20D;
								double d = loc.distance(px.getLocation());
								double t = d;
								double v_x = -(g - (loc.getX() - px.getLocation().getX()) / t);
								double v_y = -(g - (loc.getY() - px.getLocation().getY()) / t);
								double v_z = -(g - (loc.getZ() - px.getLocation().getZ()) / t);
								Vector v = px.getVelocity();
								v.setX(v_x);
								v.setY(v_y);
								v.setZ(v_z);
								px.setVelocity(v);
							}
						}
					} else {
						KitAPI.getInstance().messageCooldown2(player);
					}
				}
			}
		}
	}
	
	@EventHandler
	private void onPlayerDeathTank(PlayerDeathEvent e) {
		if (!(e.getEntity().getKiller() instanceof Player)) return;
		Player player = e.getEntity().getKiller();
		if (KitAPI.getInstance().hasKit(player, "Tank") || KitAPI.getInstance().hasKit2(player, "Tank")) {
			e.getEntity().getWorld().createExplosion(e.getEntity().getLocation(), 3.0F);
		}
	}
	
	@EventHandler
	private void onEntityExplodeTank(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		Player player = (Player) e.getEntity();
		if (KitAPI.getInstance().hasKit(player, "Tank") || KitAPI.getInstance().hasKit2(player, "Tank")) {
			if (e.getCause() == DamageCause.BLOCK_EXPLOSION || e.getCause() == DamageCause.ENTITY_EXPLOSION) {
				e.setCancelled(true);
			}
		}
	}
}
