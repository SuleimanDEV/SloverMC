package br.com.slovermc.hg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.BukkitMain;
import br.com.slovermc.hg.StateEnum;
import br.com.slovermc.hg.utils.Permission;

public class cFlyCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player)sender;
		if (label.equalsIgnoreCase("fly")) {
			if (player.hasPermission(Permission.getInstance().FLY)) {
				if (BukkitMain.state == StateEnum.STARTING) {
					if (!player.getAllowFlight()) {
						player.sendMessage("�a�lFLY �fVoc� ativou o modo voar!");
						player.setAllowFlight(true);
					} else {
						player.sendMessage("�a�lFLY �fVoc� desativou o modo voar!");
						player.setAllowFlight(false);
					}
				} else {
					player.sendMessage("�c�lERRO �fUtilize isso apenas durante o pr�-game!");
				}
			}
		}
		return false;
	}
}
