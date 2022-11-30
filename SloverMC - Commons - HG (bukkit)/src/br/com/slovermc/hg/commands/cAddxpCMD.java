package br.com.slovermc.hg.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.mysql.SkyAPI;
import br.com.slovermc.hg.utils.MessageStrings;

public class cAddxpCMD implements CommandExecutor {
	
	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("addxp")) {
			if (!p.hasPermission("slovermc.addxp")) {
				p.sendMessage(MessageStrings.noperm);
			} else {
				if (args.length == 0) {
					sender.sendMessage("§9§lXP §fUtilize /addxp (player) <quantia>.");
					return true;
				}
				if (args.length <= 1) {
					sender.sendMessage("§9§lXP §fUtilize /addxp (player) <quantia>.");
					return true;
				}
				Player target = Bukkit.getPlayerExact(args[0]);
				if ((target == null) || (!(target instanceof Player))) {
					sender.sendMessage(MessageStrings.notonline);
					return true;
				}
				if (isNumeric(args[1])) {
					int xp = Integer.parseInt(args[1]);
					SkyAPI.addXp(target, xp);
					p.sendMessage("§9§lXP §fVocê setou no§e " + target.getName() + " §fcerca de §6§l" + xp
							+ " §6§lXP's§f.");
					target.sendMessage(
							"§9§lXP §fVocê ganhou §6§l" + xp + " §6§lXP's§f.");
				}
			}
		}
		return false;
	}
}

