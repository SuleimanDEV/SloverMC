package br.com.slovermc.lobby.hologramas;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.lobby.BukkitMain;

public final class SetHl extends Command {

	public SetHl(String name, String description, String usageMessage, List<String> aliases) {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
		final Player battleplayer = (Player) sender;
		if (!battleplayer.hasPermission("slovermc.holograma")) {
			battleplayer.sendMessage(BukkitMain.noperm);
			return true;
		}
		if (args.length == 0 && args.length < 1) {
			battleplayer.sendMessage("�b�lHOLOGRAMA�f Set hologram �b/set hl�f.");
			return true;
		} else if (args.length == 1 && args.length < 2) {
			if (!HologramaCommand.validWarpName(HologramLoc.convertWarpName(args[0]))
					&& !HologramLoc.convertWarpName(args[0]).equalsIgnoreCase("1v1loc1") && !HologramLoc.convertWarpName(args[0]).equalsIgnoreCase("1v1loc2")) {
				battleplayer.sendMessage("�b�lHOLOGRAMA�f Set hologram �b/set hl�f.");
				return true;
			}
			LocationsHl.setNewBattleWarpLocation(battleplayer, HologramLoc.convertWarpName(args[0]),
					battleplayer.getLocation());
			if (HologramLoc.convertWarpName(args[0]).equalsIgnoreCase("Hologram")) {
				Holograma hl = HologramaAPI.holograma.get("hl");
				if (hl == null) {
					HologramaAPI.topXpHologramLoader();
				}
				if (hl != null) {
					HologramaAPI.removeoldXpHologramLocation();
				}
			}

			battleplayer.sendMessage("�b�lHOLOGRAMA�f Voc� setou o local �3" + HologramLoc.convertWarpName(args[0]) + "�f.");
			return true;
		} else if (args.length > 1) {
			battleplayer.sendMessage("�b�lHOLOGRAMA�f Set hologram �b/set hl�f.");
			return true;
		}
		return false;
		}
}
