package br.com.slovermc.kitpvp.command.essentials;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.coords.LocationsConstructor;
import br.com.slovermc.kitpvp.hologram.HalogramAPI;
import br.com.slovermc.kitpvp.hologram.Hologram;
import br.com.slovermc.kitpvp.utils.BattleStrings;
import br.com.slovermc.kitpvp.warps.WarpsAPI;

public final class SetWarpCommand extends Command {

	public SetWarpCommand(String name, String description, String usageMessage, List<String> aliases) {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
		if (!BukkitMain.NotInGame(sender)) {
			sender.sendMessage(BattleStrings.getCommandOnlyInGameMessage());
			return true;
		}
		final Player battleplayer = (Player) sender;
		if (!battleplayer.hasPermission("slovermc.setwarp")) {
			battleplayer.sendMessage(BattleStrings.getNoPermissionToCommandMessage());
			return true;
		}
		if (args.length == 0 && args.length < 1) {
			battleplayer.sendMessage("§2§lLOCAL§f Sete o local de uma warp com /setwarp §e(warp)§f.");
			return true;
		} else if (args.length == 1 && args.length < 2) {
			if (!WarpCommand.validWarpName(WarpsAPI.convertWarpName(args[0]))
					&& !WarpsAPI.convertWarpName(args[0]).equalsIgnoreCase("1v1loc1") && !WarpsAPI.convertWarpName(args[0]).equalsIgnoreCase("1v1loc2")) {
				battleplayer.sendMessage("§2§lLOCAL§f Este local não está pré-registrado para ser setado.");
				return true;
			}
			LocationsConstructor.setNewBattleWarpLocation(battleplayer, WarpsAPI.convertWarpName(args[0]),
					battleplayer.getLocation());
			if (WarpsAPI.convertWarpName(args[0]).equalsIgnoreCase("Hologram")) {
				Hologram hl = HalogramAPI.holograma.get("hl");
				if (hl == null) {
					HalogramAPI.topXpHologramLoader();
				}
				if (hl != null) {
					HalogramAPI.removeoldXpHologramLocation();
				}
			}
			battleplayer.sendMessage("§2§lLOCAL§f Você setou o local §3" + WarpsAPI.convertWarpName(args[0]) + "§f.");
			return true;
		} else if (args.length > 1) {
			battleplayer.sendMessage("§2§lLOCAL§f Sete o local de uma warp com /setwarp §e(warp)§f.");
			return true;
		}
		return false;
	}

}
