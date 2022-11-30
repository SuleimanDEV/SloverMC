package br.com.slovermc.ycommons.bungee.command.register;

import br.com.slovermc.ycommons.api.account.Slover;
import br.com.slovermc.ycommons.api.account.SloverPlayer;
import br.com.slovermc.ycommons.api.mysql.connection.MySQL;
import br.com.slovermc.ycommons.api.mysql.functions.MySQLFunctions;
import br.com.slovermc.ycommons.bungee.command.CommandArgs;
import br.com.slovermc.ycommons.bungee.punish.PunishMethods;
import br.com.slovermc.ycommons.bungee.punish.PunishMethods.KickResult;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BanCommand extends CommandArgs {

	public BanCommand(String name) {
		super(name);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(net.md_5.bungee.api.CommandSender sender, String[] args) {
		if (this.isPlayer(sender)) {
			ProxiedPlayer p = this.getPlayerFromSender(sender);
			if (p.getServer().getInfo().getName().equalsIgnoreCase("login")) {
				return;
			}
		}
		if (!sender.hasPermission("matchmc.ban")) {
			sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar este comando.");
			return;
		} else if (!MySQL.isConnected()) {
			sender.sendMessage("§4§lERRO§f A MySQL nao esta §b§lCONECTADA!");
			return;
		} else if (args.length <= 1) {
			sender.sendMessage("§4§lBAN§f Utilize /ban <jogador> (tempo) [motivo].");
			return;
		} else if (args.length > 2) {
			ProxiedPlayer p = this.getPlayerFromArgs(args[0]);
			long time = 0L;
			String[] lenth = args[1].split(",");

			for (int i = 0; i < lenth.length; i++) {
				if (!lenth[i].replaceAll("[0-9]", "").contains("d") && !lenth[i].replaceAll("[0-9]", "").contains("h")
						&& !lenth[i].replaceAll("[0-9]", "").contains("m")
						&& !lenth[i].replaceAll("[0-9]", "").contains("s") && !args[1].equalsIgnoreCase("permanente")) {
					sender.sendMessage(
							"§4§lERRO§f Utilize ',' e letras para indicar tempo, exemplo: §e§l'30d,10h,5m,15s' §fou §e§l'permanente'§f.");
					return;
				}
				long days = 0L;
				long hours = 0L;
				long minutes = 0L;
				long seconds = 0L;
				if (!args[1].equalsIgnoreCase("permanente")) {
					if (lenth[i].replaceAll("[0-9]", "").contains("d")) {
						days = Integer.parseInt(lenth[i].replaceAll("[A-Za-z]", "")) * 86400;
					}
					if (lenth[i].replaceAll("[0-9]", "").contains("h")) {
						hours = Integer.parseInt(lenth[i].replaceAll("[A-Za-z]", "")) * 3600;
					}
					if (lenth[i].replaceAll("[0-9]", "").contains("m")) {
						minutes = Integer.parseInt(lenth[i].replaceAll("[A-Za-z]", "")) * 60;
					}
					if (lenth[i].replaceAll("[0-9]", "").contains("s")) {
						seconds = Integer.parseInt(lenth[i].replaceAll("[A-Za-z]", "")) * 1;
					}
					time += days + hours + minutes + seconds;
				}
			}
			long totalLenth = 0L;
			if (!args[1].equalsIgnoreCase("permanente")) {
				totalLenth = time * 1000L + System.currentTimeMillis();
			}
			String motive = "";
			for (int i = 2; i < args.length; i++) {
				motive = motive + args[i] + " ";
			}
			if (motive == "") {
				motive = "não colocado";
			}
			if (args[1].equalsIgnoreCase("permanente")) {
				SloverPlayer toBan = Slover.getSloverPlayer(args[0]);
				toBan.register();
				if (toBan.isOnline()) {
					PunishMethods.resultDisconnect(p, sender.getName(), motive, MySQLFunctions.getBans().getDate(), -1,
							KickResult.KICK_BANNED);
				}
				toBan.setBanned(sender.getName(), motive, MySQLFunctions.getBans().getDate(), -1);
				for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
					if (staff.hasPermission("matchmc.ban")
							&& !staff.getServer().getInfo().getName().equalsIgnoreCase("login")) {
						//sender.sendMessage("§4§lBAN §fVocê baniu o jogador §c" + args[0] + " §fpor §cMotivo: " + motive);
						staff.sendMessage("§4§lBAN §f" + args[0] + " foi §c§lBANIDO §f por " + sender.getName() + " Motivo: " + motive);
					}
				}
				BungeeCord.getInstance().broadcast("§f" + args[0] + "§f foi §c§lBANIDO PERMANENTEMENTE§f do servidor.");
				return;
			} else {
				SloverPlayer toBan = Slover.getSloverPlayer(args[0]);
				toBan.register();
				if (toBan.isOnline()) {
					PunishMethods.resultDisconnect(p, sender.getName(), motive, MySQLFunctions.getBans().getDate(),
							totalLenth, KickResult.KICK_TEMPBANNED);
				}
				toBan.setBanned(sender.getName(), motive, MySQLFunctions.getBans().getDate(), totalLenth);
				for (ProxiedPlayer online : BungeeCord.getInstance().getPlayers()) {
					if (online.hasPermission("matchmc.ban")
							&& !online.getServer().getInfo().getName().equalsIgnoreCase("login")) {
						//sender.sendMessage("§4§lBAN §fVocê baniu o jogador §c" + args[0] + "§f por §cMotivo: " + motive);
						online.sendMessage("§4§lBAN §f" + args[0] + " foi §c§lBANIDO TEMPORARIAMENTE §f por " + sender.getName() + " Motivo: " + motive);
					}
				}
				BungeeCord.getInstance().broadcast("§f" + args[0] + "§f foi §c§lBANIDO TEMPORARIAMENTE§f do servidor.");
			}
		}
	}
}
