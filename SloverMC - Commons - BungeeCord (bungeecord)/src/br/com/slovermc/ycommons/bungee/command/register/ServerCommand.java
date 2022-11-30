package br.com.slovermc.ycommons.bungee.command.register;

import br.com.slovermc.ycommons.bungee.command.CommandArgs;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ServerCommand extends CommandArgs {

	public ServerCommand(String name) {
		super(name);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (this.isPlayer(sender)) {
			ProxiedPlayer p = this.getPlayerFromSender(sender);
			if (p.getServer().getInfo().getName().equalsIgnoreCase("login")) {
				return;
			} else if (args.length == 0) {
				p.sendMessage("§b§lCONNECT§f Utilize /connect <servidor>.");
				return;
			} else if (args.length == 1) {
				if (p.getServer().getInfo().getName().equalsIgnoreCase(args[0])) {
					p.sendMessage("§b§lCONNECT§f Você ja está neste servidor!");
					return;
				} else if (BungeeCord.getInstance().getServerInfo(args[0]) == null) {
					p.sendMessage("§b§lCONNECT§f Este servidor está offline ou não existe!");
					return;
				} else {
					try {
						p.sendMessage("§b§lCONNECT§f Estamos tentando te conectar ao servidor §b"
								+ args[0].toUpperCase() + "§f!");
						p.connect(BungeeCord.getInstance().getServerInfo(args[0]));
						return;
					} catch (NullPointerException e) {
						p.sendMessage("§b§lCONNECT§f Este servidor está offline ou não existe!");
						return;
					}
				}
			} else {
				p.sendMessage("§b§lCONNECT§f Utilize /connect <servidor>.");
				return;
			}
		} else {
			sender.sendMessage("§c§lERRO§f Comando disponível apenas in-game!");
			return;
		}
	}
}
