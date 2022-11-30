package br.com.slovermc.login.api;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public final class API {

	public static final boolean hasPermission(final Player p, final String permission) {
		return p.hasPermission(permission);
	}

	public static final void sendPlayerMessage(final Player p, final String message) {
		p.sendMessage(message);
	}

	public static final void setPlayerGameMode(final Player p, final GameMode mode) {
		p.setGameMode(mode);
	}

	public static final Player getPlayer(final String name) {
		return Bukkit.getPlayer(name);
	}

	@SuppressWarnings("deprecation")
	public static final OfflinePlayer getOfflinePlayer(final String name) {
		return Bukkit.getOfflinePlayer(name);
	}

	public static String timeLeftMethod(Long log) {
		String time = "";
		long totalLenth = log;
		long timeLefting = totalLenth - System.currentTimeMillis();
		long seconds = timeLefting / 1000L;
		time = timeRemaining(time, seconds);
		return time;
	}

	public static String timeRemaining(String restingTime, long lenth) {

		int days = (int) TimeUnit.SECONDS.toDays(lenth);
		long hours = TimeUnit.SECONDS.toHours(lenth) - days * 24;
		long minutes = TimeUnit.SECONDS.toMinutes(lenth) - TimeUnit.SECONDS.toHours(lenth) * 60L;
		long seconds = TimeUnit.SECONDS.toSeconds(lenth) - TimeUnit.SECONDS.toMinutes(lenth) * 60L;

		String totalDay = days + " dia(s) ";

		String totalHours = hours + " hora(s) ";

		String totalMinutes = minutes + " minuto(s) ";

		String totalSeconds = seconds + " segundo(s) ";

		if (days == 0) {
			totalDay = "";
		}
		if (hours == 0L) {
			totalHours = "";
		}
		if (minutes == 0L) {
			totalMinutes = "";
		}
		if (seconds == 0L) {
			totalSeconds = "";
		}
		restingTime = totalDay + totalHours + totalMinutes + totalSeconds;
		restingTime = restingTime.trim();
		if (restingTime.equals("")) {
			restingTime = "§c§lNunca";
		}
		return restingTime;
	}
}
