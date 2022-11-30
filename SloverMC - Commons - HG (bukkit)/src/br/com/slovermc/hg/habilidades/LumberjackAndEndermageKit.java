package br.com.slovermc.hg.habilidades;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.api.KitAPI;
import br.com.slovermc.hg.api.PlayerAPI;

@SuppressWarnings({ "deprecation" })
public class LumberjackAndEndermageKit implements Listener {

	@EventHandler
	private void onPlayerInteractEndermage(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK && player.getItemInHand().getType() == Material.PORTAL) {
			if (KitAPI.getInstance().hasKit(player, "Endermage") || KitAPI.getInstance().hasKit2(player, "Endermage")) {
				e.setCancelled(true);
				player.setItemInHand(null);
				player.updateInventory();
				Block block = e.getClickedBlock();
				Location location = block.getLocation();
				BlockState blockstate = block.getState();
				block.setType(Material.ENDER_PORTAL_FRAME);
				for (Player players : Bukkit.getOnlinePlayers()) {
					new BukkitRunnable() {
						int tempo = 5;
						
						public void run() {
							if (!players.getInventory().contains(Material.ENDER_PORTAL_FRAME) && PlayerAPI.getInstance().hasPlayer(players) && players != player && Endermage(location, players.getLocation())) {
								block.setType(blockstate.getType());
								block.setData(blockstate.getBlock().getData());
								cancel();
								player.teleport(location.clone().add(0, 1, 0));
								players.teleport(location.clone().add(0, 1, 0));
								player.sendMessage("§b§lENDERMAGE §fVocê puxou alguém, ambos tem 5 segundos de invencibilidade!");
								players.sendMessage("§b§lENDERMAGE §fVocê foi puxado por alguém, ambos tem 5 segundos de invencibilidade!");
								player.setNoDamageTicks(100);
								players.setNoDamageTicks(100);
								players.getWorld().playEffect(players.getLocation(), Effect.ENDER_SIGNAL, 9);
					            player.getWorld().playEffect(location, Effect.ENDER_SIGNAL, 9);
					            players.playSound(players.getLocation(), Sound.ENDERMAN_TELEPORT, 1.2F, 1.0F);
					            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1.2F, 1.0F);
					            if (!player.getInventory().contains(Material.PORTAL)) {
					            	if (KitAPI.getInstance().hasKit(player, "Endermage") || KitAPI.getInstance().hasKit2(player, "Endermage")) {
					            		ItemStack portal = new ItemStack(Material.PORTAL);
					            		ItemMeta mportal = portal.getItemMeta();
					            		mportal.setDisplayName("§5Endermage");
					            		portal.setItemMeta(mportal);
					            		player.getInventory().addItem(portal);
					            		player.updateInventory();
					            	}
					            }
								return;
							}
							if (tempo == 0) {
								cancel();
								block.setType(blockstate.getType());
								block.setData(blockstate.getBlock().getData());
								if (!player.getInventory().contains(Material.PORTAL)) {
									if (KitAPI.getInstance().hasKit(player, "Endermage")
											|| KitAPI.getInstance().hasKit2(player, "Endermage")) {
										ItemStack portal = new ItemStack(Material.PORTAL);
										ItemMeta mportal = portal.getItemMeta();
										mportal.setDisplayName("§5Endermage");
										portal.setItemMeta(mportal);
										player.getInventory().addItem(portal);
										player.updateInventory();
									}
								}
								 return;
							}
							--tempo;
						}
					}.runTaskTimer(BukkitMain.getPlugin(), 0, 20);
				}
			}
		}
	}
	
	@EventHandler
	private void onPlayerLumberJack(BlockBreakEvent e) {
		Player player = e.getPlayer();
		Block block = e.getBlock();
		ItemStack item = player.getItemInHand();
		if (KitAPI.getInstance().hasKit(player, "Lumberjack") || KitAPI.getInstance().hasKit2(player, "Lumberjack") && item.getType() == Material.WOOD_AXE) {
			World w = player.getWorld();
			Double y = block.getLocation().getY() + 1;
			Location wood_location = new Location(w, block.getX(), y, block.getZ());
			while (wood_location.getBlock().getType() == Material.LOG) {
				wood_location.getBlock().breakNaturally();
				y += 1;
				wood_location.setY(y);
			}
			while (wood_location.getBlock().getType() == Material.LOG_2) {
				wood_location.getBlock().breakNaturally();
				y += 1;
				wood_location.setY(y);
			}
		}
	}
	
	boolean Endermage(Location portal, Location target) {
		return (Math.abs(portal.getX() - target.getX()) < 2.5D) && (Math.abs(portal.getZ() - target.getZ()) < 2.5D)
				&& (Math.abs(portal.getY() - target.getY()) > 3.0D);
	}
}
