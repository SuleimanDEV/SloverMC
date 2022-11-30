package br.com.slovermc.lobby.commands;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cTellCMD implements CommandExecutor {

	public static HashMap<Player, Player> gettell = new HashMap<>();
	static ArrayList<Player> telloff = new ArrayList<>();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (label.equalsIgnoreCase("tell")) {
			if (args.length == 0) {
				p.sendMessage("§4§lTELL §fUtilize /tell (jogador) <mensagem>.");
				return true;
			}
			Player player = Bukkit.getPlayer(args[0]);
			if (player == p) {
				p.sendMessage("§4§lTELL §fVocê não pode enviar mensagens para si mesmo.");
				return true;
			}
			Player target = Bukkit.getPlayer(args[0]);
			if (args[0].toLowerCase().equalsIgnoreCase("on")) {
				telloff.remove(p);
				p.sendMessage("§4§lTELL §fVocê ativou o seu tell.");
			} else if (args[0].toLowerCase().equalsIgnoreCase("off")) {
				telloff.add(p);
				p.sendMessage("§4§lTELL §fVocê desativou o seu tell.");
			}
			if (args.length > 1) {
				if (target == null) {
						p.sendMessage("§4§lTELL §fEste jogador não está online.");
						return true;
					}
					if (telloff.contains(target)) {
						p.sendMessage("§4§lTELL §fO tell deste jogador está desativado.");
						return true;
					}
					StringBuilder sb = new StringBuilder();
					for (int i = 1; i < args.length; i++) {
						sb.append(args[i]).append(" ");
					}
					String allArgs = sb.toString().trim();
					p.sendMessage("§8(§7Você§f: " + target.getDisplayName() + "§8) §f- §e" + allArgs + ".");
					target.sendMessage("§8(" + p.getDisplayName() + "§f:§7 você§8) §f- §e" + allArgs + ".");
					gettell.put(p, target);
					gettell.put(target, p);
				}
			} else if (label.equalsIgnoreCase("r")) {
				p.sendMessage("§4§lTELL §fUtilize /tell (jogador) <mensagem>.");
			}
			return false;
		}
}