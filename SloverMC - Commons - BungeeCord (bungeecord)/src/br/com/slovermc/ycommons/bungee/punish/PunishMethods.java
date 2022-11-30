package br.com.slovermc.ycommons.bungee.punish;

import br.com.slovermc.ycommons.api.lenth.Lenth;
import br.com.slovermc.ycommons.api.mysql.functions.MySQLFunctions;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PunishMethods {

	public static enum KickResult {
		KICK_TEMPBANNED, KICK_BANNED;
	}

	@SuppressWarnings("deprecation")
	public static void resultDisconnect(ProxiedPlayer p, String author, String motive, String date, long lenth,
			KickResult result) {
		if (p == null) {
			return;
		} else {
			switch (result) {
			case KICK_BANNED:
				p.disconnect("§fVocê foi §4§lBANIDO PERMANENTEMENTE \n §fPor " + author + " na data "
						+ MySQLFunctions.getBans().getDate() + " \n §c§lMotivo: §f" + motive
						+ " \n §fFoi §e§lbanido injustamente§f? Peça §e§lAPPEAL§f em: \n §fhttp://loja.matchmc.com.br \n "
						+ "§fCompre seu §3§lUNBAN§f em http://loja.matchmc.com.br para ter o §3§lACESSO§f liberado.");
			case KICK_TEMPBANNED:
				p.disconnect("§fVocê foi §c§lBANIDO TEMPORARIAMENTE \n §fPor " + author + " na data "
						+ MySQLFunctions.getBans().getDate() + " \n §c§lMotivo: §f" + motive
						+ " \n §fExpira em: §c" + Lenth.convertLenth(lenth) + "\n \n §fFoi §e§lbanido injustamente§f? Peça §e§lAPPEAL§f em: \n §http://loja.matchmc.com.br \n "
						+ "§fCompre seu §3§lUNBAN§f em http://loja.matchmc.com.br para ter o §3§lACESSO§f liberado.");
			}
		}
	}
}
