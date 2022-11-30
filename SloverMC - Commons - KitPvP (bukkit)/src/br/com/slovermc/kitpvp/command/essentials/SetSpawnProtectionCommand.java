package br.com.slovermc.kitpvp.command.essentials;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import br.com.slovermc.kitpvp.coords.LocationsConstructor;
import br.com.slovermc.kitpvp.utils.BattleStrings;

public final class SetSpawnProtectionCommand extends Command {

	public SetSpawnProtectionCommand(String name, String description, String usageMessage, List<String> aliases) {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
		if (!sender.hasPermission("slovermc.setspawnprotection")) {
			sender.sendMessage(BattleStrings.getNoPermissionToCommandMessage());
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage(" ");
			sender.sendMessage("§8§lPROTEÇAO§f Utilize /setspawnprotection <spawn/fps> (distância).");
			sender.sendMessage(
					"§e§lEXEMPLO§f /setspawnprotection spawn 15 §c(setei a proteção da warp spawn para 15 blocos de distancia)§f.");
			sender.sendMessage(" ");
			return true;
		}
		if (args.length == 1) {
			if (!args[0].equalsIgnoreCase("spawn") && !args[0].equalsIgnoreCase("fps")) {
				sender.sendMessage(" ");
				sender.sendMessage("§8§lPROTEÇAO§f Utilize /setspawnprotection <spawn/fps> (distância).");
				sender.sendMessage(
						"§e§lEXEMPLO§f /setspawnprotection spawn 15 §c(setei a proteção da warp spawn para 15 blocos de distancia)§f.");
				sender.sendMessage(" ");
				return true;
			} else {
				sender.sendMessage("§8§lPROTEÇAO§f Utilize /setspawnprotection " + args[0] + " (distância).");
				return true;
			}
		}
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("spawn")) {
				try {
					LocationsConstructor.setBattleWarpSpawnDistanceProtection(Integer.valueOf(args[1]));
					sender.sendMessage("§8§lPROTEÇAO§f Voce setou a proteçao do SPAWN para §8§l" + args[1] + " BLOCOS");
					return true;
				} catch (Exception ex) {
					sender.sendMessage("§8§lPROTEÇAO§f Utilize apenas §9§lnumeros§f para indicar a distancia!");
					return true;
				}
			} else if (args[0].equalsIgnoreCase("fps")) {
				try {
					LocationsConstructor.setBattleWarpFpsDistanceProtection(Integer.valueOf(args[1]));
					sender.sendMessage("§8§lPROTEÇAO§f Voce setou a proteçao da FPS para §8§l" + args[1] + " BLOCOS");
					return true;
				} catch (Exception ex) {
					sender.sendMessage("§8§lPROTEÇAO§f Utilize apenas §9§lnumeros§f para indicar a distancia!");
					return true;
				}
			} else {
				sender.sendMessage(" ");
				sender.sendMessage("§8§lPROTEÇAO§f Utilize /setspawnprotection <spawn/fps> (distância).");
				sender.sendMessage(
						"§e§lEXEMPLO§f /setspawnprotection spawn 15 §c(setei a proteção da warp spawn para 15 blocos de distancia)§f.");
				sender.sendMessage(" ");
				return true;
			}
		}
		if (args.length > 2) {
			sender.sendMessage(" ");
			sender.sendMessage("§8§lPROTEÇAO§f Utilize /setspawnprotection <spawn/fps> (distância).");
			sender.sendMessage(
					"§e§lEXEMPLO§f /setspawnprotection spawn 15 §c(setei a proteção da warp spawn para 15 blocos de distancia)§f.");
			sender.sendMessage(" ");
			return true;
		}
		return false;
	}
}
