package br.com.slovermc.hg.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.utils.MessageStrings;
import br.com.slovermc.hg.utils.Permission;

public class cClearDropsCMD implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("cleardrops")) {
			if (player.hasPermission(Permission.getInstance().CLEARDROPS)) { 
				if (args.length == 0) { 
					for (Entity entity : Bukkit.getWorld("world").getEntities()) { 
						if ((entity instanceof Item)) { 
							entity.remove();
						}
					}
					Bukkit.broadcastMessage("§4§lCLEAR DROPS §fOs drops foram limpos!");
				}
			} else { 
				player.sendMessage(MessageStrings.noperm);
			}
		}
		return false;
	}
}
