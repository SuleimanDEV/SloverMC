package br.com.slovermc.hg.habilidades;

import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.utils.CopyOfFishingHook;
import net.minecraft.util.com.google.common.collect.Lists;
import net.minecraft.util.com.google.common.collect.Maps;

public class LauncherAndGrapplerKit implements Listener {
	
	private List<Player> launcher = Lists.newArrayList();
	private List<Player> nerf = Lists.newArrayList();
	private Map<Player, CopyOfFishingHook> hook = Maps.newHashMap();
	
	@EventHandler
	private void onPlayerInteractGrappler(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if (player.getItemInHand().getType() == Material.LEASH) {
			if (nerf.contains(player)) {
				e.setCancelled(true);
				player.updateInventory();
				player.sendMessage("§b§lGRAPPLER §fVocê está em pvp, aguarde para usá-lo!");
				return;
			}
			if (KitAPI.getInstance().hasKit(player, "Grappler") || KitAPI.getInstance().hasKit2(player, "Grappler")) {
				e.setCancelled(true);
				if (player.getLocation().getY() >= 128) {
					player.updateInventory();
					player.sendMessage("§b§lGRAPPLER §fVocê está acima da borda!");
					Vector velocity = player.getLocation().getDirection().multiply(0).setY(-3);
					player.setVelocity(velocity);
					if (hook.containsKey(player)) {
						hook.get(player).remove();
					}
					return;
				}
				if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
					if (hook.containsKey(player)) {
						hook.get(player).remove();
					}
					CopyOfFishingHook nmsHook = new CopyOfFishingHook(player.getWorld(), ((CraftPlayer) player).getHandle());
					nmsHook.spawn(player.getEyeLocation().add(player.getLocation().getDirection().getX(),
							player.getLocation().getDirection().getY(), player.getLocation().getDirection().getZ()));
					nmsHook.move(player.getLocation().getDirection().getX() * 5.0D,
							player.getLocation().getDirection().getY() * 5.0D, player.getLocation().getDirection().getZ() * 5.0D);
					hook.put(player, nmsHook);
				} else {
					if (hook.containsKey(player)) {
						if (hook.get(player).isHooked()) {
							double d = ((CopyOfFishingHook) hook.get(player)).getBukkitEntity().getLocation()
									.distance(player.getLocation());
							double t = d;
							double v_x = (0.5D + 0.06000000000000001D * t)
									* (((CopyOfFishingHook) hook.get(player)).getBukkitEntity().getLocation().getX()
											- player.getLocation().getX())
									/ t;
							double v_y = (0.5D + 0.04D * t)
									* (((CopyOfFishingHook) hook.get(player)).getBukkitEntity().getLocation().getY()
											- player.getLocation().getY())
									/ t;
							double v_z = (0.5D + 0.06000000000000001D * t)
									* (((CopyOfFishingHook) hook.get(player)).getBukkitEntity().getLocation().getZ()
											- player.getLocation().getZ())
									/ t;
							player.setFallDistance(-5.0F);
							Vector v = player.getVelocity();
							v.setX(v_x);
							v.setY(v_y);
							v.setZ(v_z);
							player.setVelocity(v);
							player.getWorld().playSound(player.getLocation(), Sound.STEP_GRAVEL, 4.0F, 4.0F);
						} else {
							player.sendMessage("§b§lGRAPPLER §fO gancho não está preso!");
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	private void onPlayerLeashGrappler(PlayerLeashEntityEvent e) {
		Player player = e.getPlayer();
		if (player.getItemInHand().getType() == Material.LEASH) {
			if (KitAPI.getInstance().hasKit(player, "Grappler") || KitAPI.getInstance().hasKit2(player, "Grappler")) {
				player.updateInventory();
				e.setCancelled(true);
				if (hook.containsKey(player)) {
					if (hook.get(player).isHooked()) {
						double d = hook.get(player).getBukkitEntity().getLocation().distance(player.getLocation());
						double t = d;
						double v_x = (1.0D + 0.07000000000000001D * t)
								* hook.get(player).getBukkitEntity().getLocation().getX()
								- player.getLocation().getX() / t;
						double v_y = (1.0D + 0.03D * t) * hook.get(player).getBukkitEntity().getLocation().getY()
								- player.getLocation().getY() / t;
						double v_z = (1.0D + 0.07000000000000001D * t)
								* hook.get(player).getBukkitEntity().getLocation().getZ()
								- player.getLocation().getZ() / t;

						Vector v = player.getVelocity();
						v.setX(v_x);
						v.setY(v_y);
						v.setZ(v_z);
						player.setVelocity(v);
					}
				}
			}
		}
	}
	
	@EventHandler
	private void onEntityDamageGrappler(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		if (!(e.getDamager() instanceof Player)) return;
		Player player = (Player)e.getEntity();
		if (KitAPI.getInstance().hasKit(player, "Grappler") || KitAPI.getInstance().hasKit2(player, "Grappler")) {
			nerf.add(player);
		}
		if (nerf.contains(player)) {
			new BukkitRunnable() {
				public void run() {
					nerf.remove(player);
				}
			}.runTaskLater(BukkitMain.getPlugin(), 150);
		}
	}
	
	@EventHandler
	private void onPlayerHeldGrappler(PlayerItemHeldEvent e) {
		Player player = e.getPlayer();
		if (hook.containsKey(player)) {
			hook.get(player).remove();
			hook.remove(player);
		}
	}
	
	@EventHandler
	private void onPlayerMoveGrappler(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if (hook.containsKey(player) && player.getItemInHand().getType() != Material.LEASH) {
			hook.get(player).remove();
			hook.remove(player);
		}
	}
	
	@EventHandler
	private void onPlayerMoveLauncher(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		Location player_location = player.getLocation();
		if (player_location.add(0, -0.08, 0).getBlock().getType() == Material.SPONGE) {
			player.setVelocity(new Vector(0, 7, 0));
			if (!launcher.contains(player)) {
				launcher.add(player);
			}
		}
	}
	
	@EventHandler
	private void onPlayerDamageLauncher(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) return;
		Player player = (Player)e.getEntity();
		if (e.getCause() == DamageCause.FALL && launcher.contains(player)) {
			launcher.remove(player);
			e.setCancelled(true);
		}
	}
}
