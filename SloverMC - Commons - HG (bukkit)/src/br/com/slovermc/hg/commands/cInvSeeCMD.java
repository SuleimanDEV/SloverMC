package br.com.slovermc.hg.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.utils.MessageStrings;
import br.com.slovermc.hg.utils.Permission;

public class cInvSeeCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("invsee")) {
			if (player.hasPermission(Permission.getInstance().ADMIN)) {
				if (args.length == 0) {
					player.sendMessage("§a§lINV §fUtilize /invsee (jogador).");
					return true;
				}
				if (args.length == 1) {
					Player target = Bukkit.getPlayerExact(args[0]);
					if (target != null) {
						player.openInventory(target.getInventory());
						player.sendMessage("§a§lINV §fVocê abriu o inventário de§b " + target.getName() + "§f.");
					} else {
						player.sendMessage(MessageStrings.notonline);
					}
				}
			} else { 
				player.sendMessage(MessageStrings.noperm);
			}
		}
		return false;
	}
}
