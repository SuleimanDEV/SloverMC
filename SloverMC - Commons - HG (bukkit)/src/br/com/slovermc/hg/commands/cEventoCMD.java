package br.com.slovermc.hg.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.utils.MessageStrings;
import br.com.slovermc.hg.utils.Permission;

public class cEventoCMD implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("evento")) {
			if (player.hasPermission(Permission.getInstance().EVENT)) {
				if (args.length == 0) {
					player.sendMessage("§4§lEVENTO §fUtilize /evento <on/off>.");
					return true;
				}
				if (args[0].equalsIgnoreCase("on")) { 
					if (BukkitMain.state != StateEnum.STARTING) return true;
					if (BukkitMain.getPlugin().evento == false) { 
						BukkitMain.getPlugin().evento = true;
						Bukkit.broadcastMessage("§4§lEVENTO §fO modo evento foi ativado!");
					} else { 
						player.sendMessage("§4§lEVENTO §fO modo evento já está ativado!");
					}
				}
				if (args[0].equalsIgnoreCase("off")) { 
					if (BukkitMain.state != StateEnum.STARTING) return true;
					if (BukkitMain.getPlugin().evento == true) { 
						BukkitMain.getPlugin().evento = false;
						Bukkit.broadcastMessage("§4§lEVENTO §fO modo evento foi desativado!");
					} else { 
						player.sendMessage("§4§lEVENTO §fO modo evento já está desativado!");
					}
				}
			} else { 
				player.sendMessage(MessageStrings.noperm);
			}
		}
		return false;
	}
}
