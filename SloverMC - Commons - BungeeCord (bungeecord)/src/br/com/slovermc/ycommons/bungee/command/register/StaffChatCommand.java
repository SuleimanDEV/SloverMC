package br.com.slovermc.ycommons.bungee.command.register;

import java.util.ArrayList;

import br.com.slovermc.ycommons.api.account.Slover;
import br.com.slovermc.ycommons.api.account.SloverPlayer;
import br.com.slovermc.ycommons.bungee.command.CommandArgs;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class StaffChatCommand extends CommandArgs {

	public StaffChatCommand(String name) {
		super(name);
	}

	public static final ArrayList<ProxiedPlayer> inSc = new ArrayList<>();

	public static boolean isStaffChating(ProxiedPlayer p) {
		return inSc.contains(p);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (this.isPlayer(sender)) {
			SloverPlayer lp = Slover.getSloverPlayer(sender.getName());
			ProxiedPlayer p = this.getPlayerFromSender(sender);
			if (lp.getProxiedPlayer().getServer().getInfo().getName().equalsIgnoreCase("login")) {
				return;
			}
			if (!p.hasPermission("matchmc.sc")) {
				sender.sendMessage("§c§lERRO §fVocê não possui permissão para executar este comando.");
				return;
			} else if (args.length >= 0) {
				if (isStaffChating(p)) {
					inSc.remove(p);
					p.sendMessage("§e§lSTAFFCHAT§f Você saiu do staffchat.");
					return;
				} else {
					inSc.add(p);
					p.sendMessage("§e§lSTAFFCHAT§f Você entrou no staffchat.");
					return;
				}
			}
		} else {
			sender.sendMessage("§c§lERRO§f Comando disponível apenas in-game.");
			return;
		}
	}
}
