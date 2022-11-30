package br.com.slovermc.kitpvp.command.essentials;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import br.com.slovermc.kitpvp.utils.BattleStrings;

public final class CcCommand extends Command {

	public CcCommand(String name, String description, String usageMessage, List<String> aliases) {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(final CommandSender sender, final String arg1, final String[] args) {
		if (!sender.hasPermission("slovermc.cc")) {
			sender.sendMessage(BattleStrings.getNoPermissionToCommandMessage());
			return true;
		}
		if (args.length >= 0) {
			for (int i = 1; i < 101; i++) {
				Bukkit.getServer().broadcastMessage(" ");
			}
			Bukkit.getServer().broadcastMessage("§4§lCHAT§f O chat do §e§lSERVIDOR§f foi limpo!");
			return true;
		}
		return false;
	}
}
