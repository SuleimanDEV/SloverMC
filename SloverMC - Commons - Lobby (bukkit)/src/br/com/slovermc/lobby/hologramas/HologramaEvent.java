package br.com.slovermc.lobby.hologramas;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import br.com.slovermc.lobby.BukkitMain;
import net.minecraft.util.io.netty.util.internal.ConcurrentSet;

public class HologramaEvent implements Listener {
	
	/**
	 * 
	 */

	private static final ConcurrentSet<Holograma> holograms = new ConcurrentSet<>();
	public static final int distance = Bukkit.getViewDistance() * 16;

	public static ConcurrentSet<Holograma> getHolograms() {
		return holograms;
	}

	@EventHandler
	public void onPlayerJoin(final PlayerJoinEvent event) {
		new BukkitRunnable() {
			@Override
			public void run() {
				for (Holograma hologram : holograms) {
					if (!hologram.isRegister())
						continue;

					if (hologram.getLocation().distance(event.getPlayer().getLocation()) < distance) {
						if (!hologram.isVisible(event.getPlayer())) {
							hologram.show(event.getPlayer());
							hologram.lock(event.getPlayer(), 5);
						}
					} else {
						hologram.hide(event.getPlayer());
					}
				}

			}
		}.runTaskAsynchronously(BukkitMain.getPlugin(BukkitMain.class));
	}

	@EventHandler
	public void onPlayerTeleport(final PlayerTeleportEvent event) {
		if (event.isCancelled())
			return;
		new BukkitRunnable() {
			@Override
			public void run() {
				for (Holograma hologram : holograms) {
					if (!hologram.isLocked(event.getPlayer()) || !hologram.isRegister())
						continue;

					if (hologram.getLocation().distance(event.getTo()) <= distance) {
						if (hologram.getLocation().distance(event.getFrom()) > distance) {
							hologram.hide(event.getPlayer());
							hologram.show(event.getPlayer());
							hologram.lock(event.getPlayer(), 5);
						}
					} else {
						hologram.hide(event.getPlayer());
					}
				}
			}
		}.runTaskAsynchronously(BukkitMain.getPlugin(BukkitMain.class));
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		new BukkitRunnable() {
			@Override
			public void run() {
				for (Holograma hologram : holograms) {
					if (!hologram.isLocked(event.getPlayer()) || !hologram.isRegister())
						continue;

					if (hologram.getLocation().distance(event.getTo()) <= distance) {
						if (!hologram.isVisible(event.getPlayer())) {
							hologram.show(event.getPlayer());
							hologram.lock(event.getPlayer(), 5);
						}
					} else {
						hologram.hide(event.getPlayer());
					}
				}
			}
		}.runTaskAsynchronously(BukkitMain.getPlugin(BukkitMain.class));

	}

	@EventHandler
	public void onPlayerRespawn(final PlayerRespawnEvent event) {
		new BukkitRunnable() {
			@Override
			public void run() {
				for (Holograma hologram : holograms) {
					if (!hologram.isRegister())
						continue;

					if (hologram.getLocation().distance(event.getPlayer().getLocation()) <= distance) {
						hologram.hide(event.getPlayer());
						hologram.show(event.getPlayer());
					} else {
						hologram.hide(event.getPlayer());
					}
				}
			}
		}.runTaskAsynchronously(BukkitMain.getPlugin(BukkitMain.class));
	}

	@EventHandler
	public void onPlayerQuit(final PlayerQuitEvent event) {
		for (Holograma hologram : holograms)
			hologram.hide(event.getPlayer());
	}
}
