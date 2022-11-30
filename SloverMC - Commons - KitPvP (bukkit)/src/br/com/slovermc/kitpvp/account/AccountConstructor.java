package br.com.slovermc.kitpvp.account;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.bukkit.entity.Player;

import br.com.slovermc.kitpvp.battleplayer.BattlePlayerAPI;

public final class AccountConstructor {

	public static final String Prefix = "SloverAccounts.";

	public static void newBattlePlayerAccount(final Player bp) {
		newBattlePlayerAccount(bp.getName());
	}

	public static String BrasilHour() {
		TimeZone tz = TimeZone.getTimeZone("America/Brasil");
		Calendar calendar = GregorianCalendar.getInstance(tz);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return sdf.format(calendar.getTime());
	}
	
	public static String BrasilTime() {
		TimeZone tz = TimeZone.getTimeZone("America/Brasil");
		Calendar calendar = GregorianCalendar.getInstance(tz);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		return sdf.format(calendar.getTime());
	}

	public static void newBattlePlayerAccount(final String bpName) {
		if (AccountManager.hasAccount(bpName)) {
			return;
		}
		AccountFile.getAccountsFile().set(Prefix + bpName.toLowerCase() + ".UniqueID",
				BattlePlayerAPI.getBattlePlayerUuidString(BattlePlayerAPI.BattlePlayer(bpName).getUniqueId()));
		AccountFile.getAccountsFile().set(Prefix + bpName.toLowerCase() + ".OficialNickName", bpName);
		AccountFile.getAccountsFile().set(Prefix + bpName.toLowerCase() + ".Kills", 0);
		AccountFile.getAccountsFile().set(Prefix + bpName.toLowerCase() + ".Deaths", 0);
		AccountFile.getAccountsFile().set(Prefix + bpName.toLowerCase() + ".KillStreak", 0);
		AccountFile.getAccountsFile().set(Prefix + bpName.toLowerCase() + ".Xp", 0);
		AccountFile.getAccountsFile().set(Prefix + bpName.toLowerCase() + ".Money", 0);
		AccountFile.getAccountsFile().set(Prefix + bpName.toLowerCase() + ".Cash", 0);
		AccountFile.getAccountsFile().set(Prefix + bpName.toLowerCase() + ".FirstLogin", BrasilHour());
		AccountFile.getAccountsFile().set(Prefix + bpName.toLowerCase() + ".LastLoin", BrasilHour());
		AccountFile.getAccountsFile().set(Prefix + bpName.toLowerCase() + ".Type", "Gamer");
		AccountFile.getAccountsFile().set(Prefix + bpName.toLowerCase() + ".YoutubeChannel", "Nenhum");
		AccountFile.getAccountsFile().set(Prefix + bpName.toLowerCase() + ".Twitter", "Nenhum");
		AccountFile.getAccountsFile().set(Prefix + bpName.toLowerCase() + ".Email", "Nenhum");
		AccountFile.saveAccountsFile();
	}
}