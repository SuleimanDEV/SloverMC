package br.com.slovermc.hg.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.hg.manager.FeastManager;

public class cFeastCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("feast")) {
			if (!FeastManager.getInstance().feastspawnado) {
				player.sendMessage("§c§lFEAST §fO feast ainda não foi spawnado!");
			} else {
				player.setCompassTarget(FeastManager.getInstance().feast);
				player.sendMessage("§3§lBUSSOLA §fBússola apontando para o feast!");
			}
		}
		return false;
	}
}
