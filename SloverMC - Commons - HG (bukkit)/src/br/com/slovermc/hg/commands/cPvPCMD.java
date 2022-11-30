package br.com.slovermc.hg.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.utils.MessageStrings;
import br.com.slovermc.hg.utils.Permission;

public class cPvPCMD implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player)sender;
		if (label.equalsIgnoreCase("pvp")) {
			if (player.hasPermission(Permission.getInstance().PVP)) { 
				if (args.length == 0) { 
					player.sendMessage("§4§lPVP §fUtilize /pvp <on/off>.");
					return true;
				}
				if (args[0].equalsIgnoreCase("on")) {
					if (Bukkit.getWorld("world").getPVP()) { 
						player.sendMessage("§4§lPVP §fO pvp já está ativado!");
					} else { 
						Bukkit.broadcastMessage("§4§lPVP §fO pvp foi ativado!");
						Bukkit.getWorld("world").setPVP(true);
					}
				}
				if (args[0].equalsIgnoreCase("off")) {
					if (!Bukkit.getWorld("world").getPVP()) { 
						player.sendMessage("§4§lPVP §fO pvp já está desativado!");
					} else { 
						Bukkit.broadcastMessage("§4§lPVP §fO pvp foi desativado!");
						Bukkit.getWorld("world").setPVP(false);
					}
				}
			} else { 
				player.sendMessage(MessageStrings.noperm);
			}
		}
		return false;
	}
}
