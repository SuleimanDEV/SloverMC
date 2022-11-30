package br.com.slovermc.kitpvp.command.essentials;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.utils.BattleStrings;
import br.com.slovermc.kitpvp.warps.WarpsAPI;

public final class SpawnCommand extends Command {

	public SpawnCommand(String name, String description, String usageMessage, List<String> aliases) {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
		if (!BukkitMain.NotInGame(sender)) {
			sender.sendMessage(BattleStrings.getCommandOnlyInGameMessage());
			return true;
		}
		final Player bp = (Player) sender;
		if (args.length >= 0) {
			new WarpsAPI(bp, "Spawn");
			return true;
		}
		return false;
	}
}
