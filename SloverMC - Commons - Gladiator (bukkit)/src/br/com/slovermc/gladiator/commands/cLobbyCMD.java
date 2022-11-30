package br.com.slovermc.gladiator.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import br.com.slovermc.gladiator.BukkitMain;
import br.com.slovermc.gladiator.utils.Messages;

public class cLobbyCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(Messages.getCommandOnlyForPlayersMessage());
			return true;
		}
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("lobby")) {
			p.sendMessage("");
			p.sendMessage("§b§lCONNECT §fConectando lobby...");
			p.sendMessage("§b§lCONNECT §fVocê se conectou ao lobby.");
			p.sendMessage("");
			sendServer(p, "Lobby");
		}
		return false;
	}
	
	public static void sendServer(Player player, String serverName) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("Connect");
		out.writeUTF(serverName);
		player.sendPluginMessage(BukkitMain.getInstance(), "BungeeCord", out.toByteArray());
	}

}
