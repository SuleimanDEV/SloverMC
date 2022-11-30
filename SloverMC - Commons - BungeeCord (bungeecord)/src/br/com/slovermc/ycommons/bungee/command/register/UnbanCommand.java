package br.com.slovermc.ycommons.bungee.command.register;

import br.com.slovermc.ycommons.api.account.Slover;
import br.com.slovermc.ycommons.api.account.SloverPlayer;
import br.com.slovermc.ycommons.bungee.command.CommandArgs;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class UnbanCommand extends CommandArgs {

	public UnbanCommand(String name) {
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
	
		if (!sender.hasPermission("matchmc.unban")) {
			sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar este comando.");
			return;
		} else if (args.length == 0) {
			sender.sendMessage("§4§lUNBAN§f Utilize /unban <player>.");
			return;
		} else if (args.length == 1) {
			SloverPlayer toUnban = Slover.getSloverPlayer(args[0]);
			if (!toUnban.isBanned()) {
				sender.sendMessage("§c§lERRO§f O player §e" + toUnban.getName() + "§f não esta banido.");
				return;
			} else {
				toUnban.unban();
				for (ProxiedPlayer online : BungeeCord.getInstance().getPlayers()) {
					if (sender.hasPermission("matchmc.unban") && !online.getServer().getInfo().getName().equalsIgnoreCase("login")) {
					//	sender.sendMessage("§4§lUNBAN §fVocê desbaniu o jogador §c" + args[0]);
						online.sendMessage("§4§lUNBAN §f" + args[0] + " foi §c§lDESBANIDO §f por " + sender.getName());
					}
				}
				BungeeCord.getInstance().broadcast("§f" + args[0] + "§f foi §c§lDESBANIDO§f do servidor.");
				return;
			}
		} else {
			sender.sendMessage("§4§lUNBAN§f Utilize /unban <player>.");
			return;
		}
	}
}
