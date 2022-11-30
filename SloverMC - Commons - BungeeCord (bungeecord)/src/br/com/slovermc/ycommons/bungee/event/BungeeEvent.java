package br.com.slovermc.ycommons.bungee.event;

import br.com.slovermc.ycommons.api.account.Slover;
import br.com.slovermc.ycommons.api.account.SloverPlayer;
import br.com.slovermc.ycommons.api.lenth.Lenth;
import br.com.slovermc.ycommons.api.mysql.functions.MySQLFunctions;
import br.com.slovermc.ycommons.bungee.command.register.StaffChatCommand;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class BungeeEvent implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onLogin(PreLoginEvent event) {
		SloverPlayer lp = Slover.getSloverPlayer(event.getConnection().getName());
		lp.unbanIfExpires();

		lp.register(event.getConnection().getAddress().getHostString());

		if (lp.isBanned()) {
			String author = MySQLFunctions.getBans().getBanAuthor(lp.getName());
			String motive = MySQLFunctions.getBans().getBanMotive(lp.getName());
			String date = MySQLFunctions.getBans().getBanDate(lp.getName());
			long lenth = lp.getBanLenth();

			event.setCancelReason((lp.isTempBanned() ? "§fVocê foi §c§lBANIDO TEMPORARIAMENTE \n §fPor " + author
					+ " na data " + date + " \n §c§lMotivo: §f" + motive + " \n §fExpira em: §c"
					+ Lenth.convertLenth(lenth)
					+ " \n \n §fFoi §e§lbanido injustamente§f? Peça §e§lAPPEAL§f em: \n §fhttp://loja.matchmc.com.br \n "
					+ "§fCompre seu §3§lUNBAN§f em http://loja.matchmc.com.br para ter o §3§lACESSO§f liberado."
					: "§fVocê foi §4§lBANIDO PERMANENTEMENTE \n §fPor " + author + " na data " + date
							+ " \n §c§lMotivo: §f" + motive
							+ " \n \n §fFoi §e§lbanido injustamente§f? Peça §e§lAPPEAL§f em: \n §fhttp://loja.matchmc.com.br \n "
							+ "§fCompre seu §3§lUNBAN§f em http://loja.matchmc.com.br para ter o §3§lACESSO§f liberado."));
			event.setCancelled(true);
		}

		if (lp.getName().equalsIgnoreCase("CONSOLE")) {
			event.getConnection().disconnect("-");
		}
		
		lp = null;
	}
	

	@EventHandler
	  public void onServerListPing(ProxyPingEvent event) {
	      ServerPing motdserver = event.getResponse();
	      motdserver.setDescription("      §6§lMatch§f§lMC §7» §eServidores de Minecraft                       §3§lNOVIDADE: §6§lKITPVP COM DOUBLEXP!");
	      event.setResponse(motdserver);
	    }

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onChat(ChatEvent event) {
		if (event.getSender() instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) event.getSender();
			SloverPlayer lp = Slover.getSloverPlayer(p.getName());

			lp.unmuteIfExpires();

			if (lp.isMuted() && !event.getMessage().startsWith("/") && !event.getMessage().startsWith("//")
					&& !StaffChatCommand.isStaffChating(lp.getProxiedPlayer())) {
				if (lp.isTempMuted()) {
					event.setCancelled(true);
					String author = MySQLFunctions.getBans().getMuteAuthor(lp.getName());
					String motive = MySQLFunctions.getBans().getMuteMotive(lp.getName());
					long lenth = lp.getMuteLenth();

					lp.sendMessage("§9§lMUTE§f Você foi §b§lTEMPORARIAMENTE MUTADO§f por " + author + ".");
					lp.sendMessage(" §fMotivo: " + motive + ". Tempo de §5§lMUTE§f: §9" + Lenth.convertLenth(lenth));
					return;
				} else {
					event.setCancelled(true);
					String author = MySQLFunctions.getBans().getMuteAuthor(lp.getName());
					String motive = MySQLFunctions.getBans().getMuteMotive(lp.getName());

					lp.sendMessage("§4§lMUTE§f Você foi §3§lPERMANENTEMENTE MUTADO§f por §b" + author + "§f.");
					lp.sendMessage(
							" §fMotivo: §e" + motive + "§f. Compre seu §4§lUNMUTE§f em: http://loja.matchmc.com.br");
					return;
				}
			} else if (StaffChatCommand.isStaffChating(p) && !event.getMessage().startsWith("/")
					&& !event.getMessage().startsWith("//")) {
				if (!p.hasPermission("matchmc.sc")) {
					StaffChatCommand.inSc.remove(p);
					event.setCancelled(false);
				} else {
					event.setCancelled(true);
					for (ProxiedPlayer online : BungeeCord.getInstance().getPlayers()) {
						if (!online.getServer().getInfo().getName().equalsIgnoreCase("login")) {
							if (online.hasPermission("matchmc.sc")) {
								online.sendMessage("§b[§eS§b] §7" + p.getName() + "§e: "
										+ event.getMessage().replaceAll("&", "§"));
							}
						}
					}
				}
			}
			lp = null;
		}
	}
}
