package br.com.slovermc.hg.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.utils.MessageStrings;

public class cPingCMD implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("ping")) {
			if (args.length == 0) { 
			    int ping = ((CraftPlayer) player).getHandle().ping;
				player.sendMessage("§4§lPING §fSeu ping é de§b " + ping + " §fms!");
				return true;
			}
			Player target = Bukkit.getPlayerExact(args[0]);
			if (target != null) { 
				int ping = ((CraftPlayer) target).getHandle().ping;
				player.sendMessage("§4§lPING §fO ping de §b" + target.getName() + "§f é de §e" + ping + "§f ms!");
			} else { 
				player.sendMessage(MessageStrings.notonline);
			}
		}
		return false;
	}
}
