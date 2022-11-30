package br.com.slovermc.hg.eventos;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import br.com.slovermc.hg.utils.Schematic;

public class BordaListeners implements Listener {

	/*@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if (player.getLocation().getBlockX() > Bukkit.getWorld("world").getSpawnLocation().getBlockX() + 501) {
			if (Main.state == StateEnum.GAME || Main.state == StateEnum.INVINCIBILITY) {
				player.damage(4.0D);
				player.sendMessage("§cVocê está na borda do mundo!");
			}
			else if (Main.state == StateEnum.STARTING || Main.state == StateEnum.END) { 
				player.sendMessage("§cVocê está na borda do mundo!");
				double x = 0.0D;
				double y = 80.0D;
				double z = 0.0D;
				Location location = new Location(Bukkit.getWorlds().get(0), x, y, z);
				player.teleport(location);
			}
		}
		else if (player.getLocation().getBlockX() < -(501 - Bukkit.getWorld("world").getSpawnLocation().getBlockX())) {
			if (Main.state == StateEnum.GAME || Main.state == StateEnum.INVINCIBILITY) {
				player.damage(4.0D);
				player.sendMessage("§cVocê está na borda do mundo!");
			}
			else if (Main.state == StateEnum.STARTING || Main.state == StateEnum.END) { 
				player.sendMessage("§cVocê está na borda do mundo!");
				double x = 0.0D;
				double y = 80.0D;
				double z = 0.0D;
				Location location = new Location(Bukkit.getWorlds().get(0), x, y, z);
				player.teleport(location);
			}
		}
		else if (player.getLocation().getBlockZ() > Bukkit.getWorld("world").getSpawnLocation().getBlockZ() + 501) {
			if (Main.state == StateEnum.GAME || Main.state == StateEnum.INVINCIBILITY) {
				player.damage(4.0D);
				player.sendMessage("§cVocê está na borda do mundo!");
			}
			else if (Main.state == StateEnum.STARTING || Main.state == StateEnum.END) { 
				player.sendMessage("§cVocê está na borda do mundo!");
				double x = 0.0D;
				double y = 80.0D;
				double z = 0.0D;
				Location location = new Location(Bukkit.getWorlds().get(0), x, y, z);
				player.teleport(location);
			}
		}
		else if (player.getLocation().getBlockZ() < -(501 - Bukkit.getWorld("world").getSpawnLocation().getBlockZ())) {
			if (Main.state == StateEnum.GAME || Main.state == StateEnum.INVINCIBILITY) {
				player.damage(4.0D);
				player.sendMessage("§cVocê está na borda do mundo!");
			}
			else if (Main.state == StateEnum.STARTING || Main.state == StateEnum.END) { 
				player.sendMessage("§cVocê está na borda do mundo!");
				double x = 0.0D;
				double y = 80.0D;
				double z = 0.0D;
				Location location = new Location(Bukkit.getWorlds().get(0), x, y, z);
				player.teleport(location);
			}
		}
	}*/
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		Block block = e.getBlock();
		if (block == Schematic.getInstance().border) { 
			e.setCancelled(true);
			player.sendMessage("§c§lERRO §fVocê não pode quebrar blocos da borda do mundo!");
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e ) {
		Player player = e.getPlayer();
		Block block = e.getBlock();
		if (block == Schematic.getInstance().border) { 
			e.setCancelled(true);
			player.sendMessage("§c§lERRO §fVocê não pode quebrar blocos da borda do mundo!");
		}
	}
}
