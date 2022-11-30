package br.com.slovermc.kitpvp.command.essentials;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.utils.BattleStrings;

public final class LobbyCommand extends Command {

	public LobbyCommand(String name, String description, String usageMessage, List<String> aliases) {
		super(name, description, usageMessage, aliases);
	}
	
	public static final void connect(Player player, String serverName) {
		ByteArrayDataOutput output = ByteStreams.newDataOutput();
		output.writeUTF("Connect");
		output.writeUTF(serverName);
		player.sendPluginMessage(BukkitMain.getPlugin(), BukkitMain.BungeeChannel, output.toByteArray());
	}

	@Override
	public boolean execute(final CommandSender sender, final String label, final String[] args) {
		if (!BukkitMain.NotInGame(sender)) {
			sender.sendMessage(BattleStrings.getCommandOnlyInGameMessage());
			return true;
		}
		final Player bp = (Player) sender;
		if (args.length >= 0) {
			bp.sendMessage("");
			bp.sendMessage("§b§lCONNECT§f Conectando... ");
			bp.sendMessage("§b§lCONNECT§f Você foi conectado com sucesso. ");
			bp.sendMessage("");
			connect(bp, "lobby");
			return true;
		}
		return false;
	}
}
