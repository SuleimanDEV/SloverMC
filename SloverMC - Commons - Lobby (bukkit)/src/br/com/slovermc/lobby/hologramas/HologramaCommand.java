package br.com.slovermc.lobby.hologramas;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public final class HologramaCommand extends Command {

	public HologramaCommand(String name, String description, String usageMessage, List<String> aliases) {
		super(name, description, usageMessage, aliases);
	}

	public static final String[] warpsList = { "Hologram", "Halogram", "Holograma", "Hl" };

	public static final boolean validWarpName(final String args) {
		for (String warps : warpsList) {
			if (args.equalsIgnoreCase(warps)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
		final Player battleplayer = (Player) sender;
		if (args.length == 1 && args.length < 2) {
			new HologramLoc(battleplayer, HologramLoc.convertWarpName(args[0]));
			return true;
		} else if (args.length > 1) {
			return true;
		}
		return false;
	}
}