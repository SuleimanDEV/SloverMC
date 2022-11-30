package br.com.slovermc.kitpvp.command.essentials;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import br.com.slovermc.kitpvp.utils.BattleStrings;

public final class PvPCommand extends Command {

	public PvPCommand(String name, String description, String usageMessage, List<String> aliases) {
		super(name, description, usageMessage, aliases);
	}
	
	public static boolean onPvP = true;

	@Override
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
		if (!sender.hasPermission("slovermc.pvp")) {
			sender.sendMessage(BattleStrings.getNoPermissionToCommandMessage());
			return true;
		}
		if (args.length >= 0) {
			if (onPvP) {
				onPvP = false;
				Bukkit.getServer().broadcastMessage("§4§lPVP§f O pvp global foi desabilitado!");
				return true;
			} else {
				onPvP = true;
				Bukkit.getServer().broadcastMessage("§4§lPVP§f O pvp global foi habilitado!");
				return true;
			}
		}
		return false;
	}
}
