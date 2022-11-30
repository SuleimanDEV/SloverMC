package br.com.slovermc.lobby.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.lobby.api.SkyAPI;
import br.com.slovermc.lobby.BukkitMain;

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
				p.sendMessage(BukkitMain.noperm);
			} else {
				if (args.length == 0) {
					sender.sendMessage("�9�lXP �fUtilize /removexp (player) <quantia>.");
					return true;
				}
				if (args.length <= 1) {
					sender.sendMessage("�9�lXP �fUtilize /removexp (player) <quantia>.");
					return true;
				}
				Player target = Bukkit.getPlayerExact(args[0]);
				if ((target == null) || (!(target instanceof Player))) {
					sender.sendMessage(BukkitMain.notonline);
					return true;
				}
				if (isNumeric(args[1])) {
					int xp = Integer.parseInt(args[1]);
					SkyAPI.addXp(target, -xp);
					p.sendMessage("�9�lXP �fVoc� removeu do�e " + target.getName() + " �fcerca de �6�l" + xp
							+ " XP's�f.");
					target.sendMessage(
							"�9�lXP �fVoc� perdeu �6�l" + xp + " �6�lXP's�f.");
				}
			}
		}
		return false;
	}
}
