package br.com.slovermc.ycommons.bungee.command.register;

import br.com.slovermc.ycommons.api.account.Slover;
import br.com.slovermc.ycommons.api.account.SloverPlayer;
import br.com.slovermc.ycommons.bungee.command.CommandArgs;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class YoutuberCommand extends CommandArgs {

	public YoutuberCommand(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (this.isPlayer(sender)) {
			SloverPlayer lp = Slover.getSloverPlayer(sender.getName());
			if (this.isPlayer(sender)) {
				ProxiedPlayer p = this.getPlayerFromSender(sender);
				if (p.getServer().getInfo().getName().equalsIgnoreCase("login")) {
					return;
				}
			}
			if (args.length >= 0) {
				lp.sendMessage("              §6§lREQUESITOS           ");
				lp.sendMessage("");
				lp.sendMessage(" §fRequisitos para §3§lYOUTUBER+§f (todos necessários)");
				lp.sendMessage("    §fFeed back §c§lBOM§f, boa §1§lMETA§f de like.");
				lp.sendMessage("   §fE cerca de §e2000 §lINSCRITOS§f.");
				lp.sendMessage("");
				lp.sendMessage(" §fRequisitos para §b§lYOUTUBER§f (todos necessários)");
				lp.sendMessage("   §fFeed back §c§lBOM§f, boa §1§lMETA§f de like.");
				lp.sendMessage("   §fE cerca de §e900 §lINSCRITOS§f.");
				lp.sendMessage("");
				lp.sendMessage(" §fRequisitos para §6§lPRO §f(todos necessários)");
				lp.sendMessage("    §fFeed back §c§lBOM§f, boa §1§lMETA§f de like.");
				lp.sendMessage("   §fE cerca de §e500 §lINSCRITOS§f.");
				lp.sendMessage("");
				lp.sendMessage(" §fRequisitos para §a§lLIGHT§f (todos necessários)");
				lp.sendMessage("    §fFeed back §c§lBOM§f, boa §1§lMETA§f de like.");
				lp.sendMessage("   §fE cerca de §e400 §lINSCRITOS§f.");
				lp.sendMessage("");
				return;
			}
		}
	}
}
