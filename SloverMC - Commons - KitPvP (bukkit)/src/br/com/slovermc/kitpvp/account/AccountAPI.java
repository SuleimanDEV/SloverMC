package br.com.slovermc.kitpvp.account;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import br.com.slovermc.kitpvp.battleplayer.BattlePlayerAPI;

public final class AccountAPI {

	public static final void updateLastLogin(final Player bp) {
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".LastLoin",
				AccountConstructor.BrasilHour());
		AccountFile.saveAccountsFile();
	}

	public static final void addBattlePlayerMoney(final Player bp, final int money) {
		int bpstatus = AccountFile.getAccountsFile()
				.getInt(AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Money");
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Money",
				bpstatus + money);
		AccountFile.saveAccountsFile();
	}
	
	public static final void addBattlePlayerDoubleXp(final Player bp, final int doublexp) {
		int bpstatus = AccountFile.getAccountsFile()
				.getInt(AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".DoubleXp");
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".DoubleXp",
				bpstatus + doublexp);
		AccountFile.saveAccountsFile();
	}
	
	public static final void addBattlePlayerDoubleXp(final OfflinePlayer bp, final int doublexp) {
		int bpstatus = AccountFile.getAccountsFile()
				.getInt(AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".DoubleXp");
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".DoubleXp",
				bpstatus + doublexp);
		AccountFile.saveAccountsFile();
	}
	
	public static final void removeBattlePlayerDoubleXp(final Player bp, final int doublexp) {
		int bpstatus = AccountFile.getAccountsFile()
				.getInt(AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".DoubleXp");
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".DoubleXp",
				bpstatus - doublexp);
		AccountFile.saveAccountsFile();
	}
	
	public static final void removeBattlePlayerDoubleXp(final OfflinePlayer bp, final int doublexp) {
		int bpstatus = AccountFile.getAccountsFile()
				.getInt(AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".DoubleXp");
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".DoubleXp",
				bpstatus - doublexp);
		AccountFile.saveAccountsFile();
	}

	public static final void addBattlePlayerKillStreak(final Player bp, final int streak) {
		int bpstatus = AccountFile.getAccountsFile().getInt(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".KillStreak");
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".KillStreak",
				bpstatus + streak);
		KsAPI.addKillStreak(bp, streak);
		AccountFile.saveAccountsFile();
	}
	
	public static final void addBattlePlayerKills(final Player bp, final int kills) {
		int bpstatus = AccountFile.getAccountsFile().getInt(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Kills");
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Kills",
				bpstatus + kills);
		KillsAPI.addKills(bp, kills);
		AccountFile.saveAccountsFile();
	}
	
	public static final void addBattlePlayerDeath(final Player bp, final int deaths) {
		int bpstatus = AccountFile.getAccountsFile().getInt(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Deaths");
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Deaths",
				bpstatus + deaths);
		AccountFile.saveAccountsFile();
	}

	public static final void addBattlePlayerCash(final Player bp, final int cash) {
		int bpstatus = AccountFile.getAccountsFile()
				.getInt(AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Cash");
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Cash",
				bpstatus + cash);
		AccountFile.saveAccountsFile();
	}

	public static final void addBattlePlayerKillStreak(final OfflinePlayer bp, final int streak) {
		int bpstatus = AccountFile.getAccountsFile().getInt(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".KillStreak");
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".KillStreak",
				bpstatus + streak);
		AccountFile.saveAccountsFile();
	}

	public static final void addBattlePlayerMoney(final OfflinePlayer bp, final int money) {
		int bpstatus = AccountFile.getAccountsFile()
				.getInt(AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Money");
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Money",
				bpstatus + money);
		AccountFile.saveAccountsFile();
	}

	public static final void addBattlePlayerCash(final OfflinePlayer bp, final int cash) {
		int bpstatus = AccountFile.getAccountsFile()
				.getInt(AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Cash");
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Cash",
				bpstatus + cash);
		AccountFile.saveAccountsFile();
	}

	public static final void addBattlePlayerYoutubeChannel(final Player bp, final String channel) {
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".YoutubeChannel",
				channel);
		AccountFile.saveAccountsFile();
	}

	public static final void addBattlePlayerTwitter(final Player bp, final String twitter) {
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Twitter",
				twitter);
		AccountFile.saveAccountsFile();
	}

	public static final void addBattlePlayerEmail(final Player bp, final String email) {
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Email", email);
		AccountFile.saveAccountsFile();
	}

	public static final void setBattlePlayerType(final Player bp, final String type) {
		AccountFile.getAccountsFile()
				.set(AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Type", type);
		AccountFile.saveAccountsFile();
	}

	public static final void removeBattlePlayerKillStreak(final OfflinePlayer bp) {
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".KillStreak", 0);
		KsAPI.removeKillStreak(bp);
		AccountFile.saveAccountsFile();
	}
	
	public static final void removeBattlePlayerKillStreak(final Player bp) {
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".KillStreak", 0);
		KsAPI.removeKillStreak(bp);
		AccountFile.saveAccountsFile();
	}

	public static final void removeBattlePlayerMoney(final Player bp, final int money) {
		int bpstatus = AccountFile.getAccountsFile()
				.getInt(AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Money");
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Money",
				bpstatus - money);
		AccountFile.saveAccountsFile();
	}

	public static final void removeBattlePlayerKills(final Player bp, final int kills) {
		int bpstatus = AccountFile.getAccountsFile()
				.getInt(AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Kills");
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Kills",
				bpstatus - kills);
		KillsAPI.removeKills(bp, kills);
		AccountFile.saveAccountsFile();
	}

	public static final void removeBattlePlayerCash(final Player bp, final int cash) {
		int bpstatus = AccountFile.getAccountsFile()
				.getInt(AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Cash");
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Cash",
				bpstatus - cash);
		AccountFile.saveAccountsFile();
	}

	public static final void removeBattlePlayerMoney(final OfflinePlayer bp, final int money) {
		int bpstatus = AccountFile.getAccountsFile()
				.getInt(AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Money");
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Money",
				bpstatus - money);
		AccountFile.saveAccountsFile();
	}

	public static final void removeBattlePlayerYoutubeChannel(final Player bp) {
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".YoutubeChannel",
				"Nenhum");
		AccountFile.saveAccountsFile();
	}

	public static final void removeBattlePlayerTwitter(final Player bp, final String twitter) {
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Twitter",
				"Nenhum");
		AccountFile.saveAccountsFile();
	}

	public static final void removeBattlePlayerEmail(final Player bp, final String email) {
		AccountFile.getAccountsFile().set(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Email", "Nenhum");
		AccountFile.saveAccountsFile();
	}

	public static final int getBattlePlayerMoney(final Player bp) {
		return AccountFile.getAccountsFile()
				.getInt(AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Money");
	}

	public static final int getBattlePlayerXp(final Player bp) {
		return AccountFile.getAccountsFile()
				.getInt(AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Xp");
	}
	
	public static final int getBattlePlayerDoubleXp(final Player bp) {
		return AccountFile.getAccountsFile()
				.getInt(AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".DoubleXp");
	}
	
	public static final int getBattlePlayerDoubleXp(final OfflinePlayer bp) {
		return AccountFile.getAccountsFile()
				.getInt(AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".DoubleXp");
	}

	public static final String getBattlePlayerType(final Player bp) {
		return AccountFile.getAccountsFile()
				.getString(AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Type");
	}

	public static final String getBattlePlayerYoutubeChannel(final Player bp) {
		return AccountFile.getAccountsFile().getString(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".YoutubeChannel");
	}

	public static final String getBattlePlayerTwitter(final Player bp) {
		return AccountFile.getAccountsFile().getString(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Twitter");
	}

	public static final String getBattlePlayerFirstLogin(final Player bp) {
		return AccountFile.getAccountsFile().getString(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".FirstLogin");
	}

	public static final String getBattlePlayerLastLogin(final Player bp) {
		return AccountFile.getAccountsFile().getString(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".LastLogin");
	}
	
	public static final String getBattlePlayerUuid(final Player bp) {
		return AccountFile.getAccountsFile().getString(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".UniqueID");
	}

	public static final int getBattlePlayerMoney(final OfflinePlayer bp) {
		return AccountFile.getAccountsFile()
				.getInt(AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Money");
	}

	public static final int getBattlePlayerKillStreak(final OfflinePlayer bp) {
		return AccountFile.getAccountsFile().getInt(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".KillStreak");
	}

	public static final String getBattlePlayerType(final OfflinePlayer bp) {
		return AccountFile.getAccountsFile()
				.getString(AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Type");
	}

	public static final String getBattlePlayerYoutubeChannel(final OfflinePlayer bp) {
		return AccountFile.getAccountsFile().getString(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".YoutubeChannel");
	}

	public static final String getBattlePlayerTwitter(final OfflinePlayer bp) {
		return AccountFile.getAccountsFile().getString(
				AccountConstructor.Prefix + BattlePlayerAPI.getBattlePlayerName(bp).toLowerCase() + ".Twitter");
	}
}