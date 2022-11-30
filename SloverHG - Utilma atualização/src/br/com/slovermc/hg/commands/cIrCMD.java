package br.com.slovermc.hg.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.api.PlayerAPI;
import br.com.slovermc.hg.utils.MessageStrings;

public class cIrCMD implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) return true;
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("ir")) {
			if (PlayerAPI.getInstance().hasEspectator(player)) { 
				if (args.length == 0) { 
					player.sendMessage("§6§lIR §fUtilize /ir (jogador).");
					return true;
				}
				Player target = Bukkit.getPlayerExact(args[0]);
				if (target != null) { 
					if (PlayerAPI.getInstance().hasPlayer(target)) { 
						player.teleport(target);
						player.sendMessage("§6§lIR §fVocê se teleportou até o §b" + target.getName() + "§f.");
					} else {  
						player.sendMessage(MessageStrings.notonline);
					}
				} else { 
					player.sendMessage(MessageStrings.notonline);
				}
			} else { 
				player.sendMessage("§c§lERRO §fVocê só pode utilizar este comando no modo espectador!");
			}
		}
		return false;
	}
}
