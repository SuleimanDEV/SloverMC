package br.com.slovermc.hg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cMiniSloverCMD implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("minislover")) {
			player.sendMessage("§b§lMINISLOVER");
			player.sendMessage("§fOs eventos MiniSlover ocorreram nos horários á baixo:");
			player.sendMessage("");
			player.sendMessage("§a14:00 §fhoras, §a17:00 §fhoras e §a20:00§f.");
			player.sendMessage("§fSeguidos pelo horário de brasília!");
			player.sendMessage("");
			player.sendMessage("§6www.slovermc.com");
		}
		return false;
	}
}
