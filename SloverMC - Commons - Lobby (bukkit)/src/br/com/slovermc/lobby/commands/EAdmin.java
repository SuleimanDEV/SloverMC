package br.com.slovermc.lobby.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.slovermc.lobby.BukkitMain;

public class EAdmin implements Listener {
	
	@EventHandler
	private void onclickedInteractAdmin(PlayerInteractEvent e) {
		final Player player = e.getPlayer();
		String player_name = player.getName();

		if (cAdminCMD.admins.contains(player_name)) {
			if (player.getItemInHand() == null || !player.getItemInHand().hasItemMeta())
				return;

			if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("�eQuickAdmin �7(Troca r�pida)")) {
				player.performCommand("admin");
				player.setAllowFlight(true);
				player.setFlying(true);

				new BukkitRunnable() {
					public void run() {
						player.performCommand("admin");
						player.setAllowFlight(true);
						player.setFlying(true);
					}
				}.runTaskLater(BukkitMain.plugin, 7);
				player.setAllowFlight(true);
				player.setFlying(true);
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	private void onPlayerDropItemAdmin(PlayerDropItemEvent e) {
		Player player = e.getPlayer();

		if (cAdminCMD.admins.contains(player.getName())) {
			if (!e.getItemDrop().getItemStack().hasItemMeta())
				return;

			if (!e.getItemDrop().getItemStack().getItemMeta().getDisplayName()
					.contains(e.getItemDrop().getItemStack().getType().toString())) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	private void onPlayerPickUpAdmin(PlayerPickupItemEvent e) {
		Player player = e.getPlayer();

		if (cAdminCMD.admins.contains(player.getName())) {
			e.setCancelled(true);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	private void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();

		for (Player players : Bukkit.getOnlinePlayers()) {
			if (cAdminCMD.admins.contains(players.getName()) && (!players.hasPermission("glad.admin"))) {
				player.hidePlayer(players);
			}
		}
	}
}
