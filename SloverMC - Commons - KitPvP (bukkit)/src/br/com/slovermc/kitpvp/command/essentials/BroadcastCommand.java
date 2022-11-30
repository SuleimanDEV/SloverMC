package br.com.slovermc.kitpvp.command.essentials;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import br.com.slovermc.kitpvp.utils.BattleStrings;

public final class BroadcastCommand extends Command {

	public BroadcastCommand(String name, String description, String usageMessage, List<String> aliases) {
		super(name, description, usageMessage, aliases);
	}

	@Override
	public boolean execute(final CommandSender sender, final String arg1, final String[] args) {
		if (!sender.hasPermission("slovermc.broadcast")) {
			sender.sendMessage(BattleStrings.getNoPermissionToCommandMessage());
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage("§4§lBROADCAST§f Utilize /broadcast (mensagem).");
			return true;
		}
		if (args.length > 0) {
			String msg = "";
			for (int i = 0; i < args.length; i++) {
				msg = msg + args[i] + " ";
			}
			Bukkit.getServer().broadcastMessage(" ");
			Bukkit.getServer().broadcastMessage("§4§lBROADCAST §f" + msg.replace("&", "§"));
			Bukkit.getServer().broadcastMessage(" ");
			return true;
		}
		return false;
	}
}
