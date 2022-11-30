package br.com.slovermc.kitpvp.command.essentials;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import br.com.slovermc.kitpvp.utils.BattleStrings;

public final class DanoCommand extends Command {

	public DanoCommand(String name, String description, String usageMessage, List<String> aliases) {
		super(name, description, usageMessage, aliases);
	}
	
	public static boolean onDamage = true;

	@Override
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
		if (!sender.hasPermission("slovermc.dano")) {
			sender.sendMessage(BattleStrings.getNoPermissionToCommandMessage());
			return true;
		}
		if (args.length >= 0) {
			if (onDamage) {
				onDamage = false;
				Bukkit.getServer().broadcastMessage("§4§lDANO§f O dano global foi desabilitado.");
				return true;
			} else {
				onDamage = true;
				Bukkit.getServer().broadcastMessage("§4§lDANO§f O dano global foi habilitado.");
				return true;
			}
		}
		return false;
	}
}
