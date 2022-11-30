package br.com.slovermc.kitpvp.command.essentials;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.slovermc.groupmanager.api.IGroupApi;
import br.com.slovermc.kitpvp.mysql.RankList;
import br.com.slovermc.kitpvp.mysql.SkyAPI;

public class cXpCMD implements CommandExecutor {
	
	public static String Grupo(Player p) {
		  return IGroupApi.convertGroupColor(br.com.slovermc.groupmanager.Main.database.getGroup(p));
		 }

	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		Player p = (Player)sender;
		if (cmd.getName().equalsIgnoreCase("account") || (cmd.getName().equalsIgnoreCase("xp"))) {
			
			if (args.length == 0) {
				p.sendMessage("");
				p.sendMessage("            §b§lACCOUNT");
				p.sendMessage("");
				p.sendMessage("§fJogador: §7" + p.getName());
				p.sendMessage("§fLiga: " + RankList.getScoreRank(p));
				p.sendMessage("§fRank: " + Grupo(p));
				p.sendMessage("");
				p.sendMessage("§fXP: §a" + SkyAPI.getXp(p));
				p.sendMessage("§fDoubleXP: §b0");
				p.sendMessage("§fMoedas: §b" + SkyAPI.getMoney(p));
				p.sendMessage("");
				p.sendMessage("§e§lCONTA: §fEstas são suas informações disponíveis.");
				p.sendMessage("");
				return true;
			}
			if (args.length == 1) {
				Player player = Bukkit.getPlayer(args[0]);
				if (player == p) {
					p.sendMessage("§b§lACCOUNT §fPara ver seus status utilize /account.");
					return true;
				}
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					p.sendMessage("§c§lERRO §fEste jogador não está online.");
				} else {
					p.sendMessage("");
					p.sendMessage("            §b§lACCOUNT");
					p.sendMessage("");
					p.sendMessage("§fJogador: §7" + target.getName());
					p.sendMessage("§fLiga: " + RankList.getScoreRank(target));
					p.sendMessage("§fRank: " + Grupo(target));
					p.sendMessage("");
					p.sendMessage("§fXP: §a" + SkyAPI.getXp(target));
					p.sendMessage("§fDoubleXP: §b0");
					p.sendMessage("§fMoedas: §b" + SkyAPI.getMoney(target));
					p.sendMessage("");
					p.sendMessage("§e§lCONTA: §fEstas são as informações de §7" + target.getName() + "§f.");
					p.sendMessage("");
					return true;
				}
			}
		}
		return false;
	}
}
