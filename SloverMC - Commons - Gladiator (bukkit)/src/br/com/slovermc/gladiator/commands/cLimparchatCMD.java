package br.com.slovermc.gladiator.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.gladiator.BukkitMain;
import br.com.slovermc.gladiator.utils.Strings;

public class cLimparchatCMD implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] Args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Strings.notplayer);
			return true;
		} else {

			if (cmd.getName().equalsIgnoreCase("cc")) {
				Player p = (Player) sender;

				if (!p.hasPermission("slovermc.cc")) {
					p.sendMessage(BukkitMain.semp);
					return true;
				} else {

					for (Player online : Bukkit.getOnlinePlayers()) {
						for (int r = 0; r < 140; r++) {
							online.sendMessage(
									"                                                                      ");
						}
						if (online.hasPermission("slovermc.cc")) {
						}
					}
					Bukkit.broadcastMessage("§4§lCHAT §fO chat do §e§lSERVIDOR§f foi limpo!");
				}
			}
		}

		return false;
	}

}
