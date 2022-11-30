package groupmanager.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import groupmanager.api.IGroupApi;
import groupmanager.databaseapi.SqlConnection;
import groupmanager.main.Main;

public class cSetGroup implements CommandExecutor {
	
	public static boolean validGroup(final String args) {
		final String[] grouplist = {"dono", "developer", "diretor", "admin", "gerente", "modgc", "mod", "trial", "ajudante", "youtuber+"
				, "youtuber","designer", "builder", "sapphire", "elite", "beta", "pro", "light", "vip", "mvp", "membro"};
		for (String list : grouplist) {
			if (args.equalsIgnoreCase(list)) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings({ "deprecation", "null" })
	@Override
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		if (cmd.getName().equalsIgnoreCase("setgroup") || cmd.getName().equalsIgnoreCase("groupset")) {
			if (!sender.hasPermission("slover.setgroup")) {
				sender.sendMessage("§c§lPERMISSAO§f Você não tem §c§lPERMISSAO§f para usar este comando!");
				return true;
			}
			if (args.length == 0) {
				sender.sendMessage("§6§lGROUPSET§f Para setar um rank utilize /setgroup <player> (grupo)");
				return true;
			}
			if (args.length == 1) {
				sender.sendMessage("§6§lGROUPSET§f Sintaxe incompleta: §3§l/setgroup " + args[0] + " <grupo>");
				return true;
			}
			if (args.length == 2) {
				final Player grouped = Bukkit.getPlayer(args[0]);
				if (SqlConnection.connection == null) {
					sender.sendMessage("§6§lGROUPSET§f A §a§lCONEXAO§f com a §b§lMYSQL§f está §c§lNULA!§f Não será possível setar §e§lGRUPOS!");
					return true;
				}
				if (!validGroup(args[1])) {
					sender.sendMessage("§6§lGROUPSET§f Este grupo não existe entre nosso CÓDIGOS!");
					return true;
				}
				if (grouped == null) {
					final OfflinePlayer goff = Bukkit.getOfflinePlayer(args[0]);
					if (goff.getName().length() > 16) {
						sender.sendMessage("§6§lGROUPSET§f Este nick possui mais de §9§l16 CARATERES!§f Não é um nick §2§lVALIDO!");
						return true;
					}
					if (!Main.database.verifyPlayerRegister(goff.getName())) {
						Main.database.newPlayer(goff);
					}
					Main.database.setGroup(goff, args[1].toUpperCase());
					sender.sendMessage("§6§lGROUPSET§f Você §balterou§ o cargo de" + grouped.getName() + grouped.getUniqueId() + "§f para o cargo de " + IGroupApi.convertGroupColor(args[1]));
					return true;
				}
				if (grouped.getName().length() > 16) {
					sender.sendMessage("§6§lGROUPSET§f Este nick possui mais de §9§l16 CARATERES!§f Não é um nick §2§lVALIDO!");
					return true;
				}
				if (!Main.database.verifyPlayerRegister(grouped.getName())) {
					Main.database.newPlayer(grouped);
				}
				IGroupApi.unsetPermissions(grouped, Main.database.getGroup(grouped));
				Main.database.setGroup(grouped, args[1].toUpperCase());
				IGroupApi.setPermissions(grouped, args[1]);
				grouped.sendMessage("§6§lGROUPSET§f Parabéns, você teve seu rank alterado para " + IGroupApi.convertGroupColor(args[1]));
				sender.sendMessage("§6§lGROUPSET§f Você alterou o §6§lGRUPO§f do §e " + grouped.getName() + "§e(" + grouped.getUniqueId() + "§e) "+  "§f para " + IGroupApi.convertGroupColor(args[1]));
				return true;
			}
			if (args.length > 2) {
				sender.sendMessage("§6§lGROUPSET§f Para setar um rank para o jogador utilize: §e/setgroup <player> (grupo)");
				return true;
			}
		}
		return false;
	}	
}