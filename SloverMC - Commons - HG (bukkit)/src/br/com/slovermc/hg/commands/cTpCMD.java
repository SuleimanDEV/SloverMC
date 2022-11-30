package br.com.slovermc.hg.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.utils.MessageStrings;
import br.com.slovermc.hg.utils.Permission;

public class cTpCMD implements CommandExecutor {

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("tp")) {
			if (player.hasPermission(Permission.getInstance().TP)) {
				if (args.length == 0) {
					player.sendMessage("§b§lTELEPORT §fUtilize /tp <jogador>.");
					return true;
				}
				if (args.length == 1) {
					Player target_player = Bukkit.getPlayerExact(args[0]);
					if (target_player != null) {
						player.teleport(target_player);
						player.sendMessage("§b§lTELEPORT §fVocê se teleportou até§e " + target_player.getName() + "§f.");
					}
				}
			} else { 
				player.sendMessage(MessageStrings.noperm);
			}
		}
		
		if (label.equalsIgnoreCase("tphere")) {
			if (player.hasPermission(Permission.getInstance().TP)) {
				if (args.length == 0) {
					player.sendMessage("§b§lTPHERE §fUtilize /tphere <jogador>.");
					return true;
				}
				if (args.length == 1) {
                    Player target_player = Bukkit.getPlayerExact(args[0]);
					player.sendMessage("§b§lTPHERE §fVocê puxou§b " + target_player.getName() + "§f.");
					target_player.teleport(player);
				}
			} else { 
				player.sendMessage(MessageStrings.noperm);
			}
		}
		if (label.equalsIgnoreCase("tpall")) {
			if (player.hasPermission(Permission.getInstance().TP)) {
				for (Player players : Bukkit.getOnlinePlayers()) {
					players.teleport(player);
				}
				player.sendMessage("§6§lTPALL §fVocê puxou todos até você.");
			} else { 
				player.sendMessage(MessageStrings.noperm);
			}
		}
		return false;
	}
}
