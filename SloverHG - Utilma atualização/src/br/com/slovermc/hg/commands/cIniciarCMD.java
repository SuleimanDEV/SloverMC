package br.com.slovermc.hg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.manager.Manager;
import br.com.slovermc.hg.utils.MessageStrings;
import br.com.slovermc.hg.utils.Permission;

public class cIniciarCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		if (player.hasPermission(Permission.getInstance().START)) {
			if (BukkitMain.state == StateEnum.STARTING) {
				Manager.getInstance().invincibilityTime();
				player.sendMessage("§4§lSTART §fVocê iniciou a partida.");
			} else {
				player.sendMessage("§4§lSTART §fA partida já iniciou.");
			}
		} else { 
			player.sendMessage(MessageStrings.noperm);
		}
		return false;
	}
}
