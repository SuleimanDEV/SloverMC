package br.com.slovermc.groupmanager.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import br.com.slovermc.groupmanager.Main;

public class cMiniliga implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (command.getName().equalsIgnoreCase("miniliga") || command.getName().equalsIgnoreCase("evento")
				|| command.getName().equalsIgnoreCase("minizenix") || command.getName().equalsIgnoreCase("event")) {
			if (sender instanceof Player) {
				 ByteArrayDataOutput out = ByteStreams.newDataOutput();
				  out.writeUTF("connect");
				  out.writeUTF("evento");
				  ((Player) sender).sendPluginMessage(Main.getPlugin(), "BungeeCord", out.toByteArray());
				  return true;
			}
		}
		return false;
	}
}
