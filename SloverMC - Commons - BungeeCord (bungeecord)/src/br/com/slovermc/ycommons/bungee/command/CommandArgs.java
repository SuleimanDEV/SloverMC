package br.com.slovermc.ycommons.bungee.command;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public abstract class CommandArgs extends Command {

	public CommandArgs(String name) {
		super(name);
	}
	
	public boolean isPlayer(CommandSender sender) {
		return (sender instanceof ProxiedPlayer);
	}
	
	public ProxiedPlayer getPlayerFromArgs(String name) {
		return BungeeCord.getInstance().getPlayer(name);
	}
	
	public ProxiedPlayer getPlayerFromSender(CommandSender sender) {
		return (ProxiedPlayer) sender;
	}
}
