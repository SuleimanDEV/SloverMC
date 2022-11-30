package br.com.slovermc.gladiator.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.gladiator.api.SkyAPI;
import br.com.slovermc.gladiator.utils.Strings;

public class cRemoveXpCMD implements CommandExecutor {
	
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
		if (cmd.getName().equalsIgnoreCase("removexp")) {
			if (!p.hasPermission("slovermc.removexp")) {
				p.sendMessage(Strings.noperm);
			} else {
				if (args.length == 0) {
					sender.sendMessage("§9§lXP §fUtilize /removexp (player) (quantia).");
					return true;
				}
				Player target = Bukkit.getPlayerExact(args[0]);
				if ((target == null) || (!(target instanceof Player))) {
					sender.sendMessage(Strings.notonline);
					return true;
				}
				if (isNumeric(args[1])) {
					int xp = Integer.parseInt(args[1]);
					SkyAPI.addXp(target, -xp);
					p.sendMessage("§9§lXP §9Você removeu do§e " + target.getName() + " §fcerca de §a" + xp
							+ " §fXP's.");
					target.sendMessage(
							"§9§lXP §fVocê perdeu §9§l" + xp + " XP§f.");
				}
			}
		}
		return false;
	}
}
