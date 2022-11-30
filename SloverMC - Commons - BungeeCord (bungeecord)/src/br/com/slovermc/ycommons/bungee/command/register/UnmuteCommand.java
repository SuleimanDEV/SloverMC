package br.com.slovermc.ycommons.bungee.command.register;

import br.com.slovermc.ycommons.api.account.Slover;
import br.com.slovermc.ycommons.api.account.SloverPlayer;
import br.com.slovermc.ycommons.bungee.command.CommandArgs;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class UnmuteCommand extends CommandArgs {

	public UnmuteCommand(String name) {
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
		if (!sender.hasPermission("matchmc.unmute")) {
			sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar este comando.");
			return;
		} else if (args.length == 0) {
			sender.sendMessage("§4§lUNMUTE§f Utilize /unmute <player>.");
			return;
		} else if (args.length == 1) {
			SloverPlayer toUnmute = Slover.getSloverPlayer(args[0]);
			if (!toUnmute.isMuted()) {
				sender.sendMessage("§4§lERRO§f O player " + toUnmute.getName() + "§f nao esta §b§lmutado.");
				return;
			} else {
				toUnmute.unmute();
				for (ProxiedPlayer online : BungeeCord.getInstance().getPlayers()) {
					if (sender.hasPermission("matchmc.unmute") && !online.getServer().getInfo().getName().equalsIgnoreCase("login")) {
						//sender.sendMessage("§4§lUNMUTE §fVocê desmutou o jogador §c" + args[0]);
						online.sendMessage("§4§lUNMUTE §f" + args[0] + " foi §c§lDESMUTADO §f por " + sender.getName());
					}
				}
				BungeeCord.getInstance().broadcast("§f" + args[0] + "§f foi §c§lDESMUTADO§f do servidor.");
				return;
			}
		} else {
			sender.sendMessage("§4§lUNMUTE§f Utilize /unmute <player>.");
			return;
		}
	}
}
