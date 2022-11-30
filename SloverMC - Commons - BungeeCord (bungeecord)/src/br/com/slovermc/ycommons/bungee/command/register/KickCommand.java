package br.com.slovermc.ycommons.bungee.command.register;

import br.com.slovermc.ycommons.bungee.command.CommandArgs;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class KickCommand extends CommandArgs {

	public KickCommand(String name) {
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
		if (!sender.hasPermission("matchmc.kick")) {
			sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar este comando.");
			return;
		} else if (args.length == 0) {
			sender.sendMessage("§4§lKICK§f Utilize /kick <player> (motivo).");
			return;
		} else if (args.length >= 1) {
			ProxiedPlayer toKick = this.getPlayerFromArgs(args[0]);
			if (toKick == null) {
				sender.sendMessage("§c§lERRO§f Este jogador está offline.");
				return;
			} else {
				String motive = "";
				for (int i = 1; i < args.length; i++) {
					motive = motive + args[i] + " ";
				}
				if (motive == "") {
					motive = "não colocado";
				}
				toKick.disconnect("§fVocê foi §c§lKICKADO DO SERVIDOR! \n §fPor " + sender.getName() + " \n \n "
						+ "§c§lMotivo: §f" + motive);
				for (ProxiedPlayer online : BungeeCord.getInstance().getPlayers()) {
					if (sender.hasPermission("matchmc.kick") && !online.getServer().getInfo().getName().equalsIgnoreCase("login")) {
						//sender.sendMessage("§4§lKICK §fVocê kickou o jogador §c" + args[0] + " §fpor §cMotivo: " + motive);
						online.sendMessage("§4§lKICK §f" + args[0] + "("+ toKick.getUniqueId() + ") foi §c§lKICKADO §f por " + sender.getName() + " Motivo: " + motive);
					}
				}
				BungeeCord.getInstance().broadcast("§f" + args[0] + "§f foi §c§lKICKADO§f do servidor.");
				return;
			}
		}
	}
}
