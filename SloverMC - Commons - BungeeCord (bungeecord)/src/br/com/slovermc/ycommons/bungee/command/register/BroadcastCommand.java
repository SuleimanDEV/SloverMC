package br.com.slovermc.ycommons.bungee.command.register;

import br.com.slovermc.ycommons.api.account.Slover;
import br.com.slovermc.ycommons.api.account.SloverPlayer;
import br.com.slovermc.ycommons.bungee.command.CommandArgs;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;

public class BroadcastCommand extends CommandArgs {

	public BroadcastCommand(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (this.isPlayer(sender)) {
			SloverPlayer lp = Slover.getSloverPlayer(sender.getName());
			if (lp.getProxiedPlayer().getServer().getInfo().getName().equalsIgnoreCase("login")) {
				return;
			}
			if (!sender.hasPermission("matchmc.bc")) {
				lp.sendMessage("§c§lERRO§f Você não possui permissão para executar este comando.");
				return;
			} else if (args.length == 0) {
				lp.sendMessage("§4§lBROADCAST§f Utilize /bc <mensagem>.");
				return;
			} else if (args.length >= 1) {
				String msg = "";
				for (int i = 0; i < args.length; i++) {
					msg = msg + args[i] + " ";
				}
				BungeeCord.getInstance().broadcast("");
				BungeeCord.getInstance().broadcast("§4§lBROADCAST §f" + msg.replace("&", "§"));
				BungeeCord.getInstance().broadcast("");
				return;
			}
		}
	}
}
