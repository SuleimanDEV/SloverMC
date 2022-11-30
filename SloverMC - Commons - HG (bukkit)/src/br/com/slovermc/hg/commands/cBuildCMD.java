package br.com.slovermc.hg.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.utils.MessageStrings;
import br.com.slovermc.hg.utils.Permission;

public class cBuildCMD implements CommandExecutor, Listener {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player)sender;
		if (label.equalsIgnoreCase("build")) {
			if (player.hasPermission(Permission.getInstance().BUILD)) { 
				if (args.length == 0) { 
					player.sendMessage("§cUtilize: /build (on|off)");
					return true;
				}
				if (args[0].equalsIgnoreCase("on")) { 
					if (BukkitMain.getPlugin().build == false) { 
						BukkitMain.getPlugin().build = true;
						Bukkit.broadcastMessage("§e§lBUILD §fO modo construção foi ativado!");
					} else { 
						player.sendMessage("§e§lBUILD §fO modo construção já está ativado!");
					}
				}
				if (args[0].equalsIgnoreCase("off")) { 
					if (BukkitMain.getPlugin().build == true) { 
						BukkitMain.getPlugin().build = false;
						Bukkit.broadcastMessage("§e§lBUILD §fO modo construção foi desativado!");
					} else { 
						player.sendMessage("§e§lBUILD §fO modo construção já está desativado!");
					}
				}
			} else { 
				player.sendMessage(MessageStrings.noperm);
			}
		}
		return false;
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) { 
		if (BukkitMain.getPlugin().build == false) { 
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) { 
		if (BukkitMain.getPlugin().build == false) { 
			e.setCancelled(true);
		}
	}
}
