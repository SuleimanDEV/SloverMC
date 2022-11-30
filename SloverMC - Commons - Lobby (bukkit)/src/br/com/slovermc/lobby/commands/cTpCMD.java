package br.com.slovermc.lobby.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.lobby.BukkitMain;

public class cTpCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(BukkitMain.notplayer);
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("tp")) {
			if (!p.hasPermission("slovermc.tp")) {
				p.sendMessage(BukkitMain.noperm);
				return true;
			}
			if (args.length == 0) {
				p.sendMessage("§6§lTELEPORT §fUtilize /tp (jogador).");
			}
			if (args.length == 1) {
				Player p2 = Bukkit.getPlayer(args[0]);
				if (p2 == null) {
					p.sendMessage(BukkitMain.notonline);
				}
				p.teleport(p2);
				p.sendMessage("§6§lTELEPORT §fVocê foi teleportado até o(a) §e" + p2.getName() + ".");
				return true;
			}
			if (args.length > 1) {
				p.sendMessage("§6§lTELEPORT §fUtilize /tp (jogador).");
				return true;
			}
		}
		return false;
	}	
}