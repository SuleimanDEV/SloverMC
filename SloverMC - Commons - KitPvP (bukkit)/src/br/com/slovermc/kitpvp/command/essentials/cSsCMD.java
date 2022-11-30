package br.com.slovermc.kitpvp.command.essentials;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import br.com.slovermc.kitpvp.BukkitMain;

public class cSsCMD implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("�c�lERRO �fEste comando s� pode ser executado in-game.");
			return true;
		}
		Player p = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("ss") || (cmd.getName().equalsIgnoreCase("screenshare"))) {
			if (!p.hasPermission("slovermc.ss")) {
				sender.sendMessage("�c�lERRO �fVoc� n�o possui permiss�o para executar este comando.");
				return true;
			}
			if (args.length == 0) {
				sender.sendMessage("�6�lSCREENSHARE �fPara puxar um jogador no screenshare utilize o comando �6/ss (jogador)�f.");
				return true;
			}
			Player simesmo = Bukkit.getPlayer(args[0]);
			if (simesmo == p) {
				p.sendMessage("�6�lSCREENSHARE �fVoc� n�o pode puxar a si mesmo.");
				return true;
			}

			if (args.length == 1) {
				Player online = Bukkit.getPlayer(args[0]);
				if (online == null) {
					p.sendMessage("�6�lSCREENSHARE �fEste jogador n�o est� online no momento.");
					return true;
				} else {
					online.sendMessage("�6�lSCREENSHARE �fVoc� foi puxado para a screenshare.");
					p.sendMessage("�6�lSCREENSHARE �fVoc� puxou o jogador " + online.getName() + "�f para a ss.");
					Bukkit.broadcastMessage("");
					Bukkit.broadcastMessage("�6�lSCREENSHARE �fO jogador �e" + online.getName() + "�f foi puxado para a �4�lSCREENSHARE�f.");
					Bukkit.broadcastMessage("");
					sendServer(p, "screenshare");
					sendServer(online, "screenshare");
				}
			}
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
