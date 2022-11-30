package br.com.slovermc.hg.habilidades;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.api.KitAPI;

@SuppressWarnings({ "deprecation" })
public class FlashAndBlinkKit implements Listener {

	@EventHandler
	private void onPlayerFlahs(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if (KitAPI.getInstance().hasKit(player, "Flash")) {
			if (player.getItemInHand().getType() == Material.REDSTONE_TORCH_ON) {
				if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					e.setCancelled(true);
					player.updateInventory();
				}
				if (!KitAPI.getInstance().hasCooldown(player)) {
					KitAPI.getInstance().addCooldown(player, 40);
					Location teleport_location = player.getTargetBlock(null, 45).getLocation();
					player.teleport(teleport_location);
				} else {
					KitAPI.getInstance().messageCooldown(player);
				}
			}
			return;
		}
		
		if (BukkitMain.getPlugin().duoKit()) {
			if (KitAPI.getInstance().hasKit2(player, "Flash")) {
				if (player.getItemInHand().getType() == Material.REDSTONE_TORCH_ON) {
					if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
						e.setCancelled(true);
						player.updateInventory();
					}
					if (!KitAPI.getInstance().hasCooldown2(player)) {
						KitAPI.getInstance().addCooldown2(player, 40);
						Location teleport_location = player.getTargetBlock(null, 45).getLocation();
						player.teleport(teleport_location);
					} else {
						KitAPI.getInstance().messageCooldown2(player);
					}
				}
			}
		}
	}
	
	@EventHandler
	private void onPlayerBlink(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if (KitAPI.getInstance().hasKit(player, "Blink")) {
			if (player.getItemInHand().getType() == Material.NETHER_STAR) {
				if (!KitAPI.getInstance().hasCooldown(player)) {
					KitAPI.getInstance().addCooldown(player, 35);
					Location teleport_location = player.getTargetBlock(null, 7).getLocation();
					teleport_location.getBlock().getRelative(BlockFace.DOWN).setType(Material.LEAVES);
					player.teleport(teleport_location.add(0, 2, 0));
				} else {
					KitAPI.getInstance().messageCooldown(player);
				}
			}
			return;
		}

		if (BukkitMain.getPlugin().duoKit()) {
			if (KitAPI.getInstance().hasKit2(player, "Blink")) {
				if (player.getItemInHand().getType() == Material.NETHER_STAR) {
					if (!KitAPI.getInstance().hasCooldown2(player)) {
						KitAPI.getInstance().addCooldown2(player, 35);
						Location teleport_location = player.getTargetBlock(null, 7).getLocation();
						teleport_location.getBlock().getRelative(BlockFace.DOWN).setType(Material.LEAVES);
						player.teleport(teleport_location.add(0, 2, 0));
					} else {
						KitAPI.getInstance().messageCooldown2(player);
					}
				}
			}
		}
	}
}
