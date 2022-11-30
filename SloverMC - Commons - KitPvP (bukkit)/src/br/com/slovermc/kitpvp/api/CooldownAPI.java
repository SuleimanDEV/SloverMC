package br.com.slovermc.kitpvp.api;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import br.com.slovermc.kitpvp.BukkitMain;
import br.com.slovermc.kitpvp.utils.TimeConverter;

public final class CooldownAPI {

	public static final HashMap<String, Long> Cooldown = new HashMap<>();
	public static final HashMap<Player, Integer> task = new HashMap<>();

	public static final void runCooldown(final Player bp, final int cooldown) {
		if (Cooldown.containsKey(bp.getName())) {
			Cooldown.remove(bp.getName());
		}
		Cooldown.put(bp.getName(), Long.valueOf(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(cooldown)));
		Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitMain.getPlugin(), new Runnable() {
			@Override
			public void run() {
				if (Cooldown.containsKey(bp.getName())) {
					Cooldown.remove(bp.getName());
				}
			}
		}, cooldown * 20);
	}

	public static final String getCooldown(final Player bp) {
		final long time = TimeUnit.MILLISECONDS
				.toSeconds(((Long) Cooldown.get(bp.getName())).longValue() - System.currentTimeMillis());

		if ((Cooldown.containsKey(bp.getName()))
				|| (((Long) Cooldown.get(bp.getName())).longValue() > System.currentTimeMillis())) {
			return TimeConverter.ConvertTime((int)time);
		}
		return "0:00";
	}

	public static final void removeCooldown(final Player bp) {
		if (Cooldown.containsKey(bp.getName())) {
			Cooldown.remove(bp.getName());
		}
	}

	public static String getTime(final Long log) {
		String time = "";
		long totalLenth = log;
		long timeLefting = totalLenth - System.currentTimeMillis();
		long seconds = timeLefting / 1000L;
		time = getTime(time, seconds);
		return time;
	}

	public static String getTime(String time, final long lenth) {

		final long minutes = TimeUnit.SECONDS.toMinutes(lenth) - TimeUnit.SECONDS.toHours(lenth) * 60L;
		final long seconds = TimeUnit.SECONDS.toSeconds(lenth) - TimeUnit.SECONDS.toMinutes(lenth) * 60L;
		String totalMinutes = minutes + "m,";

		String totalSeconds = seconds + "s";

		if (minutes == 0L) {
			totalMinutes = "0,";
		}
		if (seconds == 0L) {
			totalSeconds = "00s";
		}
		time = totalMinutes + totalSeconds;
		time = time.trim();
		if (time.equals("")) {
			time = "0s";
		}
		return time;
	}
}
