package br.com.slovermc.kitpvp.battleplayer;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public final class BattlePlayerAPI {

	public static final boolean validate(String characters) {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9_]{1,16}");
		Matcher matcher = pattern.matcher(characters);
		return matcher.matches();
	}

	public static final int Mojang_MaxPlayerNameLenth = 16;

	public static final int getMaxMojangPlayerNameLenth() {
		return Mojang_MaxPlayerNameLenth;
	}

	public static final Player BattlePlayer(final Player bp) {
		return bp;
	}
	
	public static final Player BattlePlayer(final String bpName) {
		return Bukkit.getPlayer(bpName);
	}
	
	@SuppressWarnings("deprecation")
	public static final OfflinePlayer BattlePlayerOffline(final String bpName) {
		return Bukkit.getOfflinePlayer(bpName);
	}

	public static final String getBattlePlayerName(final Player bpName) {
		if (bpName.getName().length() > getMaxMojangPlayerNameLenth()) {
			return bpName.getName().substring(0, 16);
		}
		return bpName.getName();
	}
	
	public static final String getBattlePlayerName(final OfflinePlayer bpName) {
		if (bpName.getName().length() > getMaxMojangPlayerNameLenth()) {
			return bpName.getName().substring(0, 16);
		}
		return bpName.getName();
	}

	public static final UUID getBattlePlayerUuid(final Player bp) {
		return bp.getUniqueId();
	}
	
	public static final UUID getBattlePlayerUuid(final OfflinePlayer bp) {
		return bp.getUniqueId();
	}

	public static final String getBattlePlayerUuidString(final UUID uuid) {
		return uuid.toString();
	}

	public final boolean validBattlePlayer(final Player bp) {
		if (getBattlePlayerName(bp).length() > 16) {
			return false;
		} else if (!validate(getBattlePlayerName(bp))) {
			return false;
		} else {
			return true;
		}
	}
}
