package br.com.slovermc.hg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.utils.Countdown;
import br.com.slovermc.hg.utils.MessageStrings;
import br.com.slovermc.hg.utils.Permission;

public class cTempoCMD implements CommandExecutor {

	String prefix = BukkitMain.prefix;

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		if (player.hasPermission(Permission.getInstance().TIME)) {
			if (args.length == 0) {
				player.sendMessage("§8§lTEMPO §fUtilize /tempo <tempo>.");
				return true;
			}
			if (args.length == 1) {
				try {
					Integer newtime = Integer.valueOf(args[0]);
					Countdown.getCountDown().changeTime(newtime);
					player.sendMessage("§8§lTEMPO §fTempo alterado para " + Countdown.getCountDown().Timer(newtime) + ".");
				} catch (Exception e) {
					player.sendMessage("§8§lTEMPO §fUtilize apenas números.");
				}
				return true;
			}
		} else { 
			player.sendMessage(MessageStrings.noperm);
		}
		return false;
	}
}
