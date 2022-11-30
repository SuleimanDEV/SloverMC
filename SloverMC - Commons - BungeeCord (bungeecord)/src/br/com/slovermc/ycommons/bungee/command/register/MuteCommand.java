package br.com.slovermc.ycommons.bungee.command.register;

import br.com.slovermc.ycommons.api.account.Slover;
import br.com.slovermc.ycommons.api.account.SloverPlayer;
import br.com.slovermc.ycommons.api.lenth.Lenth;
import br.com.slovermc.ycommons.api.mysql.connection.MySQL;
import br.com.slovermc.ycommons.api.mysql.functions.MySQLFunctions;
import br.com.slovermc.ycommons.bungee.command.CommandArgs;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MuteCommand extends CommandArgs {

	public MuteCommand(String name) {
		super(name);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (this.isPlayer(sender)) {
			ProxiedPlayer p = this.getPlayerFromSender(sender);
			if (p.getServer().getInfo().getName().equalsIgnoreCase("login")) {
				return;
			}
		} 
		if (!sender.hasPermission("matchmc.mute")) {
			sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar este comando.");
			return;
		} else if (!MySQL.isConnected()) {
			sender.sendMessage("§4§lERRO§f A MySQL nao esta §b§lCONECTADA!");
			return;
		} else if (args.length <= 1) {
			sender.sendMessage("§4§lMUTE§f Utilize /mute <jogador> (tempo) [motivo].");
			return;
		} else if (args.length > 2) {
			long time = 0L;
			String[] lenth = args[1].split(",");

			for (int i = 0; i < lenth.length; i++) {
				if (!lenth[i].replaceAll("[0-9]", "").contains("d") && !lenth[i].replaceAll("[0-9]", "").contains("h")
						&& !lenth[i].replaceAll("[0-9]", "").contains("m")
						&& !lenth[i].replaceAll("[0-9]", "").contains("s") && !args[1].equalsIgnoreCase("permanente")) {
					sender.sendMessage(
							"§c§lERRO§f Utilize ',' e letras para indicar tempo, exemplo: §e§l'30d,10h,5m,15s' §fou §e§l'permanente'§f.");
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
				SloverPlayer toMute = Slover.getSloverPlayer(args[0]);
				toMute.setMuted(sender.getName(), motive, MySQLFunctions.getBans().getDate(), -1);
				if (toMute.isOnline()) {
					toMute.sendMessage(
							"§4§lMUTE§f Você foi §3§lPERMANENTEMENTE MUTADO§f por §b" + sender.getName() + "§f.");
					toMute.sendMessage(
							" §fMotivo: §c" + motive + "§f. Compre seu §4§lUNMUTE§f em: http://store.slovermc.com.br");
				}
				for (ProxiedPlayer online : BungeeCord.getInstance().getPlayers()) {
					if (sender.hasPermission("matchmc.mute") && !online.getServer().getInfo().getName().equalsIgnoreCase("login")) {
						//sender.sendMessage("§4§lMUTE §fVocê mutou o jogador §c" + args[0] + " §fpor §cMotivo: " + motive);
						online.sendMessage("§4§lMUTE §f" + args[0] + " foi §c§lMUTADO §f por " + sender.getName() + " Motivo: " + motive);
					}
				}
				BungeeCord.getInstance().broadcast("§f" + args[0] + "§f foi §c§lMUTADO§f do servidor.");
				return;
			} else {
				SloverPlayer toMute = Slover.getSloverPlayer(args[0]);
				toMute.setMuted(sender.getName(), motive, MySQLFunctions.getBans().getDate(), totalLenth);
				if (toMute.isOnline()) {
					toMute.sendMessage(
							"§4§lMUTE§f Você foi §b§lTEMPORARIAMENTE MUTADO§f por §b" + sender.getName() + "§f.");
					toMute.sendMessage(
							" §fMotivo: §c" + motive + "§f. Tempo de §5§lMUTE§f: " + Lenth.convertLenth(totalLenth) + "§f.");
				}
				for (ProxiedPlayer staff : BungeeCord.getInstance().getPlayers()) {
					if (staff.hasPermission("matchmc.mute")
							&& !staff.getServer().getInfo().getName().equalsIgnoreCase("login")) {
						//sender.sendMessage("§4§lMUTE §fVocê mutou o jogador §c" + args[0] + " §fpor §cMotivo: " + motive);
						staff.sendMessage("§4§lMUTE §f" + args[0] + " foi §c§lMUTADO §f por " + sender.getName() + " Motivo: " + motive);
					}
				}
				BungeeCord.getInstance().broadcast("§f" + args[0] + "§f foi §c§lMUTADO§f do servidor.");
			}
		}
	}
}
