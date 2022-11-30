package br.com.slovermc.hg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cLigaCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {

		Player p = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("liga") || (cmd.getName().equalsIgnoreCase("ligas"))) {
			p.sendMessage("");
			p.sendMessage("         §6§lLIGAS");
			p.sendMessage("§fAs ligas estão abaixo:");
			p.sendMessage("§f- UNRANKED§7: §b0 XP's.");
			p.sendMessage("§a☰ PRIMARY§7: §b5.000 XP's.");
			p.sendMessage("§e☲ ADVANCED§7: §b10.000 XP's.");
			p.sendMessage("§1☷ EXPERT§7: §b15.000 XP's.");
			p.sendMessage("§7✶ SILVER: §b20.000 XP's.");
			p.sendMessage("§6✷ GOLD§7: §b30.000 XP's.");
			p.sendMessage("§b✦ DIAMOND§7: §b40.000 XP's.");
			p.sendMessage("§2�?� SAPPHIRE§7: §b50.000 XP's.");
			p.sendMessage("§5✹ ELITEE§7: §b60.000 XP's.");
			p.sendMessage("§c✫ MASTER§7: §b70.000 XP's.");
			p.sendMessage("§4✪ LEGENDARY§7: §b90.000 XP's");
			p.sendMessage("§8X XITER§7: §b100.000 XP's.");
			p.sendMessage("");
			p.sendMessage("§fEstão são as ligas, para upar você precisa");
			p.sendMessage("§fde XP's ganhados em wins §e(vitórias)§f.");
			p.sendMessage("");
		}
		return false;
	}

}
